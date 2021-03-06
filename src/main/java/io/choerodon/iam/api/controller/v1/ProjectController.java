package io.choerodon.iam.api.controller.v1;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import io.choerodon.base.annotation.Permission;
import io.choerodon.base.constant.PageConstant;
import io.choerodon.base.enums.ResourceType;
import io.choerodon.core.exception.CommonException;
import io.choerodon.iam.infra.dto.ProjectDTO;
import io.choerodon.iam.infra.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.base.BaseController;
import io.choerodon.core.iam.InitRoleCode;
import io.choerodon.iam.app.service.ProjectService;

/**
 * @author flyleft
 */
@RestController
@RequestMapping(value = "/v1/projects")
public class ProjectController extends BaseController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 按照Id查询项目
     *
     * @param id 要查询的项目ID
     * @return 查询到的项目
     */
    @Permission(type = ResourceType.PROJECT, roles = {InitRoleCode.PROJECT_OWNER})
    @GetMapping(value = "/{project_id}")
    @ApiOperation(value = "通过id查询项目")
    public ResponseEntity<ProjectDTO> query(@PathVariable(name = "project_id") Long id) {
        return new ResponseEntity<>(projectService.queryProjectById(id), HttpStatus.OK);
    }

    /**
     * 根据id集合查询项目
     *
     * @param ids id集合，去重
     * @return 项目集合
     */
    @Permission(permissionWithin = true)
    @ApiOperation(value = "根据id集合查询项目")
    @PostMapping("/ids")
    public ResponseEntity<List<ProjectDTO>> queryByIds(@RequestBody Set<Long> ids) {
        return new ResponseEntity<>(projectService.queryByIds(ids), HttpStatus.OK);
    }

    /**
     * 根据projectId和param模糊查询loginName和realName两列
     */
    @Permission(type = ResourceType.PROJECT, roles = {InitRoleCode.PROJECT_OWNER, InitRoleCode.PROJECT_MEMBER})
    @ApiOperation(value = "分页模糊查询项目下的用户")
    @GetMapping(value = "/{project_id}/users")
    public ResponseEntity<PageInfo<UserDTO>> list(@PathVariable(name = "project_id") Long id,
                                                  @RequestParam(defaultValue = PageConstant.PAGE, required = false) final int page,
                                                  @RequestParam(defaultValue = PageConstant.SIZE, required = false) final int size,
                                                  @RequestParam(required = false, name = "id") Long userId,
                                                  @RequestParam(required = false) String email,
                                                  @RequestParam(required = false) String param) {
        return new ResponseEntity<>(projectService.pagingQueryTheUsersOfProject(id, userId, email, page, size, param), HttpStatus.OK);
    }

    /**
     * 项目层更新项目，code和organizationId都不可更改
     */
    @Permission(type = ResourceType.PROJECT, roles = InitRoleCode.PROJECT_OWNER)
    @ApiOperation(value = "修改项目")
    @PutMapping(value = "/{project_id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable(name = "project_id") Long id,
                                             @RequestBody ProjectDTO projectDTO) {
        if (StringUtils.isEmpty(projectDTO.getName())) {
            throw new CommonException("error.project.name.empty");
        }
        if (projectDTO.getName().length() < 1 || projectDTO.getName().length() > 32) {
            throw new CommonException("error.project.code.size");
        }
        if (projectDTO.getObjectVersionNumber() == null) {
            throw new CommonException("error.objectVersionNumber.null");
        }
        projectDTO.setId(id);
        //项目code不可编辑
        projectDTO.setCode(null);
        //组织id不可编辑
        projectDTO.setOrganizationId(null);
        return new ResponseEntity<>(projectService.update(projectDTO), HttpStatus.OK);
    }

    @Permission(type = ResourceType.PROJECT, roles = InitRoleCode.PROJECT_OWNER)
    @ApiOperation(value = "禁用项目")
    @PutMapping(value = "/{project_id}/disable")
    public ResponseEntity<ProjectDTO> disableProject(@PathVariable(name = "project_id") Long id) {
        return new ResponseEntity<>(projectService.disableProject(id), HttpStatus.OK);
    }

    @Permission(permissionWithin = true)
    @GetMapping(value = "/check/{code}")
    public ResponseEntity<Boolean> checkProjCode(@PathVariable(name = "code") String code) {
        return new ResponseEntity<>(projectService.checkProjCode(code), HttpStatus.OK);
    }
}

package io.choerodon.iam.app.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.choerodon.iam.api.dto.CheckPermissionDTO;
import io.choerodon.iam.infra.dto.PermissionDTO;

import java.util.List;
import java.util.Set;

/**
 * @author wuguokai
 */
public interface PermissionService {

    PageInfo<PermissionDTO> pagingQuery(int page, int size, PermissionDTO permissionDTO, String param);

    List<CheckPermissionDTO> checkPermission(List<CheckPermissionDTO> checkPermissionDTOList);

    Set<PermissionDTO> queryByRoleIds(List<Long> roleIds);

    List<PermissionDTO> query(String level, String serviceName, String code);

    void deleteByCode(String code);

    PageInfo<PermissionDTO> listPermissionsByRoleId(int page,int size, Long id, String params);
}

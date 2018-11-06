package io.choerodon.iam.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wuguokai
 */
public class ClientDTO {
    @ApiModelProperty(value = "客户端ID/非必填")
    private Long id;
    @ApiModelProperty(value = "客户端名称/必填")
    @Size(min = 1, max = 32, message = "error.name.size")
    @NotNull(message = "error.clientName.null")
    private String name;
    @ApiModelProperty(value = "组织ID/必填")
    private Long organizationId;
    @ApiModelProperty(value = "客户端资源/非必填/默认：default")
    private String resourceIds;
    @ApiModelProperty(value = "客户端秘钥/必填")
    @NotNull(message = "error.secret.null")
    private String secret;
    @ApiModelProperty(value = "作用域/非必填")
    private String scope;
    @ApiModelProperty(value = "授权类型/必填")
    @NotNull(message = "error.authorizedGrantTypes.null")
    private String authorizedGrantTypes;
    @ApiModelProperty(value = "重定向地址/非必填")
    private String webServerRedirectUri;
    @ApiModelProperty(value = "访问授权超时时间/必填")
    private Long accessTokenValidity;
    @ApiModelProperty(value = "授权超时时间/必填")
    private Long refreshTokenValidity;
    @ApiModelProperty(value = "附加信息/非必填")
    private String additionalInformation;
    @ApiModelProperty(value = "自动授权域/非必填")
    private String autoApprove;
    @ApiModelProperty(value = "乐观锁版本号")
    private Long objectVersionNumber;

    @JsonIgnore
    private String param;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public Long getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Long accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Long getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Long refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

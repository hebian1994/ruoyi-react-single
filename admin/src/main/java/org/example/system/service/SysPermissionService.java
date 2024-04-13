package org.example.system.service;

import org.example.system.domain.SysUser;

import java.util.Set;

/**
 * 权限信息 服务层
 *
 * @author ruoyi
 */
public interface SysPermissionService
{
    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user);

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user);
}

package org.example.system.service.impl;

import org.example.system.domain.SysRole;
//org.example.system.domain.SysRole
import org.example.system.domain.SysUser;
import org.example.system.service.SysMenuService;
import org.example.system.service.SysPermissionService;
import org.example.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        //// 管理员拥有所有权限
        //if (Objects.isNull(user)) {
        //    return roles;
        //}
        //if (user.isAdmin()) {
        //    roles.add("admin");
        //} else {
        //    roles.addAll(sysRoleService.selectRolePermissionByUserId(user.getUserId()));
        //}
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<String>();
        perms.add("*:*:*");
        // 管理员拥有所有权限
        //if (user.isAdmin()) {
        //    perms.add("*:*:*");
        //} else {
        //    List<SysRole> roles = user.getRoles();
        //    if (!CollectionUtils.isEmpty(roles)) {
        //        // 多角色设置permissions属性，以便数据权限匹配权限
        //        for (SysRole role : roles) {
        //            Set<String> rolePerms = sysMenuService.selectMenuPermsByRoleId(role.getRoleId());
        //            role.setPermissions(rolePerms);
        //            perms.addAll(rolePerms);
        //        }
        //    } else {
        //        perms.addAll(sysMenuService.selectMenuPermsByUserId(user.getUserId()));
        //    }
        //}
        return perms;
    }
}

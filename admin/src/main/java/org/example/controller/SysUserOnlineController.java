package org.example.controller;

import org.example.common.core.constant.CacheConstants;
import org.example.common.core.utils.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.system.domain.SysUserOnline;
import org.example.system.model.LoginUser;
import org.example.system.service.SysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import org.example.common.core.web.controller.BaseController;
import org.example.common.core.web.domain.AjaxResult;
import org.example.common.core.web.page.TableDataInfo;
import org.example.system.log.annotation.Log;
import org.example.system.log.enums.BusinessType;
import org.example.system.security.annotation.RequiresPermissions;


/**
 * 在线用户监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/systemn/online")
public class SysUserOnlineController extends BaseController {
    @Autowired
    private SysUserOnlineService userOnlineService;

    @Autowired
    private RedisService redisService;

    @RequiresPermissions("monitor:online:list")
    @GetMapping("/list")
    public TableDataInfo list(String ipaddr, String userName) {
        Collection<String> keys = redisService.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys) {
            LoginUser user = redisService.getCacheObject(key);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
                userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
            } else if (StringUtils.isNotEmpty(ipaddr)) {
                userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
            } else if (StringUtils.isNotEmpty(userName)) {
                userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
            } else {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(userOnlineList);
    }

    /**
     * 强退用户
     */
    @RequiresPermissions("monitor:online:forceLogout")
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId) {
        redisService.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + tokenId);
        return success();
    }
}

package org.example.controller;

import org.example.common.core.constant.CacheConstants;
import org.example.common.core.utils.poi.ExcelUtil;
import org.example.common.core.web.controller.BaseController;
import org.example.common.core.web.domain.AjaxResult;
import org.example.common.core.web.page.TableDataInfo;
import org.example.common.redis.service.RedisService;
import org.example.system.domain.SysLogininfor;
import org.example.system.log.annotation.Log;
import org.example.system.log.enums.BusinessType;
import org.example.system.service.SysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.system.security.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.example.common.core.utils.PageUtils.startPage;

/**
 * 系统访问记录
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/logininfor")
public class SysLogininforController extends BaseController {
    @Autowired
    private SysLogininforService logininforService;

    @Autowired
    private RedisService redisService;

    @org.example.system.security.annotation.RequiresPermissions("system:logininfor:list")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor) {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @org.example.system.security.annotation.RequiresPermissions("system:logininfor:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(response, list, "登录日志");
    }

    @org.example.system.security.annotation.RequiresPermissions("system:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @RequiresPermissions("system:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("system:logininfor:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public AjaxResult unlock(@PathVariable("userName") String userName) {
        redisService.deleteObject(CacheConstants.PWD_ERR_CNT_KEY + userName);
        return success();
    }

    @org.example.system.security.annotation.InnerAuth
    @PostMapping
    public AjaxResult add(@RequestBody SysLogininfor logininfor) {
        return toAjax(logininforService.insertLogininfor(logininfor));
    }
}

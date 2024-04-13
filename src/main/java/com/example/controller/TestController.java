package com.example.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.example.domain.Test;
import com.example.service.ITestService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * testController
 * 
 * @author louis
 * @date 2024-04-13
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController
{
    @Autowired
    private ITestService testService;

    /**
     * 查询test列表
     */
    @RequiresPermissions("example:test:list")
    @GetMapping("/list")
    public TableDataInfo list(Test test)
    {
        startPage();
        List<Test> list = testService.selectTestList(test);
        return getDataTable(list);
    }

    /**
     * 导出test列表
     */
    @RequiresPermissions("example:test:export")
    @Log(title = "test", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Test test)
    {
        List<Test> list = testService.selectTestList(test);
        ExcelUtil<Test> util = new ExcelUtil<Test>(Test.class);
        util.exportExcel(response, list, "test数据");
    }

    /**
     * 获取test详细信息
     */
    @RequiresPermissions("example:test:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(testService.selectTestById(id));
    }

    /**
     * 新增test
     */
    @RequiresPermissions("example:test:add")
    @Log(title = "test", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Test test)
    {
        return toAjax(testService.insertTest(test));
    }

    /**
     * 修改test
     */
    @RequiresPermissions("example:test:edit")
    @Log(title = "test", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Test test)
    {
        return toAjax(testService.updateTest(test));
    }

    /**
     * 删除test
     */
    @RequiresPermissions("example:test:remove")
    @Log(title = "test", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(testService.deleteTestByIds(ids));
    }
}

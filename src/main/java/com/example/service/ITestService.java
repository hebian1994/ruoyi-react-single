package com.example.service;

import java.util.List;
import com.example.domain.Test;

/**
 * testService接口
 * 
 * @author louis
 * @date 2024-04-13
 */
public interface ITestService 
{
    /**
     * 查询test
     * 
     * @param id test主键
     * @return test
     */
    public Test selectTestById(Long id);

    /**
     * 查询test列表
     * 
     * @param test test
     * @return test集合
     */
    public List<Test> selectTestList(Test test);

    /**
     * 新增test
     * 
     * @param test test
     * @return 结果
     */
    public int insertTest(Test test);

    /**
     * 修改test
     * 
     * @param test test
     * @return 结果
     */
    public int updateTest(Test test);

    /**
     * 批量删除test
     * 
     * @param ids 需要删除的test主键集合
     * @return 结果
     */
    public int deleteTestByIds(Long[] ids);

    /**
     * 删除test信息
     * 
     * @param id test主键
     * @return 结果
     */
    public int deleteTestById(Long id);
}

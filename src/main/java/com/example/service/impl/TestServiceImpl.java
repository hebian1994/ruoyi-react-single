package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mapper.TestMapper;
import com.example.domain.Test;
import com.example.service.ITestService;

/**
 * testService业务层处理
 * 
 * @author louis
 * @date 2024-04-13
 */
@Service
public class TestServiceImpl implements ITestService 
{
    @Autowired
    private TestMapper testMapper;

    /**
     * 查询test
     * 
     * @param id test主键
     * @return test
     */
    @Override
    public Test selectTestById(Long id)
    {
        return testMapper.selectTestById(id);
    }

    /**
     * 查询test列表
     * 
     * @param test test
     * @return test
     */
    @Override
    public List<Test> selectTestList(Test test)
    {
        return testMapper.selectTestList(test);
    }

    /**
     * 新增test
     * 
     * @param test test
     * @return 结果
     */
    @Override
    public int insertTest(Test test)
    {
        return testMapper.insertTest(test);
    }

    /**
     * 修改test
     * 
     * @param test test
     * @return 结果
     */
    @Override
    public int updateTest(Test test)
    {
        return testMapper.updateTest(test);
    }

    /**
     * 批量删除test
     * 
     * @param ids 需要删除的test主键
     * @return 结果
     */
    @Override
    public int deleteTestByIds(Long[] ids)
    {
        return testMapper.deleteTestByIds(ids);
    }

    /**
     * 删除test信息
     * 
     * @param id test主键
     * @return 结果
     */
    @Override
    public int deleteTestById(Long id)
    {
        return testMapper.deleteTestById(id);
    }
}

package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.mapper.ApplyMoneyMapper;
import com.qf.pojo.ApplyMoney;
import com.qf.service.ApplyMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyMoneyServiceImpl implements ApplyMoneyService {
    @Autowired
    private ApplyMoneyMapper applyMoneyMapper;

    /**
     * 查询当前用户的最大id值
     * @return 查询到的最大id值
     */
    @Override
    public Integer getMaxId() {
        return applyMoneyMapper.getMaxId();
    }

    /**
     * 将新的贷款存储信息存储到数据库中
     * @param applyMoney 新的申请贷款的信息
     * @return 存储结果
     */
    @Override
    public Integer addNewApply(ApplyMoney applyMoney) {
        return applyMoneyMapper.addNewApply(applyMoney);
    }

    /**
     * 根据当前登录用户名获取当前用户对应的借款信息
     * @param loginName 当前登录用户名
     * @return 查询到的借款结果集
     */
    @Override
    public List<ApplyMoney> loadByLoginName(String loginName) {
        return applyMoneyMapper.loadByLoginName(loginName);
    }

    /**
     * 根据借款信息id和借款人用户名删除对应的借款信息
     * @param applyMoney 借款信息id和借款人的用户名
     * @return 删除结果
     */
    @Override
    public Boolean delApplByapplUser(ApplyMoney applyMoney) {
        return applyMoneyMapper.delApplByapplUser(applyMoney)>0? true:false;
    }



    /**
     * 加载所有的申请贷款信息给管理员审核
     * @return 申请贷款的信息集合
     */
    @Override
    public List<ApplyMoney> loadAllApplyMoney(int page,int rows) {
        PageHelper.startPage(page,rows);
        return applyMoneyMapper.loadAllApplyMoney();
    }

    /**
     * 根据每页数据的行数获取最大页数
     * @param rows 每页数据默认的行数
     * @return 页数
     */
    @Override
    public Integer getMaxPage(int rows) {
        return applyMoneyMapper.getCount()%rows==0? applyMoneyMapper.getCount()/rows:applyMoneyMapper.getCount()/rows+1;
    }
}

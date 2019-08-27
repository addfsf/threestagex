package com.qf.service;

import com.qf.pojo.ApplyMoney;

import java.util.List;

public interface ApplyMoneyService {

    /**
     * 获取当前列表中最大的id值
     * @return 查询到的最大id
     */
    public Integer getMaxId();

    /**
     * 将新的申请贷款信息存储到数据库中
     * @param applyMoney 新的申请贷款的信息
     * @return 添加结果
     */
    public Integer addNewApply(ApplyMoney applyMoney);

    /**
     * 根据用户明白查询对应用户的借款信息
     * @param loginName 当前登录用户名
     * @return 查询借款的信息结果
     */
    public List<ApplyMoney> loadByLoginName(String loginName);

    /**
     * 根据借款信息id对应的用户名对应的借款信息
     * @param applyMoney 借款信息id和借款人的用户名
     * @return 删除结果
     */
    public Boolean delApplByapplUser(ApplyMoney applyMoney);

    /**
     * 加载所有的贷款信息
     * @return 贷款信息集合
     */
    public List<ApplyMoney> loadAllApplyMoney(int page,int rows);


    /**
     * 根据行数获取最大的页数
     * @param rows 每页数据默认的行数
     * @return 页数
     */
    public Integer getMaxPage(int rows);
}

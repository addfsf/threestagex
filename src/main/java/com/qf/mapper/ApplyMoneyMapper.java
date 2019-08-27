package com.qf.mapper;

import com.qf.pojo.ApplyMoney;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMoneyMapper {

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
     * 根据借款信息id与当前的用户名删除对应的借款信息
     * @param applyMoney 借款信息id和借款人的用户名
     * @return 删除结果
     */
    public Integer delApplByapplUser(ApplyMoney applyMoney);

    /**
     * 加载所有的贷款信息
     * @return 贷款信息集合
     */
    public List<ApplyMoney> loadAllApplyMoney();

    /**
     * 获取当前列表中的总行数
     * @return
     */
    public Integer getCount();
}

package com.qf.mapper;

import com.qf.pojo.Apply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMapper {
    /**
     * 将新的实名认证信息添加到数据库中
     * @param apply 新的实名认证信息
     * @return 添加结果
     */
    public Integer addNewApply(Apply apply);

    /**
     * 获取实名认证列表中的最大id值
     * @return 查询到的最大ic
     */
    public Integer getMaxId();

    /**
     * 加载所有的实名认证信息
     * @return 实名认证信息集合
     */
    public List<Apply> loadAllApply();

    /**
     * 根据实名认证信息的id修改对应实名认证信息的实名认证状态
     * @param id 实名认证信息的id
     * @return 修改实名认证状态的结果
     */
    public Integer changeState(int id);

    /**
     * 根据登录用户名查询当前对应用户的实名认证信息
     * @param loginName 登录用户名
     * @return 查询到的实名认证信息
     */
    public Apply loadByLoginName(String loginName);

    /**
     * 根据实名认证信息id更新实名认证信息
     * @param apply 新的实名认证信息
     * @return 更新的结果
     */
    public Integer upApplyByLoginName(Apply apply);
}

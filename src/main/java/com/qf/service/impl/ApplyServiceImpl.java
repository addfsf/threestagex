package com.qf.service.impl;

import com.qf.mapper.ApplyMapper;
import com.qf.pojo.Apply;
import com.qf.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyMapper applyMapper;

    /**
     * 将新的实名认证信息添加到数据库中
     * @param apply 新的实名认证信息
     * @return 添加结果
     */
    @Override
    public Boolean addNewApply(Apply apply) {
        return applyMapper.addNewApply(apply)>0? true:false;
    }

    /**
     * 获取实名认证表中最大的id值
     * @return 查询到的最大id值
     */
    @Override
    public Integer getMaxId() {
        return applyMapper.getMaxId();
    }

    /**
     * 加载所有的实名认证信息
     * @return 实名认证信息的集合
     */
    @Override
    public List<Apply> loadAllApply() {
        return applyMapper.loadAllApply();
    }

    /**
     * 根据实名认证信息id修改对应信息的实名认证状态
     * @param id 实名认证信息的id
     * @return 修改的结果
     */
    @Override
    public Boolean changeState(int id) {
        return applyMapper.changeState(id)>0? true:false;
    }

    /**
     * 根据登录用户名查询当前用户对应的实名认证信息
     * @param loginName 登录用户名
     * @return 查询到的实名认证信息
     */
    @Override
    public Apply loadByLoginName(String loginName) {
        return applyMapper.loadByLoginName(loginName);
    }

    /**
     * 根据实名认证信息id更新新的实名认证信息
     * @param apply 新的实名认证信息
     * @return 修改结果
     */
    @Override
    public Boolean upApplyByLoginName(Apply apply) {
        return applyMapper.upApplyByLoginName(apply)>0? true:false;
    }
}

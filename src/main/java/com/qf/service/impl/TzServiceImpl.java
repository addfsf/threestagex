package com.qf.service.impl;

import com.qf.mapper.TzMapper;
import com.qf.pojo.Gz_Tz;
import com.qf.pojo.Tz;
import com.qf.pojo.TzIdLoginName;
import com.qf.service.TzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TzServiceImpl implements TzService {

    @Autowired
    private TzMapper tzMapper;

    /**
     * 根据登录用户名查询当前用户的投资信息
     * @param loginName 当前用户登录名
     * @return
     */
    @Override
    public List<Tz> loadTzByLoginName(String loginName) {
        return tzMapper.loadTzByLoginName(loginName);
    }

    /**
     * 根据当前登录的用户名查询当前用户关注的投资
     * @param loginName 当前登录用户名
     * @return 未关注的投资集合
     */
    @Override
    public List<Tz> loadTzByWTZLoginName(String loginName) {
        return tzMapper.loadTzByWTZLoginName(loginName);
    }

    /**
     * 根据用户名和取消关注的业务id删除对应关系表中的数据
     * @param tzIdLoginName 用户名和取消关注业务的id
     * @return 删除的结果
     */
    @Override
    public Boolean delGzUserByLoginName(TzIdLoginName tzIdLoginName) {
        return tzMapper.delGzUserByLoginName(tzIdLoginName)>0? true:false;
    }

    /**
     * 加载所有投资信息
     * @return
     */
    @Override
    public List<Tz> loadAll() {
        return tzMapper.loadAll();
    }

    /**
     * 将用户新的关注信息添加到关系表中
     * @param gz_tz 新的关注投资关系
     * @return 添加结果
     */
    @Override
    public Boolean addNewGzTz(Gz_Tz gz_tz) {
        return tzMapper.addNewGzTz(gz_tz)>0?true :false;
    }
}

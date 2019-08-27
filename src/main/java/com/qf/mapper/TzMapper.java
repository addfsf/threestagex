package com.qf.mapper;

import com.qf.pojo.Gz_Tz;
import com.qf.pojo.Tz;
import com.qf.pojo.TzIdLoginName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TzMapper {

    /**
     * 根据登录用户名获取当前对应用户的所有投资过的投资信息
     * @param loginName 当前用户登录名
     * @return 投资信息对象
     */
    public List<Tz> loadTzByLoginName(String loginName);

    /**
     * 根据用户名查询当前用户未投资仅关注的项目
     * @param loginName 当前登录用户名
     * @return 为投资的业务集合
     */
    public List<Tz> loadTzByWTZLoginName(String loginName);

    /**
     * 根据用户名和取消关注的业务id到关系表中删除数据
     * @param tzIdLoginName 用户名和取消关注业务的id
     * @return 取消删除的结果
     */
    public Integer delGzUserByLoginName(TzIdLoginName tzIdLoginName);

    /**
     * 加载所有投资信息
     * @return
     */
    public List<Tz> loadAll();

    /**
     * 将新的投资关注信息添加到关系表中
     * @param gz_tz 新的关注投资关系
     * @return 添加结果
     */
    public Integer addNewGzTz(Gz_Tz gz_tz);
}

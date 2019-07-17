package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogDao {


    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method) values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);
}

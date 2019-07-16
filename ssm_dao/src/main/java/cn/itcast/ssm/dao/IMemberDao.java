package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    @Select("select * from member where id=#{memberId}")
    public Member findById(String memberId);
}

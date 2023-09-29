package com.boilerplate.member.infrastructure.provider.mybatis.mapper;

import org.apache.ibatis.annotations.*;

import com.boilerplate.member.domain.data.entity.Member;

@Mapper
public interface MemberMapper {

    @Options(useGeneratedKeys = true, keyProperty = "code")
    @Insert("""
        insert into member (member_id, member_pw, member_nm, member_roles)
        values (#{id}, #{password}, #{name}, #{roles})
        """)
    void save(Member member);

    @ResultMap("memberRowMapper")
    @Select("""
        select *
        from member
        where member_id = #{id}
        """)
    Member findById(@Param("id") String id);

    @Update("""
        update member
        set last_lgn_dttm = now()
        where member_id = #{id}
        """)
    void updateLastLoginDateTime(@Param("id") String id);
}

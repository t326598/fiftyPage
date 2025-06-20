package com.fifty.fifty.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fifty.fifty.domain.UserAuth;
import com.fifty.fifty.domain.Users;


@Mapper
public interface UserMapper {

    // 회원 조회
    public Users select(String id) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 회원 삭제
    public int delete(String username) throws Exception;

    // 이메일 조회
    public Users selectByEmail(String email) throws Exception;
    // 아이디 조회
    public Users selectByUsername(String username) throws Exception;
}
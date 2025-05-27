package com.fifty.fifty.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.fifty.fifty.domain.Files;

 @Mapper
public interface FilesMapper {
    
    public List<Files> AllList(Map<String, Object> params) throws Exception;

    public int allCount(Map<String, Object> params) throws Exception;

    public List<Files> list(Map<String, Object> params) throws Exception;

    public int count(Map<String, Object> params);

    public int insert(Files files) throws Exception;

    public int update(Files files) throws Exception;

    public int delete(Long no) throws Exception;

}

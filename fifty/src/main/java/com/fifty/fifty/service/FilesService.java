package com.fifty.fifty.service;

import java.util.List;
import java.util.Map;

import com.fifty.fifty.domain.Files;


public interface FilesService {
    
    public List<Files> AllList(Map<String, Object> params) throws Exception;

    public int allCount(Map<String, Object> params) throws Exception;

    public List<Files> list(Map<String, Object> params) throws Exception;

    public int count(Map<String, Object> params) throws Exception;

    public int insert(Files files) throws Exception;

    public int insertList(List<Files> fileList) throws Exception;

    public int update(Files files) throws Exception;

    public int delete(Long no) throws Exception;

}

package com.fifty.fifty.domain;

import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Files {
    private Long no;
    private String id;
    private String crt;
    private String path;
    private String name;
    private Long size;
    private String trueDay;
    private Date createdAt; 

    private MultipartFile data;

        public Files(){
            this.id = UUID.randomUUID().toString();
        }

}

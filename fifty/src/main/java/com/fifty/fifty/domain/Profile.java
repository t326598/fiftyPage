package com.fifty.fifty.domain;

import lombok.Data;

@Data
public class Profile {
    private Long no;
    private String filePath;
    private String content;
    private String title;
    private String subContent;
}

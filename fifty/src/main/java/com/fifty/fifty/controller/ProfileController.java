package com.fifty.fifty.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fifty.fifty.domain.Profile;
import com.fifty.fifty.service.ProfileServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileServiceImpl;

     @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity getMethodName() throws Exception {
        List<Profile> list = profileServiceImpl.list();

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        return ResponseEntity.ok(result);

        
    }

  @PutMapping()
        public ResponseEntity<?> updateProfile(@RequestBody Profile request) {
            try {
                System.out.println("왜안나오나요??" + request);
                // 이전 파일 삭제 처리
                if (request.getOldFilePath() != null && !request.getOldFilePath().equals(request.getFilePath())) {
                    Path oldFile = Paths.get("/path/to/upload/dir", request.getOldFilePath());
                    Files.deleteIfExists(oldFile);
                }

                // 프로필 업데이트 처리
                int result = profileServiceImpl.update(request);

                return ResponseEntity.ok(result);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("프로필 수정 실패");
            }
        }
    

   @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        // 서버에 파일 저장
          System.out.println("나옴:?" + file);
        MultipartFile multipartFile = file;
        

    String originName = multipartFile.getOriginalFilename();
    long fileSize = multipartFile.getSize();
    String fileName = UUID.randomUUID().toString() + "_" + originName;
    byte[] fileData = multipartFile.getBytes();
    String filePath = uploadPath + "/" + fileName;

    File uploadFile = new File(filePath);

    File parentDir = uploadFile.getParentFile();
    if (!parentDir.exists()) {
        parentDir.mkdirs();  // 디렉토리 생성
}

    FileCopyUtils.copy(fileData, uploadFile);

        Map<String, String> response = new HashMap<>();
        response.put("filePath", fileName);
        return ResponseEntity.ok(response);
    }

    
}

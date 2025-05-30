CREATE TABLE `plan` (
	`no`	BIGINT	NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '일정번호',
	`file_no`	BIGINT	NULL,
	`crt`	VARCHAR(100)	NOT NULL	COMMENT '일정 카테고리',
	`title`	VARCHAR(100)	NOT NULL	COMMENT '일정 제목',
	`content`	VARCHAR(1000)	NOT NULL	COMMENT '일정 상세내용',
	`start_at`	TIMESTAMP	NOT NULL	COMMENT '시작일자',
	`end_at`	TIMESTAMP	NULL	COMMENT '종료일자',
	`created_at`	TIMESTAMP	NOT NULL	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NULL	COMMENT '수정일자'
);

CREATE TABLE `Notice` (
	`no`	BIGINT	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '공지사항번호',
	`file_no`	BIGINT	NULL	COMMENT '공지사항 이미지',
	`title`	VARCHAR(100)	NOT NULL	COMMENT '제목',
	`content`	VARCHAR(100)	NOT NULL	COMMENT '내용',
	`created_at`	TIMESTAMP	NOT NULL	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NOT NULL	COMMENT '수정일자'
);


CREATE TABLE fifty_fifty_music_char (
    no INT PRIMARY KEY COMMENT '차트 번호 (1~10 고정)',
    video_id VARCHAR(50) NOT NULL COMMENT '유튜브 영상 ID',
    title VARCHAR(255) NOT NULL COMMENT '영상 제목',
    thumbnail_url VARCHAR(255) NOT NULL COMMENT '썸네일 이미지 URL',
    video_url VARCHAR(255) NOT NULL COMMENT '유튜브 영상 URL',
    view_count BIGINT NOT NULL COMMENT '현재 조회수',
    published_at DATETIME NOT NULL COMMENT '영상 게시일',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '마지막 업데이트 시각'
);

CREATE TABLE `files` (
	`no`	INT	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '사진번호',
	`crt`	VARCHAR(100)	NULL	COMMENT '멤버 카테고리',
	`path`	VARCHAR(100)	NOT NULL	COMMENT '파일경로',
	`name`	VARCHAR(100)	NOT NULL	COMMENT '파일이름',
	`size`	INT	NOT NULL	COMMENT '파일용량',
	`true_day`	VARCHAR(100)	NOT NULL	COMMENT '사진 찍힌일자',
	`created_at`	TIMESTAMP	NOT NULL	COMMENT '등록일자'
);

CREATE TABLE `Profile` (
	`no`	INT	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '프로필번호',
	`file_path`	VARCHAR(500) NULL	COMMENT '프로필 파일',
	`content`	VARCHAR(1000)	NOT NULL	COMMENT '프로필 내용',
	`title`	VARCHAR(100)	NOT NULL	COMMENT '프로필 제목'
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (  
    no          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "PK",
    id          VARCHAR(100) NOT NULL COMMENT "UK",
    username    VARCHAR(100) NOT NULL COMMENT "아이디",
    password    VARCHAR(100) NOT NULL COMMENT "비밀번호",
    name        VARCHAR(100) NULL COMMENT "이름",
    email       VARCHAR(100) NULL COMMENT "이메일",
    created_at  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT "등록일자",
    updated_at  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT "수정일자",
    enabled     BOOLEAN NULL DEFAULT TRUE COMMENT "활성화여부"
) COMMENT '회원';

CREATE TABLE `user_auth` (
    no          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "PK",
    username    VARCHAR(100) NOT NULL COMMENT "아이디",
    auth        VARCHAR(100) NOT NULL COMMENT "권한"
) COMMENT "회원권한";

DROP TABLE IF EXISTS `plan`;
DROP TABLE IF EXISTS `files`;
DROP TABLE IF EXISTS `Profile`;
DROP TABLE IF EXISTS `fifty_fifty_music_char`;
DROP TABLE IF EXISTS `Notice`;

INSERT INTO plan (file_no, crt, title, content, start_at, end_at, created_at)
VALUES
(NULL, '1', 'BTS 콘서트', '서울 고척돔에서 열리는 콘서트', '2025-05-22 19:00:00', '2025-05-22 21:30:00', NOW()),

(NULL, '2', '아이유 팬미팅', '팬들과 함께하는 소규모 팬미팅', '2025-05-25 14:00:00', '2025-05-25 16:00:00', NOW()),

(NULL, '3', '신곡 홍보 인터뷰', '라디오 출연 및 인터뷰', '2025-05-23 10:00:00', '2025-05-23 11:00:00', NOW()),

(NULL, '1', 'New Jeans 콘서트', '올림픽공원 KSPO 돔', '2025-05-28 18:00:00', '2025-05-28 20:30:00', NOW()),

(NULL, '2', '예능 방송 출연', '유퀴즈 온 더 블럭 출연', '2025-05-29 17:00:00', '2025-05-29 18:30:00', NOW());


INSERT INTO notice (title, content, created_at)
VALUES 
  ('서비스 점검 안내', '시스템 점검으로 인해 5월 25일(토) 00:00 ~ 04:00 동안 서비스 이용이 제한됩니다.', NOW()),
  ('신규 기능 출시', '새로운 일정 공유 기능이 추가되었습니다. 자세한 내용은 공지사항을 참고해주세요.', NOW()),
  ('이용약관 변경 안내', '2025년 6월 1일부터 새로운 이용약관이 적용됩니다. 변경된 내용은 홈페이지 하단에서 확인 가능합니다.', NOW());


CREATE TABLE monthly_background (
  id INT AUTO_INCREMENT PRIMARY KEY,
  month INT NOT NULL UNIQUE, -- 1~12월
  image_url VARCHAR(255) NOT NULL
);


INSERT INTO monthly_background (month, image_url) VALUES
(1, 'C:/upload/c47cccf7-9700-45b0-b2d3-89898f75d965_1.webp'),
(2, 'C:/upload/a1e87334-ab8e-4f9c-9cff-964faacae2e2_2.webp'),
(3, 'C:/upload/2cab1dfc-37ce-4bfd-99f1-84faed1f4f25_1.png'),
(4, 'C:/upload/8c654b4d-ed45-4450-9e37-be10cddb2bfa_xcvdsf.jpg'),
(5, 'C:/upload/e829be92-4cbf-41c7-a6d6-6e22610673ef_dfgdfg.jpg'),
(6, 'C:/upload/8cb44160-9970-4c46-8f38-9842cc8a0024_ChatGPT Image 2025년 5월 16일 오전 01_49_15.png'),
(7, 'C:/upload/71889616-0da8-4fb0-81a6-a833f58bbfeb_ChatGPT Image 2025년 5월 16일 오전 12_47_42.png'),
(8, 'C:/upload/ae53b7dd-9bc9-40f1-a5e9-1b5e890e1a25_gm&tr.png'),
(9, 'C:/upload/a7d3255d-c4cb-4986-bf26-b41c65f6fcf0_ChatGPT Image 2025년 5월 16일 오전 01_42_02.png'),
(10, 'C:/upload/2c9ac7be-cc5d-4dd6-9c72-7dde4a1dd4db_ChatGPT Image 2025년 5월 16일 오전 01_26_46.png'),
(11, 'C:/upload/3f749d0a-d23c-41bf-a363-a1b46feb5ef9_2.jpg'),
(12, 'C:/upload/b202bcbd-0803-403d-bf69-44f52c050c75_5.jpg');

ALTER TABLE fifty_fifty_music_char
ADD COLUMN chart_date DATE GENERATED ALWAYS AS (DATE(`date`)) STORED;

ALTER TABLE fifty_fifty_music_char
ADD UNIQUE KEY uq_platform_song_date (platform, song_title, DATE(date));

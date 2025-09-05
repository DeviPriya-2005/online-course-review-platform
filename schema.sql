CREATE DATABASE course_review_db;

USE course_review_db;

-- Course Table
CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    instructor VARCHAR(100)
);

-- Review Table
CREATE TABLE review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    user VARCHAR(100) NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course(id),
    UNIQUE (course_id, user)
);

package com.example.bookspresso.mapper.post;

import com.example.bookspresso.dto.post.PostCommentDTO;
import com.example.bookspresso.dto.post.PostModifySelectDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PostDetailMapperTest {
@Autowired PostDetailMapper postDetailMapper;
    @Test
    void selectPostDetail() {

    }

}
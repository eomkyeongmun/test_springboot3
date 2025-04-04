package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest //해당 클래스를 jpa와 연동해 테스트
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticle() {
        {
            //입력데이터 준비
            Long articleId = 4L;
            //실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상 데이터
            Article article = new Article(4L,"인생 영화는?","댓글");
            Comment a = new Comment(1L,article,"park","굿굿");
            Comment b = new Comment(2L,article,"kim","굿굿굿");
            List<Comment> expected = Arrays.asList(a,b);
            //비교 및 검증
            assertEquals(expected.toString(),comments.toString(),"4번 글의 모든 댓글을 출력!");


        }
    }

    @Test
    void findByNickname() {
        String nickname="eom";
        List<Comment> comments = commentRepository.findByNickname(nickname);
        Comment a= new Comment(1L,new Article(4L,"인생 영화는?","댓글"),nickname,"굿굿");
        Comment b= new Comment(6L,new Article(6L,"취미?","댓글"),nickname,"야구");
        List<Comment> expected = Arrays.asList(a,b);
        assertEquals(expected.toString(),comments.toString());
    }
}
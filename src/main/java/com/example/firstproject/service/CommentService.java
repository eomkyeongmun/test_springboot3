package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    //조회
    public List<CommentDto> comments(Long articleId) {
//        //댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        //엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i=0;i<comments.size();i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
        //결과
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).
                orElseThrow(()->new IllegalArgumentException("댓글생성 실패!"+ "대상 게시글이 없습니다."));
        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        //댓글 엔티티를 DB에 저장
        Comment created= commentRepository.save(comment);
        //DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
    //수정
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target=commentRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("댓글 수정 실패"));
        //댓글 수정
        target.patch(dto);
        //DB로 갱신
        Comment updated= commentRepository.save(target);
        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
        
    }

    //삭제
    @Transactional
    public CommentDto delete(Long id) {
    //댓글 조회 및 예외발생
        Comment target= commentRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("댓글을 삭제할수없습니다"));
    //댓글 삭제
        commentRepository.delete(target);
    //삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }

}

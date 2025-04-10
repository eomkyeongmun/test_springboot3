package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //댓글조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable("articleId") Long articleId) {
        //서비스에 위임
        List<CommentDto> dtos= commentService.comments(articleId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("articleId")Long articleId, @RequestBody CommentDto dto) {
        //서비스에 위임
        CommentDto createDto=commentService.create(articleId,dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") Long id, @RequestBody CommentDto dto) {
        //서비스에 위임
        CommentDto updateDto=commentService.update(id,dto);
        //결과응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable("id") Long id) {
        CommentDto deleteDto=commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}

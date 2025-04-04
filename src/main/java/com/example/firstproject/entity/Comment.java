package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //엔티티 임을 선언
@Getter //getter 메소드를 자동생성
@ToString //모든 필드를 출력할 수 있는 Tostring 메서드를 자동 생성
@AllArgsConstructor //모든 필드를 매개변수로 갖는 생성자를 자동 생성
@NoArgsConstructor // 매개변수가 없는 기본 생성자 자동 생성
public class Comment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 자동으로 1씩 증가
    private Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    private String nickname; //댓글 작성자
    private String body; //댓글 내용

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if(dto.getId() !=null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 ID가 잘못되었습니다.");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}

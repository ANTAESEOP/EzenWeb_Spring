package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.Board.BoardEntity;
import lombok.*;

@NoArgsConstructor//빈 생성자
@AllArgsConstructor//풀 생성자
@Getter//필드들의 get메소드 주입
@Setter//필드들의 set메소드 주입
@ToString//객체내 필드 정보 확인 ToString메소드 주입
@Builder//객체 생성 안전성 보장 [매개변수 개수 /순서 무관 ]
public class BoardDto {
    private int bno;            // 게시물 번호
    private String btitle;      // 게시물 제목
    private String bcontent;    // 게시물 내용
    private int bview;          // 조회수
    private String bfile;       // 첨부파일
    private int mno;            // 작성자 [ 회원번호 - FK ]
    private int bcno;            // 카테고리 [ 카테고리 - FK ]
    private String memail;         // 회원 아이디

    // 1. 형번환
    public BoardEntity toEntity() {
        // * 빌더 패턴을 이용한 객체 생성
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .bfile(this.bfile)
                .build();
    }
}
package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.Board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDto {
    private int bno;            // 게시물 번호
    private String btitle;      // 게시물 제목
    private String bcontent;    // 게시물 내용
    private int bview;          // 조회수
    private MultipartFile bfile;       // 첨부파일
        // spring : MultipartFile
        // jsp : cos 라이브러리 사용
    private String bfilename; // 첨부파일 출력용
    private int bcno;            // 카테고리 [ 카테고리 - FK ]
    private String memail;         // 회원 아이디

    private String bdate; // 작성일

    // 1. 형번환
    public BoardEntity toEntity() {
        // * 빌더 패턴을 이용한 객체 생성
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}
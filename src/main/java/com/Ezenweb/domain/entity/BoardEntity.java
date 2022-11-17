package com.Ezenweb.domain.entity;

import com.Ezenweb.domain.Dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
@Entity // Entity 정의
@Table(name = "board") // 테이블명 정의
@AllArgsConstructor // 풀생성자
@NoArgsConstructor // 깡통 ( 빈 생성자 )
@Getter // 호출
@Setter // 저장
@Builder // builder
@ToString
public class BoardEntity {
    // 1. 필드
    @Id // pk
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto increment
    private int bno;            // 게시물 번호

    @Column(nullable = false)   // ( noy null )
    private String btitle;      // 게시물 제목

    @Column(nullable = false , columnDefinition = "TEXT")   // ( noy null ) , 저장할 수 있는 길이
    private String bcontent;    // 게시물 내용

    @Column(nullable = false)   // ( noy null )
    @ColumnDefault( "0" ) // JPA insert 할 경우 default
    private int bview;          // 조회수

    @Column(nullable = false)   // ( noy null )
    private String bfile;       // 첨부파일

    @Column(nullable = false)   // ( noy null )
    private int mno;            // 작성자 [ 회원번호 - fk ]

    @Column(nullable = false)   // ( noy null )
    private  int cno;           // 카테고리 [ 카테고리 - fk ]

    // 1. 형번환
    public BoardDto todto(){
        // * 빌더 패턴을 이용한 객체 생성
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .bfile(this.bfile)
                .mno(this.mno)
                .cno(this.cno)
                .build();
    }
    // 작성일 , 수정일 - > 상속 ( 여러 엔티티에서 사용되는 필드라서 )
}
/*
    자바 ---------------------> DB 자료형
    int                        int
    double float               float
    String                     varchar

        columndefault = "DB자료형"
*/
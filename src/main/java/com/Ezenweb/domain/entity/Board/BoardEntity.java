package com.Ezenweb.domain.entity.Board;

import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.Bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.Member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity // Entity 정의
@Table(name = "board") // 테이블명 정의
@AllArgsConstructor // 풀생성자
@NoArgsConstructor // 깡통 ( 빈 생성자 )
@Getter // 호출
@Setter // 저장
@Builder // builder
@ToString
public class BoardEntity extends BaseEntity {
    // 1. 필드
    @Id // pk
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto increment
    private int bno;            // 게시물 번호

    @Column(nullable = false)   // ( noy null )
    private String btitle;      // 게시물 제목

    @Column(nullable = false , columnDefinition = "TEXT")   // ( noy null ) , 저장할 수 있는 길이
    private String bcontent;    // 게시물 내용

    @Column(nullable = false)   // ( noy null )
    @ColumnDefault( "0" )       // JPA insert 할 경우 default
    private int bview;          // 조회수

    @Column
    private String bfile;       // 첨부파일

    // 연관관계 [ 회원번호[pk] <--양방향--> 게시물번호[fk]
    @ManyToOne // [ 1 : N ] FK 에 해당 어노테이션
    @JoinColumn(name="mno") // 테이블에서 사용할 fk 의 필드명 정의
    @ToString.Exclude // 해당 필드는 ToString 사용하지 않는다. ( 양방향 일 경우 필수!!! )
    private MemberEntity memberEntity; // PK 에 엔티티 객체

    // 연관관계2 [ 카테고리번호[pk] <--양방향--> 게시물번호[fk]
    @ManyToOne
    @JoinColumn(name="bcno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    // 작성일 , 수정일 - > 상속 ( 여러 엔티티에서 사용되는 필드라서 )
    // 1. 형번환
    public BoardDto toDto(){
        // * 빌더 패턴을 이용한 객체 생성
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .memail(this.memberEntity.getMemail())
                .bfilename(this.bfile)
                .bdate(
                        this.getCdate().toLocalDate().toString().equals( LocalDateTime.now().toLocalDate().toString() ) ?
                        this.getCdate().toLocalTime().format( DateTimeFormatter.ofPattern(" HH : mm : ss ")) :
                        this.getCdate().toLocalDate().toString()
                )
                .build();
    }

}
/*
    연관관계
    @OneToOne       1 : 1   회원이 하나의 게시물만 작성 가능할 때
    @ManyToMany     n : 1   회원이 여러개의 게시물을 작성 가능 할 때
    @OneToMany      1 : n
    @ManyToMany     n : n
*/

/*
    자바 ---------------------> DB 자료형
    int                        int
    double float               float
    String                     varchar

        columndefault = "DB자료형"
*/
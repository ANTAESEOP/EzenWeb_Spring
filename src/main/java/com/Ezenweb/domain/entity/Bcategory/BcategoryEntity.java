package com.Ezenweb.domain.entity.Bcategory;

import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.Board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Entity 정의
@Table(name = "bcategory") // 테이블명 정의
@AllArgsConstructor // 풀생성자
@NoArgsConstructor // 깡통 ( 빈 생성자 )
@Getter // 호출
@Setter // 저장
@Builder // builder
@ToString
public class BcategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int bcno;       // 카테고리 번호
    private String bcname;  // 카테고리 이름

    @OneToMany( mappedBy = "bcategoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList
            = new ArrayList<>();
}

package com.Ezenweb.domain.entity.test;

import com.Ezenweb.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbcategory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TbcategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int tbcno;          // 카테고리 번호
    private String tbcname;     // 카테고리 이름

    @OneToMany( mappedBy = "testcategoryEntity")
    @Builder.Default
    private List<TboardEntity> tboardEntityList
            = new ArrayList<>();

    public TbcategoryDto todto(){
        return TbcategoryDto.builder()
                .tbcno( this.tbcno )
                .tbcname( this.tbcname )
                .build();
    }
}

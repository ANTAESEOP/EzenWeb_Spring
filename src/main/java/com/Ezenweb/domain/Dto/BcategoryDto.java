package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.Bcategory.BcategoryEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor // 풀생성자
@NoArgsConstructor // 깡통 ( 빈 생성자 )
@Getter // 호출
@Setter // 저장
@Builder // builder
@ToString

public class BcategoryDto {
    private int bcno;       // 카테고리 번호
    private String bcname;  // 카테고리 이름

    public BcategoryEntity toEntity(){
        return BcategoryEntity.builder()
               .bcno( this.bcno )
               .bcname( this.bcname )
               .build();
        // this : 해당 메소드를 호출하는 객체의 필드 호출시 사용
    }
}

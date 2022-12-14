package com.Ezenweb.domain.entity.test;

import lombok.*;

@NoArgsConstructor//빈 생성자
@AllArgsConstructor//풀 생성자
@Getter//필드들의 get메소드 주입
@Setter//필드들의 set메소드 주입
@ToString//객체내 필드 정보 확인 ToString메소드 주입
@Builder//객체 생성 안전성 보장 [매개변수 개수 /순서 무관 ]

public class TbcategoryDto {
    private int tbcno;
    private String tbcname;
    
    public TbcategoryEntity toEntity(){
        return TbcategoryEntity.builder()
                .tbcno(this.tbcno)
                .tbcname(this.tbcname)
                .build();
    }
}

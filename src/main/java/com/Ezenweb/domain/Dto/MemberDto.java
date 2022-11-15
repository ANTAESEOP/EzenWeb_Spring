package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.MemberEntity;
import lombok.*;

@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 풀 생성자
@Getter // 필드들의 get 메소드 주입
@Setter // 필드들의 set 메소드 주입
@ToString // 객체내 필드 정보 확인 ToString 메소드 주입
@Builder // 객체 생성 안전성 보장 [ 매개변수 개수 / 순서 무관 ]
public class MemberDto {
    private int mno;
    private String memail;
    private String mpassword;
    // * dto ---> entity 변환
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .build();
    }
}

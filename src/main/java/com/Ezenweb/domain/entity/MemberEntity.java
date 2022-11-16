package com.Ezenweb.domain.entity;

import com.Ezenweb.domain.Dto.MemberDto;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
    @Table(name="member")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    @ToString
    @Builder
    public class MemberEntity {
        // 1. 필드
        @Id // 엔티티당 무조건 1개이상[ PK ]
        @GeneratedValue( strategy = GenerationType.IDENTITY ) // 자동번호 부여
        private int mno; // 회원 번호 필드
        @Column(nullable = false) // not null
        private String memail; // 회원 이메일 = 회원아이디 필드
        @Column(nullable = false) // not null
        private String mpassword; // 회원 비밀번호 필드
        @Column(nullable = false) // not null
        private String mphone; // 회원 전화번호 필드

        // 2. 생성자 [ 롬복으로 사용 ]
        // 3. 메소드 [ 롬복으로 사용 ]
        // * 엔티티 --> Dto
        public MemberDto toDto(){
            return MemberDto
                    .builder() // 빌더 메소드 시작
                    .mno(this.mno)
                    .memail(this.memail)
                    .mpassword(this.mpassword)
                    .mphone(this.mphone)
                    .build(); // 빌더 메소드 끝
        }
    }


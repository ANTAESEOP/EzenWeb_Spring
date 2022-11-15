package com.Ezenweb.domain.entity;

import lombok.*;
import javax.persistence.*;
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
        private int mno;
        private String memail;
        private String mpassword;
        // 2. 생성자 [ 롬복으로 사용 ]
        // 3. 메소드 [ 롬복으로 사용 ]
    }


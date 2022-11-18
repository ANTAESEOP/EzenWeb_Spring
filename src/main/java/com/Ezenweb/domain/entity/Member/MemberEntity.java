package com.Ezenweb.domain.entity.Member;

import com.Ezenweb.domain.Dto.MemberDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.Board.BoardEntity;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
    @Table(name="member")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    @ToString
    @Builder
    public class MemberEntity extends BaseEntity {
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

        @OneToMany(mappedBy = "memberEntity") // [ 1 : N ] PK 에 해당 어노테이션 mappedBy = "fk")
        @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
        private List<BoardEntity> boardEntityList
                = new ArrayList<>();
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


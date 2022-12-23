package com.Ezenweb.domain.entity.room;

import lombok.*;

import javax.persistence.*;

@Entity // 해당연결된 DB의 테이블과 매핑[연결]
@Table(name="roomimg") // db에서 사용될 테이블 이름
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class RoomImgEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int rimgno;
    private String rimg;

    @ManyToOne
    @JoinColumn(name="rno")
    @ToString.Exclude
    private RoomEntity roomEntity;

}
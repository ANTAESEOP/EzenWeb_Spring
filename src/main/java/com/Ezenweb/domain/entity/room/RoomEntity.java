package com.Ezenweb.domain.entity.room;

import com.Ezenweb.domain.dto.RoomDto;
import com.Ezenweb.domain.entity.Member.MemberEntity;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당연결된 DB의 테이블과 매핑[연결]
@Table(name="room") // db에서 사용될 테이블 이름
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class RoomEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int rno;
    private String rtitle;
    private int rprice;
    private String rtrans;
    private String rname;
    private String rlat;
    private String rlng;

    @ManyToOne
    @JoinColumn(name = "mno")
    @ToString.Exclude
    private MemberEntity memberEntity; // 작성자 엔티티

    @OneToMany( mappedBy = "roomEntity")
    @Builder.Default
    @ToString.Exclude
    private List<RoomImgEntity> roomImgEntityList = new ArrayList<>();

    public RoomDto toDto() {
        // 이미지엔티티에서 이미지이름만 추출
        List<String> list = new ArrayList<>();
        roomImgEntityList.forEach( (img) -> {
            list.add( img.getRimg() );
        });
        return RoomDto.builder()
                .rno( this.rno)
                .rtitle( this.rtitle)
                .rprice( this.rprice)
                .rtrans(this.rtrans)
                .rname(this.rname)
                .rlng( this.rlng)
                .rlat(this.rlat)
                .meamil( this.getMemberEntity().getMemail() )// 멤버엔티티->작성자
                .getrimg( list )// 이미지엔티티->이미지이름들
                .build();
    }

}
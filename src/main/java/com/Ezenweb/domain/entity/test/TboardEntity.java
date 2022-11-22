package com.Ezenweb.domain.entity.test;

import com.Ezenweb.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity // Entity 정의
@Table(name = "tboard") // 테이블명 정의
@AllArgsConstructor // 풀생성자
@NoArgsConstructor // 깡통 ( 빈 생성자 )
@Getter // 호출
@Setter // 저장
@Builder // builder
@ToString

public class TboardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int tbno;

    @Column(nullable = false , columnDefinition = "TEXT")
    private String tbcontent;    // 게시물 내용

    @Column(nullable = false)   // ( noy null )
    private String tbuser;      // 작성자

    @ManyToOne
    @JoinColumn(name="tbcno")
    @ToString.Exclude
    private TbcategoryEntity testcategoryEntity;

    public TboardDto toDto(){
        return TboardDto.builder()
                .tbno(this.tbno)
                .tbcontent(this.tbcontent)
                .tbuser(this.tbuser)
                .build();
    }
}

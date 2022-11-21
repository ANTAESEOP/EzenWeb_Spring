package com.Ezenweb.domain.entity.test;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class TboardDto {
    private int tbno;
    private String tbtitle;
    private String tbcontent;
    private String tbuser;
    private int tbcno;

    public TboardEntity toEntity(){
        return TboardEntity.builder()
                .tbno(this.tbno)
                .tbcontent(this.tbcontent)
                .tbuser(this.tbuser)
                .build();
    }
}

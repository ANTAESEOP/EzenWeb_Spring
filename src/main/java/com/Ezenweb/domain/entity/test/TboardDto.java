package com.Ezenweb.domain.entity.test;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class TboardDto {
    private int tbno;
    private String tbcontent;
    private String tbuser;
    private int tbcno;
    private MultipartFile tbfile; // 첨부파일 객체 [ 업로드용 ]
    private String tbfilename;

    public TboardEntity toEntity(){
        return TboardEntity.builder()
                .tbno(this.tbno)
                .tbcontent(this.tbcontent)
                .tbuser(this.tbuser)
                .build();
    }
}

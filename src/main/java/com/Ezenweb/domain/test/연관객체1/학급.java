package com.Ezenweb.domain.test.연관객체1;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class 학급 {
    @ManyToOne
    String 학급명;
    List<학생> 학생리스트
            = new ArrayList<>();
}

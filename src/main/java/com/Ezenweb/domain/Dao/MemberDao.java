package com.Ezenweb.domain.Dao;

import com.Ezenweb.domain.Dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class MemberDao {
    @RestController // Restful api 사용하는 controller 명시
    public class MemberController {
        @Autowired // 스프링 컨테이너 빈 생성 [ 외부에 메모리 위임 ]
        private MemberService memberService; // 서비스 객체 생성
        @GetMapping("/setmember") // restful api
        public int setmember(  ){
            MemberDto memberDto = new MemberDto(0,"qweqwe@qweqwe","qweqwe");
            int result = memberService.setmember( memberDto ); // 1. 서비스[ 비지니스 로직 ] 호출
            return result;  // 2. 반환
        }
    }


}

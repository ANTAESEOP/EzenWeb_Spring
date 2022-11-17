package com.Ezenweb.controller;

import com.Ezenweb.domain.Dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // Restful api 사용하는 controller 명시
@RequestMapping("/member") // 공통 URL 매핑 주소
public class MemberController {
    // --------------------------------- 전역 객체  ---------------------------------- //
    @Autowired // 스프링 컨테이너 빈 생성 [ 외부에 메모리 위임 ]
    private MemberService memberService; // 서비스 객체 생성

    // --------------------------------- HTML 반환 매핑 ---------------------------------- //
    @GetMapping("/signup") // 회원 가입
    public Resource getsignup() {
        return new ClassPathResource("templates/member/signup.html"); // 프로젝트내 resource -> templates -> member -> signup.html 반환
    }
    @GetMapping("/login") // 로그인
    public Resource getlogin() {
        return new ClassPathResource("templates/member/login.html"); // 프로젝트내 resource -> templates -> member -> login.html 반환
    }
    @GetMapping("/findpassword") // 비밀번호 찾기
    public Resource findpassword() {
        return new ClassPathResource("templates/member/findpassword.html"); // �
    }

    @GetMapping("/delete") // 회원 탈퇴
    public Resource getdelete() {
        return new ClassPathResource("templates/member/delete.html");
    }

    @GetMapping("/update") // 비밀번호 수정
    public Resource getupdate() {
        return new ClassPathResource("templates/member/update.html");
    }

    // --------------------------------- 서비스/기능 매핑 ------------------------------------- //
    @PostMapping("/setmember") // 1. 회원가입 기능
    public int setmember(@RequestBody MemberDto memberDto) {
        int result = memberService.setmember(memberDto); // 1. 서비스[ 비지니스 로직 ] 호출
        return result;  // 2. 반환
    }

    @PostMapping("/getmember") // 2. 로그인 기능
    public int getmember(@RequestBody MemberDto memberDto) {
        int result = memberService.getmember(memberDto); // 1. 서비스[ 비지니스 로직 ] 호출
        return result;
    }

    @GetMapping("getpassword") // 3. 비밀번호 찾기 기능
    public String findpassword(@RequestParam("memail") String memail) {
        String result = memberService.findpassword(memail); // 1. 서비스[ 비지니스 로직 ] 호출
        return result;
    }

    @DeleteMapping("/setdelete") // 4. 회원탈퇴
    public int setdelete(@RequestParam("mpassword") String mpassword) {
        // 1. 서비스 처리
        int result = memberService.setdelete(mpassword);
        // 2. 서비스 결과 반환
        return result;
    }

    @PutMapping("/setupdate") // 5. 비밀번호 찾기 기능
    public int setupdate(@RequestParam("mpassword") String mpassword) {
        int result = memberService.setupdate(mpassword); // 1. 서비스[ 비지니스 로직 ] 호출
        // 2. 서비스 결과 반환
        return result;
    }

    @GetMapping("/getloginMno") // 6. 회원번호 호출
    public int getloginMno() {
        int result = memberService.getloginMno();
        return result;
    }
    @GetMapping("/logout") // 7. 로그아웃
    public int logout() {
        int result = memberService.logout();
        return result;
    }
    @GetMapping("/list") // 8. 회원목록
    public List<MemberDto> list (){
        List<MemberDto> list = memberService.list();
        System.out.println("확인" + list);
        return list;
    }
    @GetMapping("/getauth")
    public String getauth(@RequestParam("toemail") String toemail ) {
        return memberService.getauth( toemail );
    }
}



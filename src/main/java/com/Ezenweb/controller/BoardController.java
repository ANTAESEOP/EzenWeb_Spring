package com.Ezenweb.controller;

import com.Ezenweb.domain.Dto.BcategoryDto;
import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController // @Controller + @ResponseBody = @RestController [ 컴포넌트 주입 ]
@RequestMapping("/board") // 공통 URL [ localhost:8080/board ]
public class BoardController {

    // 컨트롤 역할 : HTTP 요청 / ModelAndView 응답

    // ---------------- 1. 전역변수 ---------------- //
    // 1. 서비스 메소드 호출을 위한 객체 생성 [ ioc 제어역전 ]
        // 1. 개발자가 new 연산자 사용해서 JVM 힙 메모리 할당해서 객체 생성
            // private BoardService boardService = new BoardService();
        // 2. @Autowired 어노테이션 이용해서 Spring 컨테이너 빈 [메모리] 생성
    @Autowired
    private BoardService boardService;
    // ---------------- 2. 페이지 로드 [VIEW] ---------------- //
/*
    // 1. 게시물 페이지 열기 [ localhost:8080/board/list ]
    @GetMapping("/list")
    public Resource getlist() { return new ClassPathResource("templates/board/list.html"); }

    // 2. 게시물 쓰기 페이지 열기 [ localhost:8080/board/write ]
    @GetMapping("/write")
    public Resource getwrite() { return new ClassPathResource("templates/board/write.html"); }

    // 3. 게시물 조회 페이지 열기 [ localhost:8080/board/view ]
    @GetMapping("/view")
    public Resource getbview() { return new ClassPathResource("templates/board/view.html"); }

    // 4. 게시물 수정 페이지 열기 [ localhost:8080/board/update ]
    @GetMapping("/update")
    public Resource getbupdate() { return new ClassPathResource("templates/board/update.html"); }*/

    // ---------------- 3. 요청과 응답 처리 [MODEL] ---------------- //

        // 1. HTTP 메소드 매핑 : @PostMapping @GetMapping @DeleteMapping @PutMapping
        // 2. HTTP 요청 메소드 메소드 매핑 : @RequestBody @RequestParam @PathVariable

/*    // 1. 게시물 쓰기 [ 첨부파일 없을 때]
    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BoardDto boardDto){
        System.out.println("확인" + boardDto.toString() ) ;
        return boardService.setboard(boardDto);
    }*/

    // 1. 게시물 쓰기 [ 첨부파일 없을 때]
    @PostMapping("/setboard")
    public boolean setboard( BoardDto boardDto ){
        System.out.println("확인" + boardDto.toString() ) ;
        return boardService.setboard(boardDto);
    }

    // 2. 게시물 목록 조회 [ 페이징 , 검색 ]
    @GetMapping("/boardlist")
    public List <BoardDto> boardlist(
            @RequestParam("bcno") int bcno ,
            @RequestParam("page") int page ,
            @RequestParam("key") String key ,
            @RequestParam("keyword") String keyword
            ){
        return boardService.boardlist( bcno , page , key , keyword );
    }
    // 3. 게시물 개별 조회
    @GetMapping("/getboard")
    public BoardDto getboard(@RequestParam("bno") int bno ){
        return boardService.getboard(bno);
    }
    // 4. 게시물 삭제
    @DeleteMapping("/deleteboard")
    public boolean deleteboard(@RequestParam("bno") int bno ){
        return boardService.deleteboard( bno );
    }
    // 5. 게시물 수정 [ 첨부파일 ]
    @PutMapping("/updateboard")
    public boolean updateboard( BoardDto boardDto){
        return boardService.updateboard( boardDto );
    }

    // 6. 카테고리 등록
    @Transactional
    @PostMapping("/setbcategory")
    public boolean setbcategory (@RequestBody  BcategoryDto bcategoryDto) {
        return boardService.setbcategory(bcategoryDto);
    }
    // 7. 모든 카테고리 출력
    @GetMapping("/bcategorylist")
    public List<BcategoryDto> bcategorylist () {
        return boardService.bcategorylist();
    }

    // 8. 첨부파일 다운로드
    @GetMapping("/filedownload")
    public void filedownload(@RequestParam("filename") String filename ){
        boardService.filedownload( filename );
    }
    // 9. 조회수 증가
    @PutMapping("/bviewup")
    public boolean bviewup( BoardDto boardDto ){ return boardService.bviewup(boardDto); }
}

/*
            // 1. Pageable 인터페이스  [ import 사용시 domain 패키지 ]
            // 2. PageRequest 구현클래스
                // 1.PageRequest.of( 현재페이지번호 , 표시할레코드수 )
 */
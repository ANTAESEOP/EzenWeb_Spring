package com.Ezenweb.service;

import com.Ezenweb.domain.Dto.BcategoryDto;
import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.domain.entity.Bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.Bcategory.BcategoryRepository;
import com.Ezenweb.domain.entity.Board.BoardEntity;
import com.Ezenweb.domain.entity.Board.BoardRepositiry;
import com.Ezenweb.domain.entity.Member.MemberEntity;
import com.Ezenweb.domain.entity.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service // 컴포넌트 주입 [ Spring MVC ]
public class BoardService {
    // ---------------- 1. 전역변수 ----- [ 현재 서비스 객체 @Autowired ----------- //
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언

    @Autowired
    private HttpServletResponse response; // 응답 객체 선언

    @Autowired
    private MemberRepository memberRepository; // 회원 리포지토리 객체 선언

    @Autowired
    private BoardRepositiry boardRepositiry; // 게시물 리포지토리 객체 선언

    @Autowired
    private BcategoryRepository bcategoryRepository;

    @Autowired
    private MemberService memberService;

    // 첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\springweb\\EzenWeb\\src\\main\\resources\\static\\bupload\\";

    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    // 1. 메소드
            /*
                1. insert : boardRepositiry.save            Boardentity entity
                2. selete : boardRepositiry.findAll()       List<> elist
                3. selete : boardRepositiry.findById(번호)
                4. delete : boardRepositiry.delete( entity );
             */
    // ---------------- 2. 서비스 ---------------- //

    // 0. 첨부파일 다운로드
    public void filedownload( String filename ){
        String realfilename = ""; // 0. uuid 제거
        String [] split = filename.split("_");// 1. _ 기준으로 자르기
        for( int i = 1 ; i < split.length; i++){    // 2. uuid 제외한 반복문 돌리기
            realfilename += split[i];               // 3. 뒷자리 문자열 추가
            if( split.length-1 != i ){ // 마지막 인덱스가 아니면
                realfilename += "_";                // 문자열[1] _ 문자열[2] _ 문자열[3].확장자명
            }
        }
        String filepath = path+filename;  // 1.경로 찾기
        try { // 2. 해더 구성 [ HTTP 해서 자원하는 다운로드 형식 메소드 [ response ]
            response.setHeader(
                    "Content-Disposition", // 다운로드 형식 [ 브라우저 마다 다름 ]
                    "attachment;filename=" + URLEncoder.encode(realfilename , "UTF-8" ) ); // 다운로드에 표시될 파일명
            File file = new File(filepath); // 해당 경로의 파일 객체화
        // 3. 다운로드 스트림 [ ]
            BufferedInputStream fin = new BufferedInputStream( new FileInputStream( file ) );  // 1. 입력 스트림 객체 선언
            byte[] bytes = new byte[ (int) file.length()]; // 2. 파일에 길이만큼 배열 선언
            fin.read( bytes );      // * 스트림 읽기 [ 대상 : new FileInputStream(file) ] // 3. 파일의 길이만큼 읽어와서 배열에 저장
            BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream()); // 4. 출력 스트림 객체 선언
            fout.write( bytes );    // * 스트림 내보내기 [ response.getOutputStream() ] // 5. 응답하기 [ 배열 내보내기 ]
            fout.flush(); fout.close(); fin.close(); // 6. 버퍼 초기화 혹은 스트림 닫기

        } catch (Exception e) {System.out.println(e);}
    }
    // * 첨부파일 업로드 [ 1. 쓰기메소드 2. 수정메소드
    public boolean fileupload( BoardDto boardDto , BoardEntity boardEntity){
        if( boardDto.getBfile() != null ) {
            // * 업로드 된 파일의 이름 [ 문제점 : 파일명 중복 ]
            String uuid = UUID.randomUUID().toString(); // 1. 난수 생성
            String filename = uuid + "_" + boardDto.getBfile().getOriginalFilename(); // 2. 난수 + 파일명
            // * 첨부파일 명 db 에 등록
            boardEntity.setBfile(filename); // 해당 파일명 엔티티에 저장 // 3. 난수 + 파일명 엔티티 에 저장

            // * 첨부파일 업로드 // 3. 저장 할 경로 [ 전역변수 ]
            try {
                // 4. 경로 + 파일명 [ 객체화 ]
                File uploadfile = new File(path + filename);  // 4. 경로 + 파일명 [ 객체화 ]
                boardDto.getBfile().transferTo(uploadfile);         // 5. 해당 객체 경로 로 업로드
            } catch (Exception e) {
                System.out.println(e + "첨부파일 업로드 실패");
            }
            return true;
        } else { return false; }
    }

    // 1. 게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto) {
        // --------------------------------//
        MemberEntity memberEntity = memberService.getEntity();
        if( memberEntity == null ){ return false;}
        // --------------------------------//
        Optional<BcategoryEntity> optional = bcategoryRepository.findById( boardDto.getBcno() );
        if( !optional.isPresent() ){ return false; }
        BcategoryEntity bcategoryEntity = optional.get();
        // --------------------------------//
        BoardEntity boardEntity = boardRepositiry.save(boardDto.toEntity());
        if (boardEntity.getBno() != 0) {         // 2. 생성된 entity의 게시물번호가 0이 아니면 성공

            fileupload( boardDto , boardEntity );

            // 1. 회원 <--> 게시물 연관관계 대입
            boardEntity.setMemberEntity(memberEntity); // **** !!!! 5. fk 대입
            memberEntity.getBoardEntityList().add(boardEntity); // *** 양방향 [ pk 필드에 fk 연결 ]
            // 2. 카테고리 <--> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity( bcategoryEntity );
            bcategoryEntity.getBoardEntityList().add( boardEntity );
            return true;
        } else {
            return false;
        }
    }

    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(int bcno) {
        List<BoardEntity> elist = null;
        if( bcno == 0 ){ elist = boardRepositiry.findAll(); } // 카테고리번호가 0이면 전체보기
        else {
            BcategoryEntity bcEntity = bcategoryRepository.findById( bcno ).get();
            elist = bcEntity.getBoardEntityList();
        } // 카테고리 번호가 0 이 아니면 선택된 카테고리별 보기
        List<BoardDto> dlist = new ArrayList<>(); // 2. 컨트롤에게 전달할때 형 변환 [ entity -> dto ] : 역할이 달라서
        for (BoardEntity entity : elist) { // 3. 변환
            dlist.add( entity.toDto());
        }
        return dlist; // 4. 변환된 리스트 dlist 변환
    }

    // 3. 게시물 개별 조회 ******** object 로 값 보냄
    @Transactional
    public BoardDto getboard(int bno) {
        // 1. 입력받은 게시물 번호로 엔티티 검색 [ Optional ]
        Optional<BoardEntity> optional = boardRepositiry.findById(bno);
        if (optional.isPresent()) { // 2. Optional 안에 있는 내용물 확인 .isPresent()
            BoardEntity boardEntity = optional.get(); // 엔티티 꺼내기 .get();
            return boardEntity.toDto();
        } else {
            return null;
        }
    }

    @Transactional
    // 4. 게시물 삭제
    public boolean deleteboard(int bno) {
        System.out.println(bno);
        Optional<BoardEntity> optional = boardRepositiry.findById(bno);
        if (optional.isPresent()) {
            BoardEntity entity = optional.get();
            // 첨부파일 같이 삭제
            if(entity.getBfile() != null){
                File file = new File(path + entity.getBfile());
                if( file.exists() ){ file.delete(); }
            }
            boardRepositiry.delete(entity); // 찾은 엔티티를 삭제한다.
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    // 5. 게시물 수정 [ 첨부파일 1. 첨부파일 있을때 -> 첨부파일 변경 , 2. 첨부파일 없을대 -> 첨부파일 추가]
    public boolean updateboard(BoardDto boardDto) {
        // 1. DTO 에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepositiry.findById(boardDto.getBno());
        if (optional.isPresent()) {
            BoardEntity entity = optional.get();

                // 1. 수정할 첨부파일이 있을때 ---> 기존 첨부파일 삭제 후 ---> 새로운 첨부파일 업로드 , db 수정한다.
                if( boardDto.getBfile() != null ){ // boardDto : 수정할 정보 boardEntity : 원본 [ db 테이블 ]
                    if(entity.getBfile() != null) {// 기존 첨부파일 있을때
                        File file = new File(path + entity.getBfile());
                        if (file.exists()) { // 존재하면
                            file.delete();  // 파일삭제
                        }
                        entity.setBfile(null); // db 처리
                    }
                    fileupload(boardDto , entity); // 기존 첨부파일 없을때
                }
            // * 수정처리 [ 메소드 별도 존재 X / 엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            return true;
        } else {
            return false;
        }
    }

    // 6. 카테고리 등록
    public boolean setbcategory(BcategoryDto bcategoryDto) {
        BcategoryEntity bce = bcategoryRepository.save(bcategoryDto.toEntity());
        if (bce.getBcno() != 0) {
            return true;
        } else {
            return false;
        }
    }

    // 7. 모든 카테고리 출력
    public List<BcategoryDto> bcategorylist() {
        List<BcategoryEntity> entityList = bcategoryRepository.findAll();
        List<BcategoryDto> dtolist = new ArrayList<>();
        entityList.forEach(e -> dtolist.add( e.toDto() ) );
        return dtolist;
    }
}

    // 화살표함수 [ 람다식 표현 ] js : ( 인수 ) => { 실행코드 }     java -> { 실행코드 }
    /*      1. 리스트를 순회하는 방법 3가지
            for (int i = 0; i < entityList.size(); i++) {
            BcategoryEntity e = entityList.get(i);
            System.out.println(e.toString() );
            }

            for (BcategoryEntity e : entityList) {
            System.out.println( e.toString() );
            }

            entityList.forEach( e -> e.toString());
     */


/*
    1. pk + 파일명
    2. uuid ( 범용 고유 식별자 클래스 : UUID.randomUUid().toString() )  + 파일명
    3. 업로드 날짜 / 시간 + 파일명
    4. 중복 된 파일명을 검색해서 파일명 뒤에 + ( 중복 수 + 1 )
    * 첨부파일 명 db 에 등록
*/


// 1. MultpartFile 인터페이스
// .getOriginalFilename() : 해당 인터페이스에 연결( 주소 )된 파일의 이름 호출
// .transferTo() : 파일 이동 [ 사용자pc --> 개발자pc ]
// .transferTo( 파일 객체 )
// File : java 외 파일을 객체화 클래스
// new File("경로") : 해당 경로의 파일을 객체화


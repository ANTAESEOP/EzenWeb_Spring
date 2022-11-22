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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service // 컴포넌트 주입 [ Spring MVC ]
public class BoardService {
    // ---------------- 1. 전역변수 ---------------- //
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언

    @Autowired
    private MemberRepository memberRepository; // 회원 리포지토리 객체 선언

    @Autowired
    private BoardRepositiry boardRepositiry; // 게시물 리포지토리 객체 선언

    @Autowired
    private BcategoryRepository bcategoryRepository;

    @Autowired
    private MemberService memberService;

    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    // 1. 메소드
            /*
                1. insert : boardRepositiry.save            Boardentity entity
                2. selete : boardRepositiry.findAll()       List<> elist
                3. selete : boardRepositiry.findById(번호)
                4. delete : boardRepositiry.delete( entity );
             */
    // ---------------- 2. 서비스 ---------------- //
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

    // 3. 게시물 개별 조회
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
            boardRepositiry.delete(entity); // 찾은 엔티티를 삭제한다.
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    // 5. 게시물 수정 [ 첨부파일 ]
    public boolean updateboard(BoardDto boardDto) {
        // 1. DTO 에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepositiry.findById(boardDto.getBno());
        // 2.
        if (optional.isPresent()) {
            BoardEntity entity = optional.get();
            // * 수정처리 [ 메소드 별도 존재 X / 엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            entity.setBfile(boardDto.getBfile());
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





package com.Ezenweb.service;

import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.domain.entity.Bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.Bcategory.BcategoryRepository;
import com.Ezenweb.domain.entity.Board.BoardEntity;
import com.Ezenweb.domain.entity.Board.BoardRepositiry;
import com.Ezenweb.domain.entity.Member.MemberEntity;
import com.Ezenweb.domain.entity.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean setboard(BoardDto boardDto){

        // 1. 로그인 정보 확인 [ 세션 = loginMno ]
        Object object = request.getSession().getAttribute("loginMno");
        if( object == null ) { return false; }

        // 2. 로그인 된 회원정보 호출
        int mno = (Integer)object; // 로그인된 회원 번호
        // 3. 회원번호 --> 회원정보 호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if (!optional.isPresent()) { return false; }
        // 4. 로그인된 회원의 엔티티
        MemberEntity memberEntity = optional.get();

        // 1. dto --> entity
        BoardEntity boardEntity = boardRepositiry.save( boardDto.toEntity() );
        // 2. 게시물 번호가 0이 아니면
        if( boardEntity.getBno() != 0 ){
            // ***!!!! 5. fk 대입
            boardEntity.setMemberEntity( memberEntity );
            // *** 양방향 [ pk 필드에 fk 연결 ]
            memberEntity.getBoardEntityList().add(boardEntity);
            return true;
        } else { return false;}
    }
    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        // 1. 모든 엔티티 호출한다.
        List < BoardEntity > elist = boardRepositiry.findAll();
        // 2. 컨트롤에게 전달할때 형 변환 [ entity -> dto ] : 역할이 달라서
        List<BoardDto> dlist = new ArrayList<>();
        // 3. 형변환
        for( BoardEntity entity : elist){
            dlist.add( entity.toDto() );
        }
        // 4. 변환된 리스트 dlist 변환
        return dlist;
    }
    // 3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard( int bno ){
        // 1. 입력받은 게시물 번호로 엔티티 검색 [ Optional ]
        Optional < BoardEntity > optional = boardRepositiry.findById(bno);
        if( optional.isPresent() ){ // 2. Optional 안에 있는 내용물 확인 .isPresent()
            BoardEntity boardEntity = optional.get(); // 엔티티 꺼내기 .get();
            return boardEntity.toDto();
        } else {
            return null;
        }
    }
    @Transactional
    // 4.
    public boolean deleteboard( int bno ){
        System.out.println(bno);
        Optional< BoardEntity > optional = boardRepositiry.findById(bno);
        if( optional.isPresent() ) {
            BoardEntity entity = optional.get();
            boardRepositiry.delete(entity); // 찾은 엔티티를 삭제한다.
            return true;
        } else { return false; }
    }
    @Transactional
    // 5. 게시물 수정 [ 첨부파일 ]
    public boolean updateboard( BoardDto boardDto ){
        // 1. DTO 에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional < BoardEntity > optional = boardRepositiry.findById(boardDto.getBno());
        // 2.
        if ( optional.isPresent()){
            BoardEntity entity = optional.get();
            // * 수정처리 [ 메소드 별도 존재 X / 엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            entity.setBtitle( boardDto.getBtitle() );
            entity.setBcontent( boardDto.getBcontent() );
            entity.setBfile( boardDto.getBfile() );
            return true;
        } else { return false; }
    }

    @Transactional
    public int createcategory (BoardDto boardDto) {
        BcategoryEntity entity = bcategoryRepository.save(boardDto.toEntity());
        return entity.getBcno();
    }

}





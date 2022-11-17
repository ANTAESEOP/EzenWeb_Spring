package com.Ezenweb.service;

import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.domain.entity.BoardEntity;
import com.Ezenweb.domain.entity.BoardRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 컴포넌트 주입 [ Spring MVC ]
public class BoardService {
    // ---------------- 1. 전역변수 ---------------- //
    @Autowired
    private BoardRepositiry boardRepositiry;
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
        // 1. dto --> entity
        BoardEntity entity = boardRepositiry.save( boardDto.toenEntity() );
        // 2. 게시물 번호가 0이 아니면
        if( entity.getBno() != 0 ){ return true;
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
            dlist.add( entity.todto() );
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
            return boardEntity.todto();
        } else {
            return null;
        }
    }
    @Transactional
    // 4. 게시물 삭제
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
}





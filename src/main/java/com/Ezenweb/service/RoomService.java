package com.Ezenweb.service;

import com.Ezenweb.domain.dto.RoomDto;
import com.Ezenweb.domain.entity.Member.MemberEntity;
import com.Ezenweb.domain.entity.Member.MemberRepository;
import com.Ezenweb.domain.entity.room.RoomEntity;
import com.Ezenweb.domain.entity.room.RoomImgEntity;
import com.Ezenweb.domain.entity.room.RoomImgRepository;
import com.Ezenweb.domain.entity.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomImgRepository roomImgRepository;

    // 현재 스프링의 배포된 내장 서버 폴더
    // String path ="C:\\프로젝트경로\\build\\resources\\main\\static\\bupload\\";
    //String path ="C:\\Users\\504t\\Desktop\\team\\ezen_web_2022_webapp_spring\\build\\resources\\main\\static\\bupload\\";
    // 현재 스프링의 배포된 내장 서버내 리액트 리소스 [static->media] 폴더 경로
    // 서버 [ 내장서버 ]가 재 시작되면 빌드폴더에 업로드 파일 사라짐.
    String path ="C:\\Users\\504t\\Desktop\\team\\ezen_web_2022_webapp_spring\\build\\resources\\main\\static\\static\\media\\";


    @Transactional
    public boolean write(RoomDto roomDto ){
        // 1. 등록한 유저 [ 로그인한 유저 ]
        String loginMemail = memberService.getloginMno();
        if( loginMemail == null ){ return false; }
        MemberEntity memberEntity = memberRepository.findByMemail( loginMemail ).get();
        // 2. 룸/사진 등록
        // 1. 룸 저장 [ save( 엔티티 ) 반환타입 [ 저장된 매핑 레코드 ]
        RoomEntity roomEntity = roomRepository.save( roomDto.toEntity() );
        if( roomEntity.getRno() < 1 ){ return false; }
        // 2. 룸에 회원엔티티 대입  // 3. 회원엔티티에 룸 대입   [ 양방향 관계 ]
        roomEntity.setMemberEntity( memberEntity );
        memberEntity.getRoomEntityList().add( roomEntity );
        // 3. 사진등록 저장
        roomDto.getRimg().forEach( (img) -> { // 첨부파일 여러개 일경우 혹은 존재할경우

            if( !img.getOriginalFilename().equals("") ){ // 실제 첨부파일의 파일명이 존재할경우
                RoomImgEntity roomImgEntity = roomImgRepository.save(  RoomImgEntity.builder().build() );// 필드가 적을때는 굳이 DTO 필요없음
                // 4. 룸에 사진엔티티 대입  // 5  사진엔티티에 룸 대입   [ 양방향 관계 ]
                roomEntity.getRoomImgEntityList().add( roomImgEntity );
                roomImgEntity.setRoomEntity( roomEntity );

                // 첨부파일 사진 업로드
                try {
                    // 첨부파일에 식별자 추가 [ pk + 파일명 ] // 파일명이 중복될수 있기 때문에
                    String filename = roomImgEntity.getRimgno()+img.getOriginalFilename(); // 첨부파일된 실제 파일
                    roomImgEntity.setRimg( filename );// DB에 식별자가 추가된 파일명으로 변경
                    File file = new File(path + filename);  // 경로+첨부파일명 => file 클래스 객체화 [ transferTo함수의 인수 file ]
                    img.transferTo(file);
                }catch ( Exception e ){  System.out.println("[업로드 실패] "+ e );  }
                // MultipartFile 인터페이스
                // transferTo : 업로드 [ 파일 이동 ] 함수  // 기존파일.transferTo( 이동할경로 )  // 예외처리 필수
            }
        });
        return true;
    }
    // 2. 룸 출력
    @Transactional
    public List<RoomDto> getroomlist(){

        Map<String , Integer> list = roomRepository.findBySearch();
        for( String s :  list.keySet() ) {
            System.out.println( s );
            System.out.println( list.get(s));
        }

        // 1. 모든 룸 엔티티 꺼내기
        List<RoomEntity> roomEntityList = roomRepository.findAll(); // 매핑된 엔티티들
        List<RoomDto> roomDtoList = new ArrayList<>();              // 출력용 디티오
        // 2. 형변환
        roomEntityList.forEach( (e) -> {  roomDtoList.add(  e.toDto() ); });
        return roomDtoList;
    }
}












package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.RoomDto;
import com.Ezenweb.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    // @Autowired : 스프링 빈에 등록된 클래스만 가능~
    @Autowired
    private RoomService roomService;

    // 주의 : 리액트 라우터에 있는 path의 주소가 같으면 오류 발생
    @PostMapping("/setroom")
    public boolean write( RoomDto roomDto ){ return roomService.write( roomDto ); }

    @GetMapping("/getroomlist")
    public List<RoomDto> getroomlist(){ return roomService.getroomlist();}

}














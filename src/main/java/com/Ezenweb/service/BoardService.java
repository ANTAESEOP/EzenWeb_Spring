package com.Ezenweb.service;

import com.Ezenweb.domain.Dao.BoardDao;
import com.Ezenweb.domain.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service // 컴포넌트 [ Spring MVC ]
public class BoardService {

    // 1. 게시물 등록 서비스
    public boolean setboard( @RequestBody BoardDto boardDto ){
        return true;
    }



}
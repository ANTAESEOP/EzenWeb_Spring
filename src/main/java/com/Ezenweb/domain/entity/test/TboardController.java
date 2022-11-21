package com.Ezenweb.domain.entity.test;

import com.Ezenweb.domain.Dto.BcategoryDto;
import com.Ezenweb.domain.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tboard") // url 정의
public class TboardController {

    // 전역변수
    @Autowired
    private TbaordService tbaordService;


    // html 정의
    @GetMapping("/tblist")
    public Resource gettlist(){return new ClassPathResource("templates/test/tblist.html");}

    @GetMapping("/tbwrite")
    public Resource gettwrite(){return new ClassPathResource("templates/test/tbwrite.html");}

    // 카테고리 출력
    @GetMapping("tblist")
    public List <TbcategoryDto> tblist(@RequestParam("bcno") int bcno ) {return tbaordService.tblist(bcno);}

    // 글등록
    @PostMapping("/tsetboard")
    public boolean tsetboard(@RequestBody TboardDto tboardDto){ return tbaordService.tbwrite(tboardDto); }

    // 카테고리 등록
    @PostMapping("/tsetcategory")
    public boolean tsetcategory(@RequestBody TbcategoryDto tbcategoryDto){return tbaordService.tsetcategory(tbcategoryDto);}





















}

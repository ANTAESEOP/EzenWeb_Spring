package com.Ezenweb.domain.entity.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/tboard") // url 정의
public class TboardController {

    // 전역변수
    @Autowired
    private TbaordService tbaordService;

    // html 정의
    @GetMapping("/tblistz")
    public Resource tblistz(){return new ClassPathResource("templates/test/tbwrite.html");}
    // ------------------------------------------------------------------------------------------------

    // 글 출력
    @GetMapping("tblist")
    public List<TboardDto> tblist(@RequestParam("tbcno") int tbcno ) {return tbaordService.tblist(tbcno);}

    // 모든 카테고리 출력
    @GetMapping("tbclist")
    public List<TbcategoryDto> tbclist(){return tbaordService.tbclist();}

    // 글등록
    @PostMapping("/tsetboard")
    public boolean tsetboard( TboardDto tboardDto ){ return tbaordService.tbwrite( tboardDto ); } // 첨부파일이 있을때 @RequestBody 지우기

    // 카테고리 등록
    @Transactional
    @PostMapping("/tsetcategory")
    public boolean tsetcategory(@RequestBody TbcategoryDto tbcategoryDto){
        return tbaordService.tsetcategory(tbcategoryDto);
    }

    // 글 삭제
    @DeleteMapping("/bdelete")
    public boolean bdelete(@RequestParam("tbno") int tbno){return tbaordService.bdelete( tbno );}

    @Transactional
    @PutMapping("/bupdate")
    public boolean bupdate(@RequestBody TboardDto tboardDto){
        return tbaordService.bupdate(tboardDto);
    }


















}

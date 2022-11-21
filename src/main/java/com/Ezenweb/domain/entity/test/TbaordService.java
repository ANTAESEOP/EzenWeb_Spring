package com.Ezenweb.domain.entity.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TbaordService {

    @Autowired
    private TBoardRepositiry tBoardRepositiry;

    @Autowired TBcategoryRepositiry tBcategoryRepositiry;

/*
    @Transactional
    public List<TbcategoryDto> tblist(int tbcno){
        List<TbcategoryDto> elist = null;
        if( bnco == 0 ){ elist = tBoardRepositiry.findAll();}
        else{
            TbcategoryEntity tbcEntity = tBcategoryRepositiry.findAllById( bnco ).get();
            elist = tbcEntity.getTboardEntityList();
        }
        List<TboardDto>
    }
*/




    // 글등록
    @Transactional
    public boolean tbwrite(TboardDto tboardDto){
        TboardEntity tboardEntity = tBoardRepositiry.save(tboardDto.toEntity());
        return true;
    }
    // 카테고리 등록
    @Transactional
    public boolean tsetcategory(TbcategoryDto tbcategoryDto){
        TbcategoryEntity tbcategoryEntity = tBcategoryRepositiry.save(tbcategoryDto.toEntity());
        return true;
    }
}

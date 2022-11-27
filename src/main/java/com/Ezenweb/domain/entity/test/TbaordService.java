package com.Ezenweb.domain.entity.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TbaordService {

    @Autowired
    private TBoardRepositiry tBoardRepositiry;

    @Autowired TBcategoryRepositiry tBcategoryRepositiry;

    String path = "C:\\Users\\안태섭\\IdeaProjects\\EzenWeb_Spring\\src\\main\\resources\\static\\js\\tbupload\\";

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

    // 첨부파일 업로드
    @Transactional
    public boolean fileupload(TboardDto tboardDto , TboardEntity tboardEntity){
        if( tboardDto.getTbfile() != null ){
            String uuid = UUID.randomUUID().toString(); // 난수를 생성한다. // 파일 명이 같을 수 있는 경우의수 대비
            String filename = uuid + "_" + tboardDto.getTbfile().getOriginalFilename(); // filename 은 난수_파일명
            tboardEntity.setTbfile(filename); // 파일명 엔티티에 저장
            try{
                File uploadfile = new File(path + filename); // 경로 + 파일명을 객체화
                tboardDto.getTbfile().transferTo(uploadfile); // 해당 객체 경로로 업로드 한다.
            }catch (Exception e){
                System.out.println(e + " 첨부파일 업로드 실패 ");
            }
            return true;
        }else{return false;}
    }


    // 글등록
    @Transactional
    public boolean tbwrite(TboardDto tboardDto){

        Optional<TbcategoryEntity> optional = tBcategoryRepositiry.findById( tboardDto.getTbcno() );
        if( !optional.isPresent()){ return false; }
        TbcategoryEntity tbcategoryEntity = optional.get();

        TboardEntity tboardEntity = tBoardRepositiry.save(tboardDto.toEntity());
        if(tboardEntity.getTbno() != 0){

            fileupload(tboardDto , tboardEntity ); // 업로드 함수 실행.

        tboardEntity.setTestcategoryEntity(tbcategoryEntity);
        tbcategoryEntity.getTboardEntityList().add(tboardEntity);
            return true;
       } else {
            return false;
        }
    }

    // 카테고리 등록
    @Transactional
    public boolean tsetcategory ( TbcategoryDto tbcategoryDto) {
        TbcategoryEntity tbcategoryEntity = tBcategoryRepositiry.save(tbcategoryDto.toEntity());
        if(tbcategoryEntity.getTbcno() != 0) {
            return true;
        } else {
            return false;
        }
    }

    // 게시물 목록 조회
    @Transactional
    public List<TboardDto> tblist (int tbcno) {
        List<TboardEntity> elist = null;
        if ( tbcno == 0 ) { elist = tBoardRepositiry.findAll(); }
        else{
            TbcategoryEntity tbcEntity = tBcategoryRepositiry.findById( tbcno ).get();
            elist = tbcEntity.getTboardEntityList();
        }
        List<TboardDto> dlist = new ArrayList<>();
        for ( TboardEntity tbcEntity : elist ) {
            dlist.add( tbcEntity.toDto());
        }
        return dlist;
    }

    // 카테고리 출력
    public List<TbcategoryDto> tbclist(){
        List<TbcategoryEntity> tbcategoryEntityList = tBcategoryRepositiry.findAll();
        List<TbcategoryDto> tdtolist = new ArrayList<>();
        tbcategoryEntityList.forEach(e -> tdtolist.add(e.todto() ) );
        return tdtolist;
    }

    @Transactional
    // 4. 게시물 삭제
    public boolean bdelete( int tbno ){
        System.out.println(tbno);
        Optional<TboardEntity> optional = tBoardRepositiry.findById(tbno);
        if(optional.isPresent()){
            TboardEntity entity = optional.get();
            tBoardRepositiry.delete(entity);
            return true;
        } else {
            return false;
        }
    }
    @Transactional
    // 5. 게시물 수정
    public boolean bupdate(TboardDto tboardDto){
        Optional<TboardEntity> optional = tBoardRepositiry.findById(tboardDto.getTbno());
        if( optional.isPresent() ){
            TboardEntity entity = optional.get();
            entity.setTbcontent(tboardDto.getTbcontent());
            return true;
        } else {
            return false;
        }



    }


}

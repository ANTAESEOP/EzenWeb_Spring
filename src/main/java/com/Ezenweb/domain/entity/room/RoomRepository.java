package com.Ezenweb.domain.entity.room;

import com.Ezenweb.domain.entity.Board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RoomRepository extends JpaRepository<RoomEntity , Integer> {


    @Query(value = "select count(*) as 사진수 from room r , roomimg rimg where r.rno = rimg.rno;", nativeQuery = true )
    Map<String , Integer> findBySearch( );

}
package com.Ezenweb.domain.entity.Bcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 해당 인터페이스가 리포지토리 임을 명시
public interface BcategoryRepository extends JpaRepository<BcategoryEntity , Integer> {




}

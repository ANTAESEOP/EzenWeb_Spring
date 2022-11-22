package com.Ezenweb.domain.entity.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBcategoryRepositiry extends JpaRepository<TbcategoryEntity , Integer> {
}

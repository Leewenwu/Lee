package com.joyfulresort.reserveorder.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ResRepository extends JpaRepository<ResVO, Integer>{

	
	@Transactional
	@Modifying
	@Query(value = "SELECT * from reserve_order ORDER BY reserve_order_id DESC" , nativeQuery = true)
	List<ResVO> findAllRes ();

}
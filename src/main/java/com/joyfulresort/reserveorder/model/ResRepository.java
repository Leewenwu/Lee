package com.joyfulresort.reserveorder.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ResRepository extends JpaRepository<ResVO, Integer> {

	@Transactional
	@Modifying
	@Query(value = "SELECT * from reserve_order ORDER BY reserve_order_id DESC", nativeQuery = true)
	List<ResVO> findAllRes();

	@Transactional
	@Query(value = "SELECT * FROM reserve_order WHERE reserve_order_date LIKE ?1 AND booking_date LIKE ?2", nativeQuery = true)
	List<ResVO> findByDates(LocalDate reserveOrderDate, LocalDate bookingDate);

	@Transactional
	@Query(value = "SELECT * FROM reserve_order WHERE reserve_order_date LIKE %?1%", nativeQuery = true)
	List<ResVO> findByReserveOrderDate(LocalDate reserveOrderDate);
    
	@Transactional
    @Query(value = "SELECT * FROM reserve_order WHERE booking_date LIKE %?1%", nativeQuery = true)
	List<ResVO> findByBookingDate(LocalDate bookingDate);	
	
	
//	List<ResVO> findByTimeBetween(Date TimeStart,Date TimeEnd);
		
	
		
	
}

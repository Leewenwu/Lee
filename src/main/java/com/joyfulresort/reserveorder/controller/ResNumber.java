package com.joyfulresort.reserveorder.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyfulresort.reserveorder.model.ResService;

@Controller
@Validated
public class ResNumber {

	@Autowired
	ResService resSvc;

	@PostMapping("/total")
	@ResponseBody
	public Integer total(
			@RequestParam(value = "bookingDate", required = false) 
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingDate2,
			@RequestParam(value = "bookingDate", required = false)
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDate bookingDate,
			ModelMap model) {
		Integer num;
//		Integer num = resSvc.countNumber101(bookingDate);
		Integer hour = 0 ;
		if (hour >14 && hour <21) {
			hour =	bookingDate2.getHour();
			num = resSvc.countNumber102(bookingDate);
		}else {
			num = resSvc.countNumber101(bookingDate);
		}
		
		if (num == null ) {
			 num = -1;
		}
//		System.out.println(num);
		return num;

	}
}

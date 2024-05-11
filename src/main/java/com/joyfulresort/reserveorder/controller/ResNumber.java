package com.joyfulresort.reserveorder.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyfulresort.reserveorder.model.ResService;
import com.joyfulresort.reserveorder.model.ResVO;

@Controller
@Validated
public class ResNumber {

	@Autowired
	ResService resSvc;

	@PostMapping("/total")
	@ResponseBody
	public Integer total(
//	@RequestParam(value = "reserveNumber", required = false) Integer reserveNumber,
			@RequestParam(value = "bookingDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDate bookingDate,
			ModelMap model) {

		Integer num = resSvc.countNumber101(bookingDate);
//		if (num != null) {
//			num = resSvc.countNumber101(bookingDate);
//			if (num > 100) {
//				num = resSvc.countNumber102(bookingDate);
//				System.out.println(num);
//				return num;
//			}
//		} 
		if (num == null) {
			 num = -1;
		}
//		System.out.println(num);
		return num;

	}
}

package com.joyfulresort.reserveorder.controller;

import java.time.LocalDate;

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
			@RequestParam(value = "bookingDate" , required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate bookingDate,
			ModelMap model) {
		Integer num = null;

		num = resSvc.countNumber101(bookingDate);
		if (num > 100) {
			num = resSvc.countNumber102(bookingDate);
		} 
		return num;
	}

}

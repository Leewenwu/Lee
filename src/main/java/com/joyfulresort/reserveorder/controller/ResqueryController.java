package com.joyfulresort.reserveorder.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joyfulresort.member.model.MemberService;
import com.joyfulresort.reserveorder.model.ResService;
import com.joyfulresort.reserveorder.model.ResVO;
import com.joyfulresort.reservesession.model.RessionService;

@Validated
@Controller
@RequestMapping("/reserve")
public class ResqueryController {

	@Autowired
	MemberService memberSvc;
	@Autowired
	RessionService ressionSvc;
	@Autowired
	ResService resSvc;

	@PostMapping("get_query")
	public String get_query(
	@NotEmpty(message="請勿空白")
	@Digits(integer = 5, fraction = 0, message = "超出數字範圍!") 
	@Pattern(regexp = "\\d+") 
	@RequestParam("reserveOrderId") String reserveOrderId,
//	@RequestParam("memberVO")String memberVO,
			ModelMap model) {

		ResVO resVO = resSvc.getOneRes(Integer.valueOf(reserveOrderId));
//				resVO =resSvc.getOneRes(Integer.valueOf(memberVO));

		List<ResVO> list = resSvc.getAllRes();
		model.addAttribute("ResList", list);
		model.addAttribute("ResListDate", list);// 用來顯示下拉選單

		if (resVO == null) {
			model.addAttribute("message", "查無資料");
			return "back-end/reserve/reserveorder";
		}

		model.addAttribute("ResVO", resVO);
		model.addAttribute("ResList", resVO);

		return "back-end/reserve/reserveorder";
	}

	public BindingResult removeFieldError(ResVO resVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());

		result = new BeanPropertyBindingResult(resVO, "resVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;

	}
}

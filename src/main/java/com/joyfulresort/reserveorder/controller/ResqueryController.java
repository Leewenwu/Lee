package com.joyfulresort.reserveorder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Digits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
//	@NotEmpty(message="請勿空白")
	@Digits(integer = 4, fraction = 0, message = "只能是數字,且不得大於4位數") @RequestParam("reserveOrderId") String reserveOrderId,
			ModelMap model) {

		ResVO resVO = resSvc.getOneRes(Integer.valueOf(reserveOrderId));

		List<ResVO> list = resSvc.getAllRes();
		model.addAttribute("ResList", list);//為何配合顯示所有的表格
		model.addAttribute("ResListData", list);// 用來顯示下拉選單

		if (resVO == null) {
			model.addAttribute("message", "沒有符合的資料");
//			return "back-end/reserve/reserveorder"; //差無資料是否返回顯示所有
		}

//	
		model.addAttribute("ResList", resVO); 
		
		
		return "back-end/reserve/reserveorder";
	}

	
	
	@PostMapping("get_many_query")
	public String get_many_query(
	@Digits(integer = 10, fraction = 0, message = "只能是數字,且不得大於10位數") 
	@RequestParam("reserveOrderState") String reserveOrderState,
	@RequestParam("bookingDate") String bookingDate,
	@RequestParam("reserveOrderDate")String reserveOrderDate,
			ModelMap model) {
	 
		  Map<String, String> map = new HashMap<>();
		    if (reserveOrderState != null) {
		        map.put("reserveOrderState", reserveOrderState);
		    }
		    if (bookingDate != null) {
		        map.put("bookingDate", bookingDate);
		    }
		    if (reserveOrderDate != null) {
		        map.put("reserveOrderDate", reserveOrderDate);
		    }
		    List<ResVO> resultList = getManyQuery(map);
		    model.addAttribute("resultList", resultList);
	
		return "back-end/reserve/reserveorder";
	}

	
	
	
	
	
	
	
	private List<ResVO> getManyQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}



	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder strBuilder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			strBuilder.append(violation.getMessage() + "   ");

		}

		List<ResVO> list = resSvc.getAllRes();
//		model.addAttribute("ResListDate", list);
		model.addAttribute("ResList", list);

		String message = strBuilder.toString();
		return new ModelAndView("/back-end/reserve/reserveorder", "message", message);

	}

}

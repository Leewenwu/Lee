package com.joyfulresort.reserveorder.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiFileChooserUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyfulresort.member.model.MemberService;
import com.joyfulresort.reservecontent.model.ResContentVO;
import com.joyfulresort.reservecontent.model.ResContentService;
import com.joyfulresort.reserveorder.model.ResService;
import com.joyfulresort.reserveorder.model.ResVO;
import com.joyfulresort.reservesession.model.RessionService;

@Validated
@Controller
@RequestMapping("/reserve")
public class ResController {

	@Autowired
	MemberService memberSvc;
	@Autowired
	RessionService ressionSvc;
	@Autowired
	ResService resSvc;
	@Autowired
	ResContentService rescontentSvc;

	@GetMapping("reservebackadd") // 後端新增訂單
	public String reservebackadd(ModelMap model) {
		ResVO resVO = new ResVO();
		model.addAttribute("resVO", resVO);

		return "back-end/reserve/reserveadd";
	}

	
	
	@PostMapping("insert")
	public String insert(@Valid ResVO resVO, BindingResult result, HttpServletRequest request, ModelMap model)
			throws IOException {
		
//	if(result.hasErrors()) {
//		return"back-end/404";
//	}
		resSvc.addRes(resVO);
		
		List<ResVO> list = resSvc.getAllRes();
		model.addAttribute("ResList", list);
		model.addAttribute("success", "新增成功!");
		
		return "back-end/reserve/reserveorder";
	}
@PostMapping("get_for_update")
	public String get_for_update(@RequestParam("reserveOrderId") String reserveOrderId, ModelMap model) {

		ResVO resVO = resSvc.getOneRes(Integer.valueOf(reserveOrderId));
		List<ResVO> list = resSvc.getAllRes();
		model.addAttribute("ResList", list);
		model.addAttribute("resVO", resVO);

		return "back-end/reserve/reserveupdate";
	}

	@PostMapping("update")
	public String update(@Valid ResVO resVO, BindingResult result, ModelMap model) throws IOException {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "back-end/404";
		}

		resSvc.updateRes(resVO);

		List<ResVO> resList = resSvc.getAllRes();
		model.addAttribute("ResList", resList);
		resVO = resSvc.getOneRes(Integer.valueOf(resVO.getReserveOrderId()));
		model.addAttribute("resVO", resVO);

		return "redirect:/reserve/reserveorder";
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

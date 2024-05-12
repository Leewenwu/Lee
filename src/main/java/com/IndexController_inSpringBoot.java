package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joyfulresort.member.model.MemberService;
import com.joyfulresort.member.model.MemberVO;
import com.joyfulresort.reservecontent.model.ResContentVO;
import com.joyfulresort.reservecontent.model.RescontentService;
import com.joyfulresort.reserveorder.model.ResService;
import com.joyfulresort.reserveorder.model.ResVO;
import com.joyfulresort.reservesession.model.RessionService;
import com.joyfulresort.reservesession.model.RessionVO;

@Controller
public class IndexController_inSpringBoot {
	@Autowired
	MemberService memberSvc;
	@Autowired
	RessionService ressionSvc;
	@Autowired
	ResService resSvc;
	@Autowired
	RescontentService rescontentSvc;

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/joyfulresort")
	public String test(Model model) {
		return "front-end/test";
	}

	@GetMapping("/joyfulresort/restaurant")
	public String restaurant(Model model) {
		return "front-end/restaurant/main";
	}
	
	@GetMapping("/joyfulresort/reservefrontadd")//前端新增訂單
	public String reservefrontadd(ModelMap model) {  
		ResVO resVO = new ResVO();
		model.addAttribute("resVO",resVO);
		
		return "front-end/restaurant/reserveorder";	
	}


	
	
	@GetMapping("/main_page")
	public String indexWithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Model model) {
		model.addAttribute("message", name);
		return "back-end/main_page"; // view
	}

	@GetMapping("/member/member")
	public String member(Model model) {
		return "back-end/member/member";
	}
 
	@GetMapping("/reserve/reserveorder")
	public String listAllres(Model model) {
		return "back-end/reserve/reserveorder";
	}

	@GetMapping("/reserve/reservecontent")
	public String reservecontent(Model model) {
		return "back-end/reserve/reservecontent";
	}
	
	
//	----------------------------------------------
	@PostMapping("insertfront")
	public String insertfront(@Valid ResVO resVO, BindingResult result, HttpServletRequest request, ModelMap model)
			throws IOException {
		if(result.hasErrors()) {
			System.out.println("新增訂單錯誤");
		}
		resSvc.addRes(resVO);
		List<ResVO> list = resSvc.getAllRes();
		model.addAttribute("ResList", list);
		model.addAttribute("success", "新增成功");
	
		return "front-end/restaurant/main";
	}
	
	
	@ModelAttribute("MemberList")
	protected List<MemberVO> referenceMemberList(Model model) {

		List<MemberVO> list = memberSvc.getAllmember();
		return list;
	}

	@ModelAttribute("ResList")
	protected List<ResVO> referenceResList(Model model) {

		List<ResVO> list = resSvc.getAllRes();
		return list;
	}

	@ModelAttribute("ResListData")
	protected List<ResVO> referenceResListData(Model model) {

		List<ResVO> list = resSvc.getAllRes();// 首次進入 讓下拉式選單抓資料
		return list;
	}

	@ModelAttribute("ResssionList")
	protected List<RessionVO> referenceRessionList(Model model) {

		List<RessionVO> list = ressionSvc.getAllRessions();
		return list;
	}
	@ModelAttribute("ContentList")
	protected List<ResContentVO> referenceContentList(Model model) {

		List<ResContentVO> list = rescontentSvc.getAllContent();
		return list;
	}

}
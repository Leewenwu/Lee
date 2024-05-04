package com;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.joyfulresort.member.model.MemberService;
import com.joyfulresort.member.model.MemberVO;
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

	@Value("${welcome.message}")
	private String message;

	private List<String> myList = Arrays.asList("XDDD Spring Boot Quickstart 官網 : https://start.spring.io", "IDE 開發工具",
			"直接使用(匯入)官方的 Maven Spring-Boot-demo Project + pom.xml",
			"直接使用官方現成的 @SpringBootApplication + SpringBootServletInitializer 組態檔",
			"依賴注入(DI) HikariDataSource (官方建議的連線池)", "Thymeleaf",
			"Java WebApp (<font color=red>快速完成 Spring Boot Web MVC</font>)");

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("myList", myList);
		return "index"; // view
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

	@ModelAttribute("ResssionList")
	protected List<RessionVO> referenceRessionList(Model model) {

		List<RessionVO> list = ressionSvc.getAllRessions();
		return list;
	}

}
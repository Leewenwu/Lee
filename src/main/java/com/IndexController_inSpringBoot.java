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

@Controller
public class IndexController_inSpringBoot {
	
	// @Autowired (●自動裝配)(Spring ORM 課程)
	// 目前自動裝配了EmpService --> 供第60使用
	@Autowired
	MemberService memberSvc;
	
    // inject(注入資料) via application.properties
    @Value("${welcome.message}")
    private String message;
	
    private List<String> myList = Arrays.asList("XDDD Spring Boot Quickstart 官網 : https://start.spring.io", "IDE 開發工具", "直接使用(匯入)官方的 Maven Spring-Boot-demo Project + pom.xml", "直接使用官方現成的 @SpringBootApplication + SpringBootServletInitializer 組態檔", "依賴注入(DI) HikariDataSource (官方建議的連線池)", "Thymeleaf", "Java WebApp (<font color=red>快速完成 Spring Boot Web MVC</font>)");
    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);
        model.addAttribute("myList", myList);
        return "index"; //view
    }
    
 
    @GetMapping("/main_page")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "back-end/main_page"; //view
    }
    
   
  
    @GetMapping("/member/member")
	public String member(Model model) {
		return "back-end/member/member";
	}
//    
//    @GetMapping("/emp/listAllEmp")
//	public String listAllEmp(Model model) {
//		return "back-end/emp/listAllEmp";
//	}
//    
    @ModelAttribute("MemberList")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MemberVO> referenceListData(Model model) {
		
    	List<MemberVO> list = memberSvc.getAll();
		return list;
	}

}
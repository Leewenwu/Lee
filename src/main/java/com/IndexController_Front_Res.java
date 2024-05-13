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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joyfulresort.member.model.MemberService;
import com.joyfulresort.member.model.MemberVO;
import com.joyfulresort.reservecontent.model.ResContentVO;
import com.joyfulresort.reservecontent.model.ResContentService;
import com.joyfulresort.reserveorder.model.ResService;
import com.joyfulresort.reserveorder.model.ResVO;
import com.joyfulresort.reservesession.model.RessionService;
import com.joyfulresort.reservesession.model.RessionVO;

@Controller
@RequestMapping("/joyfulresort")
public class IndexController_Front_Res {

	@Autowired
	ResService resSvc;
	@Autowired
	ResContentService rescontentSvc;

	@GetMapping("/restaurant")
	public String restaurant(Model model) {
		return "front-end/restaurant/main";
	}

	@GetMapping("reservefrontadd") // 前端新增訂單
	public String reservefrontadd(ModelMap model) {
		ResVO resVO = new ResVO();
		model.addAttribute("resVO", resVO);

		return "front-end/restaurant/reserveorder";
	}

	@PostMapping("insertfront")
	public String insertfront(@Valid ResVO resVO, BindingResult result, HttpServletRequest request, ModelMap model)
			throws IOException {
		if (result.hasErrors()) {
			System.out.println("前端新增訂單錯誤");
		}
		resSvc.addRes(resVO);
		model.addAttribute("success", "新增成功");
	
		return "redirect:/joyfulresort/restaurant";
//		return "front-end/restaurant/main"; 會多報Request method 'GET' not supported] 無解 但功能正常
	}

	@ModelAttribute("ContentList")
	protected List<ResContentVO> referenceContentList(Model model) {

		List<ResContentVO> list = rescontentSvc.getAllContent();
		return list;
	}

}

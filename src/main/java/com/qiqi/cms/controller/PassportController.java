package com.qiqi.cms.controller;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qiqi.cms.domain.User;
import com.qiqi.cms.service.UserService;
import com.qiqi.cms.utils.CMSRuntimeException;
import com.qiqi.cms.vo.UserVO;

/**
 * 
 * @ClassName: PassportController
 * @Description: 登录或注册
 * @author: charles
 * @date: 2019年7月17日 下午4:01:39
 */
@Controller
public class PassportController {

	@Resource
	private UserService userService;

	/***
	 * 
	 * @Title: toReg
	 * @Description: 去注册页面
	 * @return
	 * @return: String
	 */
//	@RequestMapping( value="toReg",method=RequestMethod.GET)
	@GetMapping("toReg")
	public String toReg(UserVO userVO, Model model) {

		return "passport/reg";

	}

	/**
	 * 
	 * @Title: reg
	 * @Description: 用户注册
	 * @param userVO
	 * @return
	 * @return: String
	 */
	// @RequestMapping(value="reg",method=RequestMethod.POST)
	@PostMapping("reg")
	public String reg(@Valid UserVO userVO, BindingResult result, Model model,RedirectAttributes redirectAttributes) {

		// 注册信息不符合要求
		if (result.hasErrors()) {
			return "passport/reg";// 注册失败,回到注册页面现在错误消息
		}

		try {
			userService.insert(userVO);
			// 注册成功.重定向到到登录页面,把用户名携带到登录页面并填充
			redirectAttributes.addFlashAttribute("msg","恭喜您注册成功,请登录!");
			redirectAttributes.addFlashAttribute("username",userVO.getUsername());
			
			return "redirect:toLogin";
		} catch (CMSRuntimeException e) {
			//封装自定义异常消息
			model.addAttribute("msg", e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "未知异常,请与管理员联系");
		}
		return "passport/reg";// 注册失败,回到注册页面

	}

	/**
	 * 
	 * @Title: toLogin
	 * @Description: 去登录
	 * @return
	 * @return: String
	 */
	@GetMapping("toLogin")
	public String toLogin(User user) {

		return "passport/login";
	}
	
	
	@PostMapping("login")
	public String login(User user,HttpSession session,Model model) {
		try {
			
			userService.login(user.getUsername(), user.getPassword());//登录成功
			session.setAttribute("username", user.getUsername());//存session
			return "redirect:/toIndex";
			
		}  catch (CMSRuntimeException e) {
			model.addAttribute("msg", e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "登录失败,系统出现未知系统!");
		}
		return "passport/login";//登录失败,回到登录页面
		
	}

}

package Final.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Final.frame.Biz;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class UserController {
	@Resource(name="ubiz")
	Biz<String,User> ubiz;
	@Resource(name="reserbiz")
	Biz<Integer, Reservation> reserbiz;
	
	
//	@RequestMapping("/login.mc")
//	public ModelAndView login() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("center","login");
//		mv.setViewName("main");
//		return mv;
//	}
//	
	@RequestMapping("/uregister.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center","user/register");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/userregister.mc")
	public ModelAndView user_register(User user) {
		ModelAndView mv = new ModelAndView();
		try {
			ubiz.register(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("center","login");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/usercheckId.mc")
	@ResponseBody
	public ModelAndView usercheckId(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String userid = request.getParameter("userid");
		String result="";
		try {
			User dbuser = ubiz.get(userid);
			if(dbuser != null) {
				result = "1";
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(result);
				out.close();
			}else {
				result = "0";
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(result);
				out.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("center","user/register");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/login.mc")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center","user/login");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/curegister.mc")
	public ModelAndView curegister() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center","register");
		mv.setViewName("main");
	
		return mv;
	}
}

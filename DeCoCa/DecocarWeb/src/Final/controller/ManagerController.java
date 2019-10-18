package Final.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Final.frame.Biz;
import Final.vo.Car;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class ManagerController {
	@Resource(name = "ubiz")
	Biz<String, User> ubiz;
	
	@Resource(name = "carbiz")
	Biz<Integer, Car> cbiz;
	
	@Resource(name = "reserbiz")
	Biz<Integer, Reservation> rbiz;

	@RequestMapping("/manmain.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/main");
		return mv;
	}
	
	@RequestMapping("/manageUser.mc")
	public ModelAndView manageU() {
		ModelAndView mv = new ModelAndView();
		String a;
		ArrayList<User> ulist = null;
		try {
			ulist = ubiz.getAll("a");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ulist);
		mv.addObject("ulist", ulist);
		mv.addObject("center", "ulist");
		mv.setViewName("manager/main");
		return mv;
	}

	@RequestMapping("/manageCar.mc")
	public ModelAndView manageC() {
		ModelAndView mv = new ModelAndView();
		
		ArrayList<Car> clist = null;
		try {
			clist = cbiz.getAll(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(clist);
		mv.addObject("clist", clist);
		mv.addObject("center", "clist");
		mv.setViewName("manager/main");
		return mv;
	}
	
	@RequestMapping("/manageSche.mc")
	public ModelAndView manageS() {
		ModelAndView mv = new ModelAndView();
		
		ArrayList<Reservation> slist = null;
		try {
			slist = rbiz.getAll(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(slist);
		mv.addObject("slist", slist);
		mv.addObject("center", "slist");
		mv.setViewName("manager/main");
		return mv;
	}

	//	@RequestMapping("/uregister.mc")
//	public ModelAndView main() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("center", "user/register");
//		mv.setViewName("main");
//		return mv;
//	}
//
//	@RequestMapping("/userregister.mc")
//	public ModelAndView user_register(User user) {
//		ModelAndView mv = new ModelAndView();
//		try {
//			ubiz.register(user);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mv.addObject("center", "login");
//		mv.setViewName("main");
//		return mv;
//	}
//
//	@RequestMapping("/usercheckId.mc")
//	@ResponseBody
//	public ModelAndView usercheckId(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mv = new ModelAndView();
//		String userid = request.getParameter("userid");
//		String result = "";
//		try {
//			User dbuser = ubiz.get(userid);
//			if (dbuser != null) {
//				result = "1";
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
//				out.write(result);
//				out.close();
//			} else {
//				result = "0";
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
//				out.write(result);
//				out.close();
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mv.addObject("center", "user/register");
//		mv.setViewName("main");
//		return mv;
//	}
//
//	@RequestMapping("/login.mc")
//	public ModelAndView login() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("center", "user/login");
//		mv.setViewName("main");
//		return mv;
//	}
//
//	@RequestMapping("/userupdate.mc")
//	public ModelAndView userupdate(HttpSession session, User user) {
//		ModelAndView mv = new ModelAndView();
//		try {
//			user = ubiz.get(user.getUserid());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("수정전 " + user);
//		session.setAttribute("loginuser", user);
//		mv.addObject("center", "user/updatenew");
//		mv.setViewName("main");
//		return mv;
//	}
//
//	@RequestMapping("/userupdateimpl.mc")
//	public ModelAndView uupduserupdateimplate(HttpServletRequest request, User user, String userid,
//			HttpServletResponse response) {
//		ModelAndView mv = new ModelAndView();
//		
//		try {
//			ubiz.modify(user);
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out;
//			out = response.getWriter();
//			out.println("<script>alert('수정되었습니다.');</script>");
//			//out.println("<script>alert('수정되었습니다.'); location.href='main.mc'</script>");
//			out.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		mv.addObject("center", "center");
//		mv.setViewName("main");
//		return mv;
//	}
//
//	@RequestMapping("/curegister.mc")
//	public ModelAndView curegister() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("center", "register");
//		mv.setViewName("main");
//
//		return mv;
//	}
}

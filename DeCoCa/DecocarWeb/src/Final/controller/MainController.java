package Final.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Final.frame.Biz;
import Final.vo.CarStatus;
import Final.vo.Client;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class MainController {
	@Resource(name = "ubiz")
	Biz<String, User> ubiz;
	
	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;

	@Resource(name = "reserbiz")
	Biz<Integer, Reservation> rbiz;
	
	@Resource(name = "Ureserbiz")
	Biz<String, Reservation> uresbiz;
	
	@RequestMapping("/main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "center");
		mv.setViewName("main");
		// mv.setViewName("map2");
		return mv;
	}

	@RequestMapping("/loginimpl.mc")
	public ModelAndView loginimpl(ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String token = request.getParameter("USERDEVICE");
		int usertype = 0;
		ArrayList<CarStatus> cslist = null;
		ArrayList<Reservation> relist = null;
		User dbuser = null; 
		try {
			dbuser = ubiz.get(userid);
			cslist = csbiz.getAll(1);
			relist = rbiz.getAll(1);
			if (pwd.equals(dbuser.getPwd())) {
				session.setAttribute("loginuser", dbuser);
				usertype = dbuser.getUsertype();
				if(!token.equals("hi")) {
					dbuser.setUserdevice(token);
					ubiz.modify(dbuser);
				}
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('incorrect password'); location.href='login.mc'</script>");
				out.flush();
			}
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('check'); location.href='login.mc'</script>");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			cslist = csbiz.getAll(1);
			relist = rbiz.getAll(1);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("cslist", cslist);
		mv.addObject("relist", relist);

		if (usertype == 1) {
//			mv.addObject("manager/center", "manager/center");
			mv.setViewName("main");
		} else {
			mv.addObject("center", "center");
			mv.setViewName("main");
		}
		
		System.out.println("USER : "+dbuser);
		
		return mv;
	}

	@RequestMapping("/logout.mc")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		mv.addObject("center", "center");
		mv.setViewName("main");
		return mv;
	}
		
	@RequestMapping("/mypage.mc")
	public ModelAndView mypage() {
		ModelAndView mv = new ModelAndView();		
		try {
		} catch (Exception e) {	
			e.printStackTrace();
		}
		mv.addObject("center","mypage");
		mv.setViewName("main");
		return mv;
	}

	@RequestMapping("/updateStateAll.mc")
	public ModelAndView updateAll() {
		ModelAndView mv = new ModelAndView();
		Client c = new Client("70.12.60.110", 9999);
		c.setMsg(1, 0000, 0000);
		try {
			c.startClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("carlist");
		return mv;
	}

	@RequestMapping("/updateState.mc")
	public ModelAndView updateOnecar(ModelAndView mv, String car) {
		int car_id = Integer.parseInt(car);
		Client c = new Client("70.12.60.110", 9999);
		c.setMsg(2, car_id, 0000);
		try {
			c.startClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("carlist");
		return mv;
	}
	public void sendPush(Reservation reserve) {
		String uid = reserve.getUserid();
		User u = null;
		try {
			u = ubiz.get(uid);
			String token = u.getUserdevice();
			int pin = reserve.getPinNum();
			FcmUtil fcm = new FcmUtil();
			fcm.send_FCM(token, "decoca", pin + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendPushtoReicever(Reservation reserve) {
		String uid = reserve.getReuserid();
		User u = null;
		try {
			u = ubiz.get(uid);
			String token = u.getUserdevice();
			int pin = reserve.getPinNum();
			FcmUtil fcm = new FcmUtil();
			fcm.send_FCM(token, "decoca", pin + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
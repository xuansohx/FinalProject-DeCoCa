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
			mv.setViewName("admin/admin");
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
	@RequestMapping("/schedule.mc")
	public ModelAndView schedule1(HttpSession session ,String type) {
		ModelAndView mv = new ModelAndView();

		int stype = Integer.parseInt(type);
		session.setAttribute("stype", stype);
		mv.setViewName("schedule");
		return mv;
	}
	// �뒪耳�伊댁뿉�꽌 value媛� 媛��졇�삤湲�
	@RequestMapping("/schregisterimpl.mc")
	public void schregisterimpl(Reservation reserve, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();		
		String dfull = reserve.getCalDate();
		String ddate = dfull.substring(0, 10);
		String dtime = dfull.substring(11, 16);
		System.out.println(dfull+" = "+ddate+""+dtime);
		reserve.setCalDate(ddate);
		reserve.setsTime(dtime);
		
		/* create PinNumber */
		Random r = new Random();
		String key = ""; // pinNumber(temp)
		for(int i=0; i<6; i++) {
			String ran = Integer.toString(r.nextInt(10));
			if(!key.contains(ran)) {
				key += ran;
			}else {
				i -=1;
			}
		}
		int pinNum = Integer.parseInt(key); // pinNumber(Final) 
		reserve.setPinNum(pinNum); // set PinNum (DB)
    System.out.println(reserve.toString());
		String uid = reserve.getUserid();
		try {
			rbiz.register(reserve);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			sendPush(reserve);
			response.sendRedirect("schelist.mc?userid="+uid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/schelist.mc")
	public ModelAndView schelist(Reservation reserve,String userid) {
		ModelAndView mv = new ModelAndView();
		ArrayList<Reservation> rlist = null;
		try {
			//rlist = rbiz.get();
			rlist = uresbiz.getAll(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(rlist.);
		mv.addObject("rlist", rlist);
		mv.addObject("center", "schelist");
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
		c.setMsg(0, car_id, 0000);
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
}
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
import org.springframework.web.servlet.ModelAndView;

import Final.frame.Biz;
import Final.vo.CarStatus;

import Final.vo.Client;
import Final.vo.Customer;

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
		int usertype = 0;
		ArrayList<CarStatus> cslist = null;
		ArrayList<Reservation> relist = null;
		try {
			User dbuser = ubiz.get(userid);
			cslist = csbiz.get();
			relist = rbiz.get();
			if (pwd.equals(dbuser.getPwd())) {
				session.setAttribute("loginuser", dbuser);
				usertype = dbuser.getUsertype();
				System.out.println("유저타입 : " + usertype);
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>alert('비밀번호가 틀렸습니다.'); location.href='login.mc'</script>");
				out.flush();
			}
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('아이디가 틀렸습니다.'); location.href='login.mc'</script>");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			// 자동차 상태
			cslist = csbiz.get();

			// 예약 상태
			relist = rbiz.get();
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

	@RequestMapping("/userupdateimpl.mc")
	public ModelAndView uupduserupdateimplate(HttpServletRequest request, User user, String userid,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		try {
			ubiz.modify(user);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("<script>alert('수정되었습니다.'); location.href='main.mc'</script>");
			out.flush();

			mv.addObject("center", "uupdate");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("main");
		return mv;
	}

	// 민경이 시간 제어 부문
	@RequestMapping("/schedule.mc")
	public ModelAndView schedule() {
		ModelAndView mv = new ModelAndView();
		// mv.addObject("center","scheregister");
		mv.setViewName("schedule");
		return mv;
	}
	// 스케쥴에서 value값 가져오기
	@RequestMapping("/schregisterimpl.mc")
	public void schregisterimpl(Reservation reserve, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		System.out.println(reserve.toString());
		try {
			rbiz.register(reserve);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			sendPush(reserve); // 일정을 등록하면 그 사람에게 인증키를 보내준다.
			response.sendRedirect("schelist.mc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 스케쥴 리스트
	@RequestMapping("/schelist.mc")
	public ModelAndView schelist(Reservation reserve, ArrayList<Reservation> rlist) {
		ModelAndView mv = new ModelAndView();

		try {
			rlist = rbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(rlist.);
		mv.addObject("rlist", rlist);
		mv.addObject("center", "schelist");
		mv.setViewName("main");
		return mv;
	}
  
	// 마이페이지
	@RequestMapping("/mypage.mc")
	public ModelAndView mypage(String userid) {
		ModelAndView mv = new ModelAndView();
		User user = null;
		try {
			user = ubiz.get(userid);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		mv.addObject("u",user);
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
		User u;
		try {
			u = ubiz.get(uid);
			String token = u.getUserdevice();
			int pin = reserve.getPinNum();
			FcmUtil fcm = new FcmUtil();
			fcm.send_FCM(token, "데꼬가~", pin + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
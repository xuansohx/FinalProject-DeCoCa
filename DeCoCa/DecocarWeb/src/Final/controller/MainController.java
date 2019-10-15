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
import Final.vo.Customer;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class MainController {
	@Resource(name="ubiz")
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
		//mv.setViewName("map2");
		return mv;
	}

	@RequestMapping("/loginimpl.mc")
	public ModelAndView loginimpl(ModelAndView mv, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userid= request.getParameter("userid");
		String pwd= request.getParameter("pwd");
		int usertype = 0 ;
		ArrayList<CarStatus> cslist = null;
		ArrayList<Reservation> relist = null;
		User dbuser = null; 
		try {
			dbuser = ubiz.get(userid);
			cslist = csbiz.get();
			relist = rbiz.get();
			if(pwd.equals(dbuser.getPwd())) {
				session.setAttribute("loginuser", dbuser);
				usertype = dbuser.getUsertype();
				System.out.println("유저타입 : "+usertype);
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

	

	// 민경이 시간 제어 부문
	@RequestMapping("/schedule.mc")
	public ModelAndView schedule1(HttpSession session ,String type) {
		ModelAndView mv = new ModelAndView();

		int stype = Integer.parseInt(type);
		session.setAttribute("stype", stype);
		mv.setViewName("schedule");
		return mv;
	}
	
	// 스케쥴에서 value값 가져오기 
	@RequestMapping("/schregisterimpl.mc")
	public void schregisterimpl(Reservation reserve, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		System.out.println(reserve.toString());
		
		String dfull = reserve.getCalDate();
		
		String ddate = dfull.substring(0, 10);
		String dtime = dfull.substring(11, 16);
		System.out.println(dfull+" = "+ddate+""+dtime);
		reserve.setCalDate(ddate);
		reserve.setsTime(dtime);
		try {
			
			rbiz.register(reserve);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			response.sendRedirect("schelist.mc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// 스케쥴 리스트
	@RequestMapping("/schelist.mc")
	public ModelAndView schelist(Reservation reserve ,ArrayList<Reservation> rlist) {
		ModelAndView mv = new ModelAndView();
		
		try {
			rlist = rbiz.get();
		} catch (Exception e) {	
			e.printStackTrace();
		}
//		System.out.println(rlist.);
		mv.addObject("rlist", rlist);
		mv.addObject("center","schelist");
		mv.setViewName("main");
		return mv;
	}
}
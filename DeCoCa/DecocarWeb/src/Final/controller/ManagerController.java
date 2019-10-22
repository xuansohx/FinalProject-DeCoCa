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
		mv.setViewName("main");
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
		mv.addObject("center","manager/ulist");
		mv.addObject("ulist",ulist); 
		mv.setViewName("main");
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
		mv.addObject("center","manager/clist");
		mv.addObject("clist",clist);
		mv.setViewName("main");
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
		mv.addObject("center","manager/slist");
		mv.addObject("slist",slist); 
		mv.setViewName("main");
		return mv;
	}

}

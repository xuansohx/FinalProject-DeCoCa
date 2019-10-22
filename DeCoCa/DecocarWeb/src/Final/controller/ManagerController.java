package Final.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Final.frame.Biz;
import Final.vo.Car;
import Final.vo.CarStatus;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class ManagerController {
	@Resource(name = "ubiz")
	Biz<String, User> ubiz;
	
	@Resource(name = "carbiz")
	Biz<Integer, Car> cbiz;
	
	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;
	
	@Resource(name = "reserbiz")
	Biz<Integer, Reservation> rbiz;

	@Resource(name = "Ureserbiz")
	Biz<String, Reservation> uresbiz;
	
	@RequestMapping("/manmain.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/main");
		return mv;
	}
	
	// manager version. All user list
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

	// manager version. All car list
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
	
	// manager version. All schedule list
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
	
	
	// manager version. schedule list per user
	@RequestMapping("/userschelistM.mc")
	public ModelAndView userschelistM(Reservation reserve, String userid) {
		ModelAndView mv = new ModelAndView();
		ArrayList<Reservation> slist = null;
		try {
			slist = uresbiz.getAll(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("slist", slist);
		mv.addObject("center", "slist");
		mv.setViewName("manager/main");
		return mv;
	}
	
	// manager version. schedule list per user
	@RequestMapping("/schedetailM.mc")
	public ModelAndView schedetailM(Reservation reserve, int calid) {
		ModelAndView mv = new ModelAndView();
		try {
			reserve = rbiz.get(calid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("r", reserve);
		mv.addObject("center", "sdetail");
		mv.setViewName("manager/main");
		return mv;
	}
	
	
	// manager version. schedule list per user
	@RequestMapping("/cardetailM.mc")
	public ModelAndView cardetailM(int carid) {
		ModelAndView mv = new ModelAndView();
		Car car = null;
		CarStatus cs = null;
		try {
			car = cbiz.get(carid);
			cs = csbiz.get(carid);
//			System.out.println("##"+car);
//			System.out.println("##"+cs);
			cs.setCarid(carid);
//			System.out.println("**"+cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("cs", cs);
		mv.addObject("center", "cdetail");
		mv.setViewName("manager/main");
		return mv;
	}
	
}

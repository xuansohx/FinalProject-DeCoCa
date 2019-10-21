package Final.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import Final.frame.Biz;
import Final.vo.CarStatus;
import Final.vo.Reservation;

@Controller
public class CarStatusController {

	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;

	@Resource(name = "Ureserbiz")
	Biz<String, Reservation> urbiz;

	@Resource(name = "reserbiz")
	Biz<Integer, Reservation> rbiz;

	@RequestMapping("/ctatedetail.mc")
	public ModelAndView ctatedetail() {
		ModelAndView mv = new ModelAndView();
		ArrayList<Reservation> reservationList = null;
		try {
			reservationList = rbiz.getAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("rl", reservationList);
		mv.setViewName("admin/cardetail");
		return mv;
	}
	
	@RequestMapping("/statusCenter.mc")
	public ModelAndView getStatusFromAndroid(String carid , String carstatus) {
		ModelAndView mv = new ModelAndView();
		int car_id = Integer.parseInt(carid);
		CarStatus cs = new CarStatus(car_id,carstatus);
		System.out.println(carstatus);
		try {
			csbiz.modify(cs);
			//csbiz.register(cs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}
	@RequestMapping("/getStatus.mc")
	public ModelAndView getStatus(String carid) {
		ModelAndView mv = new ModelAndView();
		CarStatus cs = null;
		int caridd = Integer.parseInt(carid);
		System.out.println(caridd);
		try {
			cs = csbiz.get(caridd);
			System.out.println(cs);
//			csbiz.modify(cs);
			//csbiz.register(cs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}
	
}

package Final.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import Final.frame.Biz;
import Final.vo.Car;
import Final.vo.CarStatus;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class CarStatusController {
	@Resource(name = "ubiz")
	Biz<String, User> ubiz;

	@Resource(name = "carbiz") 
	Biz<Integer,Car> carbiz; 
	 
	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;

	@Resource(name = "Ureserbiz") // id's reservation
	Biz<String, Reservation> urbiz;
	
	@Resource(name = "reserbiz") // All reservation
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
	public ModelAndView getStatusFromAndroid(CarStatus cs) {
		ModelAndView mv = new ModelAndView();
		System.out.println(cs.toString());		
		try {
			csbiz.modify(cs);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}
	
	@RequestMapping("/allocation.mc")
	public void allocation(Reservation reserve, Car car,HttpServletResponse response) {
		// Reservation
		int rcarid = reserve.getCarid(); 
		int rcalid = reserve.getCalid(); 
		
		// Car
		int ccarid = car.getCarid(); 
		int ccalid = car.getCalid();
		
		// for My Reservation List
		// Reservation에서 UserID 가져옴
		String uid = reserve.getUserid(); 
		
		// Allocation
		// reservation table에는 carid, car table에는 calid
		if(rcarid == 0 && ccalid ==0) {
			
		}
		
		try {
			rbiz.register(reserve);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// Reservation의 userid를 통하여 list 생성
			response.sendRedirect("schelist.mc?userid="+uid);
			
		}catch (IOException e) {
				e.printStackTrace();
			}
	
	}
}

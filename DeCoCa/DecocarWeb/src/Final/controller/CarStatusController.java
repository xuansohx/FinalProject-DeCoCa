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
import Final.vo.Customer;
import Final.vo.Reservation;
@Controller
public class CarStatusController {

	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;
	
	@Resource(name = "cbiz")
	Biz<Integer, Customer> cbiz;
	
	@Resource(name = "Ureserbiz")
	Biz<String, Reservation> urbiz;
	
	@Resource(name = "reserbiz")
	Biz<Integer, Reservation> rbiz;


	@RequestMapping("/ctatedetail.mc")
	public ModelAndView ctatedetail() {
		ModelAndView mv = new ModelAndView();
		ArrayList<Reservation> reservationList = null;
		try {
			reservationList = rbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		mv.addObject("rl", reservationList);
		
		
		//carlocation4 : 지역명을 위도 경도로 나타내는 예제
		mv.setViewName("admin/cardetail");
		return mv;
	}

}

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

@Controller
public class CarStatusController {

	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;
	
	@Resource(name = "cbiz")
	Biz<Integer, Customer> cbiz;

	@RequestMapping("/carmap.mc")
	public ModelAndView carmap() {
		ModelAndView mv = new ModelAndView();
		
		//carlocation4 : 지역명을 위도 경도로 나타내는 예제
		mv.setViewName("car/carlocation4");
		return mv;
	}

}

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
import Final.vo.Car;
import Final.vo.CarStatus;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class CarStatusController {
	@Resource(name = "ubiz")
	Biz<String, User> ubiz;

	@Resource(name = "carbiz")
	Biz<Integer, Car> carbiz;

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

	
	@RequestMapping("/getPincode.mc")
	public String getPincode(HttpServletResponse response, String carid) {
		PrintWriter out = null;
		int car_id = Integer.parseInt(carid);
		Car car = null;
		int pincode =0;
		try {
			car= carbiz.get(car_id);
			int calid =car.getCalid();
			Reservation res = rbiz.get(calid);
			pincode= res.getPinNum();
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(pincode);
		out.flush();
		return "str";
	}
	@RequestMapping("/statusCenter.mc")
	public ModelAndView getStatusFromAndroid(String carid, String carstatus,String lat, String lng) {
		ModelAndView mv = new ModelAndView();
		int car_id = Integer.parseInt(carid);
		double la = Double.parseDouble(lat);
		double ln = Double.parseDouble(lng);
		CarStatus cs = new CarStatus(car_id, carstatus,la,ln);
		System.out.println(cs);
		try {
			csbiz.modify(cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}

	@RequestMapping("/allocation.mc")
	public void allocation(String calid) {
		System.out.println(calid + "배차중!");
		ArrayList<Car> clist = null;
		Reservation reserve = null;
		String uid = null;
		int rcarid = 0;
		int rcalid = 0;
		int cal_id = Integer.parseInt(calid);
		try {
			reserve = rbiz.get(cal_id);
			// reserve = urbiz.get(uid);
			uid = reserve.getUserid();
			rcarid = reserve.getCarid();
			rcalid = reserve.getCalid();
			rcalid = reserve.getCalid();
			clist = carbiz.getAll(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Array
		Car[] CarArray = new Car[clist.size()];
		int size = 0;
		// list를 배열에 집어 넣음
		for (Car temp : clist) {
			CarArray[size++] = temp;
		}

		// Allocation
		// reservation table에는 carid, car table에는 calid

		Car car = null;
		for (int i = 0; i < CarArray.length; i++) {
			car = CarArray[i];
			if (rcarid == 0) {
				if (car.getCalid() == 0) {
					reserve.setCarid(car.getCarid());
					car.setCalid(rcalid);
					System.out.println("calid로 가져온 스케줄" + rcalid);
					break;
				}
			}
		}

		reserve.setReuserid("None");
		// insert DataBase
		try {
			// reuid 가 null이 뜬다
			carbiz.modify(car);
			rbiz.modify(reserve);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// End Action and Move to other page
	}

	@RequestMapping("/getStatus.mc")
	public ModelAndView getStatus(String carid) {
		ModelAndView mv = new ModelAndView();
		CarStatus cs = null;
		int caridd = Integer.parseInt(carid);
		System.out.println("getStatus.mc 이후의 carid : " + caridd);
		try {
			cs = csbiz.get(caridd);
			System.out.println("carid로 가져온 cat stat : " + cs);
//			csbiz.modify(cs);
			// csbiz.register(cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}

}

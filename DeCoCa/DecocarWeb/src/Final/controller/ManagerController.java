package Final.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import Final.frame.Biz;
import Final.vo.Car;
import Final.vo.CarStatus;
import Final.vo.Path;
import Final.vo.Reservation;
import Final.vo.User;
import Final.vo.Path;

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
	
	@Resource(name = "pbiz")
	Biz<Integer, Path> pbiz;
	@RequestMapping("/manmain.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
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
		mv.addObject("center","manager/ulist");
		mv.addObject("ulist",ulist); 
		mv.setViewName("main");
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
		mv.addObject("center","manager/clist");
		mv.addObject("clist",clist);
		mv.setViewName("main");
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
		mv.addObject("center","manager/slist");
		mv.addObject("slist",slist); 
		mv.setViewName("main");
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
		mv.addObject("center", "manager/slist");
		mv.setViewName("main");
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
		mv.addObject("center", "manager/sdetail");
		mv.setViewName("main");
		return mv;
	}
  
	// manager version. schedule list per user
	@RequestMapping("/cardetailM.mc")
	public ModelAndView cardetailM(int carid, HttpServletResponse rep ) {
		ModelAndView mv = new ModelAndView();
		
		Car car = null;
		CarStatus cs = null;
		ArrayList<Path> path = null;

		try {
      car = cbiz.get(carid);
			cs = csbiz.get(carid);
			cs.setCarid(carid);
			path = pbiz.getAll(carid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//path add
		mv.addObject("path", path);	
		
		//arraylist��� json��� ����� (object)
		Gson gson = new Gson();
		String json = gson.toJson(path);
		
		//System.out.println(json.toString());
		mv.addObject("json", json);
		// Cutting status and String -> Integer
		/* mv.addObject("cs", cs); */
		String status = cs.getStatus(); // get Status
		int battery = Integer.parseInt(status.substring(0,3));
		int speed = Integer.parseInt(status.substring(3,6));
		int pressure = Integer.parseInt(status.substring(6,8));
		int temperature = Integer.parseInt(status.substring(8,10));
		int door = Integer.parseInt(status.substring(10,11));
		int seatbelt = Integer.parseInt(status.substring(11,12));
		int brake = Integer.parseInt(status.substring(12,13));
		int engine = Integer.parseInt(status.substring(13,14));
		mv.addObject("battery",battery);
		mv.addObject("speed",speed);
		mv.addObject("pressure",pressure);
		mv.addObject("temperature",temperature);
		mv.addObject("door",door);
		mv.addObject("seatbelt",seatbelt);
		mv.addObject("brake",brake);
		mv.addObject("engine",engine);
		mv.addObject("car",car);
		mv.addObject("center", "manager/cdetail");
		mv.setViewName("main");
		return mv;
	}	
	
	// highcharts
	@RequestMapping("/showchart.mc")
	public ModelAndView showchart() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "manager/showchart");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/chartStype.mc")
	public void chartStype(HttpServletResponse rep) {
		ArrayList<Reservation> slist = null;
		try {
			slist = rbiz.getAll(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int typecnt1 = 0;
		int typecnt2 = 0;
		int typecnt3 = 0;
		for(int i=0; i<slist.size(); i++) {
			if(slist.get(i).getsStyle() == 1) {
				typecnt1++;
			}else if(slist.get(i).getsStyle() == 2) {
				typecnt2++;
			}else if(slist.get(i).getsStyle() == 3) {
				typecnt3++;
			}
		}
		// JSON
		JSONArray jb = new JSONArray();
		JSONObject jo = new JSONObject(); 
		jb.put(typecnt1);
		jb.put(typecnt2);
		jb.put(typecnt3);
		System.out.println(jb);
		PrintWriter out = null;
		rep.setCharacterEncoding("EUC-KR");
		rep.setContentType("text/json;charset=UTF-8");
		try {
			out = rep.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(jb.toString());
	}
	
	@RequestMapping("/chartStime.mc")
	public void chartStime(HttpServletResponse rep) {
		ArrayList<Reservation> slist = null;
		try {
			slist = rbiz.getAll(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0, t8 = 0;
		for(int i=0; i<slist.size(); i++) {
			String dtime = slist.get(i).getsTime();
			String[] time = dtime.split(":");
			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			if(0<=hour && hour<3) {
				t1++;
			}else if(3<=hour && hour<6) {
				t2++;
			}else if(6<=hour && hour<9) {
				t3++;
			}else if(9<=hour && hour<12) {
				t4++;
			}else if(12<=hour && hour<15) {
				t5++;
			}else if(15<=hour && hour<18) {
				t6++;
			}else if(18<=hour && hour<21) {
				t7++;
			}else if(21<=hour && hour<24) {
				t8++;
			}
		}
		// JSON
		JSONArray jb = new JSONArray();
		JSONObject jo = new JSONObject(); 
		jb.put(t1);
		jb.put(t2);
		jb.put(t3);
		jb.put(t4);
		jb.put(t5);
		jb.put(t6);
		jb.put(t7);
		jb.put(t8);
		System.out.println(jb);
		PrintWriter out = null;
		rep.setCharacterEncoding("EUC-KR");
		rep.setContentType("text/json;charset=UTF-8");
		try {
			out = rep.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(jb.toString());
	}
	
	// show Car Location
	@RequestMapping("/showCarLoc.mc")
	public ModelAndView showCarLoc() {
		ModelAndView mv = new ModelAndView();
		
		ArrayList<Path> path1 = null;
		ArrayList<Path> path2 = null;
		ArrayList<Path> path3 = null;
		try {
			path1 = pbiz.getAll(2038);
			path2 = pbiz.getAll(2040);
			path3 = pbiz.getAll(2041);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//path add
		mv.addObject("path1", path1);	
		mv.addObject("path2", path2);	
		mv.addObject("path3", path3);	
				
		//arraylist��� json��� ����� (object)
		Gson gson = new Gson();
		String json1 = gson.toJson(path1);
		String json2 = gson.toJson(path2);
		String json3 = gson.toJson(path3);
				
		//System.out.println(json.toString());
		mv.addObject("json1", json1);
		mv.addObject("json2", json2);
		mv.addObject("json3", json3);
		
		mv.addObject("center", "manager/showcarloc");
		mv.setViewName("main");
		return mv;
	}
	
}

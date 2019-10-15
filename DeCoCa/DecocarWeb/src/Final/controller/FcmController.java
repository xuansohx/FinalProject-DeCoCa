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
import Final.vo.CarStatus;
import Final.vo.Client;
import Final.vo.Customer;
import Final.vo.Reservation;
import Final.vo.User;

@Controller
public class FcmController {
	@Resource(name="ubiz")
	Biz<String, User> ubiz;
	
	@Resource(name = "cbiz")
	Biz<String, Customer> biz;

	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;

	@Resource(name = "reserbiz")
	Biz<Integer, Reservation> rbiz;

	@RequestMapping("fcmtest.mc")
	public String fcmtest()throws Exception{
		String tokenId="cOzckTHwC2o:APA91bHMNgy3rIxUz7sgqXa6VHL3axD3NNT0VhGgM4RFZrm-3Qx9hWRdp_"
				+ "NTexvE1ynVrhkPKwKrBTLfub6VPD9MKfd8-rHj_nLhNlZZyTujD5YxKjKoRtE6_YPqUlZlC-icsQ"
				+ "-PLIb-";
		// 사용자 토큰에게 타이틀과 컨텐츠를 보낸다.
		String title="데꼬까";
		String content="인증키:7775";
		FcmUtil FcmUtil = new FcmUtil();
		FcmUtil.send_FCM(tokenId, title, content);
		return "test";
	}
}
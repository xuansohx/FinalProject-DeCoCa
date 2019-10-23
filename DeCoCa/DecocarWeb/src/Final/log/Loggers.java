package Final.log;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import Final.vo.User;
import Final.vo.Reservation;

@Service
@Aspect
public class Loggers {
	private Logger work_log = Logger.getLogger("work");
	private Logger user_log = Logger.getLogger("user");
	private Logger data_log = Logger.getLogger("data");

	// before
	@Before("execution(* Final..*Controller.*(..))")
	public void logging(JoinPoint jp) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		String viewName = null;

		// System.out.println(session);
		// System.out.println(session.getAttribute("loginuser"));
		if (jp.getSignature().getName() == "setView") {
			viewName = (String) jp.getArgs()[1];
		}

		if (session.getAttribute("loginuser") != null) {
			User user = (User) session.getAttribute("loginuser");
			// MDC.put("pnumber", user.getPnumber());
			// MDC.put("userid", user.getUserid());

			System.out.println(user.getUserid());
			System.out.println(user.getPnumber());

		}
		// MDC.put("userid","hi");
		// System.out.println("gi");
		work_log.debug(jp.getSignature().getName());
		user_log.debug(jp.getSignature().getName());
		data_log.debug(jp.getSignature().getName());

	}

	@After("execution(* Final.controller..*Controller.*(..))")
	public void loggingAfter(JoinPoint jp) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		String viewName = null;
		if (session.getAttribute("loginuser") != null) {
			User user = (User) session.getAttribute("loginuser");
			// Reservation r = (Reservation) session.getAttribute("rr");

			// MDC.put("pnumber", user.getPnumber());
			// MDC.put("userid", user.getUserid());

			System.out.println(user.getUserid());
			System.out.println(user.getPnumber());
			// System.out.println(r.getCalDate());

			// work_log.debug(jp.getSignature().getName());
			// user_log.debug(jp.getSignature().getName());
			// data_log.debug(jp.getSignature().getName());
		}
	}
	
	//return type, location, last sentance, mc
	@After("execution(* Final.controller..*Controller.schregisterimpl(..))")
	public void scheduleAfter(JoinPoint jp) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		
		
		Reservation re = (Reservation) jp.getArgs()[0];
		System.out.println(re.geteAddress());
		System.out.println(re.getMemo());
	}

	// after
//	@AfterReturning(
//		pointcut="execution(* com.sds.component..*Service.*(..))"	
//		,returning="obj"
//	)
	public void logger(JoinPoint jp, Object obj) {
		System.out.println(jp.getSignature().getName());
		System.out.println(jp.getArgs()[0].toString());
		System.out.println("RESULT:" + obj.toString());
	}

	@Around("execution(* com.sds.component..*Service.*(..))")
	public Object aroundLogger(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("함수 실행 전");
		System.out.println(pjp.getSignature().getName());
		System.out.println(pjp.getArgs()[0].toString());
		Object returnObj = pjp.proceed();
		System.out.println(returnObj.toString());
		System.out.println("함수 실행 후");
		return returnObj;
	}
}

package Final.reservation;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;
import Final.vo.Reservation;

@Service("reserbiz")
public class ReservationBiz implements Biz<Integer, Reservation> {

	@Resource(name="reserdao")
	Dao<Integer, Reservation> dao;
		
	@Override
	public void register(Reservation v) throws Exception {
		dao.insert(v);		
	}
	@Override
	public void modify(Reservation v) throws Exception {
		dao.update(v);	
	}
	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	
	}
	@Override
	public Reservation get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<Reservation> getAll(Integer k) throws Exception {
		return dao.selectAll(k);
	}
}

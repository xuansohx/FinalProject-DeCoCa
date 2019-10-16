package Final.UReservation;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;
import Final.vo.Reservation;

@Service("Ureserbiz")
public class UReservationBiz implements Biz<String, Reservation> {

	@Resource(name="Ureserdao")
	Dao<String, Reservation> dao;

	@Override
	public void register(Reservation v) throws Exception {
		dao.insert(v);	
	}
	@Override
	public void modify(Reservation v) throws Exception {
		dao.update(v);	
	}
	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);	
	}

	@Override
	public Reservation get(String k) throws Exception {
		return dao.select(k);
	}
	@Override
	public ArrayList<Reservation> getAll(String k) throws Exception {
		return dao.selectAll(k);
	}
}

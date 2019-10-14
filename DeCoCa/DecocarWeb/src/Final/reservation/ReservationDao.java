package Final.reservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Final.frame.Dao;
import Final.mapper.ReservationMapper;
import Final.vo.Reservation;


@Repository("reserdao")
public class ReservationDao implements Dao<Integer, Reservation> {
	
	@Autowired
	ReservationMapper reserm;

	@Override
	public void insert(Reservation v) throws Exception {
		reserm.insert(v);
		
	}

	@Override
	public void update(Reservation v) throws Exception {
		reserm.update(v);
		
	}

	@Override
	public void delete(Integer k) throws Exception {
		reserm.delete(k);
		
	}

	@Override
	public Reservation select(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> select() throws Exception {
		// TODO Auto-generated method stub
		return reserm.selectall();
	}
	
	

}

package Final.UReservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Final.frame.Dao;
import Final.mapper.UReservationMapper;
import Final.vo.Reservation;


@Repository("Ureserdao")
public class UReservationDao implements Dao<String, Reservation> {
	
	@Autowired
	UReservationMapper Ureserm;

	@Override
	public void insert(Reservation v) throws Exception {
		Ureserm.insert(v);
		
	}

	@Override
	public void update(Reservation v) throws Exception {
		Ureserm.update(v);
		
	}

	@Override
	public void delete(String k) throws Exception {
		Ureserm.delete(k);
		
	}

	@Override
	public Reservation select(String k) throws Exception {
		return Ureserm.select(k);
	}

	@Override
	public ArrayList<Reservation> selectAll(String k) throws Exception {
		return Ureserm.selectAll(k);
	}

	
	
}

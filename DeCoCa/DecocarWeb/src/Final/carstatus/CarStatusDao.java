package Final.carstatus;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Final.frame.Dao;
import Final.mapper.CarStatusMapper;
import Final.vo.CarStatus;


@Repository("csdao")
public class CarStatusDao implements Dao<Integer, CarStatus> {
	
	@Autowired
	CarStatusMapper carstatusm;

	@Override
	public void insert(CarStatus v) throws Exception {
		carstatusm.insert(v);
		
	}

	@Override
	public void update(CarStatus v) throws Exception {
		
	}

	@Override
	public void delete(Integer k) throws Exception {
		carstatusm.delete(k);
		
	}

	@Override
	public CarStatus select(Integer k) throws Exception {
		return null;
	}

	@Override
	public ArrayList<CarStatus> select() throws Exception {
		return carstatusm.selectall();
	}
}

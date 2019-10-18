package Final.car;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Final.frame.Dao;
import Final.mapper.CarMapper;
import Final.vo.Car;

@Repository("cardao")
public class CarDao implements Dao<Integer, Car> {
	@Autowired
	CarMapper cm;

	@Override
	public void insert(Car v) throws Exception {
		cm.insert(v);
		
	}

	@Override
	public void update(Car v) throws Exception {
		cm.update(v);
		
	}

	@Override
	public void delete(Integer k) throws Exception {
		cm.delete(k);
		
	}

	@Override
	public Car select(Integer k) throws Exception {
		return cm.select(k);
	}

	@Override
	public ArrayList<Car> selectAll(Integer k) throws Exception {
		return cm.selectall(k);
	}


}

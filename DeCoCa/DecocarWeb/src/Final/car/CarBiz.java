package Final.car;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;
import Final.vo.Car;

@Service("carbiz")
public class CarBiz implements Biz<Integer, Car> {
	@Resource(name="cardao")
	Dao<Integer, Car> dao;

	@Override
	public void register(Car v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(Car v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
		
	}

	@Override
	public Car get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<Car> getAll(Integer k) throws Exception {
		return dao.selectAll(k);
	}
	

}

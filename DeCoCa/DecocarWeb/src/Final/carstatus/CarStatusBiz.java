package Final.carstatus;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;
import Final.vo.CarStatus;

@Service("csbiz")
public class CarStatusBiz implements Biz<Integer, CarStatus> {
	
	@Resource(name="csdao")
	Dao<Integer,CarStatus> dao;

	@Override
	public void register(CarStatus v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(CarStatus v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public CarStatus get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<CarStatus> get() throws Exception {
	
		return dao.select();
	}
	
}

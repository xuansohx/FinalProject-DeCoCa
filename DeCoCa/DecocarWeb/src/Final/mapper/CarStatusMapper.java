package Final.mapper;

import java.util.ArrayList;

import Final.vo.CarStatus;

public interface CarStatusMapper {
	public void insert(CarStatus obj);
	public void delete(int obj);
	public void update(CarStatus obj);
	public CarStatus select(int obj);
	public ArrayList<CarStatus> selectall(int obj);
	

}

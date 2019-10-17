package Final.mapper;

import java.util.ArrayList;

import Final.vo.Car;

public interface CarMapper {
	public void insert(Car obj);
	public void delete(int obj);
	public void update(Car obj);
	public Car select(int obj);
	public ArrayList<Car> selectall(int obj);

}

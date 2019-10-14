package Final.mapper;

import java.util.ArrayList;


import Final.vo.Reservation;

public interface ReservationMapper {
	public void insert(Reservation obj);
	public void delete(int obj);
	public void update(Reservation obj);
	public ArrayList<Reservation> select(int obj);
	public ArrayList<Reservation> selectall();
	

}

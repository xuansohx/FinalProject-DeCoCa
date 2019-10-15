
//일단 따로 만들어 놓고, ReservationMapper가 연결되면 지우자..
package Final.mapper;

import java.util.ArrayList;
import Final.vo.Reservation;
public interface UReservationMapper {
	public void insert(Reservation obj);
	public void delete(String obj);
	public void update(Reservation obj);
	public Reservation select(String obj);
	public ArrayList<Reservation> selectall();
	

}

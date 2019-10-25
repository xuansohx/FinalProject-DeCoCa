package Final.mapper;

import java.util.ArrayList;

import Final.vo.Path;

public interface PathMapper {
	public void insert(Path obj);
	public void delete(Integer obj);
	public void update(Path obj);
	public Path select(Integer obj);
	public ArrayList<Path> selectall(Integer obj);
}




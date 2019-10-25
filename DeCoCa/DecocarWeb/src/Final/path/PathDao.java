package Final.path;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Final.frame.Dao;
import Final.mapper.PathMapper;
import Final.vo.Path;

@Repository("pdao")
public class PathDao implements Dao<Integer, Path> {
	@Autowired
	PathMapper pm;
	
	@Override
	public void insert(Path v) throws Exception {
		pm.insert(v);
	}

	@Override
	public void update(Path v) throws Exception {
		pm.update(v);
	}

	@Override
	public void delete(Integer k) throws Exception {
		pm.delete(k);
	}

	@Override
	public Path select(Integer k) throws Exception {
			return pm.select(k);
	}

	@Override
	public ArrayList<Path> selectAll(Integer k) throws Exception {
		return pm.selectall(k);
	}

}

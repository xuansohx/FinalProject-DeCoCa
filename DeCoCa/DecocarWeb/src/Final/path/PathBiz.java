package Final.path;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;
import Final.vo.Path;

@Service("pbiz")
public class PathBiz implements Biz<Integer, Path> {
	
	@Resource(name="pdao")
	Dao<Integer,Path> dao;
	
	@Override
	public void register(Path v) throws Exception {
		dao.insert(v);
	}
	
	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}
	
	@Override
	public void modify(Path v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public Path get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<Path> getAll(Integer k) throws Exception {
		return dao.selectAll(k);
	}

}

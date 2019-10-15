package Final.user;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;

import Final.vo.User;

@Service("ubiz")
public class UserBiz implements Biz<String, User> {
	
	@Resource(name="udao")
	Dao<String,User> dao;
	@Override
	public void register(User v) throws Exception {
		dao.insert(v);
	}
	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modify(User v) throws Exception {
		dao.update(v);
		
	}
	


	@Override
	public User get(String k) throws Exception {
		
		return dao.select(k);
	}

	@Override
	public ArrayList<User> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

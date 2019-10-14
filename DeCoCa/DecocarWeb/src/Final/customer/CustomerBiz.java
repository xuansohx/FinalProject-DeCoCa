package Final.customer;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Final.frame.Biz;
import Final.frame.Dao;
import Final.vo.Customer;

@Service("cbiz")
public class CustomerBiz implements Biz<String, Customer> {
	
	@Resource(name="cdao")
	Dao<String,Customer> dao;
	@Override
	public void register(Customer v) throws Exception {
		dao.insert(v);
	}
	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modify(Customer v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public Customer get(String k) throws Exception {
		
		return dao.select(k);
	}

	@Override
	public ArrayList<Customer> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

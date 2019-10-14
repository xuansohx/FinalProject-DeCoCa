package Final.frame3;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

public interface Biz3<I,S,V> {
	@Transactional
	public void register(V v) throws Exception;
	@Transactional
	public void modify(V v) throws Exception;
	@Transactional
	public void remove(I i) throws Exception;
	public ArrayList<V> get(S s) throws Exception;
	public ArrayList<V> get() throws Exception;
}

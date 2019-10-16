package Final.frame;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

public interface Biz<K,V> {
	@Transactional
	public void register(V v) throws Exception;
	@Transactional
	public void modify(V v) throws Exception;
	@Transactional
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public ArrayList<V> getAll(K k) throws Exception;
	
}

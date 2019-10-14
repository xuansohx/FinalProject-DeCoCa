package Final.frame3;

import java.util.ArrayList;

public interface Dao3<I,S,V> {
	public void insert(V v) throws Exception;
	public void update(V v) throws Exception;
	public void delete(I i) throws Exception;
	public ArrayList<V> select(S s) throws Exception;
	public ArrayList<V> select() throws Exception;
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hive {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int test[] = new int[24]; 
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://70.12.60.200:10000/default","root","111111");
		// hive2 서버를 구동하고있는 ip주소로 접속한다. id / pwd 입력 (익명으로 로그인시 생략 가능)
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time");
		
		while (rs.next()) {
			//System.out.println(rs.getString(4));
			int inx = Integer.parseInt(rs.getString(4));
			test[inx]++;
			//System.out.println(test[inx]);
		}
		
		for(int i=0;i<24;i++){
			System.out.println(test[i]);
		}
		/*
		 * for(int i=0;i<1000;i++){
		 * 
		 * test[i]=rs.getString(3); System.out.println(test[i]); }
		 */
		conn.close();
	}
}

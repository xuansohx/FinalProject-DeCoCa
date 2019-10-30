import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ramdom {

	public static void main(String[] args) {
		
		Random r = new Random();
		
		int []day = new int [1000]; 
		int []hour =new int [1000];  
		int []min = new int [1000];  
		String []play = new String [1000];
		
		String result [] = new String[1000];
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:\\example.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedOutputStream bos = new BufferedOutputStream(fos,5); 
		
		
		for(int i=0; i<1000; i++) {
			
			day[i] = r.nextInt(31)+1;
			hour[i] = r.nextInt(23)+1;
			min[i] = r.nextInt(59)+1;	
		
			result[i] = "2019-10-"+day[i]+"-"+hour[i]+"-"+min[i]+"\r\n";
			
			try {
				fos.write(result[i].getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


	}

}

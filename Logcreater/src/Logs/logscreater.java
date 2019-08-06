package Logs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class logscreater {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String loglevels[]= {"INFO","WARN","DEBUG","FATAL","ERROR"};
		String City[]= {"Istanbul","Tokyo","Moskow","Beijing","London "};
		//System.out.println(timeStamp);
		Random r=new Random(); //random
		Random t=new Random(); //random
		String dosyaYolu = "C:\\Users\\Hz.Melkor\\Desktop\\Teb-proje\\logfile.log";
		 
		try
		{
		FileOutputStream fos = new FileOutputStream(dosyaYolu);
		 
		OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
		// 
		while(true) {
			Thread.sleep(10);
		   int a=r.nextInt(5);
		   int b=t.nextInt(5);
		   String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		   System.out.println(timeStamp +" "+ loglevels[b]+" "+" "+City[a]+"    "+ "Hello-from-"+City[a]);
			osw.write(timeStamp +" "+ loglevels[b]+" "+" "+City[a]+"    "+ "Hello-from-"+City[a] +"\n");
			osw.flush();
		}
		}
		
		catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		catch(FileNotFoundException ex)
		{
		System.out.println("Hata : " + ex);
		} 
		catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

}

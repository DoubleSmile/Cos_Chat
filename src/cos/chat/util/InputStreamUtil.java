package cos.chat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class InputStreamUtil {
	
	public static String getInputStream(HttpServletRequest request){
		String result = "";
        BufferedReader br;
			try {
				br = new BufferedReader(  
				 new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
				StringBuffer sb =new StringBuffer("");  
				String temp;  
				while((temp=br.readLine())!=null){  
					sb.append(temp);  
				}  
				br.close();  
				result = sb.toString(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("读取数据过程中出现了错误",e);
				}
			return result;
	}

}

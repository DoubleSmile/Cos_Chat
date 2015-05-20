package cos.chat.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PrintUtil {

	public static void write(HttpServletRequest request,HttpServletResponse response,String target){
		try {
			PrintWriter pw=response.getWriter();
			HttpSession session=request.getSession();
			session.setAttribute("test", "a");
			pw.write(target);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

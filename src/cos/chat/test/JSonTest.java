package cos.chat.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.junit.Test;

public class JSonTest {
	@Test
	public void fun(){
		JSONObject obj=new JSONObject();
		obj.put("msg", "Test");
		try{
			obj.getString("message");
			
		}catch(JSONException e){
			System.out.println("发生了异常哦");
		}
		System.out.println("Hehe");
	}

}

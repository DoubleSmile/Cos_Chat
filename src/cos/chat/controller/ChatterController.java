package cos.chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cos.chat.service.ChatterService;
import cos.chat.util.InputStreamUtil;
import cos.chat.util.JSONUtil;
import cos.chat.util.PrintUtil;

@Controller
public class ChatterController {
	private ChatterService chatterService;
	public ChatterService getchatterService() {
		return chatterService;
	}
    @Autowired
	public void setchatterService(ChatterService chatterService) {
		this.chatterService = chatterService;
	}
    
	@RequestMapping(value="/chatter/getAllChatters",method=RequestMethod.POST)
    public ModelAndView getAllChatters(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv=new ModelAndView();
		response.setContentType("text/html;charset=utf-8");
		mv.setViewName("index");
		try{
    		String result=InputStreamUtil.getInputStream(request);
    		if(result==null||result.trim().equals("")){
    			try {
    				response.getWriter().write(new JSONObject().put("errorCode", 404).toString());
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			return mv;        	
    		}
    		
    		JSONObject json=JSONObject.fromObject(result);
    		String message=json.getString("message");
    		if(message==null||message.trim().equals("")|| (!message.equals("begin"))){
    			JSONObject errorJson=new JSONObject();
				errorJson.put("errorCode", 407);
				PrintUtil.write(request, response, errorJson.toString());
    			return mv;  
    		}
    		
    		List<String> groupNames=chatterService.getChatterGroupNames();
    		List<String> types=chatterService.getChatterTypes();
    		JSONObject all=JSONUtil.combine(groupNames, types, chatterService);
    		PrintUtil.write(request, response, all.toString());	
    		return mv;
    		}catch(Exception e){
    		JSONObject errorJson=new JSONObject();
    		errorJson.put("errorCode", 500);
    		PrintUtil.write(request, response, errorJson.toString());
    	}finally{
    		return mv;
    	}
	}	
		@RequestMapping(value="/chatter/androidGetAllChatters",method=RequestMethod.POST)
	    public ModelAndView androidGetAllChatters(HttpServletRequest request,HttpServletResponse response) throws IOException{
			ModelAndView mv=new ModelAndView();
			response.setContentType("text/html;charset=utf-8");
			mv.setViewName("index");
			try{
	    		String result=InputStreamUtil.getInputStream(request);
	    		if(result==null||result.trim().equals("")){
	    			try {
	    				response.getWriter().write(new JSONObject().put("errorCode", 404).toString());
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			return mv;        	
	    		}
	    		
	    		JSONObject json=JSONObject.fromObject(result);
	    		String message=json.getString("message");
	    		if(message==null||message.trim().equals("")|| (!message.equals("begin"))){
	    			JSONObject errorJson=new JSONObject();
					errorJson.put("errorCode", 407);
					PrintUtil.write(request, response, errorJson.toString());
	    			return mv;  
	    		}
	    		
	    		List<String> groupNames=chatterService.getChatterGroupNames();
	    		List<String> types=chatterService.getChatterTypes();
	    		JSONObject all=JSONUtil.combineForAndroid(groupNames, types, chatterService);
	    		System.out.println(all.toString());
	    		PrintUtil.write(request, response, all.toString());	
	    		return mv;
	    		}catch(Exception e){
	    		JSONObject errorJson=new JSONObject();
	    		errorJson.put("errorCode", 500);
	    		PrintUtil.write(request, response, errorJson.toString());
	    	}finally{
	    		return mv;
	    	}
    		
    }

}

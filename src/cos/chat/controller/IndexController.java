package cos.chat.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cos.chat.model.Chatter;
import cos.chat.model.User;
import cos.chat.service.ChatterService;
import cos.chat.service.LinkService;
import cos.chat.service.UserService;
import cos.chat.util.InputStreamUtil;
import cos.chat.util.PrintUtil;

@Controller
public class IndexController {
	
	private LinkService linkService;
	public LinkService getLinkService() {
		return linkService;
	}
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public ChatterService getChatterService() {
		return chatterService;
	}
	public void setChatterService(ChatterService chatterService) {
		this.chatterService = chatterService;
	}

	private ChatterService chatterService;
	public ChatterService getchatterService() {
		return chatterService;
	}
    @Autowired
	public void setchatterService(ChatterService chatterService) {
		this.chatterService = chatterService;
	}

	@RequestMapping(value="/openChat",method=RequestMethod.POST)
	public ModelAndView openChat(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		response.setContentType("text/html;charset=utf-8");
		mv.setViewName("index");
//		try{
		String result=InputStreamUtil.getInputStream(request);
		JSONObject json=JSONObject.fromObject(result);
        JSONObject errorJson=new JSONObject();
        String name=null;
        String identifier=null;
        try{
        	name=json.getString("chatterName");
        	identifier=json.getString("identifier");
        }catch(JSONException e){
        	errorJson.put("errorCode", 408);
        	PrintUtil.write(request, response, errorJson.toString());
        	return mv;
        }
        int chatterID=chatterService.getChatterID(name);
        User user=new User(identifier,chatterID,1);
        if(!userService.exists(user))
           userService.add(user);
        else{
        	userService.updateUser(user);
        }
		Chatter requestChatter=null;
		Chatter responseChatter=null;
		String matchedIdentifier=null;
		try{
			requestChatter=chatterService.getChatter(name);
		}catch(NullPointerException e){
			errorJson.put("errorCode", 406);
        	PrintUtil.write(request, response, errorJson.toString());
			return mv;
		}catch(Exception e){
			errorJson.put("errorCode", 408);
        	PrintUtil.write(request, response, errorJson.toString());
			return mv;
		}
		try{
			//核心步骤！
			//返回的是一个Map,Map里面包含了matchedChatter和匹配到的identifier
			responseChatter=(Chatter)chatterService.getMatchedChatter(name).get("matchedChatter");
			matchedIdentifier=chatterService.getMatchedChatter(name).get("matchedIdentifier").toString();
		}catch(NullPointerException e){
			errorJson.put("waitCode", 600);
        	PrintUtil.write(request, response, errorJson.toString());
			return mv;
		}catch(Exception e){
			errorJson.put("errorCode", 408);
        	PrintUtil.write(request, response, errorJson.toString());
			return mv;
		}
		
		JSONObject requestJson=JSONObject.fromObject(requestChatter);
		requestJson.put("identifier", identifier);
		JSONObject responseJson=JSONObject.fromObject(responseChatter);
		responseJson.put("identifier",matchedIdentifier);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(requestJson);
		jsonArray.add(responseJson);
		
		PrintUtil.write(request, response, jsonArray.toString());
		return mv;
		}
//	    catch(Exception e){
//		JSONObject errorJson=new JSONObject();
//		errorJson.put("errorCode", 500);
//		PrintUtil.write(request, response, errorJson.toString());}
//	}finally{
//		return mv;
//	}
	
	
	
	@RequestMapping(value="closeChat",method=RequestMethod.POST)
	public ModelAndView closeChat(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView("index");
		response.setContentType("text/html;charset=utf-8");
		try{
			String result=InputStreamUtil.getInputStream(request);
			JSONObject receiveJson=JSONObject.fromObject(result);
			//处理其中一个User的状态
			String str_chatterID=receiveJson.getString("chatterID");
			int chatterID=Integer.valueOf(str_chatterID);
			String identifier=receiveJson.getString("identifier");
			User user=new User(identifier,chatterID,0);
			userService.updateUser(user);
			//处理其中另一个User的状态
			String str_anotherChatterID=receiveJson.getString("anotherChatterID");
			int anotherChatterID=Integer.valueOf(str_anotherChatterID);
			String anotherIdentifier=receiveJson.getString("anotherIdentifier");
			User anotherUser=new User(anotherIdentifier,anotherChatterID,0);
			userService.updateUser(anotherUser);
			
			JSONObject json=new JSONObject();
			try{
				linkService.dropLink(chatterID);
			}catch(NullPointerException e){
				json.put("errorCode", 405);
				PrintUtil.write(request, response, json.toString());
				return mv;
			}
			json.put("successCode", 1);
			PrintUtil.write(request, response, json.toString());
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

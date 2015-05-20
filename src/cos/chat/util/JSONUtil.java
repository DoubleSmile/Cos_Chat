package cos.chat.util;

import java.util.List;
import java.util.Map;

import cos.chat.service.ChatterService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	public static JSONObject combine(List<String> groupNames,List<String> types,
			ChatterService chatterService){
		JSONObject object=new JSONObject();
		 for(int i=0;i<groupNames.size();i++){
	        	String groupName=groupNames.get(i);
	            List<Map<String,Object>> tempList=chatterService.getChattersByGroupName(groupName);
	            JSONArray array=JSONArray.fromObject(tempList);
	            object.put(""+groupName+"", array);
	        }
	        
	        for(int i=0;i<types.size();i++){
	        	String type=types.get(i);
	            List<Map<String,Object>> tempList=chatterService.getChattersByType(type);
	            JSONArray array=JSONArray.fromObject(tempList);
	            object.put(""+type+"", array);
	        }
	        return object;
	}
	
	public static JSONObject combineForAndroid(List<String> groupNames,List<String> types,
			ChatterService chatterService){
		JSONObject object=new JSONObject();
		JSONArray typeArray=new JSONArray();
		 for(int i=0;i<groupNames.size();i++){
	        	String groupName=groupNames.get(i);
	            List<Map<String,Object>> tempList=chatterService.getChattersByGroupName(groupName);
	            JSONArray array=JSONArray.fromObject(tempList);
	            object.put(""+groupName+"", array);
	            typeArray.add(groupName);
	        }
	        
	        for(int i=0;i<types.size();i++){
	        	String type=types.get(i);
	            List<Map<String,Object>> tempList=chatterService.getChattersByType(type);
	            JSONArray array=JSONArray.fromObject(tempList);
	            object.put(""+type+"", array);
	            typeArray.add(type);
	            object.put("TYPES",typeArray);
	        }
	        return object;
		
	}

}

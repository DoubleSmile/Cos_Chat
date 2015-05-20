package cos.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cos.chat.dao.ChatterDAO;
import cos.chat.model.Chatter;


@Service
public class ChatterService {

private ChatterDAO chatterDAO;
	public ChatterDAO getChatterDAO() {
		return chatterDAO;
	}
    @Autowired
	public void setChatterDAO(ChatterDAO chatterDAO) {
		this.chatterDAO = chatterDAO;
	}
    
    public Chatter getChatter(String name){
    	try{
    		Chatter chatter= chatterDAO.getChatter(name);
    		return chatter;
    	}catch(NullPointerException e){
    		throw e;
    	}
        
    }
    public Map<String,Object> getMatchedChatter(String name){
    	try{
           Map<String,Object> map=chatterDAO.getMatchedChatter(name);
           return map;
    	}catch(NullPointerException e){
    		throw e;
    	}
    }
    
    
    public List<Map<String,Object>> getChattersByGroupName(String groupName){
    	return chatterDAO.getChattersByGroupName(groupName);
    }
    
    public List<Map<String,Object>> getChattersByType(String type){
    	return chatterDAO.getChattersByType(type);
    }
    
    public List<String> getChatterGroupNames(){
    	return chatterDAO.getChatterGroupNames();
    }
    
    public List<String> getChatterTypes(){
    	return chatterDAO.getChatterTypes();
    }
    
    public int getChatterID(String name){
    	return chatterDAO.getIDByName(name);
    }
    
	
}

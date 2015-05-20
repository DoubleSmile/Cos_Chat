package cos.chat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cos.chat.dao.ChatterDAO;
import cos.chat.dao.LinkDAO;
import cos.chat.model.Chatter;


@Service
public class ChatterService {

private ChatterDAO chatterDAO;
private LinkDAO linkDAO;
	
	public LinkDAO getLinkDAO() {
	    return linkDAO;
	}
	@Autowired
	public void setLinkDAO(LinkDAO linkDAO) {
		this.linkDAO = linkDAO;
	}
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
    		int chatterID=chatter.getId();
    		if(linkDAO.getLinkedID(chatterID)==0)
    			return chatter;
    		else{
    			return chatter;
    		}
    	}catch(NullPointerException e){
    		throw e;
    	}
        
    }
    
    public Map<String,Object> getMatchedChatter(String name){
    	Chatter chatter=null;
    	try{
    		chatter=getChatter(name);
    	}catch(NullPointerException e){
    		throw e;
    	}
    	int chatterID=chatter.getId();
    	Map map=new HashMap();
    	try{
    	if(linkDAO.getLinkedID(chatterID)==0){
    		Chatter matchedChatter=(Chatter)chatterDAO.getMatchedChatter(name).get("matchedChatter");
    		linkDAO.addLink(chatter.getId(),matchedChatter.getId());
    		map.put("matchedIdentifier", chatterDAO.getMatchedChatter(name).get("identifier").toString());
    		map.put("matchedChatter", matchedChatter);
    		return map;
    	}
    	else{
    		Chatter matchedChatter=chatterDAO.getChatterByID(linkDAO.getLinkedID(chatterID)); 
    		map.put("matchedIdentifier", chatterDAO.getMatchedChatter(name).get("identifier").toString());
    		map.put("matchedChatter", matchedChatter);
    		return map;
    	}
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

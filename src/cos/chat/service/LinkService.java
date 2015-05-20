package cos.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cos.chat.dao.LinkDAO;

@Service
public class LinkService {

    private LinkDAO linkDAO;
	
	public LinkDAO getLinkDAO() {
	    return linkDAO;
	}
	@Autowired
	public void setLinkDAO(LinkDAO linkDAO) {
		this.linkDAO = linkDAO;
	}
	
	public void dropLink(int chatterID){
		try{
			linkDAO.dropLink(chatterID);
		}catch(NullPointerException e){
			throw e;
		}
				
	}
}

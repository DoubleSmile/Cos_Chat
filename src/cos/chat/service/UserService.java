package cos.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cos.chat.dao.UserDAO;
import cos.chat.model.User;

@Service
public class UserService {

    private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
	    return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void add(User user){
		userDAO.add(user);
	}
	
	public boolean exists(User user){
		return userDAO.exists(user);
	}
	
	public void updateUser(User user){
		userDAO.update(user);
	}
	
	public void setUnReady(User user){
		userDAO.setUnready(user.getChatterID());
	}
}

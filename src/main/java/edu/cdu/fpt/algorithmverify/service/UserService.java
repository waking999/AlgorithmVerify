package edu.cdu.fpt.algorithmverify.service;

import org.springframework.stereotype.Service;

import edu.cdu.fpt.algorithmverify.model.User;

@Service
public class UserService {
	public boolean logIn(User user){
		String name = user.getName();
		String password = user.getPassword();
//		
//		if(name.equals("1")&&password.equals("1")){
//			return true;
//		}else{
//			return false;
//		}
		return true;
		
		
	}

}

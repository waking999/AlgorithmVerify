package edu.cdu.fpt.algorithmverify.service;

import org.springframework.stereotype.Service;

import edu.cdu.fpt.algorithmverify.model.User;

@Service
public class UserService {
	public boolean logIn(User user){
		String name = user.getName();
		String password = user.getPassword();
		
		if(name.equals("test")&&password.equals("test")){
			return true;
		}else{
			return false;
		}
	}

}

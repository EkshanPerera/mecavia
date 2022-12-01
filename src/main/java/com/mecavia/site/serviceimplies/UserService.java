package com.mecavia.site.serviceimplies;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.UserDto;
import com.mecavia.site.entity.User;
import com.mecavia.site.repo.UserRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class UserService  implements com.mecavia.site.service.UserService{
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	PasswordEncoder passwordencoder = new BCryptPasswordEncoder();

	//save user
	@Override
	public String saveUser(UserDto userdto) {
		userdto.setPassword(passwordencoder.encode(userdto.getPassword()));
		if(userrepo.existsById(userdto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			userrepo.save(modelMapper.map(userdto, User.class));
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<UserDto> getUsers(){
		List<User> userslist  = userrepo.findAll();
		return modelMapper.map(userslist,new TypeToken<List<UserDto>>(){}.getType());
	}
	@Override
	public String updateUser(UserDto userdto) {
		userdto.setPassword(passwordencoder.encode(userdto.getPassword()));
		if(userrepo.existsById(userdto.getId())) {
			userrepo.save(modelMapper.map(userdto, User.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}
	@Override
	public UserDto getUser(String userid) {
		User user =  userrepo.findByid(Integer.parseInt(userid));
		if(user == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(user,UserDto.class);
		}
	}

	@Override
	public String activeinactiveUser(ActiveInactiveEntityDto aiedto) {
		if(userrepo.existsById(aiedto.getId())) {
			int res = userrepo.activeinactiveColour(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteUser(UserDto userDto) {
		if(userrepo.existsById(userDto.getId())) {
			userrepo.delete(modelMapper.map(userDto, User.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}
	public UserDto getUserbyEmail(String emil) {
		User user =  userrepo.findByemail(emil);
		if(user == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(user,UserDto.class);
		}
	}
	
	
}

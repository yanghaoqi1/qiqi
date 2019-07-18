package com.qiqi.cms.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiqi.cms.dao.UserMapper;
import com.qiqi.cms.domain.User;
import com.qiqi.cms.utils.CMSConstant;
import com.qiqi.cms.utils.CMSRuntimeException;
import com.qiqi.cms.utils.Md5Util;
import com.qiqi.cms.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	
	@Override
	public boolean insert(UserVO userVO) {
		//具体业务
		//1两次密码是否相同
		if(!userVO.getPassword().equals(userVO.getRepassword()))
		  throw new CMSRuntimeException("两次密码不一致");
		//校验用户名是否唯一
		User user = userMapper.selectByUsername(userVO.getUsername());
		if(null!=user)
		throw new CMSRuntimeException("用户名已经存在");	
		
		
		userVO.setLocked(CMSConstant.USER_STATUS_UNLOCKED);//默认账户正常  1:禁用
		userVO.setRole(CMSConstant.RULE_GENERAL);//默认注册账户为普通用户 0.  1:为管理员
		//如果用户昵称为空,则默认为用户名
		if(null==userVO.getNickname()){
			userVO.setNickname(userVO.getUsername());	
		}
		
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		return  userMapper.insert(userVO)>0;
	}

	@Override
	public User selectByUsername(String username) {
		
		User user = userMapper.selectByUsername(username);
		
		return user;
	}

	@Override
	public boolean login(String username, String password) {
		
		//用登录名找对象.
		User user = userMapper.selectByUsername(username);
		if(null!=user) {
			if(user.getPassword().equals(Md5Util.md5Encoding(password)))
				return true;
			else {
				throw new CMSRuntimeException("密码错误!");
			}
		}
		throw new CMSRuntimeException("用户名不存在!");
		
	}

}

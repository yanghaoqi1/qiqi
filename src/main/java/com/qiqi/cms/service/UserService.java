package com.qiqi.cms.service;

import com.qiqi.cms.domain.User;
import com.qiqi.cms.vo.UserVO;

public interface UserService {

	/**
	 * 
	 * @Title: insert 
	 * @Description: 用户注册
	 * @param user
	 * @return
	 * @return: int
	 */
	boolean insert(UserVO userVO);
	/**
	 * 
	 * @Title: selectByUsername 
	 * @Description:  根据用户名查询对象
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByUsername(String username);
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param username
	 * @param password
	 * @return
	 * @return: boolean
	 */
	boolean login(String username,String password);
}

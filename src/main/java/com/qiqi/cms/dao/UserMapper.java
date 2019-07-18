package com.qiqi.cms.dao;

import com.qiqi.cms.domain.User;

/**
 * 
 * @ClassName: UserMapper 
 * @Description: 用户
 * @author:yhq 
 * @date: 2019年7月17日 下午7:30:19
 */
public interface UserMapper {

	/**
	 * 
	 * @Title: insert 
	 * @Description: 注册
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(User user);
	
	/**
	 * 
	 * @Title: selectByUsername 
	 * @Description: 根据用户名查询对象
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByUsername(String username);
}

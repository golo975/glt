package com.thunisoft.glt.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thunisoft.glt.bean.User;

@Service("userService")
public class UserService {

	private static final Log logger = LogFactory.getLog(UserService.class);
	@Autowired
	private UserDao userDao ;
	
	public boolean signIn(String username) {
//		int i = userDao.jdbcInsert(username);
//		int i = userDao.jdbcTemplateInsert(username);
//		int i = userDao.insertAndGetPK(username);
//		logger.info("新插入的值的主键是：" + i);
//		userDao.batchUpdate(username);
		List<User> list = new ArrayList<User>();
		list.add(new User().setId(1).setName("gaolong"));
		list.add(new User().setId(2).setName("zhangjiayu"));
		list.add(new User().setId(5).setName("wangliang"));
		userDao.batchUpdate(list);
		return true;
	}
}

package com.thunisoft.glt.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

	private static final Log logger = LogFactory.getLog(UserService.class);
	@Autowired
	private UserDao userDao ;
	
	public boolean signIn(String username) {
//		int i = userDao.jdbcInsert(username);
//		int i = userDao.jdbcTemplateInsert(username);
		int i = userDao.insertAndGetPK(username);
		logger.info("�²����ֵ�������ǣ�" + i);
		return true;
	}
}

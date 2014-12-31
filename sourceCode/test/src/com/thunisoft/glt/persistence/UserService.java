package com.thunisoft.glt.persistence;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

	private static final Log logger = LogFactory.getLog(UserService.class);
	@Autowired
	private UserDao userDao ;
	
	public boolean signIn(String username, HibernateTemplate hibernateTemplate) {
//		int i = userDao.jdbcInsert(username);
//		int i = userDao.jdbcTemplateInsert(username);
//		int i = userDao.insertAndGetPK(username);
//		logger.info("新插入的值的主键是：" + i);
//		userDao.batchUpdate(username);
		
		/*
		List<User> list = new ArrayList<User>();
		list.add(new User().setId(6).setUsername("gaolong"));
		list.add(new User().setId(7).setUsername("zhangjiayu"));
		list.add(new User().setId(8).setUsername("wangliang"));
		int[] updateCounts = userDao.batchUpdate2(list);
		logger.info(Arrays.toString(updateCounts));
		 */
		
//		userDao.batchUpdate_insert();
		userDao.insertByHibernate(hibernateTemplate);
//		Integer id = userDao.getUser(1);
//		System.out.println(id);
		return true;
	}
	
	public boolean signIn(String username) {
//		int i = userDao.jdbcInsert(username);
//		int i = userDao.jdbcTemplateInsert(username);
//		int i = userDao.insertAndGetPK(username);
//		logger.info("新插入的值的主键是：" + i);
//		userDao.batchUpdate(username);
		
		/*
		List<User> list = new ArrayList<User>();
		list.add(new User().setId(6).setUsername("gaolong"));
		list.add(new User().setId(7).setUsername("zhangjiayu"));
		list.add(new User().setId(8).setUsername("wangliang"));
		int[] updateCounts = userDao.batchUpdate2(list);
		logger.info(Arrays.toString(updateCounts));
		 */
		
		userDao.batchUpdate_insert();
//		Integer id = userDao.getUser(1);
//		System.out.println(id);
		return true;
	}
	
	public List getUsers(){
//		return userDao.getUsers();
		return null;
	}
}

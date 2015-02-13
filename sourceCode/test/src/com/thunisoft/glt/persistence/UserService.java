package com.thunisoft.glt.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.thunisoft.glt.bean.User;

@Service("userService")
public class UserService {

//	private static final Log logger = LogFactory.getLog(UserService.class);
	@Autowired
	private IUserDao userDao ;
	
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
	
	public List<User> getUsers(){
		return userDao.getUsers();
	}
	
	public void deleteUser(int id){
		userDao.deleteUser(id);
	}
	
	public void insertUsert(String userName){
		userDao.insertUser(userName);
	}
	
	public List<User> getUser(String username){
		return userDao.getUsers(username);
	}
}

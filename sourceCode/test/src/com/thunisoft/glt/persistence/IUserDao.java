package com.thunisoft.glt.persistence;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.thunisoft.glt.bean.User;

public interface IUserDao {

	void batchUpdate_insert();
	int jdbcInsert(String username);
	int jdbcTemplateInsert(String username);
	int insertAndGetPK(final String username);
	void batchUpdate(String username);
	void batchUpdate(final List<User> userList);
	int[] batchUpdate2(final List<User> userList);
	void batchUpdate3();
	Integer getUser(int id);
	void insertByHibernate(HibernateTemplate hibernateTemplate);
}

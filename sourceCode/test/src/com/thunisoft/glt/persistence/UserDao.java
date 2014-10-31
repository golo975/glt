package com.thunisoft.glt.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends JdbcDaoSupport{

	private static final Log logger = LogFactory.getLog(UserDao.class);
	public int jdbcInsert(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://gltest975.mysql.rds.aliyuncs.com:3306/glt?user=glt975&password=671239137";
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "INSERT INTO gltuser(username)  VALUES ('" + username + "')";
			st.execute(sql);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}
	
//	public int jdbcTemplateInsert(String username){
//		JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		jdbcTemplate.update(sql);
//	}
}

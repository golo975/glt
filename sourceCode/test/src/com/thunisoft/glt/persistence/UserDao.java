package com.thunisoft.glt.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.thunisoft.glt.bean.User;

//@Repository("userDao")
public class UserDao extends BaseDao{

	private static final Log logger = LogFactory.getLog(UserDao.class);
	
	/**
	 * 以基本的jdbc的方式向数据库中插入值
	 * @param username
	 * @return
	 */
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
	
	/**
	 * 使用JdbcTemplate向数据库中插入值
	 * @param username
	 * @return
	 */
	public int jdbcTemplateInsert(String username){
		String sql = "INSERT INTO gltuser(username)  VALUES ('" + username + "')";
		this.getJdbcTemplate().update(sql);
		return 0;
	}

	/**
	 * 向数据库中插入数据并返回自动增长的主键
	 * @param username
	 * @return
	 */
	public int insertAndGetPK(final String username){
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "INSERT INTO gltuser(username)  VALUES (?)";
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setString(1, username);// 这里的值从1 开始
				return ps;
			}
		}, keyHolder);

		return ((Long)keyHolder.getKey()).intValue();// 记住这种写法 Long → int
	}
	
	/**
	 * 批量更新操作
	 * 实际试了试，batcheUpdate 和 update 的效果是一样的
	 */
	public void batchUpdate(String username){
		String sql = " update gltuser set username = '" + username + "' ";
//		getJdbcTemplate().batchUpdate(sql);
		getJdbcTemplate().update(sql);
	}
	
	public void batchUpdate(final List<User> userList){
		String sql = " update gltuser set username = ? where id = ? ";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, userList.get(i).getName());
				ps.setInt(2, userList.get(i).getId());
			}
			
			@Override
			public int getBatchSize() {
				return userList.size();
			}
		});
	}
	
	public void batchUpdate2(final List<User> userList){
		// TODO 13.4.2 Batch operations with a List of objects
	}
	
}

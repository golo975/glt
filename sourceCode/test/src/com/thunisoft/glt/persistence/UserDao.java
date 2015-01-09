package com.thunisoft.glt.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.thunisoft.glt.bean.User;

@Repository
public class UserDao extends BaseDao implements IUserDao{

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
	
	/**
	 * 这种做法的思路是，把需要的参数包装为一个可以通过索引来一次访问的数据结构，所以在这个内部类里需要两个方法来分别指名需要遍历的索引的范围，和使用索引来处理数据的方式
	 * @param userList
	 */
	public void batchUpdate(final List<User> userList){
		String sql = " update gltuser set username = ? where id = ? ";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, userList.get(i).getUsername());
				ps.setInt(2, userList.get(i).getId());
			}
			
			@Override
			public int getBatchSize() {
				return userList.size();
			}
		});
	}
	
	/**
	 * 相对使用BatchPreparedStatementSetter的方式，下面这种方式更加简洁，因为需要循环的是整个数组，因此不需要再提供循环的范围了。
	 * 当然也可以说这失去了某些灵活性，但是这种所谓的灵活性在大多数情况下是没有什么必要的。
	 * 需要注意的是，下面这个方法是基于NamedParameterJdbcTemplate的，而上面的方法是基于基本的JdbcTemplate的。
	 * 如果sql的参数的占位符(placeholder)是传统的?（问号）,就必须保证传入的参数的位置和sql中的参数的位置一一对应。
	 * @param userList
	 * @return
	 */
	public int[] batchUpdate2(final List<User> userList){
		// TODO 13.4.2 Batch operations with a List of objects
		//SqlParameterSource用来封装命名式的sql参数（即命名式占位符），用来作为NamedParameterJdbcTemplate类中的各个方法的参数
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());//这里的SqlParameterSource也可以用以String为key的Map代替
		int[] updateCounts = new NamedParameterJdbcTemplate(getDataSource()).batchUpdate(" update gltuser set username = :username where id = :id", batch);
		return updateCounts;
	}
	
	/**
	 * 需要特别注意的是，这里的update并不仅仅包括update操作，也包括inset和delete（即不包括select操作）。
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void batchUpdate_insert(){
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("username", "gaolong");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("username", "maying");
		new NamedParameterJdbcTemplate(getDataSource()).batchUpdate(" insert into gltuser (username) values ( :username ) ", new Map[]{map1, map2});
	}
	
	/**
	 * 如果需要批量执行的sql太多，想要分批次执行，比如以100条为一批次执行，可以使用这个方法。
	 * 问题是，JdbcTemplate 在批量执行sql的时候，不会做一下优化，而是直接把所有的sql都一下子执行吗？
	 * 数据库没有相关的优化吗？为什么要把这种事情留给编写业务代码的程序员来做？
	 */
	public void batchUpdate3(){
		// TODO 
		getJdbcTemplate().batchUpdate("sql", null, 100, null);
	}
	
	public Integer getUser(int id){
		return getJdbcTemplate().queryForObject("select id from gltuser where id = ? ", Integer.class, id);
	}
	
	public List<User> getUsers(){
		String sql = "SELECT * FROM gltuser ";
		return getJdbcTemplate().query(sql, new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				return user;
			}
			
		});
	}
	
	public void insertByHibernate(HibernateTemplate hibernateTemplate){
		User user = new User();
		user.setUsername("hibernate");
		hibernateTemplate.update(user);
	}
}

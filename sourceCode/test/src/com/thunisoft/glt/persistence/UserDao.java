package com.thunisoft.glt.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

//@Repository("userDao")
public class UserDao extends BaseDao{

	private static final Log logger = LogFactory.getLog(UserDao.class);
	
	/**
	 * �Ի�����jdbc�ķ�ʽ�����ݿ��в���ֵ
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
	 * ʹ��JdbcTemplate�����ݿ��в���ֵ
	 * @param username
	 * @return
	 */
	public int jdbcTemplateInsert(String username){
		String sql = "INSERT INTO gltuser(username)  VALUES ('" + username + "')";
		this.getJdbcTemplate().update(sql);
		return 0;
	}

	/**
	 * �����ݿ��в������ݲ������Զ�����������
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
				ps.setString(1, username);// �����ֵ��1 ��ʼ
				return ps;
			}
		}, keyHolder);

		return ((Long)keyHolder.getKey()).intValue();// ��ס����д�� Long �� int
	}
	
	/**
	 * �������²���
	 */
	// TODO �������²���
	public void batchUpdate(){
//		getJdbcTemplate().batchUpdate
	}
	
}

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
	 * ʵ�������ԣ�batcheUpdate �� update ��Ч����һ����
	 */
	public void batchUpdate(String username){
		String sql = " update gltuser set username = '" + username + "' ";
//		getJdbcTemplate().batchUpdate(sql);
		getJdbcTemplate().update(sql);
	}
	
	/**
	 * ����������˼·�ǣ�����Ҫ�Ĳ�����װΪһ������ͨ��������һ�η��ʵ����ݽṹ������������ڲ�������Ҫ�����������ֱ�ָ����Ҫ�����������ķ�Χ����ʹ���������������ݵķ�ʽ
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
	 * ���ʹ��BatchPreparedStatementSetter�ķ�ʽ���������ַ�ʽ���Ӽ�࣬��Ϊ��Ҫѭ�������������飬��˲���Ҫ���ṩѭ���ķ�Χ�ˡ�
	 * ��ȻҲ����˵��ʧȥ��ĳЩ����ԣ�����������ν��������ڴ�����������û��ʲô��Ҫ�ġ�
	 * ��Ҫע����ǣ�������������ǻ���NamedParameterJdbcTemplate�ģ�������ķ����ǻ��ڻ�����JdbcTemplate�ġ�
	 * ���sql�Ĳ�����ռλ��(placeholder)�Ǵ�ͳ��?���ʺţ�,�ͱ��뱣֤����Ĳ�����λ�ú�sql�еĲ�����λ��һһ��Ӧ��
	 * @param userList
	 * @return
	 */
	public int[] batchUpdate2(final List<User> userList){
		// TODO 13.4.2 Batch operations with a List of objects
		//SqlParameterSource������װ����ʽ��sql������������ʽռλ������������ΪNamedParameterJdbcTemplate���еĸ��������Ĳ���
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());//�����SqlParameterSourceҲ��������StringΪkey��Map����
		int[] updateCounts = new NamedParameterJdbcTemplate(getDataSource()).batchUpdate(" update gltuser set username = :username where id = :id", batch);
		return updateCounts;
	}
	
	/**
	 * ��Ҫ�ر�ע����ǣ������update������������update������Ҳ����inset��delete����������select��������
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
	 * �����Ҫ����ִ�е�sql̫�࣬��Ҫ������ִ�У�������100��Ϊһ����ִ�У�����ʹ�����������
	 * �����ǣ�JdbcTemplate ������ִ��sql��ʱ�򣬲�����һ���Ż�������ֱ�Ӱ����е�sql��һ����ִ����
	 * ���ݿ�û����ص��Ż���ΪʲôҪ����������������дҵ�����ĳ���Ա������
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

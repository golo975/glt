package com.thunisoft.glt.spring.aop.book.advice;

import java.sql.SQLException;

public class ForumService {
	public void removeForum(int forumId) {
		// bean sth...
		throw new RuntimeException("�����쳣��");
	}
	public void updateForum(Forum forum) throws Exception{
		// bean sth...
		throw new SQLException("���ݸ��²����쳣��");
		
	}
}

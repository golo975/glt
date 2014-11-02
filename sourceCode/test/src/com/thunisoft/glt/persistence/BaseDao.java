package com.thunisoft.glt.persistence;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * dao层的基类。
 * 直接继承 spring的JdbcDaoSupport 的类不支持以注解的方式装载bean，所以在这里写一个基类，然后所有的Dao类都继承这个类
 * 这个bean在spring中的配置文件中定义，其他的Dao层的bean以注解的方式定义。
 * XXX 这里可能有我理解不到位的地方
 * FIXME 这里弄错了，即使继承了BaseDao，如果不在配置文件里配置的话，还是会报错说找不到DateSource或者JdbcTemplate。
 * @author gaolong
 *
 */
public class BaseDao extends JdbcDaoSupport {

}

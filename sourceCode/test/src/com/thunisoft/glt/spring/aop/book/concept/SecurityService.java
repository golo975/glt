package com.thunisoft.glt.spring.aop.book.concept;

public interface SecurityService {
    boolean checkAccess(User user,String service);
}

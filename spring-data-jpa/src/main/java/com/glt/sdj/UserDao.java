package com.glt.sdj;

import org.springframework.data.repository.Repository;

public interface UserDao extends Repository {
    void save(User user);
}

package com.glt.sdj.dao;

import com.glt.sdj.model.DO.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<Tag, Long> {
}

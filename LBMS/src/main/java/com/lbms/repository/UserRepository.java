package com.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lbms.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{

}

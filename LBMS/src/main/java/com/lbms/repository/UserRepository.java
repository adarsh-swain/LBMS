package com.lbms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lbms.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{
	

//SELECT *
//FROM users 
//INNER JOIN book_transactions 
//ON users.id = book_transactions.user_id 
//WHERE users.roles = 'ROLE_STUDENT';
//	 List<UserInfo> findByRoles(String roles);
	
	@Query(value = "SELECT u.* FROM users u " + "INNER JOIN book_transactions bt ON u.id = bt.user_id "
			+ "WHERE u.roles = :role", nativeQuery = true)
	List<UserInfo> findUsersByRole(@Param("role") String role);
	
	
	@Query(value = "SELECT * FROM users WHERE roles = :role", nativeQuery = true)
	List<UserInfo> findByRole(@Param("role") String role);

	Optional<UserInfo>findByEmail(String email);

}

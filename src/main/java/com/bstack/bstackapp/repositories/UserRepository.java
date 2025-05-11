package com.bstack.bstackapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bstack.bstackapp.models.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
	
	public Optional<UserAccount> findUserByUsername(String username);

	public Optional<UserAccount> findUserById(long id);

}

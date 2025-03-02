package com.sarthak.spring.mockito.api.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sarthak.spring.mockito.api.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	List<User> findByAddress(String address);

}

package com.javatechie.spring.mockito.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sarthak.spring.mockito.api.dao.UserRepository;
import com.sarthak.spring.mockito.api.model.User;
import com.sarthak.spring.mockito.api.service.UserService;

@RunWith(SpringRunner.class) // Indicates that the test class should run with SpringRunner
@SpringBootTest // Initializes the Spring application context for the test
public class SpringBootMockitoApplicationTests {

	@Autowired // Injects the UserService bean into the test class
	private UserService service;

	@MockBean // Creates a mock for the UserRepository bean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		// Mocking the UserRepository's findAll method to return a list of users
		when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK"))
				.collect(Collectors.toList()));
		// Testing the getUsers method of the UserService and verifying the result
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		// Mocking the UserRepository's findByAddress method to return a list of users
		// with the given address
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		// Testing the getUserbyAddress method of the UserService and verifying the
		// result
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		// Mocking the UserRepository's save method to return the saved user
		when(repository.save(user)).thenReturn(user);
		// Testing the addUser method of the UserService and verifying the result
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		// Testing the deleteUser method of the UserService and verifying that
		// UserRepository's delete method is called once
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}

}

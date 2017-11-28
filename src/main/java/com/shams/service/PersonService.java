package com.shams.service;

import java.util.List;

import com.shams.model.Person;

public interface PersonService {

	public List<Person> getAll();
	public Person get( Integer id );
	public void add(Person person);
	public void delete(Integer id);
	public void edit(Person person);
}

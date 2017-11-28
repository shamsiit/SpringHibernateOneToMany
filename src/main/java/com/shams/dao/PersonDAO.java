package com.shams.dao;

import java.util.List;

import com.shams.model.Person;

public interface PersonDAO {

	public List<Person> getAll();
	public Person get( Integer id );
	public void add(Person person);
	public void delete(Integer id);
	public void edit(Person person);
	
}

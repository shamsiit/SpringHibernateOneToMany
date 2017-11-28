package com.shams.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shams.dao.PersonDAO;
import com.shams.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDAO personDao;
	
	@Override
	@Transactional
	public List<Person> getAll() {
		return personDao.getAll();
	}

	@Override
	@Transactional
	public Person get(Integer id) {
		
		return personDao.get(id);
	}

	@Override
	@Transactional
	public void add(Person person) {
		
		personDao.add(person);
		
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		personDao.delete(id);
		
	}

	@Override
	@Transactional
	public void edit(Person person) {
		
		personDao.edit(person);
		
	}

}

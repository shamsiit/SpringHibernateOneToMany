package com.shams.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shams.model.CreditCard;
import com.shams.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Person> getAll() {

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Person");
		
		List<Person> list = query.list();

		// Retrieve all
		return list;
	}

	@Override
	public Person get(Integer id) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Person as p LEFT JOIN FETCH  p.creditCards WHERE p.id=" + id);

		return (Person) query.uniqueResult();
	}

	@Override
	public void add(Person person) {


		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Persists to db
		session.save(person);

	}

	@Override
	public void delete(Integer id) {

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Person as p LEFT JOIN FETCH  p.creditCards WHERE p.id=" + id);

		// Retrieve record
		Person person = (Person) query.uniqueResult();

		Set<CreditCard> creditCards = person.getCreditCards();

		// Delete person
		session.delete(person);

		// Delete associated credit cards
		for (CreditCard creditCard : creditCards) {
			session.delete(creditCard);
		}

	}

	@Override
	public void edit(Person person) {


		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person via id
		Person existingPerson = (Person) session.get(Person.class, person.getId());

		// Assign updated values to this person
		existingPerson.setFirstName(person.getFirstName());
		existingPerson.setLastName(person.getLastName());
		existingPerson.setMoney(person.getMoney());

		// Save updates
		session.save(existingPerson);

	}

}

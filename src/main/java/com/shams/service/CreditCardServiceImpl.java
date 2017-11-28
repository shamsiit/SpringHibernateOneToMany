package com.shams.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shams.dao.CreditCardDAO;
import com.shams.model.CreditCard;

@Service
public class CreditCardServiceImpl implements CreditCardService{

	@Autowired
	CreditCardDAO creditCardDao;
	
	@Override
	@Transactional
	public List<CreditCard> getAll(Integer personId) {
		
		return creditCardDao.getAll(personId);
	}

	@Override
	@Transactional
	public List<CreditCard> getAll() {
		
		return creditCardDao.getAll();
	}

	@Override
	@Transactional
	public CreditCard get(Integer id) {
		
		return creditCardDao.get(id);
	}

	@Override
	@Transactional
	public void add(Integer personId, CreditCard creditCard) {
		
		creditCardDao.add(personId, creditCard);
		
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		creditCardDao.delete(id);
		
	}

	@Override
	@Transactional
	public void edit(CreditCard creditCard) {
		
		creditCardDao.edit(creditCard);
		
	}

}

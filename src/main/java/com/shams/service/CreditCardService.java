package com.shams.service;

import java.util.List;

import com.shams.model.CreditCard;

public interface CreditCardService {

	public List<CreditCard> getAll(Integer personId);
	public List<CreditCard> getAll();
	public CreditCard get( Integer id );
	public void add(Integer personId, CreditCard creditCard);
	public void delete(Integer id);
	public void edit(CreditCard creditCard);
}

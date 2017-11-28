package com.shams.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shams.model.Person;
import com.shams.model.PersonDTO;
import com.shams.service.CreditCardService;
import com.shams.service.PersonService;

@Controller
@RequestMapping("/main/record")
public class MainController {


	@Autowired
	private PersonService personService;

	@Autowired
	private CreditCardService creditCardService;

	/**
	 * Retrieves the "Records" page
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getRecords(Model model) {

		// Retrieve all persons
		List<Person> persons = personService.getAll();

		// Prepare model object
		List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();

		for (Person person : persons) {
			// Create new data transfer object
			PersonDTO dto = new PersonDTO();

			dto.setId(person.getId());
			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			dto.setMoney(person.getMoney());
			dto.setCreditCards(creditCardService.getAll(person.getId()));

			// Add to model list
			personsDTO.add(dto);
		}

		// Add to model
		model.addAttribute("persons", personsDTO);

		// This will resolve to /WEB-INF/jsp/records.jsp
		return "records";
	}

	/**
	 * Retrieves the "Add New Record" page
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd(Model model) {

		// Create new Person and add to model
		model.addAttribute("personAttribute", new Person());

		// This will resolve to /WEB-INF/jsp/add-record.jsp
		return "add-record";
	}

	/**
	 * Adds a new record
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAdd(@ModelAttribute("personAttribute") Person person) {

		// Delegate to service
		personService.add(person);

		// Redirect to url
		return "redirect:/main/record/list";
	}

	/**
	 * Deletes a record including all the associated credit cards
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("id") Integer personId) {

		// Delete person
		personService.delete(personId);

		// Redirect to url
		return "redirect:/main/record/list";
	}

	/**
	 * Retrieves the "Edit Existing Record" page
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam("id") Integer personId, Model model) {

		// Retrieve person by id
		Person existingPerson = personService.get(personId);

		// Add to model
		model.addAttribute("personAttribute", existingPerson);

		// This will resolve to /WEB-INF/jsp/edit-record.jsp
		return "edit-record";
	}

	/**
	 * Edits an existing record
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String postEdit(@RequestParam("id") Integer personId, @ModelAttribute("personAttribute") Person person) {

		// Assign id
		person.setId(personId);

		// Delegate to service
		personService.edit(person);

		// Redirect to url
		return "redirect:/main/record/list";
	}

}

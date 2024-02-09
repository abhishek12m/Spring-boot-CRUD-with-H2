package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Person;
import com.example.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> getAllPerson() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	public Optional<Person> getPersonById(Long id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}

	public Person personSave(Person person) {
		// TODO Auto-generated method stub
		return personRepository.save(person);
	}

	public Optional<Person> updatePerson(Person updateDetails, Long id) {
		// TODO Auto-generated method stub
		Optional<Person> optional = personRepository.findById(id);

		if (optional.isPresent()) {
			Person existingPerson = optional.get();
			existingPerson.setFirstName(updateDetails.getFirstName());
			existingPerson.setLastName(updateDetails.getLastName());
			existingPerson.setAge(updateDetails.getAge());

			Person savedPerson = personRepository.save(existingPerson);

			return Optional.of(savedPerson);
		} else {
			return Optional.empty();
		}
	}

	public void deletePerson(Long id) {
		// TODO Auto-generated method stub
		personRepository.deleteById(id);

	}

}

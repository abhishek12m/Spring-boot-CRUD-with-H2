package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;
import com.example.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/getAll")
	public List<Person> getAll() {
		return personService.getAllPerson();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		Optional<Person> person = personService.getPersonById(id);

		return person.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<Person> save(@RequestBody Person person) {
		Person savedPerson = personService.personSave(person);
		return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
		return new ResponseEntity<>("Person deleted with id " + id, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person updateDetails, @PathVariable Long id) {
		Optional<Person> updated = personService.updatePerson(updateDetails, id);
		return updated.map(val -> new ResponseEntity<>(val, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

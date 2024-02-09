package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}

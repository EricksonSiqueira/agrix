package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.person.PersonDto;
import com.betrybe.agrix.dto.person.PersonResponse;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person response entity.
   *
   * @param personDto the person dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonDto personDto) {

    Person person = personDto.toEntity();
    Person newPerson = personService.insert(person);

    PersonResponse personResponse = new PersonResponse(
        newPerson.getId(),
        newPerson.getUsername(),
        newPerson.getRole()
    );

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(personResponse);
  }

}

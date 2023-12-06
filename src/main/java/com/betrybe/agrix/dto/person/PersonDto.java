package com.betrybe.agrix.dto.person;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;
import java.util.List;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, String password, Role role) {

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    return new Person(id, username, password, role);
  }
}

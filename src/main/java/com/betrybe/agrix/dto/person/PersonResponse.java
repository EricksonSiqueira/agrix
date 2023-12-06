package com.betrybe.agrix.dto.person;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

public record PersonResponse(Long id, String username, Role role) { }

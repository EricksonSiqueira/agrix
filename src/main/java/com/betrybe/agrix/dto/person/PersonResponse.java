package com.betrybe.agrix.dto.person;

import com.betrybe.agrix.security.Role;

/**
 * The type Person response.
 */
public record PersonResponse(Long id, String username, Role role) { }

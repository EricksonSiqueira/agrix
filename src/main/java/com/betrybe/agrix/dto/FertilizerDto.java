package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * To fertilizer fertilizer.
   *
   * @return the fertilizer
   */
  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition, null);
  }
}

package com.betrybe.agrix.dto.crop;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate) {

  /**
   * To crop crop.
   *
   * @return the crop
   */
  public Crop toCrop() {
    return new Crop(id, name, plantedArea, harvestDate, plantedDate, null, null);
  }
}

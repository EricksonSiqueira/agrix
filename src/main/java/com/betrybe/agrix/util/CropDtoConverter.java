package com.betrybe.agrix.util;

import com.betrybe.agrix.dto.crop.CropResponse;
import com.betrybe.agrix.models.entities.Crop;

/**
 * The type Crop dto converter.
 */
public class CropDtoConverter {

  /**
   * Crop to crop response crop response.
   *
   * @param crop the crop
   * @return the crop response
   */
  public static CropResponse cropToCropResponse(Crop crop) {
    return new CropResponse(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getHarvestDate(),
        crop.getPlantedDate()
    );
  }

}

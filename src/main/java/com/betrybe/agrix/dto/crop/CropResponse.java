package com.betrybe.agrix.dto.crop;

import java.time.LocalDate;

/**
 * The type Crop response.
 */
public record CropResponse(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate harvestDate,
    LocalDate plantedDate) {}

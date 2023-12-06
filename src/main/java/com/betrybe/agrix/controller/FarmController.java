package com.betrybe.agrix.controller;


import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.dto.crop.CropDto;
import com.betrybe.agrix.dto.crop.CropResponse;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.util.CropDtoConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  @Secured({"USER", "MANAGER", "ADMIN"})
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return ResponseEntity.ok(allFarms);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    return ResponseEntity.ok(farm);
  }

  /**
   * Insert crop response entity.
   *
   * @param farmId  the farm id
   * @param cropDto the crop dto
   * @return the response entity
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropResponse> insertCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto
  ) {
    Crop newCrop = farmService.insertCrop(farmId, cropDto.toCrop());
    CropResponse cropResponse = new CropResponse(
        newCrop.getId(),
        newCrop.getName(),
        newCrop.getPlantedArea(),
        newCrop.getFarm().getId(),
        newCrop.getHarvestDate(),
        newCrop.getPlantedDate()
        );

    return ResponseEntity.status(HttpStatus.CREATED).body(cropResponse);
  }

  @GetMapping("/{farmId}/crops")
  private ResponseEntity<List<CropResponse>> getAllCrops(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getAllCrops(farmId);

    List<CropResponse> cropResponses = crops.stream()
        .map(CropDtoConverter::cropToCropResponse)
        .toList();

    return ResponseEntity.ok(cropResponses);
  }
}

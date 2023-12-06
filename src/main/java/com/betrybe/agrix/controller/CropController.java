package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.crop.CropResponse;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.util.CropDtoConverter;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public ResponseEntity<List<CropResponse>> getAllCrops() {
    List<CropResponse> cropResponses = cropService.getAllCrops()
        .stream().map(CropDtoConverter::cropToCropResponse)
        .toList();

    return ResponseEntity.ok(cropResponses);
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropResponse> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);
    CropResponse cropResponse = CropDtoConverter.cropToCropResponse(crop);

    return ResponseEntity.ok(cropResponse);
  }

  /**
   * Gets crop by period.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by period
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropResponse>> getCropByPeriod(
      @RequestParam LocalDate start, @RequestParam LocalDate end) {
    List<Crop> crops = cropService.getCropByPeriod(start, end);
    List<CropResponse> cropsResponse = crops.stream()
        .map(CropDtoConverter::cropToCropResponse).toList();

    return ResponseEntity.ok(cropsResponse);
  }

  /**
   * Sets fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the fertilizer
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> setFertilizer(
      @PathVariable Long cropId, @PathVariable Long fertilizerId) {
    cropService.setFertilizer(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets all fertilizers.
   *
   * @param cropId the crop id
   * @return the all fertilizers
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getAllFertilizers(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getAllFertilizers(cropId);

    return ResponseEntity.ok(fertilizers);
  }
}

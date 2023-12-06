package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer response entity.
   *
   * @param fertilizerDto the fertilizer dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService
        .insertFertilizer(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  @Secured("ADMIN")
  public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
    return ResponseEntity.ok(fertilizerService.getAllFertilizers());
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Fertilizer> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(id);

    return ResponseEntity.ok(fertilizer);
  }
}

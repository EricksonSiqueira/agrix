package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  /**
   * The Crop repository.
   */
  CropRepository cropRepository;

  /**
   * The Fertilizer repository.
   */
  FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  public Crop getCropById(Long id) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return optionalCrop.get();
  }

  /**
   * Gets crop by period.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by period
   */
  public List<Crop> getCropByPeriod(LocalDate start, LocalDate end) {
    return cropRepository.getCropByPeriod(start, end);
  }

  /**
   * Sets fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  public void setFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerRepository
        .findById(fertilizerId);

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
  }

  /**
   * Gets all fertilizers.
   *
   * @param cropId the crop id
   * @return the all fertilizers
   */
  public List<Fertilizer> getAllFertilizers(Long cropId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Crop crop = optionalCrop.get();

    return crop.getFertilizers();
  }
}

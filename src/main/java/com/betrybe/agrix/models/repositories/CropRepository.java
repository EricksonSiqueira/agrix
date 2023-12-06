package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  /**
   * Gets crop by period.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by period
   */
  @Query(value = "SELECT DISTINCT * FROM crop "
      + "WHERE crop.harvest_date >= :start AND crop.harvest_date <= :end",
      nativeQuery = true)
  List<Crop> getCropByPeriod(@Param("start") LocalDate start, @Param("end") LocalDate end);

}

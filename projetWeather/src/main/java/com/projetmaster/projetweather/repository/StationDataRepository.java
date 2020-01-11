package com.projetmaster.projetweather.repository;

import com.projetmaster.projetweather.model.StationMeasuredData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationDataRepository extends ElasticsearchCrudRepository<StationMeasuredData, String> {
    List<StationMeasuredData> findByStationIdAndMeasureDateBetweenOrderByMeasureDateDesc(String fishId, Long startTime, Long endTime, Pageable pageable);

    void deleteAllByStationId(String stationId);
}

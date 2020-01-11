package com.projetmaster.projetweather.repository;

import com.projetmaster.projetweather.model.Station;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends ElasticsearchCrudRepository<Station, String> {
}

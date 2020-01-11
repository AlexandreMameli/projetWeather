package com.projetmaster.projetweather.api;

import com.projetmaster.projetweather.api.model.Measure;
import com.projetmaster.projetweather.model.Station;
import com.projetmaster.projetweather.model.StationMeasuredData;
import com.projetmaster.projetweather.repository.StationDataRepository;
import com.projetmaster.projetweather.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Validated
@RequestMapping("/api/station")
public class StationApi {

    private final StationRepository stationRepository;
    private final StationDataRepository stationDataRepository;

    @Autowired
    public StationApi(StationRepository stationRepository, StationDataRepository stationDataRepository){
        this.stationRepository = stationRepository;
        this.stationDataRepository = stationDataRepository;
    }

    @GetMapping
    public List<Station> findAllStations() {
        return StreamSupport.stream(stationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public Station findStationById(@PathVariable("id") @NotNull String stationId) {
        return findStation(stationId);
    }

    @PostMapping
    public Station createStation(@RequestBody @NotNull @Valid final Station station) {
        Station s = stationRepository.save(station);
        return s;
    }

    @PutMapping
    public Station updateStation(@RequestBody @NotNull @Valid final Station station) {
        return createStation(station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable("id") @NotNull final String stationId) {
        stationRepository.delete(findStation(stationId));
        stationDataRepository.deleteAllByStationId(stationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{stationId}/measure")
    public List<StationMeasuredData> findLastFiveMeasures(@PathVariable("stationId") @NotNull String stationId) {
        LocalDate now = LocalDate.now();
        Instant start = now.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = now.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        return stationDataRepository.findByStationIdAndMeasureDateBetweenOrderByMeasureDateDesc(stationId, start.toEpochMilli(),
                end.toEpochMilli(), PageRequest.of(0, 5));
    }

    @PostMapping("/{stationId}/measure")
    public StationMeasuredData addStationMeasure(@PathVariable("stationId") @NotNull String stationId, @RequestBody @NotNull @Valid Measure measure) {
        return stationDataRepository.save(new StationMeasuredData(stationId, new Date(), measure.getHumidity(), measure.getTemperature(), measure.getAtmPressure()));
    }


    private Station findStation(String id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

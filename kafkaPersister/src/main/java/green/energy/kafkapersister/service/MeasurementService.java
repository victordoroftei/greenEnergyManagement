package green.energy.kafkapersister.service;

import green.energy.kafkapersister.data.MeasurementRepository;
import green.energy.kafkapersister.data.entity.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public void addMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}

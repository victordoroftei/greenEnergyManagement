package green.energy.kafkapersister.data;

import green.energy.kafkapersister.data.entity.Measurement;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement, String> {
}

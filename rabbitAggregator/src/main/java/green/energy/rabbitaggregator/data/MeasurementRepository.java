package green.energy.rabbitaggregator.data;

import green.energy.rabbitaggregator.data.entity.Measurement;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement, String> {
}

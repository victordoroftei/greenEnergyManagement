package green.energy.rabbitaggregator.data;

import green.energy.rabbitaggregator.data.entity.Summary;
import green.energy.rabbitaggregator.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SummaryRepository extends CrudRepository<Summary, String> {

    Optional<Summary> findSummaryByUser(User user);
}

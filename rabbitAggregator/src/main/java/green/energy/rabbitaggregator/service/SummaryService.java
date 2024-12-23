package green.energy.rabbitaggregator.service;

import green.energy.rabbitaggregator.data.entity.Summary;
import green.energy.rabbitaggregator.data.SummaryRepository;
import green.energy.rabbitaggregator.data.entity.User;
import green.energy.rabbitaggregator.model.exception.SummaryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SummaryService {

    private final SummaryRepository summaryRepository;

    public Summary getSummaryByUser(User user) {
        Optional<Summary> summaryOptional = summaryRepository.findSummaryByUser(user);
        summaryOptional.orElseThrow(() -> new SummaryNotFoundException("No summary found for user: %s".formatted(user.getUsername())));

        return summaryOptional.get();
    }

    public void updateSummary(Summary summary) {
        summaryRepository.save(summary);
    }
}

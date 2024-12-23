package green.energy.rabbitaggregator.service;

import green.energy.rabbitaggregator.data.entity.Summary;
import green.energy.rabbitaggregator.data.entity.User;
import green.energy.rabbitaggregator.model.MeasurementMessage;
import green.energy.rabbitaggregator.model.exception.SummaryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementSummaryService {

    private final UserService userService;

    private final SummaryService summaryService;

    public void processMessage(MeasurementMessage message) {
        String username = message.getUsername();

        User user = userService.getUserByUsername(username);

        Summary summary;
        try {
            summary = summaryService.getSummaryByUser(user);
        } catch (SummaryNotFoundException e) {
            summary = new Summary();
            summary.setUser(user);
        }

        updateSummaryFields(summary, message);

        summaryService.updateSummary(summary);
    }

    private void updateSummaryFields(Summary summary, MeasurementMessage message) {
        long oldConsumption = summary.getLifetimeConsumption() != null ? summary.getLifetimeConsumption() : 0;
        long oldProduction = summary.getLifetimeProduction() != null ? summary.getLifetimeProduction() : 0;

        long currentQuantity = message.getQuantity();
        if (currentQuantity <= 0) {
            oldConsumption += currentQuantity;
        } else {
            oldProduction += (-1) * currentQuantity;
        }

        summary.setLifetimeConsumption(oldConsumption);
        summary.setLifetimeProduction(oldProduction);
        summary.setHoursActive(summary.getHoursActive() != null ? summary.getHoursActive() + 1 : 0);
    }
}

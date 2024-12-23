package green.energy.rabbitaggregator.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "summaries")
@TypeAlias("Summary")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

    @Id
    private String id;

    @DBRef
    private User user;

    private Long lifetimeConsumption;

    private Long lifetimeProduction;

    private Integer hoursActive;
}

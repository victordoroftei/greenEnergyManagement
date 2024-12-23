package green.energy.rabbitaggregator.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "measurements")
@TypeAlias("Measurement")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    @Id
    private String id;

    @DBRef
    private User user;

    private Long quantity;

    private LocalDateTime beginTimestamp;

    private LocalDateTime endTimestamp;
}

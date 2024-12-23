package green.energy.rabbitaggregator.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@TypeAlias("User")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
}

package ca.erik.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private Long tgId;
    private String firstName;
    private String lastName;
    private String username;
    private String langCode;
    private Boolean isBot;
    private Date created;
}

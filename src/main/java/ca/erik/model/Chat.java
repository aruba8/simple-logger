package ca.erik.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "chat")
public class Chat {
    @Id
    private String id;
    private Long tgId;
    private String type;
    private String title;
    private String userName;

}

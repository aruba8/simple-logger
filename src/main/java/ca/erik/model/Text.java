package ca.erik.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "text")
public class Text {

    @Id
    private String id;
    private Integer messageId;
    private Long chatId;
    private Chat chat;
    private Long fromUserId;
    private String text;
    private Date date;
    private Text replyToText;
    private User fromUser;

}

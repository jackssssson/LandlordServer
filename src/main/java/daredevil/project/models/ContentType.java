package daredevil.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "content_types")
public class ContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_TypeID")
    private int id;

    @Column(name = "content_Type", nullable = false)
    private String contentType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "content_types")
    private List<MessageContent> message_content;

    public ContentType(String contentType, List<MessageContent> message_content) {
        this.contentType = contentType;
        this.message_content = message_content;
    }

    public ContentType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<MessageContent> getMessage_content() {
        return message_content;
    }

    public void setMessage_content(List<MessageContent> message_content) {
        this.message_content = message_content;
    }
}

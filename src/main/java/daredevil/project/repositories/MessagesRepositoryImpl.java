package daredevil.project.repositories;

import daredevil.project.models.MessageContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessagesRepositoryImpl implements MessageContentRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public MessagesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void createMessageContent(MessageContent messageContent) {

    }

    @Override
    public MessageContent getMessageContentId(int id) {
        return null;
    }

    @Override
    public void updateMessageContent(int id, MessageContent messageContent) {

    }

    @Override
    public void deleteMessageContent(int id) {

    }
}

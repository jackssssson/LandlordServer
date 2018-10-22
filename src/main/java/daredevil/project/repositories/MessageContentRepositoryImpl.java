package daredevil.project.repositories;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.models.MessageContent;
import daredevil.project.repositories.base.MessageContentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageContentRepositoryImpl implements MessageContentRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public MessageContentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void createMessageContent(MessageContent messageContent) throws CantCreateMessageContentException {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(messageContent);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "messageContent");
            throw new CantCreateMessageContentException();
        }
    }

    @Override
    public MessageContent getMessageContentId(int id) {
        MessageContent result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(MessageContent.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateMessageContent(int id, MessageContent messageContent) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            MessageContent massageContentToChange = session.get(MessageContent.class, id);

            massageContentToChange.setImage(messageContent.getImage());
            massageContentToChange.setTextMessage(messageContent.getTextMessage());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMessageContent(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getMessageContentId(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

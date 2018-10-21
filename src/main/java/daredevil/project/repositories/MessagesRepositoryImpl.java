package daredevil.project.repositories;

import daredevil.project.models.Messages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public MessagesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void createMessages(Messages messages) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(messages);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "messages");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Messages getMessagesById(int id) {
        Messages result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(Messages.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateMessages(int id, Messages messages) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            Messages messageToChange = session.get(Messages.class, id);

            messageToChange.setTimeStamp(messages.getTimeStamp());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMessages(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getMessagesById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

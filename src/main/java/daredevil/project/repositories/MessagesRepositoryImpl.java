package daredevil.project.repositories;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.models.Messages;
import daredevil.project.repositories.base.MessagesRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository {
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

    @Override
    public List<Messages> getMessageByUserName(String userName) {
        List<Messages> result=new ArrayList<>();
        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.createQuery("from Messages where sender in(from User where name = :userNameStr) ").setParameter("userNameStr", userName).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void postMesssage(Messages messages) throws CantCreateMessageException {
        try (Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.save(messages);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new CantCreateMessageException();
        }
    }

//    @Override
//    public boolean checkForNewMessagess(int id){
//        try(Session session=sessionFactory.openSession()){
//            session.beginTransaction();
//            Messages messages=session.createQuery("from Messages where ")
//        }
//    }
}

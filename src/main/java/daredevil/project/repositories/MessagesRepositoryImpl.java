package daredevil.project.repositories;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.NoNewMessagesEception;
import daredevil.project.models.DTO.MessagesDTO;
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
            messageToChange.setSeen(messages.isSeen());
            messageToChange.setImageMessage(messages.getImageMessage());
            messageToChange.setTextMessage(messages.getTextMessage());
            messageToChange.setRecipient(messages.getRecipient());
            messageToChange.setSender(messages.getSender());

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

    @Override
    public boolean checkForNewMessagess(int sender, int recipient){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<Messages> messages=session.createQuery("from Messages where sender in(from User where id=:senderID) and recipient in(from User where id=:recipientID) and seen=false and estates is NULL", Messages.class).setParameter("senderID", sender).setParameter("recipientID", recipient).list();
            if(messages.size()==0)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Messages> getNewMessagess(int sender, int recipient) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Messages> messages = session.createQuery("from Messages where sender in(from User where id=:senderID) and recipient in(from User where id=:recipientID) and seen=false and estates is NULL", Messages.class).setParameter("senderID", sender).setParameter("recipientID", recipient).list();
            return messages;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean checkForMessages(int sender, int recipient) {
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<Messages> messages=session.createQuery("from Messages where sender in(from User where id=:senderID or id=:recipientID) and recipient in(from User where id=:recipientID or id=:senderID) and estates is NULL", Messages.class).setParameter("senderID", sender).setParameter("recipientID", recipient).list();
            if(messages.size()==0)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Messages> getMessagess(int sender, int recipient) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Messages> messages = session.createQuery("from Messages where sender in(from User where id=:senderID or id=:recipientID) and recipient in(from User where id=:recipientID or id=:senderID) and estates is NULL order by timeStamp", Messages.class).setParameter("senderID", sender).setParameter("recipientID", recipient).list();
            return messages;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Messages> getEstateMessages(int sender, int recipient, int estate){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<Messages> result=session.createQuery("from Messages where sender in(from User where id=:senderID or id=:recipientID) and recipient in(from User where id=:recipientID or id=:senderID) and estates in (from Estates where id=:estateID) order by timeStamp", Messages.class).setParameter("recipientID", recipient).setParameter("senderID", sender).setParameter("estateID", estate).list();
            return result;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean checkForEstateMessages(int sender, int recipient, int estate){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            List<Messages> result=session.createQuery("from Messages where sender in(from User where id=:senderID or id=:recipientID) and recipient in(from User where id=:recipientID or id=:senderID) and estates in (from Estates where id=:estateID) order by timeStamp", Messages.class).setParameter("recipientID", recipient).setParameter("senderID", sender).setParameter("estateID", estate).list();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Messages getImageMessageForSession(int recipient, int sender) throws NoNewMessagesEception {
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Messages messages=session.createQuery("from Messages where sender in(from User where id=:senderID or id=:recipientID) and recipient in(from User where id=:recipientID or id=:senderID) and estates is NULL and messageType like 'Image message'", Messages.class).setParameter("senderID", sender).setParameter("recipientID", recipient).getSingleResult();
            session.getTransaction().commit();
            return messages;
        } catch (Exception e){
            throw  new NoNewMessagesEception();
        }
    }
}

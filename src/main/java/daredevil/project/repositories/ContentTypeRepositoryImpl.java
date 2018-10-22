package daredevil.project.repositories;

import daredevil.project.models.ContentType;
import daredevil.project.models.Messages;
import daredevil.project.repositories.base.ContentTypeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentTypeRepositoryImpl implements ContentTypeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ContentTypeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createContentType(ContentType contentType) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(contentType);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "contentType");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ContentType getContentTypeById(int id) {
        ContentType result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(ContentType.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateContentType(int id, ContentType contentType) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            ContentType contentTypeToChange = session.get(ContentType.class, id);

            contentTypeToChange.setContentType(contentType.getContentType());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteContentType(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getContentTypeById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ContentType getContentTypeByName(String contentName) {
        ContentType result;
        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.createQuery("from ContentType where contentType = :contentNameStr", ContentType.class).setParameter("contentNameStr", contentName).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }
}

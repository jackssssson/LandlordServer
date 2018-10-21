package daredevil.project.repositories;

import daredevil.project.models.ContentType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentTypeRepositoryImpl implements ContentTypeRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public ContentTypeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createContentType(ContentType contentType) {

    }

    @Override
    public ContentType getContentTypeById(int id) {
        return null;
    }

    @Override
    public void updateContentType(int id, ContentType contentType) {

    }

    @Override
    public void deleteContentType(int id) {

    }
}

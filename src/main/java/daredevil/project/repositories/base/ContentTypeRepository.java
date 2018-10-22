package daredevil.project.repositories.base;

import daredevil.project.models.ContentType;
import daredevil.project.models.Messages;

import java.util.List;

public interface ContentTypeRepository {
    void createContentType(ContentType contentType);
    ContentType getContentTypeById(int id);
    void updateContentType(int id, ContentType contentType);
    void deleteContentType(int id);
    ContentType getContentTypeByName(String contentName);
}

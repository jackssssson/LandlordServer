package daredevil.project.repositories;

import daredevil.project.models.ContentType;

public interface ContentTypeRepository {
    void createContentType(ContentType contentType);
    ContentType getContentTypeById(int id);
    void updateContentType(int id, ContentType contentType);
    void deleteContentType(int id);
}

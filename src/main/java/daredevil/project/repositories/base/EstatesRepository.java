package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.models.Estates;

public interface EstatesRepository {
    void createEstate(Estates estate) throws CantCreateEstateException;
    Estates getEstateById(int id);
    void updateEstate(int id, Estates estate);
    void deleteEstate(int id);
    Estates getEstateByUserName(String name);
}

package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.models.Estates;

import java.util.List;

public interface EstatesRepository {
    void createEstate(Estates estate) throws CantCreateEstateException;
    Estates getEstateById(int id) throws NoEstateFoundException;
    void updateEstate(int id, Estates estate);
    void deleteEstate(int id);
    Estates getEstateByUserName(String name);
    List<Estates> getUnoccupiedEstates();
    void updatePrice(int id, Estates estate);
}

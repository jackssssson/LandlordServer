package daredevil.project.repositories;

import daredevil.project.models.Estates;

public interface EstatesRepository {
    void createEstate(Estates estate);
    Estates getEstateById(int id);
    void updateEstate(int id, Estates estate);
    void deleteEstate(int id);
    Estates getEstateByUserName(String name);
}

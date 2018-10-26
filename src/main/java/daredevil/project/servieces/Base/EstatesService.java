package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.models.DTO.EstateDTO;

public interface EstatesService {
    void createEstate(EstateDTO estateDTO, String name) throws CantCreateEstateException;
}

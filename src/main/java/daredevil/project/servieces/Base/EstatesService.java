package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.Estates;

import java.text.ParseException;
import java.util.List;

public interface EstatesService {
    void createEstate(EstateDTO estateDTO, String name) throws CantCreateEstateException, CantCreateUserException;
    void setDueDate(String date, int estateID) throws ParseException, NoEstateFoundException;
    Estates getEstateById(int id) throws NoEstateFoundException;
    void setOwed(int id, String owed) throws NoEstateFoundException;
    List<Estates> getUnoccupiedEstates();

}

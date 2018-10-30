package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.Estates;

import java.text.ParseException;
import java.util.List;

public interface EstatesService {
    void createEstate(EstateDTO estateDTO, String name) throws CantCreateEstateException, CantCreateUserException;
    public void setDueDate(String date, int estateID) throws ParseException;

}

package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.Base.EstatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class EstatesServiceImpl implements EstatesService {
    private EstatesRepository estatesRepository;
    private UserRepository userRepository;

    @Autowired
    public EstatesServiceImpl(EstatesRepository estatesRepository, UserRepository userRepository) {
        this.estatesRepository = estatesRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void createEstate(EstateDTO estateDTO, String name) throws CantCreateEstateException, CantCreateUserException {
        try {
            Estates estates=new Estates(estateDTO.getPrice(), estateDTO.getEstateName(), estateDTO.getAddress());
            estatesRepository.createEstate(estates);
            User user=userRepository.getUserByName(name);
            user.addEstate(estates);
            userRepository.updateUser(user.getId(), user);
        } catch (CantCreateEstateException e) {
            throw new CantCreateEstateException();
        } catch (CantCreateUserException e) {
            throw new CantCreateUserException();
        }

    }

    @Override
    public void setDueDate(String date, int estateID) throws ParseException {
        Estates estates=estatesRepository.getEstateById(estateID);
        estates.setDate(date);
        estatesRepository.updateEstate(estateID, estates);
    }


}

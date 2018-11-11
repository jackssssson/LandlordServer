package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
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
    public void createEstate(EstateDTO estateDTO, String name)
            throws CantCreateEstateException, CantCreateUserException {
        try {
            Estates estates=new Estates(estateDTO.getPrice(), estateDTO.getEstateName(), estateDTO.getAddress());
            User user=userRepository.getUserByName(name);
            estates.setLandlord(user);
            estatesRepository.createEstate(estates);
            user.addEstate(estates);
            userRepository.updateUser(user.getId(), user);
        } catch (CantCreateEstateException e) {
            throw new CantCreateEstateException();
        } catch (CantCreateUserException e) {
            throw new CantCreateUserException();
        }

    }

    @Override
    public void setDueDate(String date, int estateID)
            throws ParseException, NoEstateFoundException {
        Estates estates=estatesRepository.getEstateById(estateID);
        estates.setDate(date);
        estatesRepository.updateEstate(estateID, estates);
    }


    @Override
    public Estates getEstateById(int id) throws NoEstateFoundException {
         Estates estates=estatesRepository.getEstateById(id);
        return estates;
    }

    @Override
    public void setOwed(int id, String owed) throws NoEstateFoundException {
        Estates estates=estatesRepository.getEstateById(id);
        float floatOwed=Float.valueOf(owed);
        estates.setPrice(floatOwed);
        estatesRepository.updatePrice(id, estates);

    }

    @Override
    public List<Estates> getUnoccupiedEstates(){
        return estatesRepository.getUnoccupiedEstates();
    }


}

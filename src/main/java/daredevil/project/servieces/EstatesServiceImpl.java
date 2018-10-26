package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.Base.EstatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

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
    public void createEstate(EstateDTO estateDTO, String name) throws CantCreateEstateException {
        try {
            Estates estates=new Estates(estateDTO.getPrice(), estateDTO.getEstateName(), estateDTO.getDuedate(), estateDTO.getAddress());
            estatesRepository.createEstate(estates);
            User user=userRepository.getUserByName(name);
            user.addEstate(estates);
            userRepository.updateUser(user.getId(), user);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CantCreateEstateException e) {
            throw new CantCreateEstateException();
        }

    }
}

package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Estates;
import daredevil.project.servieces.Base.EstatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estates")
public class EstatesController {
    private EstatesService estatesService;

    @Autowired
    public EstatesController(EstatesService estatesService) {
        this.estatesService = estatesService;
    }

    @PostMapping("/createEstate/{userName}")
    public String createEstate(@RequestBody EstateDTO estateDTO, @PathVariable String userName){
        if(estateDTO.getAddress().equals(""))
            return "Address can not be empty!";
        try {
            estatesService.createEstate(estateDTO, userName);
            return "Estate created.";
        } catch (CantCreateEstateException e) {
            return "Can't create estate!";
        } catch (CantCreateUserException e) {
            return "No such user!";
        }
    }

    @PutMapping("/setDueDate/{dueDate}/{estateID}")
    public String setDueDate(@PathVariable String dueDate, @PathVariable int estateID){
        if(dueDate.equals("error"))
            return "Enter date!";
        try {
            estatesService.setDueDate(dueDate, estateID);
        } catch (ParseException e) {
            return "Invalid date. Format is dd-mm-yyyy.";
        } catch (NoEstateFoundException e) {
            return "No estate found!";
        }
        return "DueDate is set to: "+dueDate;
    }

    @GetMapping("/getTenant/{estateID}")
    public UserDTO getTenant(@PathVariable int estateID){
        try {
            return UserDTO.getFromUser(estatesService.getEstateById(estateID).getTenant());
        } catch (NoEstateFoundException e) {
            throw new RuntimeException();
        }
    }

    @GetMapping("/getLandlord/{estateID}")
    public UserDTO getLandlord(@PathVariable int estateID){
        try {
            return UserDTO.getFromUser(estatesService.getEstateById(estateID).getLandlord());
        } catch (NoEstateFoundException e) {
            throw new RuntimeException();
        }
    }

    @PutMapping("/setOwed/{estateID}/{owed}")
    public String setOwed(@PathVariable int estateID,@PathVariable String owed){
        if(owed.equals("error"))
            return "Enter price!";
        try {
            estatesService.setOwed(estateID, owed);
            return "Owed set to: "+owed;
        } catch (NoEstateFoundException e) {
            return "No estate found";
        } catch (Exception e){
            return "Invalid price!";
        }
    }

    @GetMapping("/getUnoccupiedEstates")
    public List<EstateDTO> getUnoccupiedLandlords() {
        List<EstateDTO> result = new ArrayList<>();
        estatesService.getUnoccupiedEstates().stream().forEach(item -> result.add(EstateDTO.getFromEstate(item)));
        return result;
    }

    @GetMapping("/getEstate/{id}")
    public EstateDTO getEstateById(@PathVariable int id){
        Estates estates;
        try {
            estates=estatesService.getEstateById(id);
        } catch (NoEstateFoundException e) {
            throw new RuntimeException();
        }
        return EstateDTO.getFromEstate(estates);
    }


}

package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.Estates;
import daredevil.project.servieces.Base.EstatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        int b=5;
        try {
            estatesService.createEstate(estateDTO, userName);
            return "Estate created.";
        } catch (CantCreateEstateException e) {
            return "Can't create estate!";
        } catch (CantCreateUserException e) {
            return "No such user!";
        }
    }

    @PostMapping("/setDueDate/{dueDate}/{estateID}")
    public String setDueDate(@PathVariable String dueDate, @PathVariable int estateID){
        try {
            estatesService.setDueDate(dueDate, estateID);
        } catch (ParseException e) {
            return "Invalid date. Format is dd-mm-yyyy.";
        }
        return "DueDate is set to: "+dueDate;
    }

}

package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.servieces.Base.EstatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        try {
            estatesService.createEstate(estateDTO, userName);
            return "Estate created.";
        } catch (CantCreateEstateException e) {
            return "Can't create estate!";
        }
    }
}

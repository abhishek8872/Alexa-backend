package com.bookmycon.AlexaController;

import com.bookmycon.model.Auditoriums;
import com.bookmycon.repository.AuditoriumRepository;
import com.bookmycon.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Alexa")
public class AlexaAuditoriumController {
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Autowired
    private AuditoriumService auditoriumService;
    @GetMapping("/listofAuditorium")
    public String getListofAuditorium(){
        List<Auditoriums> audiList= auditoriumRepository.findAll();
       String listName="";
        for (Auditoriums  audi:audiList
             ) {
            listName=listName+ audi.getAuditoriumName();
            listName=listName+" ,";

        }

        return new String("list of auditorium is as follow "+listName + " you can make booking by saying make Booking for Auditorium name");
    }

}

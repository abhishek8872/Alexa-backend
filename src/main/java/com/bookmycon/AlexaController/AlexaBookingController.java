package com.bookmycon.AlexaController;

import com.bookmycon.AlexaDto.BookingObjDto;
import com.bookmycon.AlexaDto.DateDto;
import com.bookmycon.AlexaDto.NameDto;
import com.bookmycon.AlexaDto.TimeDto;
import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.Booking;
import com.bookmycon.model.User;
import com.bookmycon.repository.AuditoriumRepository;
import com.bookmycon.service.AuditoriumService;
import com.bookmycon.service.BookingService;
import com.bookmycon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Alexa")
public class AlexaBookingController {
    BookingObjDto objDto=new BookingObjDto();
    @Autowired
    UserService userService;
    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    BookingService bookingService;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @PostMapping("/bookingAuditoriamName")
    public String bookingAuditoriamName(@RequestBody NameDto nameDto){

        List<Auditoriums> audiList= auditoriumRepository.findAll();
       boolean flag=false;
        System.out.println(nameDto.getAuditoriamName());
        for (Auditoriums  audi:audiList
        ) {
            if(audi.getAuditoriumName().equals(nameDto.getAuditoriamName())){
                flag=true;
                objDto.setBookingObjAuditoriamName(nameDto.getAuditoriamName());
            }

        }
   if(flag)
   {
       return new String("you make booking for auditorium "+nameDto.getAuditoriamName()
               +" for which date you want to make booking? say for date");

   }else {
       return new String("sorry you response is not register please try again");

   }




    }

    @PostMapping("/bookingDate")
    public String bookingDate(@RequestBody DateDto dateDto){
        LocalDate localDate = LocalDate.parse(dateDto.getDate());
        List<Booking> bookingList= bookingService.getByDateFromDateTo(localDate,localDate);
       objDto.setBookingObjDate(dateDto.getDate());


            return new String("date: "+dateDto.getDate()+" choose slot by saying from time to time");



        }
    @PostMapping("/bookingTime")
    public String bookingTime(@RequestBody TimeDto timeDto) {


        if (timeDto.getTimeFrom() != null || timeDto.getTimeTo() != null) {

            objDto.setBookingObjTimeFrom(timeDto.getTimeFrom());
            objDto.setBookingObjTimeTo(timeDto.getTimeTo());

            return  alexaAddBooking();

        } else {
            return new String("sorry you response is not register please try again");

        }
    }


    public String alexaAddBooking(){
        Booking booking=new Booking();
        User user=userService.findAllUsers().get(0);
        Auditoriums auditorium=auditoriumService.findByAuditoriumByName(objDto.getBookingObjAuditoriamName()).get(0);
        booking.setUserId(user);
        booking.setAduitoriamId(auditorium);
        booking.setBookingAgenda(new String("agenda"));
        booking.setBookingDateFrom(LocalDate.parse(objDto.getBookingObjDate()));
        booking.setBookingDateTo(LocalDate.parse(objDto.getBookingObjDate()));
        booking.setBookingTimeFrom(LocalTime.parse(objDto.getBookingObjTimeFrom()+":00"));
     booking.setBookingTimeTO(LocalTime.parse(objDto.getBookingObjTimeTo()+":00"));

     bookingService.addBooking(booking);
        return  new String("your booking is confirm :- "+" Auditorium Name : "+booking.getAduitoriamId().getAuditoriumName()+" Date : "+booking.getBookingDateFrom()
                +" time From : "+booking.getBookingTimeFrom()+" time to : "+booking.getBookingTimeTO()+" Thank you");
    }



    @PostMapping("/bookingIntent")
    public String bookingIntent(@RequestBody BookingObjDto bookingObjDto){
//        LocalDate localDate = LocalDate.parse(dateDto.getDate());
//        List<Booking> bookingList= bookingService.getByDateFromDateTo(localDate,localDate);
//        objDto.setBookingObjDate(dateDto.getDate());

System.out.println(bookingObjDto);
        return new String("you make booking for "+bookingObjDto.getBookingObjAuditoriamName()+" for date "+bookingObjDto.getBookingObjDate()
                +" from time "+bookingObjDto.getBookingObjTimeFrom()+" to "+bookingObjDto.getBookingObjTimeTo());



    }
}

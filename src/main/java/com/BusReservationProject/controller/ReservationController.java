package com.BusReservationProject.controller;

import com.BusReservationProject.entity.Bus;
import com.BusReservationProject.entity.Passenger;
import com.BusReservationProject.entity.Route;
import com.BusReservationProject.entity.SubRoute;
import com.BusReservationProject.repository.BusRepository;
import com.BusReservationProject.repository.PassengerRepository;
import com.BusReservationProject.repository.RouteRepository;
import com.BusReservationProject.repository.SubRouteRepository;
import com.BusReservationProject.util.EmailService;
import com.BusReservationProject.util.ExcelGeneratorService;
import com.BusReservationProject.util.PdfTicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private PassengerRepository passengerRepository;


    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private PdfTicketGeneratorService pdfTicketGeneratorService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ExcelGeneratorService excelGeneratorService;


    @PostMapping
    public ResponseEntity<String>bookTicket(

            @RequestParam long busId,
            @RequestParam long routeId,
//            @RequestParam long seatNumber,
            @RequestBody Passenger passenger
    )
    {
        boolean busIsPresent = false;
        boolean routeIsPresent=false;
        boolean subRouteIsPresent =false;

       Optional<Bus> byId = busRepository.findById(busId);

        if (byId.isPresent())
        {
            busIsPresent=true;
           Bus bus = byId.get();
        }
        Optional<Route> byRouteId= routeRepository.findById(routeId);
        if (byRouteId.isPresent())
        {
            routeIsPresent=true;
            Bus bus = byId.get();
        }

        Optional<SubRoute> bySubRouteId= subRouteRepository.findById(routeId);
        if (byRouteId.isPresent())
        {
            subRouteIsPresent=true;
            Bus bus = byId.get();
        }

        if (busIsPresent && routeIsPresent  || busIsPresent && subRouteIsPresent)
        {
            // Add Passenger Details

            Passenger p = new Passenger();
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            p.setEmail(passenger.getEmail());
            p.setMobile(passenger.getMobile());
            p.setRouteId(routeId);
            p.setBusId(busId);
            Passenger savedPassenger = passengerRepository.save(p);
            byte[]pdfBytes  = pdfTicketGeneratorService.generateTicket(savedPassenger, byRouteId.get().getFromLocation(), byRouteId.get().getToLocation(), byRouteId.get().getFromDate());
            emailService.sendEmailWithAttachment(passenger.getEmail(),"Booking Confirmed..","You reservation id"+savedPassenger.getId(),pdfBytes,"ticket");

        }
        return new ResponseEntity<>("done...", HttpStatus.CREATED);

    }

    @GetMapping("/passengers/excel")
    public ResponseEntity<byte[]> generateExcel(){
        try {
            //Assuming you have a method to fetch passengers from database
            List<Passenger> passengers = fetchPassengersFromDatabase();
            byte[] excelBytes = excelGeneratorService.generateExcel(passengers);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment","passenger_data.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //Dummy method to fetch passengers (replace with actual data retrieval logic)
    private List<Passenger> fetchPassengersFromDatabase() {
        //Implement your data retrieval logic here
        return passengerRepository.findAll();
    }
}


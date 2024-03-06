package com.BusReservationProject.controller;

import com.BusReservationProject.payload.BusDto;
import com.BusReservationProject.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api/v1/bus")
public class BusController {


    @Autowired
    private BusService busService;

    // http://localhost:8080/api/v1/bus
    @PostMapping("/add")
    public ResponseEntity<String> addBusDetails(@RequestBody BusDto busDto) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = formatter.parse(busDto.getFromDate());
        Date toDate = formatter.parse(busDto.getToDate());

        BusDto dto = busService.addBus(busDto);
        return new ResponseEntity<>("Bus details is successful",HttpStatus.CREATED);
    }
}


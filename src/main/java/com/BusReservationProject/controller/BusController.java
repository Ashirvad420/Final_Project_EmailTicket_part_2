package com.BusReservationProject.controller;

import com.BusReservationProject.entity.Bus;
import com.BusReservationProject.entity.Route;
import com.BusReservationProject.payload.BusDto;
//import com.BusReservationProject.repository.RouteRepository;
import com.BusReservationProject.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/bus")
public class BusController {


    @Autowired
    private BusService busService;


    // http://localhost:8080/api/v1/bus

    @PostMapping("/add")
    public ResponseEntity<Bus> addBusDetails(@RequestBody BusDto busDto) throws ParseException {

      Bus bus = busService.addBus(busDto);
        return new ResponseEntity<>(bus,HttpStatus.CREATED);
    }
}


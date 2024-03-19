package com.BusReservationProject.controller;

import com.BusReservationProject.entity.Bus;
import com.BusReservationProject.entity.Route;
import com.BusReservationProject.entity.SubRoute;
import com.BusReservationProject.payload.BusDto;
//import com.BusReservationProject.repository.RouteRepository;
import com.BusReservationProject.payload.SearchListOfBusesDto;
import com.BusReservationProject.payload.SubRouteDto;
import com.BusReservationProject.repository.BusRepository;
import com.BusReservationProject.repository.RouteRepository;
import com.BusReservationProject.repository.SubRouteRepository;
import com.BusReservationProject.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/bus")
public class BusController {


    @Autowired
    private BusService busService;


    @Autowired
    private RouteRepository routeRepository;


    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private BusRepository busRepository;


    // http://localhost:8080/api/v1/bus/add

    @PostMapping("/add")
    public ResponseEntity<Bus> addBusDetails(@RequestBody BusDto busDto) throws ParseException {

      Bus bus = busService.addBus(busDto);
        return new ResponseEntity<>(bus,HttpStatus.CREATED);
    }

    // http://localhost:8080/api/v1/bus?fromLocation=&toLocationn=&fromDate
    @GetMapping
    public  List<SearchListOfBusesDto> getAllBuses(@RequestParam String fromLocation,
                                  @RequestParam String toLocation,
                                  @RequestParam String fromDate)
    {
        List<Route> routes =  routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,toLocation,fromDate);
        List<SearchListOfBusesDto> buses = new ArrayList<>();

        // for subroute

        List<SubRoute> subRoutes =  subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,toLocation,fromDate);
        if (routes !=null)
        {

            for (Route route:routes)
            {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto =  mapToSearchListOfBusesDto(bus,route);
                buses.add(searchListOfBusesDto);
            }
            return buses;
        }


        if (subRoutes !=null)
        {

            for (SubRoute route:subRoutes)
            {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto =  mapToSearchListOfBusesDto(bus,route);
                buses.add(searchListOfBusesDto);
            }
            return buses;
        }

        // from route how do I get busId
//        for (Route route:routes)
//        {
////          System.out.println(route.getBusId());
//            Bus bus = busRepository.findById(route.getBusId()).get();
//            SearchListOfBusesDto searchListOfBusesDto =  mapToSearchListOfBusesDto(bus,route);
//            buses.add(searchListOfBusesDto);
//        }
//        return buses;

        return null;
    }

    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus,Route route)
    {
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusid(bus.getId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());


        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());
        return searchListOfBusesDto;
    }


    // For SubRoute

    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus,SubRoute route)
    {
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusid(bus.getId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());


        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());
        return searchListOfBusesDto;
    }
}


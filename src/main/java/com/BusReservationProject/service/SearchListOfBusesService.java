//package com.BusReservationProject.service;
//
//import com.BusReservationProject.entity.Bus;
//import com.BusReservationProject.entity.Route;
//import com.BusReservationProject.payload.SearchListOfBusesDto;
//import com.BusReservationProject.repository.BusRepository;
//import com.BusReservationProject.repository.RouteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class SearchListOfBusesService {
//
//    @Autowired
//    private RouteRepository routeRepository;
//
//    @Autowired
//    private BusRepository busRepository;
//
//
//    public List<SearchListOfBusesDto> getAllBuses(@RequestParam String fromLocation,
//                                                  @RequestParam String toLocation,
//                                                  @RequestParam String fromDate) {
//        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
//        List<SearchListOfBusesDto> buses = new ArrayList<>();
//
//        // from route how do I get busId
//        for (Route route : routes) {
////          System.out.println(route.getBusId());  // ErroR1
//            Bus bus = busRepository.findById(route.getBusId()).get(); //ErroR2
//            SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
//            buses.add(searchListOfBusesDto);
//        }
//        return buses;
//    }
//
//    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, Route route) {
//        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
//        searchListOfBusesDto.setBusid(bus.getId());
//        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
//        searchListOfBusesDto.setPrice(bus.getPrice());
//        searchListOfBusesDto.setBusType(bus.getBusType());
//        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
//        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
//
//
//        searchListOfBusesDto.setFromLocation(route.getFromLocation());
//        searchListOfBusesDto.setToLocation(route.getToLocation());
//        searchListOfBusesDto.setFromDate(route.getFromDate());
//        searchListOfBusesDto.setToDate(route.getToDate());
//        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
//        searchListOfBusesDto.setFromTime(route.getFromTime());
//        searchListOfBusesDto.setToTime(route.getToTime());
//        return searchListOfBusesDto;
//    }
//}

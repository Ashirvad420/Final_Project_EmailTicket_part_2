package com.BusReservationProject.controller;

import com.BusReservationProject.entity.Route;
import com.BusReservationProject.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/route")
public class RouteController {

        @Autowired
        private RouteService routeService;


    @PostMapping("/{busId}")
    public ResponseEntity<Route> addRoute(@PathVariable long busId, @RequestBody Route route)
    {
       Route r = routeService.createRoute(busId,route);
       return new ResponseEntity<>(r, HttpStatus.CREATED);
    }
}
// busId:- first we will check bus is there or Not based on Id. if bus is there save Route
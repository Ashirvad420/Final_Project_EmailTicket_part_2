package com.BusReservationProject.service;

import com.BusReservationProject.entity.Bus;
import com.BusReservationProject.entity.Route;
import com.BusReservationProject.exception.ResourceNOTFound;
import com.BusReservationProject.repository.BusRepository;
import com.BusReservationProject.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;


    public Route createRoute(long busId, Route route)
    {
      Bus bus =  busRepository.findById(busId).orElseThrow(
                ()-> new ResourceNOTFound("Bus Not Found")
        );

      Route r = routeRepository.findByBusId(route.getBusId());

      if(r != null)
      {
         throw new  ResourceNOTFound("Route is already added !!");
      }

      if (r == null)
      {
          routeRepository.save(route);
          return route;
      }
      return null;
    }
}

// orElseThrow work on built in method like- findById
//orElseThrow not apply in  method:-'findByBusId' .

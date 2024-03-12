package com.BusReservationProject.repository;

import com.BusReservationProject.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {
    Route findByBusId(long busId); // this will return a route object
}

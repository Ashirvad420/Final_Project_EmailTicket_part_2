package com.BusReservationProject.service;
import com.BusReservationProject.payload.BusDto;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BusReservationProject.entity.Bus;
import com.BusReservationProject.repository.BusRepository;


@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Transactional
    public Bus addBus(BusDto busDto) {
        // Convert BusDto to Bus entity
        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAvailableSeats(busDto.getAvailableSeats());
        Bus savedBus = busRepository.save(bus);
        return savedBus;
    }

}


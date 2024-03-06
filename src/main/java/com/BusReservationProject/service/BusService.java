package com.BusReservationProject.service;

import com.BusReservationProject.entity.Bus;
import com.BusReservationProject.payload.BusDto;
import com.BusReservationProject.repository.BusRepository;
import com.BusReservationProject.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    public BusDto addBus(BusDto busDto) { // need to add the data from BusDto to bus Object
       Bus bus = mapToEntity(busDto);

        driverRepository.save(busDto.getDriver());  // this is for driverRepository

      Bus savedBus = busRepository.save(bus); // save this bus object
        return mapToDto(savedBus);


    }

    Bus mapToEntity(BusDto dto)
    {
        Bus bus = new Bus();
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setFromLocation(dto.getFromLocation());
        bus.setToLocation(dto.getToLocation());
        bus.setFromDate(dto.getFromDate());
        bus.setToDate(dto.getToDate());
        bus.setTotalDuration(dto.getTotalDuration());
        bus.setFromTime(dto.getFromTime());
        bus.setToTime(dto.getToTime());
        bus.setPrice(dto.getPrice());
        bus.setTotalSeats(dto.getTotalSeats());
        bus.setAvailableSeats(dto.getAvailableSeats());
        bus.setDriver(dto.getDriver());
        return bus;
    }

    BusDto mapToDto(Bus bus) {
        BusDto dto = new BusDto();
        dto.setBusId(bus.getBusId());
        dto.setBusId(bus.getBusId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setFromLocation(bus.getFromLocation());
        dto.setToLocation(bus.getToLocation());
        dto.setFromDate(bus.getFromDate());
        dto.setToDate(bus.getToDate());
        dto.setTotalDuration(bus.getTotalDuration());
        dto.setFromTime(bus.getFromTime());
        dto.setToTime(bus.getToTime());
        dto.setPrice(bus.getPrice());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());
        dto.setDriver(bus.getDriver());
        return dto;
    }
}

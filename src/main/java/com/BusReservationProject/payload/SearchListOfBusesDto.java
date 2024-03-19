package com.BusReservationProject.payload;

import javax.persistence.*;

import lombok.Data;

@Data
public class SearchListOfBusesDto {

 // this is BusId Dto
    private Long busid;


    private String busNumber;


    private String busType;

    private double price;


    private int totalSeats;

    private int availableSeats;


    // this is RouteId Dto
    private Long routeid;

    private String fromLocation;

    private String toLocation;

    private String fromDate;

    private String toDate;

    private String totalDuration;

    private String fromTime;

    private String toTime;
    @Column(name = "bus_id", unique = true, nullable = false)
    private long busId;
}

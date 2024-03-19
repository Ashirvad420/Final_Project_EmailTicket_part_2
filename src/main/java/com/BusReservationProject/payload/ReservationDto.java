package com.BusReservationProject.payload;

import javax.persistence.*;

public class ReservationDto {


    private String firstName;
    private String lastName;

    private String email;

    private long mobile;

    @Column(name = "bus_id",unique = true)
    private long busId;

    @Column(name = "route_id",unique = true)
    private long routeId;
}

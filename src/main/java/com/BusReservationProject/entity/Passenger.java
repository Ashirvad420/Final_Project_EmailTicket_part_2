package com.BusReservationProject.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Passengers")


public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;

    private String email;

    private long mobile;

    @Column(name = "bus_id",unique = true)
    private long busId;

    @Column(name = "route_id",unique = true)
    private long routeId;


}

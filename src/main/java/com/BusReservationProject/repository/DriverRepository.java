package com.BusReservationProject.repository;

import com.BusReservationProject.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository <Driver,Long> {
}

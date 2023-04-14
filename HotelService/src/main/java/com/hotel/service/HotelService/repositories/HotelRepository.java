package com.hotel.service.HotelService.repositories;

import com.hotel.service.HotelService.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}

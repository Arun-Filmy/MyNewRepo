package com.hotel.service.HotelService.services;

import com.hotel.service.HotelService.entites.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();

    Hotel getSingle(String id);
}

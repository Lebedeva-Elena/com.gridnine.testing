package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFiltering {
    void getAllFlights(List<Flight> flights);

    List<Flight> DepartureToCurrentPointInTime(List<Flight> flights);

    List<Flight> ArrivalDateIsEarlierThanDepartureDate(List<Flight> flights);

    List<Flight> TimeOnEarthMoreThanTwoHours(List<Flight> flights);
}

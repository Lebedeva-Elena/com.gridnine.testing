package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FlightFilteringImpl implements FlightFiltering {
    @Override
    public void getAllFlights(List<Flight> flights) {
        flights.stream().forEach(System.out::println);
    }

    @Override
    public List<Flight> DepartureToCurrentPointInTime(List<Flight> flights) {

        List<Flight> flightsNowDate = flights.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))).collect(Collectors.toList());
        flightsNowDate.stream().forEach(System.out::println);
        return flightsNowDate;
    }

    @Override
    public List<Flight> ArrivalDateIsEarlierThanDepartureDate(List<Flight> flights) {
        List<Flight> flightsArrivalDateIsEarlierThanDepartureDate = flights.stream().filter(flight ->
                flight.getSegments().stream().anyMatch(segment -> segment.getArrivalDate()
                        .isBefore(segment.getDepartureDate()))).collect(Collectors.toList());
        flightsArrivalDateIsEarlierThanDepartureDate.stream().forEach(System.out::println);
        return flightsArrivalDateIsEarlierThanDepartureDate;

    }
    @Override
    public List<Flight> TimeOnEarthMoreThanTwoHours(List<Flight> flights) {
        List<Flight> flightsTimeOnEarthMoreThanTwoHours = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++ ) {
                LocalDateTime depTime = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrTime = segments.get(i).getArrivalDate();
                if (depTime.isAfter(arrTime.plusHours(2))) {
                    flightsTimeOnEarthMoreThanTwoHours.add(flight);

                }
            }
        }
        flightsTimeOnEarthMoreThanTwoHours.stream().forEach(System.out::println);
        return flightsTimeOnEarthMoreThanTwoHours;
    }
}

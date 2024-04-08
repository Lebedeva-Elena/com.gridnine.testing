package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.FlightBuilder;
import com.gridnine.testing.service.FlightFilteringImpl;

import java.util.List;

public class Main {
    private static FlightFilteringImpl filtering;

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Все перелеты: ");
        filtering.getAllFlights(flights);
        System.out.println();

        System.out.println("Вылет до текущего ");
        filtering.DepartureToCurrentPointInTime(flights);
        System.out.println();

        System.out.println("Сегменты с датой прилёта раньше даты вылета");
        filtering.ArrivalDateIsEarlierThanDepartureDate(flights);
        System.out.println();

        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа (время на земле — " +
                "это интервал между прилётом одного сегмента и вылетом следующего за ним).");
        filtering.TimeOnEarthMoreThanTwoHours(flights);
        System.out.println();
    }

}

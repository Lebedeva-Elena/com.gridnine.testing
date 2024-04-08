package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilteringImplTest {


    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now().plusDays(3);

    public static Flight normalFlight = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(4))
    ));

    public static Flight flightTwoSegments = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(3), CURRENT_DATE.plusHours(7))
    ));

    public static  Flight departureThePast1 = new Flight(List.of(
            new Segment(CURRENT_DATE.minusDays(4), CURRENT_DATE)
    ));

    public static  Flight departureThePast2 = new Flight(List.of(
            new Segment(CURRENT_DATE.minusWeeks(1), CURRENT_DATE)
    ));

    public static  Flight arrivalBeforeDeparture1 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.minusHours(8))
    ));

    public static Flight arrivalBeforeDeparture2 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(3)),
            new Segment(CURRENT_DATE.plusHours(5), CURRENT_DATE.plusHours(4)),
            new Segment(CURRENT_DATE.plusHours(6), CURRENT_DATE.plusHours(8))
    ));

    public static  Flight moreTwoHours1 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(5), CURRENT_DATE.plusHours(6))
    ));

    public static  Flight moreTwoHours2 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(3), CURRENT_DATE.plusHours(5)),
            new Segment(CURRENT_DATE.plusHours(8), CURRENT_DATE.plusHours(9))
    ));

    public static  List<Flight> allFlight = Arrays.asList(normalFlight, flightTwoSegments, departureThePast1,
            departureThePast2, arrivalBeforeDeparture1, arrivalBeforeDeparture2, moreTwoHours1, moreTwoHours2);
    public static  List<Flight> actualListDepartureThePast = Arrays.asList(departureThePast1, departureThePast2);
    public static  List<Flight> actualListArrivalBeforeDeparture = Arrays.asList(arrivalBeforeDeparture1,
            arrivalBeforeDeparture2);
    public static  List<Flight> actualListMoreTwoHours = Arrays.asList(moreTwoHours1,moreTwoHours2);
    private final FlightFilteringImpl filtering;

    FlightFilteringImplTest(FlightFilteringImpl filtering) {
        this.filtering = filtering;
    }

    @Test
    void departureToCurrentPointInTime() {
        List<Flight> expected = filtering.DepartureToCurrentPointInTime(allFlight);
        assertEquals(expected, actualListDepartureThePast);
    }

    @Test
    void arrivalDateIsEarlierThanDepartureDate() {
        List<Flight> expected = filtering.ArrivalDateIsEarlierThanDepartureDate(allFlight);
        assertEquals(expected, actualListArrivalBeforeDeparture);
    }

    @Test
    void timeOnEarthMoreThanTwoHours() {
        List<Flight> expected = filtering.TimeOnEarthMoreThanTwoHours(allFlight);
        assertEquals(expected, actualListMoreTwoHours);
    }
}
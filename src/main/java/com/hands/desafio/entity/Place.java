package com.hands.desafio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class Place implements Serializable {

    private int batteryState;

    private int batteryPercentage;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private String category;

    private Venue venue;

}

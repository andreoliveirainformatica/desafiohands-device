package com.hands.desafio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
public class Venue implements Serializable {

    private String name;


    private int totalTime;

    private int precision;

    private GeoLocalization geoLocalization;

    private String address;

    private String city;

    private String state;

    private String country;

}

package com.hands.desafio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public class GeoLocalization implements Serializable{

    private double[] coordinate;
}

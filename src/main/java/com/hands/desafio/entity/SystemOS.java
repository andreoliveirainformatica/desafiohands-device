package com.hands.desafio.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SystemOS implements Serializable {

    private String name;

    private String version;

}
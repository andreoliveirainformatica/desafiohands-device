package com.hands.desafio.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class Device implements Serializable {

    @Id
    private String id;

    @Indexed
    private String userId;

    private Model model;

    private Home home;

    private Work work;

    private Set<String> apps;

    private List<Place> places;
}

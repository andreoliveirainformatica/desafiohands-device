package com.hands.desafio.http.dto;

import com.hands.desafio.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DeviceResponse implements Serializable {

    @Id
    private String id;

    @Indexed
    private String userId;

    private Model model;

    private Home home;

    private Work work;

    private Set<String> apps;

    private List<Place> places;

    public static DeviceResponse builder(final Device device) {
        DeviceResponse deviceResponse = new DeviceResponse();
        BeanUtils.copyProperties(device, deviceResponse);
        return deviceResponse;
    }
    
}

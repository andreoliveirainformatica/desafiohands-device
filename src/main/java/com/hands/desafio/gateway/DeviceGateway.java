package com.hands.desafio.gateway;

import com.hands.desafio.entity.Device;

import java.util.List;

public interface DeviceGateway {

    List<Device> findAll(int page);

    List<Device> findByUserId(String userId);

    void save(Device device);
}

package com.hands.desafio.gateway;

import com.hands.desafio.entity.Device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryDeviceGateway implements DeviceGateway {

    private Map<String, Device> cache = new HashMap<>();

    @Override
    public List<Device> findAll(int page) {
        return this.cache
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Device> findByUserId(String userId) {
        return this.cache
                .values()
                .stream()
                .filter(device -> device.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void save(Device device) {
        this.cache.put(device.getId(), device);
    }

    public void clearCache() {
        this.cache.clear();
    }
}

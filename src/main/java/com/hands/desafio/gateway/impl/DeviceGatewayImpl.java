package com.hands.desafio.gateway.impl;

import com.hands.desafio.entity.Device;
import com.hands.desafio.gateway.DeviceGateway;
import com.hands.desafio.gateway.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Profile("!test")
public class DeviceGatewayImpl implements DeviceGateway {

    private final DeviceRepository deviceRepository;

    @Override
    public List<Device> findAll(int page) {
        return deviceRepository.findAll(new PageRequest(page, 20)).getContent();
    }

    @Override
    public List<Device> findByUserId(String userId) {
        return deviceRepository.findByUserId(userId);
    }

    @Override
    public void save(Device device) {
        deviceRepository.save(device);
    }
}

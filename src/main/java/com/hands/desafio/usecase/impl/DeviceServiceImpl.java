package com.hands.desafio.usecase.impl;

import com.hands.desafio.entity.Device;
import com.hands.desafio.gateway.DeviceGateway;
import com.hands.desafio.http.dto.DeviceResponse;
import com.hands.desafio.usecase.DeviceService;
import com.hands.desafio.usecase.convert.csv.DeviceConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@Component
@AllArgsConstructor()
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceGateway deviceGateway;

    private final DeviceConverter deviceConverter;

    @Override
    public void importDevices(String[] devices) {

        final Map<String, Optional<Device>> matrixDevices =
                Arrays
                        .stream(devices)
                        .map(line -> deviceConverter.convert(line))
                        .collect(Collectors.groupingBy(device -> device.getId(),
                                Collectors.reducing((device1, device2) -> {
                                    device1.getApps().addAll(device2.getApps());
                                    device1.getPlaces().addAll(device2.getPlaces());
                                    return device1;
                                })));
        matrixDevices.values().forEach(optDevice -> optDevice.ifPresent(device -> deviceGateway.save(device)));
    }

    @Override
    public List<DeviceResponse> findByUserId(String userId) {
        return deviceGateway.findByUserId(userId)
                .stream()
                .map(device -> DeviceResponse.builder(device))
                .collect(Collectors.toList());
    }
}

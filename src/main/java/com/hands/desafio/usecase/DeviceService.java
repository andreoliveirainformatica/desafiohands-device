package com.hands.desafio.usecase;

import com.hands.desafio.http.dto.DeviceResponse;

import java.util.List;

public interface DeviceService {

    void importDevices(String[] devices);

    List<DeviceResponse> findByUserId(String userId);
}

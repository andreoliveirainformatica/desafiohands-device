package com.hands.desafio.gateway.repository;

import com.hands.desafio.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by andre.oliveira on 8/11/17.
 */
public interface DeviceRepository extends PagingAndSortingRepository<Device, String> {

    List<Device> findByUserId(String userId);

}

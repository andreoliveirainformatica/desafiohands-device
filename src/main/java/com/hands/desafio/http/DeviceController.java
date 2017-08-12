package com.hands.desafio.http;

import com.hands.desafio.http.dto.DeviceResponse;
import com.hands.desafio.usecase.DeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Slf4j
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("")
    public ResponseEntity<DeviceResponse> uploadDevices(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            deviceService.importDevices(new String(bytes, "UTF-8").split("\n"));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "User", nickname = "User Search By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = DeviceResponse.class),
            @ApiResponse(code = 400, message = "NotFound"),
            @ApiResponse(code = 500, message = "Failure")})
    @GetMapping("/{idUser}/device")
    public ResponseEntity<List<DeviceResponse>> findDevicesByUserId(@PathVariable String idUser)
            throws URISyntaxException {
        log.info("REST request to get extrato");
        List<DeviceResponse> content = deviceService.findByUserId(idUser);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

}

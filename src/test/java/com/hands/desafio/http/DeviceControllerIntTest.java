package com.hands.desafio.http;

import com.hands.desafio.DeviceApplication;
import com.hands.desafio.entity.Device;
import com.hands.desafio.gateway.InMemoryDeviceGateway;
import com.hands.desafio.http.error.ExceptionTranslator;
import com.hands.desafio.usecase.DeviceService;
import com.hands.desafio.usecase.convert.csv.DeviceConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceApplication.class)
public class DeviceControllerIntTest {

    @Autowired
    private DeviceService deviceService;

    private InMemoryDeviceGateway deviceGateway = new InMemoryDeviceGateway();

    private MockMvc restUserMockMvc;

    @Before
    public void setup() {
        DeviceController deviceController = new DeviceController(deviceService);
        ReflectionTestUtils.setField(deviceService, "deviceGateway",  deviceGateway);

        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", ExceptionTranslator.class);
        final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);

        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(deviceController)
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();

        deviceGateway.clearCache();
    }


    @Test
    public void getDeviceByUserById() throws Exception {
        populateCache();
        restUserMockMvc.perform(get("/api/devices/813b190e-09d0-4545-80da-ade4405c8901/device")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem("ca515e36bda92767")))
                .andExpect(jsonPath("$.[*].userId").value(hasItem("813b190e-09d0-4545-80da-ade4405c8901")))
                .andExpect(jsonPath("$.[*].model.name").value(hasItem("Motorola Moto X (2nd Gen)")))
                .andExpect(jsonPath("$.[*].model.systemOS.name").value(hasItem("android")))
                .andExpect(jsonPath("$.[*].model.systemOS.version").value(hasItem("6.0")));
    }

    private void populateCache() {
        String line =
                "813b190e-09d0-4545-80da-ade4405c8901; " +
                        "ca515e36bda92767; " +
                        "android; " +
                        "6.0; " +
                        "Motorola Moto X (2nd Gen);" +
                        " -46.5933971 -23.5169625;" +
                        " -46.6576926 -23.5597101; " +
                        "Facebook; " +
                        "1; " +
                        "74; " +
                        "11-06-2017 11:06:53; " +
                        "11-06-2017 11:44:09; " +
                        "Playground; " +
                        "Playground - Praça do Pôr do Sol; " +
                        "37; " +
                        "7; " +
                        "-46.703318492148675 " +
                        "-23.55298042202491; " +
                        "R. Des. Ferreira França  1; " +
                        "São Paulo; " +
                        "SP; " +
                        "Brasil";

        final Device device = new DeviceConverter().convert(line);
        deviceGateway.save(device);
    }



}
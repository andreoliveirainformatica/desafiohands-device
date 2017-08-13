package com.hands.desafio.usecase;

import com.hands.desafio.gateway.InMemoryDeviceGateway;
import com.hands.desafio.http.dto.DeviceResponse;
import com.hands.desafio.usecase.convert.csv.DeviceConverter;
import com.hands.desafio.usecase.impl.DeviceServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DeviceServiceTest {

    private DeviceService deviceService;

    private InMemoryDeviceGateway deviceGateway = new InMemoryDeviceGateway();

    @Before
    public void setup() {
        deviceService = new DeviceServiceImpl(deviceGateway,  new DeviceConverter());
        deviceGateway.clearCache();
    }

    @Test
    public void getDeviceByUserById() throws Exception {
        populateCache();
        final List<DeviceResponse> deviceResponses = deviceService.findByUserId("813b190e-09d0-4545-80da-ade4405c8901");
        DeviceResponse device = deviceResponses.get(0);
        assertThat(device.getId()).isEqualTo("ca515e36bda92767");
        assertThat(device.getUserId()).isEqualTo("813b190e-09d0-4545-80da-ade4405c8901");
        assertThat(device.getModel().getSystemOS().getName()).isEqualTo("android");
        assertThat(device.getModel().getSystemOS().getVersion()).isEqualTo("6.0");
        assertThat(device.getModel().getName()).isEqualTo("Motorola Moto X (2nd Gen)");
        assertThat(device.getHome().getGeoLocalization().getCoordinate()[0]).isEqualTo(Double.parseDouble("-46.5933971"));
        assertThat(device.getHome().getGeoLocalization().getCoordinate()[1]).isEqualTo(Double.parseDouble("-23.5169625"));
        assertThat(device.getWork().getGeoLocalization().getCoordinate()[0]).isEqualTo(Double.parseDouble("-46.6576926"));
        assertThat(device.getWork().getGeoLocalization().getCoordinate()[1]).isEqualTo(Double.parseDouble("-23.5597101"));
        assertThat(device.getApps().size()).isEqualTo(1);
        assertThat(device.getApps().stream().findFirst().get()).isEqualTo("Facebook");
        assertThat(device.getPlaces().get(0).getBatteryState()).isEqualTo(1);
        assertThat(device.getPlaces().get(0).getBatteryPercentage()).isEqualTo(74);
        assertThat(device.getPlaces().get(0).getArrival()).isEqualTo(LocalDateTime.parse("11-06-2017 11:06:53", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        assertThat(device.getPlaces().get(0).getDeparture()).isEqualTo(LocalDateTime.parse("11-06-2017 11:44:09", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        assertThat(device.getPlaces().get(0).getCategory()).isEqualTo("Playground");
        assertThat(device.getPlaces().get(0).getVenue().getName()).isEqualTo("Playground - Praça do Pôr do Sol");
        assertThat(device.getPlaces().get(0).getVenue().getTotalTime()).isEqualTo(37);
        assertThat(device.getPlaces().get(0).getVenue().getPrecision()).isEqualTo(7);
        assertThat(device.getPlaces().get(0).getVenue().getGeoLocalization().getCoordinate()[0]).isEqualTo(Double.parseDouble("-46.703318492148675"));
        assertThat(device.getPlaces().get(0).getVenue().getGeoLocalization().getCoordinate()[1]).isEqualTo(Double.parseDouble("-23.55298042202491"));
        assertThat(device.getPlaces().get(0).getVenue().getAddress()).isEqualTo("R. Des. Ferreira França  1");
        assertThat(device.getPlaces().get(0).getVenue().getCity()).isEqualTo("São Paulo");
        assertThat(device.getPlaces().get(0).getVenue().getState()).isEqualTo("SP");
        assertThat(device.getPlaces().get(0).getVenue().getCountry()).isEqualTo("Brasil");

    }

    @Test
    public void importDevice() throws Exception {

        populateCaches();

        final List<DeviceResponse> deviceResponses = deviceService.findByUserId("813b190e-09d0-4545-80da-ade4405c8901");
        assertThat(deviceResponses.size()).isEqualTo(1);
        DeviceResponse device = deviceResponses.get(0);
        assertThat(device.getId()).isEqualTo("ca515e36bda92767");
        assertThat(device.getUserId()).isEqualTo("813b190e-09d0-4545-80da-ade4405c8901");
        assertThat(device.getModel().getSystemOS().getName()).isEqualTo("android");
        assertThat(device.getModel().getSystemOS().getVersion()).isEqualTo("6.0");
        assertThat(device.getModel().getName()).isEqualTo("Motorola Moto X (2nd Gen)");
        assertThat(device.getHome().getGeoLocalization().getCoordinate()[0]).isEqualTo(Double.parseDouble("-46.5933971"));
        assertThat(device.getHome().getGeoLocalization().getCoordinate()[1]).isEqualTo(Double.parseDouble("-23.5169625"));
        assertThat(device.getWork().getGeoLocalization().getCoordinate()[0]).isEqualTo(Double.parseDouble("-46.6576926"));
        assertThat(device.getWork().getGeoLocalization().getCoordinate()[1]).isEqualTo(Double.parseDouble("-23.5597101"));
        assertThat(device.getApps().size()).isEqualTo(2);
        assertThat(device.getApps().toArray()[0]).isEqualTo("youtube");
        assertThat(device.getApps().toArray()[1]).isEqualTo("Facebook");
        assertThat(device.getPlaces().size()).isEqualTo(2);
        assertThat(device.getPlaces().get(0).getBatteryState()).isEqualTo(1);
        assertThat(device.getPlaces().get(0).getBatteryPercentage()).isEqualTo(74);
        assertThat(device.getPlaces().get(0).getArrival()).isEqualTo(LocalDateTime.parse("11-06-2017 11:06:53", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        assertThat(device.getPlaces().get(0).getDeparture()).isEqualTo(LocalDateTime.parse("11-06-2017 11:44:09", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        assertThat(device.getPlaces().get(0).getCategory()).isEqualTo("Playground");
        assertThat(device.getPlaces().get(0).getVenue().getName()).isEqualTo("Playground - Praça do Pôr do Sol");
        assertThat(device.getPlaces().get(0).getVenue().getTotalTime()).isEqualTo(37);
        assertThat(device.getPlaces().get(0).getVenue().getPrecision()).isEqualTo(7);
        assertThat(device.getPlaces().get(0).getVenue().getGeoLocalization().getCoordinate()[0]).isEqualTo(Double.parseDouble("-46.703318492148675"));
        assertThat(device.getPlaces().get(0).getVenue().getGeoLocalization().getCoordinate()[1]).isEqualTo(Double.parseDouble("-23.55298042202491"));
        assertThat(device.getPlaces().get(0).getVenue().getAddress()).isEqualTo("R. Des. Ferreira França  1");
        assertThat(device.getPlaces().get(0).getVenue().getCity()).isEqualTo("São Paulo");
        assertThat(device.getPlaces().get(0).getVenue().getState()).isEqualTo("SP");
        assertThat(device.getPlaces().get(0).getVenue().getCountry()).isEqualTo("Brasil");
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

        deviceService.importDevices(new String[] {line});
    }

    private void populateCaches() {
        String line =
                "813b190e-09d0-4545-80da-ade4405c8901; " +
                        "ca515e36bda92767; " +
                        "android; " +
                        "6.0; " +
                        "Motorola Moto X (2nd Gen);" +
                        " -46.5933971 -23.5169625;" +
                        " -46.6576926 -23.5597101; " +
                        "youtube; " +
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

        String line2 =
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

        deviceService.importDevices(new String[] {line, line2});
    }



}
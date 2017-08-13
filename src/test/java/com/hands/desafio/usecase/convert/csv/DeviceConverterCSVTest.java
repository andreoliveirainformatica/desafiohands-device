package com.hands.desafio.usecase.convert.csv;

import com.hands.desafio.entity.Device;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andre.oliveira on 8/12/17.
 */
public class DeviceConverterCSVTest {

    private DeviceConverter converter;

    @Before
    public void setup() {
        converter = new DeviceConverter();
    }

    @Test
    public void converterTest() {
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

        final Device device = converter.convert(line);

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
}
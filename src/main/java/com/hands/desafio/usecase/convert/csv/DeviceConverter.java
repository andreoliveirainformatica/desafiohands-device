package com.hands.desafio.usecase.convert.csv;

import com.hands.desafio.entity.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;

@Component
public class DeviceConverter implements Converter<String, Device> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public Device convert(String line) {
        String[] fields = line.split(";");
        Device device = new Device();
        device.setId(safeGetString(() -> fields[1]));
        device.setUserId(safeGetString(() -> fields[0]));
        device.setSystemOS(builderSystemOS(fields));
        device.setModel(safeGetString(() -> fields[4]));
        device.setHome(builderHome(fields));
        device.setWork(builderWork(fields));
        device.setApps(builderApps(fields));
        device.setPlaces(builderPlaces(fields));

        if (device.getPlaces() == null) {
            System.out.println("");
        }
        return device;
    }


    private SystemOS builderSystemOS(final String[] fields){
        return new SystemOS(safeGetString(() -> fields[2]),
                safeGetString(() -> fields[3]));
    }

    private Home builderHome(final String[] fields) {
        final String stringGeolocalization =  safeGetString(() -> fields[5]);
        if (!StringUtils.isEmpty(stringGeolocalization)){
            GeoLocalization geoLocalization = builderGeoLocalization(stringGeolocalization);
            return new Home(geoLocalization);
        }
        return null;
    }

    private Work builderWork(final String[] fields) {
        final String stringGeolocalization =  safeGetString(() -> fields[6]);
        if (!StringUtils.isEmpty(stringGeolocalization)){
            GeoLocalization geoLocalization = builderGeoLocalization(stringGeolocalization);
            return new Work(geoLocalization);
        }
        return null;
    }

    private Set<String> builderApps(final String[] fields) {
        final String apps = safeGetString(() -> fields[7]);

        if (!StringUtils.isEmpty(apps)){
            return new HashSet<>(Arrays.asList(apps.split("\\|")));
        }

        return new HashSet<>();
    }


    private List<Place> builderPlaces(final String[] fields) {
        List<Place> places = new ArrayList<>();
        places.add(new Place(
                Integer.parseInt(safeGetString(() -> fields[8], "0")),
                Integer.parseInt(safeGetString(() -> fields[9], "0")),
                LocalDateTime.parse(safeGetString(() -> fields[10]), formatter),
                LocalDateTime.parse(safeGetString(() -> fields[11]), formatter),
                safeGetString(() -> fields[12]),
                builderVenue(fields)));
        return places;
    }

    private Venue builderVenue(final String[] fields){
        return new Venue(
                safeGetString(() -> fields[13]),
                Integer.parseInt(safeGetString(() -> fields[14], "0")),
                Integer.parseInt(safeGetString(() -> fields[15], "0")),
                builderGeoLocalization(safeGetString(() -> fields[16])),
                safeGetString(() -> fields[17]),
                safeGetString(() -> fields[18]),
                safeGetString(() -> fields[19]),
                safeGetString(() -> fields[20]));
    }

    private GeoLocalization builderGeoLocalization(String stringGeolocalization) {
        final String[] arrGeolocalization = stringGeolocalization.split(" ");
        try {
            return new GeoLocalization(new double[]  {Double.parseDouble(arrGeolocalization[0]), Double.parseDouble(arrGeolocalization[1])});
        } catch (Exception exception) {
            return null;
        }
    }

    private String safeGetString(Supplier<String> getter) {
        try {
            return getter.get().trim().equals("null")?null:getter.get().trim();
        } catch (Exception ex) {
            return null;
        }
    }

    private String safeGetString(Supplier<String> getter, String defaulValue) {
        try {
            return getter.get().trim().equals("null")?defaulValue:getter.get().trim();
        } catch (Exception ex) {
            return defaulValue;
        }
    }
}

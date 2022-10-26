package pl.edu.pg.eti.cs.lab1.datastore;

import org.springframework.stereotype.Component;
import pl.edu.pg.eti.cs.lab1.entities.Carrier;
import pl.edu.pg.eti.cs.lab1.entities.Plane;
import pl.edu.pg.eti.cs.lab1.serialization.CloningUtility;

import java.util.*;

@Component
public class DataStore {
    private Set<Carrier> carriers = new HashSet<>();
    private Set<Plane> planes = new HashSet<>();

    public synchronized List<Carrier> findAllCarriers(){
        return new ArrayList<>(carriers);
    }

    public synchronized Optional<Carrier> findCarrier(String name){
        return carriers.stream()
                .filter(carrier -> carrier.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCarrier(Carrier carrier){
        findCarrier(carrier.getName()).ifPresentOrElse(
                newCarrier -> {
                    throw new IllegalArgumentException(
                            String.format("The carrier name \"%s \" already exists",carrier.getName()));
                },
                () -> carriers.add(carrier));
    }

    public synchronized List<Plane> findAllPlanes(){
        return new ArrayList<>(planes);
    }

    public synchronized Optional<Plane> findPlane(int id){
        return planes.stream()
                .filter(plane -> plane.getId() == id)
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createPlane(Plane plane){
        findPlane(plane.getId()).ifPresentOrElse(
                newPlane -> {
                    throw new IllegalArgumentException(
                            String.format("The plane with id= \"%s \" already exists",plane.getId()));
                },
                () -> planes.add(plane));
    }
}

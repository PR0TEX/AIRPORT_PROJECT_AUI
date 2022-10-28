package pl.edu.pg.eti.cs.lab1.datastore;

import org.springframework.stereotype.Component;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.serialization.CloningUtility;

import java.util.*;

@Component
public class DataStore {
    private final Set<Carrier> carriers = new HashSet<>();
    private final Set<Plane> planes = new HashSet<>();

    public synchronized List<Carrier> findAllCarriers() {
        return new ArrayList<>(carriers);
    }

    public synchronized Optional<Carrier> findCarrier(String name) {
        return carriers.stream()
                .filter(carrier -> carrier.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCarrier(Carrier carrier) {
        findCarrier(carrier.getName()).ifPresentOrElse(
                newCarrier -> {
                    throw new IllegalArgumentException(
                            String.format("The carrier called \"%s \" already exists", carrier.getName()));
                },
                () -> carriers.add(carrier));
    }

    public synchronized void deleteCarrier(String name) {
        findCarrier(name).ifPresentOrElse(
                carrier -> carriers.remove(carrier),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The carrier called \"%s\" does not exist", name));
                }
        );
    }

    public synchronized void updateCarrier(Carrier carrier) {
        findCarrier(carrier.getName()).ifPresentOrElse(
                currentCarrier -> {
                    carriers.remove(currentCarrier);
                    carriers.add(CloningUtility.clone(carrier));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The carrier called \"%s\" does not exist", carrier.getName()));
                });
    }

    public synchronized List<Plane> findAllPlanes() {
        return new ArrayList<>(planes);
    }

    public synchronized Optional<Plane> findPlane(int id) {
        return planes.stream()
                .filter(plane -> plane.getId() == id)
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createPlane(Plane plane) {
        findPlane(plane.getId()).ifPresentOrElse(
                newPlane -> {
                    throw new IllegalArgumentException(
                            String.format("The plane with id= \"%d \" already exists", plane.getId()));
                },
                () -> planes.add(plane));
    }

    public synchronized void deletePlane(Integer id) {
        findPlane(id).ifPresentOrElse(
                plane -> planes.remove(plane),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The plane with id \"%d\" does not exist", id));
                }
        );
    }

    public synchronized void updatePlane(Plane plane) {
        findPlane(plane.getId()).ifPresentOrElse(
                currentPlane -> {
                    planes.remove(currentPlane);
                    planes.add(CloningUtility.clone(plane));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The plane with id \"%d\" does not exist", plane.getId()));
                });
    }
}

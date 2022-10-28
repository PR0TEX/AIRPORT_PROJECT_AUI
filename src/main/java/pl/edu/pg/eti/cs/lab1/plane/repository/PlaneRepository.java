package pl.edu.pg.eti.cs.lab1.plane.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.cs.lab1.datastore.DataStore;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class PlaneRepository implements Repository<Plane, Integer> {
    private final DataStore dataStore;

    @Autowired
    public PlaneRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Plane> find(Integer id) {
        return dataStore.findPlane(id);
    }

    @Override
    public List<Plane> findAll() {
        return dataStore.findAllPlanes();
    }

    @Override
    public void create(Plane plane) {
        dataStore.createPlane(plane);
    }

    @Override
    public void delete(Plane plane) {
        dataStore.deletePlane(plane.getId());
    }

    @Override
    public void update(Plane plane) {
        dataStore.updatePlane(plane);
    }
}

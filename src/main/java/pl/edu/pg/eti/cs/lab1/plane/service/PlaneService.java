package pl.edu.pg.eti.cs.lab1.plane.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.repository.PlaneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    private final PlaneRepository repository;

    @Autowired
    PlaneService(PlaneRepository planeRepository) {
        this.repository = planeRepository;
    }

    public Optional<Plane> find(Integer id) {
        return repository.find(id);
    }

    public List<Plane> findAll() {
        return repository.findAll();
    }

    public void create(int id, String manufacturer, String model, int maxWeightPayload, int maxPeoplePayload) {
        Plane plane = Plane.builder()
                .id(id)
                .manufacturer(manufacturer)
                .model(model)
                .maxWeightPayload(maxWeightPayload)
                .maxPeoplePayload(maxPeoplePayload)
                .build();

        create(plane);
    }

    public void create(Plane plane) {
        repository.create(plane);
    }

    public void delete(Plane plane) {
        repository.delete(plane);
    }

    public void delete(Integer id) {
        delete(this.find(id).get());
    }

    public void update(Plane plane) {
        repository.update(plane);
    }
}

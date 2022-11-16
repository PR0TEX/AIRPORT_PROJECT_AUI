package pl.edu.pg.eti.cs.lab1.plane.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.repository.IPlaneRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    private final IPlaneRepository repository;
    private static int id=0;
    @Autowired
    PlaneService(IPlaneRepository planeRepository) {
        this.repository = planeRepository;
    }

    public Optional<Plane> find(Integer id) {
        return repository.findById(id);
    }

    public List<Plane> findAll() {
        return repository.findAll();
    }
    @Transactional
    public void create(String manufacturer, String model, int maxWeightPayload, int maxPeoplePayload) {
        Plane plane = Plane.builder()
                .id(id++)
                .manufacturer(manufacturer)
                .model(model)
                .maxWeightPayload(maxWeightPayload)
                .maxPeoplePayload(maxPeoplePayload)
                .build();

        create(plane);
    }
    @Transactional
    public Plane create(Plane plane) {
        return repository.save(plane);
    }
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    @Transactional
    public void update(Plane plane) {
        repository.save(plane);
    }
}

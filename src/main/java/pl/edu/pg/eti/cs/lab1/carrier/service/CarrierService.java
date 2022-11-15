package pl.edu.pg.eti.cs.lab1.carrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.repository.CarrierRepository;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {
    private final CarrierRepository repository;

    @Autowired
    CarrierService(CarrierRepository carrierRepository) {
        this.repository = carrierRepository;
    }

    public Optional<Carrier> find(String name) {
        return repository.find(name);
    }

    public List<Carrier> findAll() {
        return repository.findAll();
    }

    //public List<Carrier> findAll(String destination) {
      //  return repository.findAllByDestination(destination);
    //}

    public void create(String name, String nationality, List<String> flightDestinations, List<Plane> planes) {
        Carrier carrier = Carrier.builder()
                .name(name)
                .nationality(nationality)
                //.flightDestinations(flightDestinations)
                .planes(planes)
                .build();

        create(carrier);
    }

    public void create(Carrier carrier) {
        repository.create(carrier);
    }

    public void delete(String name) {
        repository.delete(name);
    }

    public void update(Carrier carrier) {
        repository.update(carrier);
    }
}

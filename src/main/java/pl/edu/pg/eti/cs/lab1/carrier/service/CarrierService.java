package pl.edu.pg.eti.cs.lab1.carrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.repository.ICarrierRepository;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {
    private final ICarrierRepository repository;

    @Autowired
    CarrierService(ICarrierRepository carrierRepository) {
        this.repository = carrierRepository;
    }

    public Optional<Carrier> find(int id) {
        return repository.findById(id);
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
    @Transactional
    public Carrier create(Carrier carrier) {
       return repository.save(carrier);
       //save ?
    }
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
    @Transactional
    public void update(Carrier carrier) {
        repository.save(carrier);
    }
}

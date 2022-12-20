package pl.edu.pg.eti.cs.lab1.plane.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.repository.ICarrierRepository;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.repository.IPlaneRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    private final IPlaneRepository planeRepository;
    private final ICarrierRepository carrierRepository;
    private static int id=0;
    @Autowired
    PlaneService(IPlaneRepository planeRepository, ICarrierRepository carrierRepository) {
        this.planeRepository = planeRepository;
        this.carrierRepository = carrierRepository;
    }

    public Optional<Plane> find(Integer id) {
        return planeRepository.findById(id);
    }

    public Optional<Plane> find(int carrierId, int planeId) {
        Optional<Carrier> carrier = carrierRepository.findById(carrierId);
        if(carrier.isPresent()) {
            return planeRepository.findByIdAndCarrier(planeId, carrier.get());
        } else {
            return Optional.empty();
        }

    }

    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    public List<Plane> findAll(Carrier carrier) {
        return planeRepository.findAllByCarrier(carrier);
    }

    @Transactional
    public Plane create(Plane plane) {
        return planeRepository.save(plane);
    }
    @Transactional
    public void delete(Integer id) {
        planeRepository.deleteById(id);
    }
    @Transactional
    public void update(Plane plane) {
        planeRepository.save(plane);
    }
}

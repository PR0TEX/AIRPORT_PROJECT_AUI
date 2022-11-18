package pl.edu.pg.eti.cs.lab1.plane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.List;

public interface IPlaneRepository extends JpaRepository<Plane, Integer> {
    List<Plane> findAllByCarrier(Carrier carrier);
}

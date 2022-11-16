package pl.edu.pg.eti.cs.lab1.plane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

public interface IPlaneRepository extends JpaRepository<Plane, Integer> {}

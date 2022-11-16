package pl.edu.pg.eti.cs.lab1.carrier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;

//Repository for carrier entity. Repository should be used in business layer

@Repository
public interface ICarrierRepository extends JpaRepository<Carrier,Integer> {
    //List<Carrier> findAllByDestination(String destination);
}

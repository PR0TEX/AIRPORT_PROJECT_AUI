package pl.edu.pg.eti.cs.lab1.carrier.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.datastore.DataStore;
import pl.edu.pg.eti.cs.lab1.repository.Repository;
import pl.edu.pg.eti.cs.lab1.serialization.CloningUtility;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class CarrierRepository implements Repository<Carrier, String> {
    private final DataStore dataStore;

    @Autowired
    CarrierRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Carrier> find(String name) {
        return dataStore.findCarrier(name);
    }

    @Override
    public List<Carrier> findAll() {
        return dataStore.findAllCarriers();
    }

    @Override
    public void create(Carrier carrier) {
        dataStore.createCarrier(carrier);
    }

    @Override
    public void delete(String name) {
        dataStore.deleteCarrier(name);
    }

    @Override
    public void update(Carrier carrier) {
        dataStore.updateCarrier(carrier);
    }

//    public List<Carrier> findAllByDestination(String destination) {
//        return dataStore.findAllCarriers().stream()
//                .filter(carrier -> carrier.getFlightDestinations().contains(destination))
//                .map(CloningUtility::clone)
//                .collect(Collectors.toList());
//    }

}

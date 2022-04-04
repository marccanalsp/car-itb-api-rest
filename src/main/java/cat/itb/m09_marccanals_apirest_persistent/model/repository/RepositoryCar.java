package cat.itb.m09_marccanals_apirest_persistent.model.repository;

import cat.itb.m09_marccanals_apirest_persistent.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryCar extends JpaRepository<Car, String> {
    Car findByIdCar(String id);
    List<Car> findByModel(String m);
    long countByProducer(String p);
    List<Car> findByPriceLessThan(double price);
}

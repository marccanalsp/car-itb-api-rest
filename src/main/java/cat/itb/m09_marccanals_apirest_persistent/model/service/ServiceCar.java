package cat.itb.m09_marccanals_apirest_persistent.model.service;

import cat.itb.m09_marccanals_apirest_persistent.model.entity.Car;
import cat.itb.m09_marccanals_apirest_persistent.model.repository.RepositoryCar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCar {
    private final RepositoryCar repositoryCar;
//GET
    //List cars
    public List<Car> listCar(){
        return repositoryCar.findAll();
    }

    public List<Car> listCarByModel(String model){
        return repositoryCar.findByModel(model);
    }
    public Car findCarById(String id){
        return repositoryCar.findByIdCar(id);
    }
    public long countByProducer(String producer){
        return repositoryCar.countByProducer(producer);
    }

    public List<Car> listByPriceLessThan(double price){
        return repositoryCar.findByPriceLessThan(price);
    }
//POST
    public Car addCar(Car it){
        return repositoryCar.save(it);
    }
//PUT
    public Car modifyCar(Car car){
        Car car1=null;
        if (repositoryCar.existsById(car.getIdCar())) car1 = repositoryCar.save(car);
        return  car1;
    }
//DELETE
    public Car deleteCar(String id){
        Car res = repositoryCar.findById(id).orElse(null);
        if (res!=null) repositoryCar.deleteById(id);
        return res;
    }
}

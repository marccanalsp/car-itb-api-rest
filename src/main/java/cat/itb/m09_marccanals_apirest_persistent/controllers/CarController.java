package cat.itb.m09_marccanals_apirest_persistent.controllers;

import cat.itb.m09_marccanals_apirest_persistent.model.entity.Car;
import cat.itb.m09_marccanals_apirest_persistent.model.service.ServiceCar;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final ServiceCar serviceCar;

    @CrossOrigin(origins="http://localhost:8080")
    @GetMapping("/cars")
    public ResponseEntity<?> listCars(@RequestParam(value="price", required=false) String price){
        List<Car> res;
        if(price==null){

            res = serviceCar.listCar();
        }
        else{
            res = serviceCar.listByPriceLessThan(Double.parseDouble(price));
        }
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }


    @GetMapping("/cars/{id}")
    public ResponseEntity<?> checkCar(@PathVariable String id)
    {
        Car res = serviceCar.findCarById(id);
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }

    @GetMapping("/cars/model/{model}")
    public ResponseEntity<?> listCarByModel(@PathVariable String model){
        List<Car> res = serviceCar.listCarByModel(model);
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }

    @GetMapping("/cars/count/{producer}")
    public ResponseEntity<?> countByProducer(@PathVariable String producer){
        long res = serviceCar.countByProducer(producer);
        if (res == 0) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car){
        Car res = serviceCar.addCar(car);
        return new ResponseEntity<Car>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable String id){
        Car res = serviceCar.deleteCar(id);
        if (res == null) return ResponseEntity.notFound().build();
        else return (ResponseEntity<?>) ResponseEntity.noContent();
    }

    //per modificar un usuari existent
    @PutMapping("/cars")
    public ResponseEntity<?> modifyCar(@RequestBody Car mod){
        Car res = serviceCar.modifyCar(mod);
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }
}

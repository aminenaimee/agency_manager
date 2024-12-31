package ma.formations.ioc.servicevehicle.controller;

import ma.formations.ioc.servicevehicle.Service.VehicleService;
import ma.formations.ioc.servicevehicle.dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/save")
    public ResponseEntity<VehicleDto> save(VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.save(vehicleDto));
    }
    @PutMapping("/update")
    public ResponseEntity<VehicleDto> update(VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.update(vehicleDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        vehicleService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/available")
    public ResponseEntity<List<VehicleDto>> availableVehicles(){
        return ResponseEntity.ok(vehicleService.availableVehicles());
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDto>> findAll(){
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("available/{id}")
    public ResponseEntity<VehicleDto> availableVehicle(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.availableVehicle(id));
    }

}

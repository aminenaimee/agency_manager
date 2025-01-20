package ma.formations.ioc.servicehotel.feign;

import ma.formations.ioc.servicehotel.dto.VehicleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "SERVICE-VEHICLE")
public interface VehicleService {


    @GetMapping("api/vehicles/available/{id}")
    public ResponseEntity<VehicleDto> availableVehicle(@PathVariable Long id);



}

package ma.formations.ioc.servicehotel.feign;

import ma.formations.ioc.servicehotel.controller.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-FLIGHT")
public interface FlightService {

    @GetMapping("api/flights/{id}")
    public ResponseEntity< FlightDto> findById(@PathVariable Long id);
}
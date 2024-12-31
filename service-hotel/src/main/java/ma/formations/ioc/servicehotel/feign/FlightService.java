package ma.formations.ioc.servicehotel.feign;

import ma.formations.ioc.servicehotel.controller.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SERVICE-FLIGHT")
public interface FlightService {

    @GetMapping("api/flights/{id}")
    public ResponseEntity< FlightDto> findById(@PathVariable Long id);

   /* @PostMapping("api/flights/available")
    public ResponseEntity<?> availableFlights(Long id);
*/
    @GetMapping("api/flights/available/{id}")
    public ResponseEntity<FlightDto> availableFlight(@PathVariable Long id) ;
}
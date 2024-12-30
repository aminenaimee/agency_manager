package ma.formations.ioc.servicehotel.controller;

import ma.formations.ioc.servicehotel.dto.ReservationDto;
import ma.formations.ioc.servicehotel.service.ReservationService;
import ma.formations.ioc.servicehotel.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @PostMapping("/save")
    public ResponseEntity<ReservationDto> save(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.save(reservationDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }



}

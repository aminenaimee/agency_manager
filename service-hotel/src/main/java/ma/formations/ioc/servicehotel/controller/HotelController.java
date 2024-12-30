package ma.formations.ioc.servicehotel.controller;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired

    private HotelServiceImpl hotelService;

    @GetMapping
    public ResponseEntity<List<HotelDto>> findAll() {
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<HotelDto> save(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        hotelService.deleteById(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }
}

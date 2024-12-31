package ma.formations.ioc.serviceflight.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import ma.formations.ioc.serviceflight.dto.FlightDto;
import ma.formations.ioc.serviceflight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/save")
    public ResponseEntity<FlightDto> save(
            @RequestPart("flightDto") FlightDto flightDto,
            @RequestPart(name = "file", required = false) MultipartFile image) {
        try {
            FlightDto savedFlight = flightService.save(flightDto, image);
            return ResponseEntity.ok(savedFlight);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<FlightDto> update(@RequestBody FlightDto flightDto) {
        return ResponseEntity.ok(flightService.update(flightDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(flightService.findAll());
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("pics").resolve(filename).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }


            String contentType = Files.probeContentType(filePath);


            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        flightService.deleteById(id);
        return ResponseEntity.ok("Flight deleted successfully.");
    }

    @GetMapping("/available/flights")
    public ResponseEntity<?> availableFlights() {
        return ResponseEntity.ok(flightService.availableFlights());
    }

    @GetMapping("/available/{id}")
    public ResponseEntity<FlightDto> availableFlight(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.availableFlight(id));
    }



}

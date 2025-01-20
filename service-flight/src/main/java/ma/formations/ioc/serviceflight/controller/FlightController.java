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
import java.util.List;

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
    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FlightDto> update(
            @RequestPart("flightDto") FlightDto flightDto,
            @RequestPart(name = "file", required = false) MultipartFile file) {
        try {
            FlightDto updatedFlight = flightService.update(flightDto, file);
            return ResponseEntity.ok(updatedFlight);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> findAll(){
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        flightService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

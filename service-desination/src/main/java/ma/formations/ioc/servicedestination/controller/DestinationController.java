package ma.formations.ioc.servicedestination.controller;

import ma.formations.ioc.servicedestination.dto.DestinationDto;
import ma.formations.ioc.servicedestination.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/Destinations")
public class DestinationController {

    @Autowired
    DestinationService destinationService;


    @PostMapping("/save")
    public ResponseEntity<DestinationDto> save(
            @RequestPart("destinationDto") DestinationDto destinationDto,
            @RequestPart(name = "file", required = false) MultipartFile image) {
        try {
            DestinationDto savedDestination = destinationService.save(destinationDto, image);
            return ResponseEntity.ok(savedDestination);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DestinationDto> update(@RequestBody DestinationDto destinationDto) {
        return ResponseEntity.ok(destinationService.update(destinationDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(destinationService.findAll());
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
    public ResponseEntity<DestinationDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(destinationService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        destinationService.deleteById(id);
        return ResponseEntity.ok("Destination deleted successfully.");
    }





}

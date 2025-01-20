package ma.formations.ioc.servicevehicle.controller;

import ma.formations.ioc.servicevehicle.Service.VehicleService;
import ma.formations.ioc.servicevehicle.dto.VehicleDto;
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
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


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


    @PostMapping("/save")
    public ResponseEntity<VehicleDto> save(
            @RequestPart("vehicleDto") VehicleDto vehicleDto,
            @RequestPart(name = "file", required = false) MultipartFile image) {
        try {
            VehicleDto savedFlight = vehicleService.save(vehicleDto, image);
            return ResponseEntity.ok(savedFlight);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


}

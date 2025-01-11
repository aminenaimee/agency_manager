package ma.formations.ioc.servicehotel.controller;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.dto.RoomDto;
import ma.formations.ioc.servicehotel.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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



    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        hotelService.deleteById(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }

    @PostMapping("/save")
    public ResponseEntity<HotelDto> save(
            @RequestPart("flightDto") HotelDto flightDto,
            @RequestPart(name = "file", required = false) MultipartFile image) {
        try {
            HotelDto savedFlight = hotelService.save(flightDto, image);
            return ResponseEntity.ok(savedFlight);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update")
    ResponseEntity<HotelDto> update(
            @RequestPart("hotelDto") HotelDto hotelDto,
            @RequestPart(name = "file", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(hotelService.save(hotelDto,image));
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

}

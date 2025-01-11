package ma.formations.ioc.servicehotel.controller;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.dto.RoomDto;
import ma.formations.ioc.servicehotel.service.HotelServiceImpl;
import ma.formations.ioc.servicehotel.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/rooms")
public class RoomController {
    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Autowired
    private RoomService roomService;

    @PostMapping("/available")
    public ResponseEntity<List<RoomDto>> availableRooms() {
        return ResponseEntity.ok(roomService.availableRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> findById(Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDto>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }


    @PutMapping("/update")
    ResponseEntity<RoomDto> update(
            @RequestPart("flightDto") RoomDto roomDto,
            @RequestPart(name = "file", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(roomService.save(roomDto,image));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Long id) {
        roomService.deleteById(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

    @PostMapping("/save")
    public ResponseEntity<RoomDto> save(
            @RequestPart("roomDto") RoomDto roomDto,
            @RequestPart(name = "file", required = false) MultipartFile image) {
        try {
            RoomDto savedRoom = roomService.save(roomDto, image);
            logger.info("Saved Room: {}", savedRoom); // Log the saved DTO
            return ResponseEntity.ok(savedRoom);
        } catch (Exception e) {
            logger.error("Error saving room", e);
            return ResponseEntity.badRequest().body(null);
        }
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

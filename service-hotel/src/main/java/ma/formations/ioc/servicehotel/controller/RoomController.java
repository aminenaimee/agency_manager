package ma.formations.ioc.servicehotel.controller;

import ma.formations.ioc.servicehotel.dto.RoomDto;
import ma.formations.ioc.servicehotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

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

    @PostMapping("/save")
    public ResponseEntity<RoomDto> save(RoomDto roomDto) {
        return ResponseEntity.ok(roomService.save(roomDto));
    }

    @PutMapping("/update")
    public ResponseEntity<RoomDto> update(RoomDto roomDto) {
        return ResponseEntity.ok(roomService.save(roomDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Long id) {
        roomService.deleteById(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

}

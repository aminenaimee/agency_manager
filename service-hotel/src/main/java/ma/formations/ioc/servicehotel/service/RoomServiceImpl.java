package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.dto.RoomDto;
import ma.formations.ioc.servicehotel.entity.Room;
import ma.formations.ioc.servicehotel.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Value("${file.upload-dir:pics}")
    private String uploadDir;
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public List<RoomDto> findAll() {

        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoomDto findById(Long id) {

        return roomRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id));
    }

    private String saveImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            logger.warn("No file provided or file is empty.");
            return null;
        }
        logger.info("Processing file: {}", image.getOriginalFilename());

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            boolean created = uploadDirFile.mkdirs();
            if (created) {
                logger.info("Uploads directory created: {}", uploadDirFile.getAbsolutePath());
            } else {
                logger.error("Failed to create uploads directory: {}", uploadDirFile.getAbsolutePath());
                throw new IOException("Could not create upload directory.");
            }
        }

        String sanitizedFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "_");
        Path filePath = Paths.get(uploadDir, sanitizedFileName);

        Files.write(filePath, image.getBytes());
        logger.info("File saved at: {}", filePath.toAbsolutePath());

        return filePath.toString(); // Returns the full path
    }


    @Override
    public RoomDto save(RoomDto roomDto, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            roomDto.setImagePaths(imageUrl); // Save path in DTO
            roomDto.setImageUrl("/api/rooms/files/" + Paths.get(imageUrl).getFileName().toString()); // Save accessible URL
            logger.info("Image URL set in DTO: {}", roomDto.getImageUrl());
        } else {
            logger.warn("No image provided in the request.");
        }

        Room room = convertToEntity(roomDto);

        Room savedRoom = roomRepository.save(room); // Save to DB

        logger.debug("Saved Room in DB: {}", savedRoom); // Log the saved room for debugging

        return convertToDto(savedRoom); // Return DTO after save
    }


    @Override
    public void deleteById(Long id) {

        if (!roomRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id);
        }

        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomDto> availableRooms() {

        List<Room> availableRooms = roomRepository.findAll()
                .stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());

        // Convertir la liste en DTO
        return availableRooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public RoomDto convertToDto(Room room) {
        // Mapper l'entité Room vers RoomDto
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setBeds(room.getBeds());
        roomDto.setPrice(room.getPrice());
        roomDto.setDescription(room.getDescription());
        roomDto.setAvailable(room.isAvailable());
        roomDto.setCheckIn(room.getCheckIn());
        roomDto.setCheckOut(room.getCheckOut());
        roomDto.setType(room.getType());
        roomDto.setHotel(room.getHotel());
        roomDto.setImagePaths(room.getImagePaths());
        roomDto.setImageUrl(room.getImageUrl());

        return roomDto;
    }

    public Room convertToEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setName(roomDto.getName());
        room.setBeds(roomDto.getBeds());
        room.setPrice(roomDto.getPrice());
        room.setDescription(roomDto.getDescription());
        room.setAvailable(roomDto.isAvailable());
        room.setCheckIn(roomDto.getCheckIn());
        room.setCheckOut(roomDto.getCheckOut());
        room.setType(roomDto.getType());
        room.setHotel(roomDto.getHotel());
        room.setImagePaths(roomDto.getImagePaths()); // Fixed mapping
        room.setImageUrl(roomDto.getImageUrl());     // Fixed mapping
        return room;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void resetCheckDates(){
        List<Room> rooms = roomRepository.findAll();
        for(Room room:rooms){
            if(room.getCheckOut().isAfter(LocalDate.now()) && !room.isAvailable()){
                room.setCheckIn(null);
                room.setCheckOut(null);
                room.setAvailable(true);
                roomRepository.save(room);
            }
        }
    }

    public RoomDto aviailableRoomByHotel(Long id ,Long idHotel) {
        List<Room> rooms = roomRepository.findAll();
        Room room = rooms.stream().filter(r -> r.getId().equals(id) && r.getHotel().getId().equals(idHotel)).findFirst().orElse(null);
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id);

        }
        return convertToDto(room);
    }
}

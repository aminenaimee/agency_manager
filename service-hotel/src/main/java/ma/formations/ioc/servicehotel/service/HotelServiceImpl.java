package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.entity.Hotel;
import ma.formations.ioc.servicehotel.entity.Room;
import ma.formations.ioc.servicehotel.repository.HotelRepository;
import ma.formations.ioc.servicehotel.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Value("${file.upload-dir:pics}")
    private String uploadDir;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<HotelDto> findAll() {
        return hotelRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public HotelDto findById(Long id) {
        return hotelRepository.findById(id).map(this::convertToDto).orElse(null);
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

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        Files.write(filePath, image.getBytes());
        logger.info("File saved at: {}", filePath.toAbsolutePath());

        // Return the file path for internal use and the URL for external access
        return filePath.toString();
    }

    @Override
    public HotelDto save(HotelDto hotelDto, MultipartFile image) throws IOException {
        Hotel hotel;
        if (hotelDto.getId() != null) {
            hotel = hotelRepository.findById(hotelDto.getId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + hotelDto.getId()));
        } else {
            hotel = new Hotel();
        }

        if (image != null && !image.isEmpty()) {
            String filePath = saveImage(image);
            String imageUrl = "http://localhost:8082/api/hotels/files/" + Paths.get(filePath).getFileName();
            hotel.setImageUrl(imageUrl);
            hotel.setImagePaths(filePath);
            logger.info("Image URL and path set in Hotel entity: URL={}, Path={}", imageUrl, filePath);
        } else {
            logger.warn("No image provided in the request.");
        }

        // Set other properties
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setStars(hotelDto.getStars());
        hotel.setPrice(hotelDto.getPrice());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setEmail(hotelDto.getEmail());
        hotel.setAddress(hotelDto.getAddress());

        if (hotelDto.getRooms() != null) {
            List<Room> rooms = hotelDto.getRooms();
            for (Room room : rooms) {
                roomRepository.save(room); // Save each room
            }
        }

        // Save the hotel entity to the database
        Hotel savedHotel = hotelRepository.save(hotel);

        return convertToDto(savedHotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    private HotelDto convertToDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setCity(hotel.getCity());
        hotelDto.setStars(hotel.getStars());
        hotelDto.setPrice(hotel.getPrice());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setEmail(hotel.getEmail());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setRooms(hotel.getRooms());
        hotelDto.setImageUrl(hotel.getImageUrl());
        hotelDto.setImagePaths(hotel.getImagePaths());
        return hotelDto;
    }

    private Hotel convertToEntity(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDto.getId());
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setStars(hotelDto.getStars());
        hotel.setPrice(hotelDto.getPrice());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setEmail(hotelDto.getEmail());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setRooms(hotelDto.getRooms());
        hotel.setImagePaths(hotelDto.getImagePaths());
        hotel.setImageUrl(hotelDto.getImageUrl());
        return hotel;
    }
}

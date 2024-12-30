package ma.formations.ioc.serviceflight.service;

import ma.formations.ioc.serviceflight.dto.FlightDto;
import ma.formations.ioc.serviceflight.entity.Flight;
import ma.formations.ioc.serviceflight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Value("${file.upload-dir:pics}")
    private String uploadDir;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public FlightDto findById(Long id) {
        return flightRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public FlightDto save(FlightDto flightDto, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {

            String imageUrl = saveImage(image);
            flightDto.setImageUrl(imageUrl);
            logger.info("Image URL set in DTO: {}", imageUrl);
        } else {
            logger.warn("No image provided in the request.");
        }

        Flight flight = convertToEntity(flightDto);
        flight = flightRepository.save(flight);
        logger.info("Flight saved with ID: {}", flight.getId());


        return convertToDto(flight);
    }


    @Override
    public FlightDto update(FlightDto flightDto) {
        Flight flight = convertToEntity(flightDto);
        flight = flightRepository.save(flight);
        return convertToDto(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image) throws IOException {
        System.out.println("uploadDir: " + uploadDir);
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


        String imageUrl = "http://localhost:8080/api/flights/files/" + fileName;
        return imageUrl;
    }



    private FlightDto convertToDto(Flight flight) {
        return new FlightDto(
                flight.getId(), flight.getName(), flight.getDeparture(), flight.getArrival(),
                flight.getDepartureTime(), flight.getArrivalTime(), flight.getDuration(),
                flight.getPrice(), flight.getAirline(), flight.getImageUrl(), flight.getDescription(),
                flight.getStatus(), flight.getType(), flight.getDepartureDate(), flight.getArrivalDate(),
                flight.getUserId(), flight.getImagePaths()
        );
    }

    private Flight convertToEntity(FlightDto flightDto) {
        return new Flight(
                flightDto.getId(), flightDto.getName(), flightDto.getDeparture(), flightDto.getArrival(),
                flightDto.getDepartureTime(), flightDto.getArrivalTime(), flightDto.getDuration(),
                flightDto.getPrice(), flightDto.getAirline(), flightDto.getImageUrl(), flightDto.getDescription(),
                flightDto.getStatus(), flightDto.getType(), flightDto.getDepartureDate(), flightDto.getArrivalDate(),
                flightDto.getUserId(), flightDto.getImagePaths()
        );
    }
}

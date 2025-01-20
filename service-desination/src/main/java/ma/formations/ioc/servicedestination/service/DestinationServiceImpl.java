package ma.formations.ioc.servicedestination.service;

import ma.formations.ioc.servicedestination.dto.DestinationDto;
import ma.formations.ioc.servicedestination.entity.Destination;
import ma.formations.ioc.servicedestination.repository.DestinationRepository;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinationServiceImpl implements DestinationService{
    private static final Logger logger = LoggerFactory.getLogger(DestinationServiceImpl.class);

    @Value("${file.upload-dir:pics}")
    private String uploadDir;

    @Autowired
    DestinationRepository destinationRepository;
    @Override
    public DestinationDto findById(Long id) {
        Optional<Destination>  destination= destinationRepository.findById(id);
        return destination.map(this::toDto).orElse(null);
    }


@Override
public DestinationDto update(DestinationDto destinationDto) {
    Destination destination = destinationRepository.save(toEntity(destinationDto));
    return toDto(destination);
}

    @Override
    public void deleteById(Long id) {
     destinationRepository.deleteById(id);
    }

    @Override
    public List<DestinationDto> findAll() {
        return destinationRepository.findAll().stream().map(this::toDto).toList();
    }

    public DestinationDto toDto(Destination destination) {
        DestinationDto destinationDto = new DestinationDto();
        destinationDto.setId(destination.getId());
        destinationDto.setName(destination.getName());
        destinationDto.setCountry(destination.getCountry());
        destinationDto.setActivities(destination.getActivities());
        destinationDto.setImageUrl(destination.getImageUrl()); // Ensure this is set
        destinationDto.setImagePaths(destination.getImagePaths()); // Ensure this is set
        return destinationDto;
    }


    public Destination toEntity(DestinationDto destinationDto) {
        Destination destination = new Destination();
        destination.setId(destinationDto.getId());
        destination.setName(destinationDto.getName());
        destination.setCountry(destinationDto.getCountry());
        destination.setActivities(destinationDto.getActivities());
        destination.setImageUrl(destinationDto.getImageUrl()); // Ensure this is set
        destination.setImagePaths(destinationDto.getImagePaths()); // Ensure this is set
        return destination;
    }
    @Override
    public DestinationDto save(DestinationDto destinationDto, MultipartFile image) throws IOException {
        if (destinationDto.getId() != null) {
            Optional<Destination> existingDestination = destinationRepository.findById(destinationDto.getId());
            if (existingDestination.isPresent()) {
                logger.warn("The destination named: {} already exists.", destinationDto.getName());
                return null; // Handle appropriately
            }
        }

        if (image != null && !image.isEmpty()) {
            String[] imageDetails = saveImage(image);
            destinationDto.setImageUrl(imageDetails[0]); // Public URL
            destinationDto.setImagePaths(imageDetails[1]); // Local file path
            logger.info("Image details set in DTO: URL={}, Path={}", imageDetails[0], imageDetails[1]);
        } else {
            logger.warn("No image provided in the request.");
        }

        Destination destination = toEntity(destinationDto);
        Destination savedDestination = destinationRepository.save(destination);
        logger.info("Saved Destination: {}", savedDestination);
        return toDto(savedDestination);
    }

    private String[] saveImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            logger.warn("No file provided or file is empty.");
            return new String[]{null, null};
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

        String imageUrl = "http://localhost:8086/api/Destinations/files/" + fileName;

        return new String[]{imageUrl, filePath.toString()}; // Return both URL and file path
    }




}

package ma.formations.ioc.servicevehicle.Service;

import ma.formations.ioc.servicevehicle.dto.VehicleDto;
import ma.formations.ioc.servicevehicle.entity.Vehicle;
import ma.formations.ioc.servicevehicle.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Value("${file.upload-dir:pics}")
    private String uploadDir;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public List<VehicleDto> findAll() {

        return vehicleRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public VehicleDto findById(Long id) {
        return vehicleRepository.findById(id).map(this::convertToDto).orElse(null);
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

        // Sanitize file name to avoid issues
        String sanitizedFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename().replaceAll("[^a-zA-Z0-9.]", "_");
        Path filePath = Paths.get(uploadDir, sanitizedFileName);

        Files.write(filePath, image.getBytes());
        logger.info("File saved at: {}", filePath.toAbsolutePath());

        return filePath.toString();
    }

    @Override
    public VehicleDto save(VehicleDto vehicleDto, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            vehicleDto.setImagePaths(imagePath); // Set the file path
            vehicleDto.setImageUrl("/api/vehicles/files/" + Paths.get(imagePath).getFileName().toString()); // Set the accessible URL
            logger.info("Image URL set in DTO: {}", vehicleDto.getImageUrl());
        } else {
            logger.warn("No image provided in the request.");
        }

        Vehicle vehicle = convertToEntity(vehicleDto);
        vehicle = vehicleRepository.save(vehicle); // Persist the entity

        logger.debug("Saved Vehicle: {}", vehicle); // Debug log for verification

        return convertToDto(vehicle);
    }

    @Override
    public VehicleDto update(VehicleDto vehicleDto) {
       Vehicle vehicle = convertToEntity(vehicleDto);
         vehicle = vehicleRepository.save(vehicle);
        return convertToDto(vehicle);
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleDto> availableVehicles() {
        return vehicleRepository.findAll().stream().filter(Vehicle::isStatus).map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public VehicleDto availableVehicle(Long id) {
        return vehicleRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public VehicleDto convertToDto(Vehicle vehicle){
        return new VehicleDto(vehicle.getId(),vehicle.getName(),vehicle.getBrand(),vehicle.getModel(),vehicle.getRegistrationNumber(),vehicle.getImageUrl(),vehicle.getDescription(),vehicle.isStatus(),vehicle.getFuel(),vehicle.getTransmission(),vehicle.getPrice(),vehicle.getYear(),vehicle.getImagePaths(),vehicle.getRentStartDate(),vehicle.getRentEndDate());
    }
    public Vehicle convertToEntity(VehicleDto vehicleDto){
        return new Vehicle(vehicleDto.getId(),vehicleDto.getName(),vehicleDto.getBrand(),vehicleDto.getModel(),vehicleDto.getRegistrationNumber(),vehicleDto.getImageUrl(),vehicleDto.getDescription(),vehicleDto.isStatus(),vehicleDto.getFuel(),vehicleDto.getTransmission(),vehicleDto.getPrice(),vehicleDto.getYear(),vehicleDto.getImagePaths(),vehicleDto.getRentStartDate(),vehicleDto.getRentEndDate());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetRentDates(){
       List<Vehicle> vehicles = vehicleRepository.findAll();
         for(Vehicle vehicle:vehicles){
                if(vehicle.getRentEndDate().isAfter(LocalDate.now()) && !vehicle.isStatus()){
                    vehicle.setRentStartDate(null);
                    vehicle.setRentEndDate(null);
                    vehicle.setStatus(true);
                vehicleRepository.save(vehicle);
              }
         }
    }
}

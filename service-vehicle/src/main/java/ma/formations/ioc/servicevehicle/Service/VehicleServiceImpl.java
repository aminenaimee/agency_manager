package ma.formations.ioc.servicevehicle.Service;

import ma.formations.ioc.servicevehicle.dto.VehicleDto;
import ma.formations.ioc.servicevehicle.entity.Vehicle;
import ma.formations.ioc.servicevehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
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

    @Override
    public VehicleDto save(VehicleDto vehicleDto) {
        Vehicle vehicle = convertToEntity(vehicleDto);
        vehicle = vehicleRepository.save(vehicle);
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

package ma.formations.ioc.servicevehicle.Service;


import ma.formations.ioc.servicevehicle.dto.VehicleDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VehicleService {
    public List<VehicleDto> findAll();
    public VehicleDto findById(Long id);
    public VehicleDto save(VehicleDto vehicleDto,MultipartFile image) throws IOException;
    public VehicleDto update (VehicleDto vehicleDto);
    public void delete(Long id);
    public List<VehicleDto> availableVehicles();
    public  VehicleDto availableVehicle(Long id);

}

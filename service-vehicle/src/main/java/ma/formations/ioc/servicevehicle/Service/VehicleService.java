package ma.formations.ioc.servicevehicle.Service;


import ma.formations.ioc.servicevehicle.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    public List<VehicleDto> findAll();
    public VehicleDto findById(Long id);
    public VehicleDto save(VehicleDto vehicleDto);
    public VehicleDto update (VehicleDto vehicleDto);
    public void delete(Long id);
    public List<VehicleDto> availableVehicles();
    public  VehicleDto availableVehicle(Long id);

}

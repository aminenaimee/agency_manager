package ma.formations.ioc.serviceflight.service;

import ma.formations.ioc.serviceflight.dto.FlightDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FlightService {
    List<FlightDto> findAll();
    FlightDto findById(Long id);
    FlightDto save(FlightDto flightDto, MultipartFile image) throws IOException;
    FlightDto update(FlightDto flightDto);
    void deleteById(Long id);
     List<FlightDto> availableFlights();

     FlightDto availableFlight(Long id);
}

package ma.formations.ioc.servicedestination.service;


import ma.formations.ioc.servicedestination.dto.DestinationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DestinationService {
    DestinationDto findById(Long id);
    DestinationDto save(DestinationDto destinationDto , MultipartFile image) throws IOException;
    DestinationDto update(DestinationDto destinationDto);
    void deleteById(Long id);
    List<DestinationDto> findAll();
}

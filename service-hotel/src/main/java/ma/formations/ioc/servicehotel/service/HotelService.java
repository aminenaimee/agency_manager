package ma.formations.ioc.servicehotel.service;


import ma.formations.ioc.servicehotel.dto.HotelDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HotelService {

        public List<HotelDto> findAll();

        public HotelDto findById(Long id);

        public HotelDto save(HotelDto hotel, MultipartFile image) throws IOException;

        public void deleteById(Long id);
}

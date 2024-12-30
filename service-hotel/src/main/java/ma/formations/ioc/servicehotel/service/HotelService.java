package ma.formations.ioc.servicehotel.service;


import ma.formations.ioc.servicehotel.dto.HotelDto;

import java.util.List;

public interface HotelService {

        public List<HotelDto> findAll();

        public HotelDto findById(Long id);

        public HotelDto save(HotelDto hotel);

        public void deleteById(Long id);
}

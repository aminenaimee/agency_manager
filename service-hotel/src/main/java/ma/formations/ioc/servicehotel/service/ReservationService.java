package ma.formations.ioc.servicehotel.service;


import ma.formations.ioc.servicehotel.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto save(ReservationDto reservationDto);
    List<ReservationDto> findAll();
    ReservationDto findById(Long id);
    void deleteById(Long id);
}

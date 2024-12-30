package ma.formations.ioc.servicehotel.service.common;

import ma.formations.ioc.servicehotel.service.HotelServiceImpl;
import ma.formations.ioc.servicehotel.service.ReservationServiceImpl;
import ma.formations.ioc.servicehotel.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceDetailsImpl {
    @Autowired
    ReservationServiceImpl reservationService;
    @Autowired
    HotelServiceImpl hotelService;

    @Autowired
    RoomServiceImpl   roomService;


}

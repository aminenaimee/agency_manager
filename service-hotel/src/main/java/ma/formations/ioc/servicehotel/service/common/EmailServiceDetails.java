package ma.formations.ioc.servicehotel.service.common;

import ma.formations.ioc.servicehotel.entity.Email;

public interface EmailServiceDetails {

    public Email emialDetails(Long reservationId, Long hotelId, String hotelName, String hotelMail, String roomName, String roomType, String checkIn, String checkOut);

}

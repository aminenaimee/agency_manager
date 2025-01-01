package ma.formations.ioc.servicehotel.dto;

public class ReservationDto {
    private Long id;
    private Long clientId;
    private Long hotelId;
    private Long roomId;
    private Long carId;
    private Long flightId;
    private String sessionUrl;
    public ReservationDto() {
    }
    public ReservationDto(Long flightId, Long carId, Long roomId, Long hotelId, Long clientId, Long id , String sessionUrl) {
        this.flightId = flightId;
        this.carId = carId;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.id = id;
        this.sessionUrl = sessionUrl;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getHotelId() {
        return hotelId;
    }
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }
}






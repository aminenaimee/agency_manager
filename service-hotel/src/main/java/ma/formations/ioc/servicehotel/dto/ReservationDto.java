package ma.formations.ioc.servicehotel.dto;

public class ReservationDto {
    private Long id;
    private Long clientId;
    private Long hotelId;
    private Long roomId;
    private Long carId;
    private Long flightId;
    private String sessionUrl;
    private Long totalPrice;
    public ReservationDto() {
    }

    public ReservationDto(Long id, Long clientId, Long hotelId, Long roomId, Long carId, Long flightId, String sessionUrl, Long totalPrice) {
        this.id = id;
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.carId = carId;
        this.flightId = flightId;
        this.sessionUrl = sessionUrl;
        this.totalPrice = totalPrice;
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

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}






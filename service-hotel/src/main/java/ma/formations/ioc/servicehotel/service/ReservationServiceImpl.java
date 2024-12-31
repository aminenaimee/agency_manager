package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.controller.FlightDto;
import ma.formations.ioc.servicehotel.dto.*;

import ma.formations.ioc.servicehotel.entity.Reservation;
import ma.formations.ioc.servicehotel.feign.EmailSender;
import ma.formations.ioc.servicehotel.feign.FlightService;
import ma.formations.ioc.servicehotel.feign.VehicleService;
import ma.formations.ioc.servicehotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public ReservationDto save(ReservationDto reservationDto) {
        // Validate input fields
        if (reservationDto.getClientId() == null || reservationDto.getHotelId() == null ||
                reservationDto.getRoomId() == null || reservationDto.getCarId() == null ||
                reservationDto.getFlightId() == null) {
            throw new IllegalArgumentException("All fields must be provided");
        }

        try {
            // Fetch hotel and room entities
            HotelDto hotel = hotelService.findById(reservationDto.getHotelId());
            if (hotel == null) {
                throw new RuntimeException("Hotel not found for ID: " + reservationDto.getHotelId());
            }

            RoomDto room = roomService.aviailableRoomByHotel(reservationDto.getRoomId(), reservationDto.getHotelId());
            if (room == null) {
                throw new RuntimeException("Room not available for ID: " + reservationDto.getRoomId());
            }

            FlightDto fight = flightService.availableFlight(reservationDto.getFlightId()).getBody();
            if (fight == null) {
                throw new RuntimeException("Flight not available for ID: " + reservationDto.getFlightId());
            }
            VehicleDto vehicle = vehicleService.availableVehicle(reservationDto.getCarId()).getBody();
            if (vehicle == null) {
                throw new RuntimeException("Vehicle not available for ID: " + reservationDto.getCarId());
            }

            EmailDetails emailDetails = new EmailDetails();
             //hotel
            emailDetails.setHotelName(hotel.getName());
            emailDetails.setHotelMail(hotel.getEmail());
            //room
            emailDetails.setRoomName(room.getName());
            emailDetails.setRoomType(room.getType().toString());
            emailDetails.setCheckIn(room.getCheckIn());
            emailDetails.setCheckOut(room.getCheckOut());
            //flight
            emailDetails.setDeparture(fight.getDeparture());
            emailDetails.setDepartureTime(fight.getDepartureTime());
            //vehicle
            emailDetails.setVehicleName(vehicle.getName());
            emailDetails.setVehicleBrand(vehicle.getBrand());
            emailDetails.setRentStartDate(vehicle.getRentStartDate());
            emailDetails.setRentEndDate(vehicle.getRentEndDate());




            System.out.println("Sending email with details: " + emailDetails);
            emailSender.sendMail(emailDetails);

            // Save reservation
            Reservation reservation = convertToEntity(reservationDto);
            Reservation savedReservation = reservationRepository.save(reservation);
            System.out.println("Saved reservation: " + savedReservation);

            return convertToDto(savedReservation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while processing reservation: " + e.getMessage());
        }
    }


    @Override
    public List<ReservationDto> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto findById(Long id) {
        return reservationRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    public ReservationDto convertToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setClientId(reservation.getClientId());
        reservationDto.setHotelId(reservation.getHotelId());
        reservationDto.setRoomId(reservation.getRoomId());
        reservationDto.setCarId(reservation.getCarId());
        reservationDto.setFlightId(reservation.getFlightId());
        return reservationDto;
    }

    public Reservation convertToEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setClientId(reservationDto.getClientId());
        reservation.setHotelId(reservationDto.getHotelId());
        reservation.setRoomId(reservationDto.getRoomId());
        reservation.setCarId(reservationDto.getCarId());
        reservation.setFlightId(reservationDto.getFlightId());
        return reservation;
    }
}
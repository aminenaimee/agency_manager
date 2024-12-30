package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.entity.Hotel;
import ma.formations.ioc.servicehotel.entity.Room;
import ma.formations.ioc.servicehotel.repository.HotelRepository;
import ma.formations.ioc.servicehotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<HotelDto> findAll() {
        return hotelRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public HotelDto findById(Long id) {
        return hotelRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public HotelDto save(HotelDto hotelDto) {
        Hotel hotel;
        if (hotelDto.getId() != null) {

            hotel = hotelRepository.findById(hotelDto.getId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + hotelDto.getId()));
        } else {

            hotel = new Hotel();
        }


        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setStars(hotelDto.getStars());
        hotel.setPrice(hotelDto.getPrice());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setEmail(hotelDto.getEmail());
        hotel.setAddress(hotelDto.getAddress());


        if (hotelDto.getRooms() != null) {
            List<Room> room = hotelDto.getRooms();
            for (int i = 0; i < hotelDto.getRooms().size(); i++) {
                roomRepository.save(room.get(i));
            }

        }

        Hotel savedHotel = hotelRepository.save(hotel);

        return convertToDto(savedHotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    private HotelDto convertToDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setCity(hotel.getCity());
        hotelDto.setStars(hotel.getStars());
        hotelDto.setPrice(hotel.getPrice());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setEmail(hotel.getEmail());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setRooms(hotel.getRooms());
        return hotelDto;
    }

    private Hotel convertToEntity(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDto.getId());
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setStars(hotelDto.getStars());
        hotel.setPrice(hotelDto.getPrice());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setEmail(hotelDto.getEmail());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setRooms(hotelDto.getRooms());

        return hotel;
    }
}
package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.dto.HotelDto;
import ma.formations.ioc.servicehotel.dto.RoomDto;
import ma.formations.ioc.servicehotel.entity.Room;
import ma.formations.ioc.servicehotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public List<RoomDto> findAll() {

        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoomDto findById(Long id) {

        return roomRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id));
    }

    @Override
    public RoomDto save(RoomDto roomDto) {

        Room room = convertToEntity(roomDto);


        Room savedRoom = roomRepository.save(room);

        return convertToDto(savedRoom);
    }

    @Override
    public void deleteById(Long id) {

        if (!roomRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id);
        }

        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomDto> availableRooms() {

        List<Room> availableRooms = roomRepository.findAll()
                .stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());

        // Convertir la liste en DTO
        return availableRooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public RoomDto convertToDto(Room room) {
        // Mapper l'entité Room vers RoomDto
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setBeds(room.getBeds());
        roomDto.setPrice(room.getPrice());
        roomDto.setDescription(room.getDescription());
        roomDto.setAvailable(room.isAvailable());
        roomDto.setCheckIn(room.getCheckIn());
        roomDto.setCheckOut(room.getCheckOut());
        roomDto.setType(room.getType());
        roomDto.setHotel(room.getHotel());

        return roomDto;
    }

    public Room convertToEntity(RoomDto roomDto) {
        // Mapper le DTO RoomDto vers l'entité Room
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setName(roomDto.getName());
        room.setBeds(roomDto.getBeds());
        room.setPrice(roomDto.getPrice());
        room.setDescription(roomDto.getDescription());
        room.setAvailable(roomDto.isAvailable());
        room.setCheckIn(roomDto.getCheckIn());
        room.setCheckOut(roomDto.getCheckOut());
        room.setType(roomDto.getType());
        room.setHotel(roomDto.getHotel());


        return room;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetCheckDates(){
        List<Room> rooms = roomRepository.findAll();
        for(Room room:rooms){
            if(room.getCheckOut().isAfter(LocalDate.now()) && !room.isAvailable()){
                room.setCheckIn(null);
                room.setCheckOut(null);
                room.setAvailable(true);
                roomRepository.save(room);
            }
        }
    }

    public RoomDto aviailableRoomByHotel(Long id ,Long idHotel) {
        List<Room> rooms = roomRepository.findAll();
        Room room = rooms.stream().filter(r -> r.getId().equals(id) && r.getHotel().getId().equals(idHotel)).findFirst().orElse(null);
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id);

        }
        return convertToDto(room);
    }
}

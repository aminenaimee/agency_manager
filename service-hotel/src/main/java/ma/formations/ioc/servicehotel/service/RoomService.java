package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.dto.RoomDto;

import java.util.List;

public interface RoomService {
    public List<RoomDto> findAll();

    public RoomDto findById(Long id);

    public RoomDto save(RoomDto room);

    public void deleteById(Long id);

    public List<RoomDto> availableRooms();

}

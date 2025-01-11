package ma.formations.ioc.servicehotel.service;

import ma.formations.ioc.servicehotel.dto.RoomDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {
    public List<RoomDto> findAll();

    public RoomDto findById(Long id);

    public RoomDto save(RoomDto room, MultipartFile image) throws IOException;

    public void deleteById(Long id);

    public List<RoomDto> availableRooms();

}

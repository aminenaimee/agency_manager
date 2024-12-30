package ma.formations.ioc.servicehotel.repository;

import ma.formations.ioc.servicehotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}

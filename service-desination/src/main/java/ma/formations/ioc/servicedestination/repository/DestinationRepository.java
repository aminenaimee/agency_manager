package ma.formations.ioc.servicedestination.repository;

import ma.formations.ioc.servicedestination.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {
}

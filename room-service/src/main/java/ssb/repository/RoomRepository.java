package ssb.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ssb.entity.RoomEntity;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<RoomEntity> {
}

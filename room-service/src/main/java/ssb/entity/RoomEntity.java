package ssb.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "ROOM",schema = "PUBLIC")
public class RoomEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long room_id;
    @Column(name = "name")
    private String name;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "bed_info")
    private String bedInfo;

    public RoomEntity(){
        super();
    }
    public RoomEntity(long room_id, String name, String roomNumber, String bedInfo) {
        super();
        this.room_id = room_id;
        this.name = name;
        this.roomNumber = roomNumber;
        this.bedInfo = bedInfo;
    }
}

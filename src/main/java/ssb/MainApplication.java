package ssb;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import ssb.dto.Room;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@QuarkusMain
public class MainApplication implements QuarkusApplication {
    @Inject
    DataSource dataSource;
    @Override
    public int run(String... args) throws Exception {
    String sql="SELECT NAME,ROOM_NUMBER,BED_INFO FROM ROOM";
        List<Room> rooms = new ArrayList<>();
    try {
        Connection connection = dataSource.getConnection();
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rooms.add(new Room(rs.getString("NAME"),rs.getString("ROOM_NUMBER"),rs.getString("BED_INFO")));
            }
        }
    }catch (SQLException e) {
        e.printStackTrace();
    }
        rooms.forEach(System.out::println);
        return 0;
    }
}

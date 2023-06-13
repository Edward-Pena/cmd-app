package ssb;

import io.quarkus.arc.Arc;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
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
    EntityManager entityManager;


    @Override
    public int run(String... args) throws Exception {
        Arc.container().requestContext().activate();
    List<Room> rooms = entityManager.createQuery("Select r from Room r", Room.class).getResultList();
    rooms.forEach(System.out::println);
    Arc.container().requestContext().deactivate();
        System.out.println("aaaaaaaaaaaaaaaaaaa------------------");

        return 0;
}}

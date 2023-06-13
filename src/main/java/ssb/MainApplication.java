package ssb;

import io.quarkus.arc.Arc;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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
    @ConfigProperty(name = "application.bed-info")
    private String bedInfo;

    @Override
    public int run(String... args) throws Exception {
        Arc.container().requestContext().activate();
    List<Room> rooms = entityManager.createQuery("select r from Room r where r.bedInfo=:bedInfo", Room.class)
            .setParameter("bedInfo",bedInfo).getResultList();
    rooms.forEach(System.out::println);
    Arc.container().requestContext().deactivate();
        System.out.println("AAAAAAAAA"+bedInfo);
        return 0;
}}

package ssb.controller;

import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import ssb.entity.RoomEntity;
import ssb.repository.RoomRepository;

import java.util.List;

@GraphQLApi
public class GraphApiController {
    @Inject
    RoomRepository roomRepository;

    @Query("allRooms")
    @Description("Gets all the rooms")
    public List<RoomEntity> getAllRooms(){
        return roomRepository.listAll();

    }
}

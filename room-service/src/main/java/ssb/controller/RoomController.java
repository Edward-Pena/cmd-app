package ssb.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ssb.entity.RoomEntity;
import ssb.repository.RoomRepository;

import java.util.List;

@Path("/room")
@Produces(MediaType.APPLICATION_JSON)
public class RoomController {

    @Inject
    RoomRepository roomRepository;

@GET
@Path("/all")
public Response getAll() {
    List<RoomEntity> rooms = roomRepository.listAll();
    return Response.ok(rooms).build();
}
    @GET
    @Path("/{room_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("room_id") Long room_id) {
        return roomRepository.findByIdOptional(room_id).
                map(room -> Response.ok(room).build())
                .orElse(Response.status(404).build());
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(RoomEntity roomEntity) {
        roomRepository.persist(roomEntity);
        if (roomRepository.isPersistent(roomEntity)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    @DELETE
    @Path("/{room_id}")
    @Transactional
    public Response deleteById(@PathParam("room_id") Long room_id) {
        boolean deleted = roomRepository.deleteById(room_id);
        return deleted ? Response.noContent().
                build() : Response.status(404).build();
    }

    @PUT
    @Path("/{room_id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("room_id") Long room_id, RoomEntity roomEntity) {
        RoomEntity obj = roomRepository.findById(room_id);
        obj.setRoom_id(roomEntity.getRoom_id());
        obj.setName(roomEntity.getName());
        obj.setRoomNumber(roomEntity.getRoomNumber());
        obj.setBedInfo(roomEntity.getBedInfo());
        return Response.status(200).build();

    }

}

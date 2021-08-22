package com.genciador.salas.reuniao.controller;

import com.genciador.salas.reuniao.exception.ResourceNotFoundException;
import com.genciador.salas.reuniao.model.Room;
import com.genciador.salas.reuniao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/app/v1")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getRoomList(){
        return roomService.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(name = "id") long roomId) throws ResourceNotFoundException {
        Room room = roomService.getById(roomId)
                                          .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomService.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable (value = "id") Long roomId,
                                           @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException{
        Room room = roomService.getById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setEndHour(roomDetails.getEndHour());
        room.setStartHour(roomDetails.getStartHour());
        final Room updateRoom = roomService.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId) throws ResourceNotFoundException{
        Room room = roomService.getById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
        roomService.deleteRoom(roomId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

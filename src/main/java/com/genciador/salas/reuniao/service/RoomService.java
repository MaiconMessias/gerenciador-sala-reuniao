package com.genciador.salas.reuniao.service;

import com.genciador.salas.reuniao.model.Room;
import com.genciador.salas.reuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room save(Room room){
        return roomRepository.save(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(Long idroom) {
        return roomRepository.findById(idroom);
    }

    public Room updateRoom(Room room){
        return roomRepository.save(room);
    }

    public void deleteRoom(Long idRoom) {
        roomRepository.deleteById(idRoom);
    }

}

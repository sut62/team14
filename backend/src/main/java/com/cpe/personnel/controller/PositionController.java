package com.cpe.personnel.controller;
import com.cpe.personnel.entity.Position;
import com.cpe.personnel.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.202:8081")
@RestController
public class PositionController {

    @Autowired
    private final PositionRepository positionRepository;
    public PositionController(PositionRepository  positionRepository) {
        this. positionRepository =  positionRepository;
    }
    @GetMapping("/position")
    public Collection<Position> Positions() {
        return  positionRepository.findAll().stream().collect(Collectors.toList());
    }
}

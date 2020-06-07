package com.cpe.personnel.controller;
import com.cpe.personnel.entity.Educationlevel;
import com.cpe.personnel.repository.EducationlevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.202:8081")
@RestController
public class EducationlevelController {

    @Autowired
    private final EducationlevelRepository educationlevelRepository;
    public EducationlevelController(EducationlevelRepository  educationlevelRepository) {
        this. educationlevelRepository =  educationlevelRepository;
    }
    @GetMapping("/educationlevel")
    public Collection<Educationlevel> Educationlevels() {
        return  educationlevelRepository.findAll().stream().collect(Collectors.toList());
    }
}

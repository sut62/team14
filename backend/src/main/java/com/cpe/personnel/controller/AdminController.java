package com.cpe.personnel.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import com.cpe.personnel.entity.Admin;
import com.cpe.personnel.repository.AdminRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://172.17.0.202:8081")
@RestController
public class AdminController {

    @Autowired
    private final AdminRepository adminRepository;
    public AdminController(AdminRepository  adminRepository) {
        this. adminRepository =  adminRepository;
    }
    @GetMapping("/admin")
    public Collection<Admin> Admins() {
        return  adminRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/loginAdmin/{id}/{password}")
    public Admin loginAdmin(@PathVariable("id") String user, @PathVariable("password") String password) {
        return adminRepository.findAdmin(user,password);
    }
}

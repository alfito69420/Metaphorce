package com.example.metaphorce.service;

import com.example.metaphorce.domain.UserResponse;
import com.example.metaphorce.model.Rol;
import com.example.metaphorce.model.UserEntity;
import com.example.metaphorce.repository.RolRepository;
import com.example.metaphorce.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;
    UserResponse response;

    public UserService(UserRepository userRepository/*, RolRepository rolRepository*/) {
        this.userRepository = userRepository;
        //this.rolRepository = rolRepository;
    }

    public ResponseEntity<Object> getUser() {
        List<UserEntity> users = userRepository.findAll();
        if (!users.isEmpty()) {
            response = new UserResponse(users, "Obtención de todas los Users", 200, true);
            return new ResponseEntity<>(response.response2(), HttpStatus.OK);
        } else {
            response = new UserResponse("No se encontraron Users", 400, false);
            return new ResponseEntity<>(response.response2(), HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> newUser(@RequestBody UserEntity user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasenaEncriptada = passwordEncoder.encode(user.getContrasena());
        user.setContrasena(contrasenaEncriptada);

        //  Asignacion de rol por defecto: cliente
        //Rol rol = rolRepository.findByName("CLIENT").get();
        Rol rol = rolRepository.findById(Long.valueOf(2)).get();
        user.setRoles(Collections.singleton(rol));

        this.userRepository.save(user);
        response = new UserResponse(user, "Se pudo crear la User", 200, true);
        return new ResponseEntity<>(response.response(), HttpStatus.OK);
    }

    /*public ResponseEntity<Object> newUser2(@RequestBody CreateUserDTO createUserDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasenaEncriptada = passwordEncoder.encode(createUserDTO.getPassword());

        Set<Rol> roles = createUserDTO.getRoles().stream()
                .map(rol -> Rol.builder()
                        .rol(ERoles.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .nombre(createUserDTO.getNombre())
                .contrasena(contrasenaEncriptada)
                .email(createUserDTO.getEmail())
                .roles(roles)
                .email_verificado(createUserDTO.isEmail_verificado())
                .direccion(createUserDTO.getDireccion())
                .telefono(createUserDTO.getTelefono())
                .token(createUserDTO.getToken())
                .ciudad(createUserDTO.getCiudad())
                .build();

        this.userRepository.save(user);

        response = new UserResponse(user, "Se pudo crear la User", 200, true);
        return new ResponseEntity<>(response.response(), HttpStatus.OK);
    }*/

    public ResponseEntity<Object> updateUser(Long id, UserEntity user) {
        if (userRepository.findById(id).isPresent()) {
            UserEntity existingUser = userRepository.findById(id).get();
            existingUser.setNombre(user.getNombre());
            //existingUser.setDescripcion(user.getDescripcion());
            userRepository.save(existingUser);
            response = new UserResponse(existingUser, "Se pudo actualizar", 200, true);
            return new ResponseEntity<>(response.response(), HttpStatus.OK);
        } else {
            response = new UserResponse("No existe el ID: " + id, 400, false);
            return new ResponseEntity<>(response.response(), HttpStatus.OK);
        }
    }

    ;

    public ResponseEntity<Object> eliminar(Long id) {
        //Verificar si esta vacio
        if (!this.userRepository.findById(id).isEmpty()) {
            this.userRepository.deleteById(id);
            response = new UserResponse("Si se pudo eliminar el ID :" + id, 200, true);
            return new ResponseEntity<>(response.response(), HttpStatus.OK);
        } else {
            response = new UserResponse("No existe el ID: " + id, 400, false);
            return new ResponseEntity<>(response.response(), HttpStatus.OK);

        }
    }

    ;

    public ResponseEntity<Object> getOne(Long id) {
        if (userRepository.findById(id).isPresent()) {
            UserEntity User = userRepository.findById(id).get();
            response = new UserResponse(User, "Si encontró el ID: " + id, 200, true);
            return new ResponseEntity<>(response.response(), HttpStatus.OK);
        } else {
            response = new UserResponse("No existe la User con el ID: " + id, 400, false);
            return new ResponseEntity<>(response.response(), HttpStatus.OK);
        }
    }
}

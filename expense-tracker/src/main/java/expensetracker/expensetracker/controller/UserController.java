package expensetracker.expensetracker.controller;

import expensetracker.expensetracker.model.Users;
import expensetracker.expensetracker.payload.RegistrationPayload;
import expensetracker.expensetracker.payload.Response;
import expensetracker.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import javax.validation.Valid;
import java.util.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationPayload registrationPayload) throws Exception {
        Boolean users_available = userRepository.existsByUsernameAndEmail(registrationPayload.getUsername(), registrationPayload.getEmail());
        if (users_available == Boolean.TRUE) {
            return ResponseEntity.ok(new Response(false, "User(s) exist with that username or password"));
        } else {
            Users users = new Users(
                    registrationPayload.getUsername(),
                    registrationPayload.getFirstName(), registrationPayload.getLastName(),
                    registrationPayload.getEmail(), registrationPayload.getPassword()
            );
            userRepository.save(users);
            return ResponseEntity.ok(new Response(true, "User was registered successfully"));
        }
    }

}
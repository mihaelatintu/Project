package proiect.Controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import proiect.DatabaseTables.Users;
import proiect.Services.UsersService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<Users> user = usersService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        Users user = usersService.findByPhoneNumber(phoneNumber);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with phone number " + phoneNumber + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserViaAdminCRUD(@PathVariable Long id, @RequestBody Users user) {
        Users updatedUser = usersService.updateUserViaAdminCRUD(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @PutMapping("/update/user/{id}")
    public ResponseEntity<?> updateUserViaUserCRUD(@PathVariable Long id, @RequestBody Users user ) {
        Users updatedUser = usersService.updateUserViaUserCRUD(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users users) {
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        users.setPassword(hashedPassword);
        users.setRole("user");
        Users newUser = usersService.saveUser(users);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody Users users) {
        Users storedUser = usersService.findByEmail(users.getEmail());
        if (storedUser == null) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
        boolean passwordsMatch = BCrypt.checkpw(users.getPassword(), storedUser.getPassword());
        if (passwordsMatch) {
            return new ResponseEntity<>(storedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        boolean isDeleted = usersService.deleteUserById(id);
        if (isDeleted) {
            return new ResponseEntity<>("User with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Users users = usersService.findByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/update/up/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody Users user) {
        Optional<Users> existingUser = usersService.getUserById(id);

        if (existingUser.isPresent()) {
            Users updatedUser = usersService.updateUserViaAdminCRUD(existingUser.get().getId(), user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }



}

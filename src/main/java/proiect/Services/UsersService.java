package proiect.Services;


import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import proiect.DatabaseTables.Users;
import proiect.Repositories.UsersRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }
    public Users updateUserViaAdminCRUD(Long id, Users user) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(existingUser.getPassword());
            return usersRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("User");
        }
    }

    public Users updateUserViaUserCRUD(Long id, Users user) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setEmail(user.getEmail());

            // Only update the password if it's not null
            if (user.getPassword() != null) {
                String hashedPassword = BCrypt.hashpw(Objects.requireNonNull(user.getPassword()), BCrypt.gensalt());
                existingUser.setPassword(hashedPassword);
            }

            return usersRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("Use");
        }
    }



    public Users saveUser(Users users) {
        return usersRepository.save(users);
    }

    public Users findByEmail(String email) {
        Optional<Users> user = Optional.ofNullable(usersRepository.findByEmail(email));
        return user.orElse(null);
    }

    public boolean deleteUserById(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users findByPhoneNumber(String phoneNumber) {
        return usersRepository.findByPhoneNumber(phoneNumber);
    }


    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users authenticateUser(String email, String password) {
        List<Users> usersList = usersRepository.findByEmailAndPassword(email, password);
        if (!usersList.isEmpty()) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

}
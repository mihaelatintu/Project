package proiect.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import proiect.DatabaseTables.Users;

import java.util.List;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByPhoneNumber(String phoneNumber);
    List<Users> findByEmailAndPassword(String email, String password);
}

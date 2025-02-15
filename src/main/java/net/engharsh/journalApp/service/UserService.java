package net.engharsh.journalApp.service;
import net.engharsh.journalApp.entity.User;
import net.engharsh.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;



    public void saveNewUser(User user) {
        if (!user.getPassword().startsWith("$2a$")) {
            if (user.getPassword().length() < 3) {
                throw new IllegalArgumentException("Password must be at least 3 characters long.");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }



    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }



    public void saveUser(User user){
        userRepository.save(user);
    }



    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }



    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }



    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}

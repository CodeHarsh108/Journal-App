package net.engharsh.journalApp.service;
import net.engharsh.journalApp.entity.User;
import net.engharsh.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);


    @Autowired
    private PasswordEncoder passwordEncoder;



    public boolean saveNewUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.info("ERROR");
            return false;
        }
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

package net.engharsh.journalApp.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepository;


    @Disabled("tested")
    @Test
    public void testSaveNewUser(){
        userRepository.getUserForSA();
    }

}

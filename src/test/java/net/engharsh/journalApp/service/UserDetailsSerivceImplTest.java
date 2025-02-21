//package net.engharsh.journalApp.service;
//
//
//import net.engharsh.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//
//public class UserDetailsSerivceImplTest {
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setup(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void loadUserByUserName(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((net.engharsh.journalApp.entity.User) User.builder().username("Harsh").password("Patil").roles(String.valueOf(new ArrayList<>())).build());
//       UserDetails user =  userDetailsService.loadUserByUsername("Harsh");
//        Assertions.assertNotNull(user);
//    }
//}

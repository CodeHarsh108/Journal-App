package net.engharsh.journalApp.service;
import lombok.extern.slf4j.Slf4j;
import net.engharsh.journalApp.entity.JournalEntry;
import net.engharsh.journalApp.entity.User;
import net.engharsh.journalApp.repository.JournalEntryRepository;
import net.engharsh.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {





    @Autowired
    private JournalEntryRepository journalEntryRepository;




    @Autowired
    private UserService userService;



    @Autowired
    private UserRepository userRepository;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            System.out.println("Saved Journal Entry ID: " + saved.getId());

            List<JournalEntry> entries = new ArrayList<>(user.getJournalEntries());
            entries.add(saved);
            user.setJournalEntries(entries);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println("Error while saving entry: " + e.getMessage());
            throw new RuntimeException("Error", e);
        }
    }




    public void saveEntry(JournalEntry journalEntry){
         journalEntryRepository.save(journalEntry);
    }



    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }



    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }



    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                if (journalEntryRepository.existsById(id)) {
                    journalEntryRepository.deleteById(id);
                }
            }
        } catch (Exception e) {
            log.error("Error", e);
            throw new RuntimeException("An error occured while deleting id!", e);
        }
        return removed;
    }



    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}

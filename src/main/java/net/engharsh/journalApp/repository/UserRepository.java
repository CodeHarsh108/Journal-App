package net.engharsh.journalApp.repository;
import net.engharsh.journalApp.entity.JournalEntry;
import net.engharsh.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    void deleteByUserName(String userName);
}

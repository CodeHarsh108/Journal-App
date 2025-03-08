//package net.engharsh.journalApp.scheduler;
//
//import net.engharsh.journalApp.cache.AppCache;
//import net.engharsh.journalApp.entity.JournalEntry;
//import net.engharsh.journalApp.entity.User;
//import net.engharsh.journalApp.enums.Sentiment;
//import net.engharsh.journalApp.repository.UserRepositoryImpl;
//import net.engharsh.journalApp.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.*;
//import java.util.function.Function;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import static java.util.Arrays.stream;
//
//@Component
//public class UserScheduler {
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private UserRepositoryImpl userRepository;
//
//
//    @Autowired
//    private AppCache appCache;
//
//    @Scheduled(cron = "0 0 9 * * SUN")
//    public void fetchUsersAndSendSaMail() {
//        // Fetch users who need SA emails
//        List<User> users = userRepository.getUserForSA();
//
//        // Process each user
//        for (User user : users) {
//            // Get journal entries for the user
//            List<JournalEntry> journalEntries = user.getJournalEntries();
//
//            // Filter journal entries from the last 7 days and extract sentiments
//            List<Sentiment> sentiments = journalEntries.stream()
//                    .filter(entry -> entry.getDate() != null && entry.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
//                    .map(JournalEntry::getSentiment)
//                    .filter(Objects::nonNull) // Ensure sentiments are not null
//                    .collect(Collectors.toList());
//
//            // Count the frequency of each sentiment
//            Map<Sentiment, Long> sentimentCount = sentiments.stream()
//                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//            // Find the most frequent sentiment
//            Sentiment mostFrequentSentiment = sentimentCount.entrySet().stream()
//                    .max(Map.Entry.comparingByValue())
//                    .map(Map.Entry::getKey)
//                    .orElse(null);
//
//            // Send an email if a most frequent sentiment is found
//            if (mostFrequentSentiment != null) {
//                emailService.sendEmail(user.getEmail(), "Sentiment for last seven days", mostFrequentSentiment.toString());
//            }
//        }
//    }
//
//    @Scheduled(cron = "0 0/10 * ? * *")
//    public void clearAppCache(){
//        appCache.init();
//    }
//}

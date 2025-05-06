package com.Project3.Project3;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.model.Users;
import com.Project3.Project3.repository.TravelPackageRepository;
import com.Project3.Project3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DefaultDataLoader implements CommandLineRunner {

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (travelPackageRepository.count() == 0) {
            // Fetch a user to associate with packages, or null if none
            Users defaultUser = null;
            Iterable<Users> usersIterable = userRepository.findAll();
            List<Users> users = StreamSupport.stream(usersIterable.spliterator(), false).collect(Collectors.toList());
            if (!users.isEmpty()) {
                defaultUser = users.get(0);
            }

            List<TravelPackage> defaultPackages = Arrays.asList(
                new TravelPackage(0, "Paris Getaway", "Explore the city of lights", 5, 1500.0, "Flight, Hotel, Tour Guide", defaultUser, "assets/destination-1.jpg"),
                new TravelPackage(0, "Taj Mahal Tour", "Visit the iconic Taj Mahal", 3, 1200.0, "Flight, Hotel, Entry Tickets", defaultUser, "assets/destination-2.jpg"),
                new TravelPackage(0, "Beach Holiday", "Relax on the sunny beaches", 7, 1800.0, "Flight, Hotel, Beach Activities", defaultUser, "assets/destination-3.jpg")
            );
            travelPackageRepository.saveAll(defaultPackages);
            System.out.println("Default travel packages created.");
        } else {
            System.out.println("Travel packages already exist. Skipping default data creation.");
        }
    }
}

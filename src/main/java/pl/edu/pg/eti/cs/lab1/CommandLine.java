package pl.edu.pg.eti.cs.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.cs.lab1.carrier.service.CarrierService;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.service.PlaneService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private final CarrierService carrierService;
    private final PlaneService planeService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public CommandLine(CarrierService carrierService, PlaneService planeService) {
        this.carrierService = carrierService;
        this.planeService = planeService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean running = true;
        String category;
        printCommands();
        while (running) {
            System.out.println("Enter command");
            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.println("Enter Plane or Carrier");
                    category = scanner.nextLine();
                    if (category.equals("Plane")) {
                        planeService.findAll().forEach(System.out::println);
                    } else if (category.equals("Carrier")) {
                        carrierService.findAll().forEach(System.out::println);
                    } else {
                        throw new IllegalArgumentException(
                                String.format("The category \"%s\" does not exist", category));
                    }
                }
                case "2" -> {
                    System.out.println("Enter destination");
                    String destination = scanner.nextLine();
                    carrierService.findAll(destination).forEach(System.out::println);
                }
                case "3" -> {
                    System.out.println("Enter Plane or Carrier");
                    category = scanner.nextLine();
                    if (category.equals("Plane")) {
                        System.out.println("Enter id");
                        planeService.delete(scanner.nextInt());
                    } else if (category.equals("Carrier")) {
                        System.out.println("Enter the name");
                        carrierService.delete(scanner.nextLine());
                    } else {
                        throw new IllegalArgumentException(
                                String.format("The category \"%s\" does not exist", category));
                    }
                }
                case "4" -> {
                    System.out.println("Enter Plane or Carrier");
                    category = scanner.nextLine();
                    if (category.equals("Plane")) {
                        addPlane();
                    } else if (category.equals("Carrier")) {
                        addCarrier();
                    } else {
                        throw new IllegalArgumentException(
                                String.format("The category \"%s\" does not exist", category));
                    }
                }
                case "5" -> printCommands();
                case "6" -> {
                    System.out.println("AirPort is closing...");
                    running = false;
                }
            }
        }
    }


    private void printCommands() {
        System.out.println("Available commands");
        System.out.println("1 - print all elements");
        System.out.println("2 - all carriers flying to a specified destination");
        System.out.println("3 - delete element");
        System.out.println("4 - add element");
        System.out.println("5 - print commands");
        System.out.println("6 - stop application");

    }

    private void addPlane() {
        int maxWeightPayload, maxPeoplePayload;
        String manufacturer, model;
        scanner.nextLine();
        System.out.println("Enter manufacturer");
        manufacturer = scanner.nextLine();
        System.out.println("Enter model");
        model = scanner.nextLine();
        System.out.println("Enter maxWeightPayload");
        maxWeightPayload = scanner.nextInt();
        System.out.println("Enter maxPeoplePayload");
        maxPeoplePayload = scanner.nextInt();

        planeService.create(manufacturer, model, maxWeightPayload, maxPeoplePayload);
    }

    private void addCarrier() {
        List<String> destinations;
        List<Plane> planes;
        String name, nationality;
        System.out.println("Enter name");
        name = scanner.nextLine();
        System.out.println("Enter nationality");
        nationality = scanner.nextLine();
        System.out.println("Enter number of destinations");
        destinations = getNewDestinations(scanner.nextInt());
        System.out.println("Enter number of planes");
        planes = getNewPlanes(scanner.nextInt());
        carrierService.create(name, nationality, destinations, planes);
    }

    private List<String> getNewDestinations(int amount) {
        List<String> destinations = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < amount; i++) {
            System.out.println("Enter destination");
            destinations.add(scanner.nextLine());
        }
        return destinations;
    }

    private List<Plane> getNewPlanes(int amount) {
        List<Plane> planes = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            addPlane();
            int lastIndex = planeService.findAll().size() - 1;
            planes.add(planeService.find(lastIndex).get());
        }
        return planes;
    }
}

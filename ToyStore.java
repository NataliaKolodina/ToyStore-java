

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class ToyStore {
    private List<Toy> toyList;
    private PriorityQueue<Toy> toyQueue;

    public List<Toy> getToyList() {
        return toyList;
    }

    public ToyStore() {
        toyList = new ArrayList<>();
        toyQueue = new PriorityQueue<>(Comparator.comparingInt(Toy::getId));
    }

    public void createToy(int id, String name, int quantity) {
        Toy toy = new Toy(id, name, quantity);
        toyList.add(toy);
        toyQueue.add(toy);
    }

    public void addToyToStore(Toy toy) {
        toyList.add(toy);
        toyQueue.add(toy);
    }

    public void removeToyFromStore(Toy toy) {
        toyList.remove(toy);
        toyQueue.remove(toy);
    }

    public void writeToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Toy toy : toyList) {
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Toy getToyById(int id) {
        for (Toy toy : toyList) {
            if (toy.getId() == id) {
                System.out.println(toy.getId() + "," + toy.getName() + "," + toy.getQuantity());

                return toy;
            }
        }
        return null;
    }
    public void displayToys() {
        for (Toy toy : toyList) {
            System.out.println(toy.getId() + "," + toy.getName() + "," + toy.getQuantity());
        }
    }
   
}
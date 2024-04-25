
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

class ToyRaffle {
    private PriorityQueue<Toy> prizeQueue;

    public PriorityQueue<Toy> getPrizeQueue() {
        return prizeQueue;
    }

    public ToyRaffle() {
        prizeQueue = new PriorityQueue<>(Comparator.comparingInt(Toy::getId));
    }

    public void randomSelection(List<Toy> toyList) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(toyList.size());
            Toy toy = toyList.get(index);

            prizeQueue.add(toy);

        }
    }

    public void writePrizesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize_toys.txt"))) {
            for (Toy toy : prizeQueue) {
                writer.write(toy.getId() + "," + toy.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Подсчет веса %% выпадения каждой игрушки
    public void calculatePercentage() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Toy toy : prizeQueue) {
            countMap.put(toy.getName(), countMap.getOrDefault(toy.getName(), 0) + 1);
        }
        for (String name : countMap.keySet()) {
            double percentage = (double) countMap.get(name) / prizeQueue.size() * 100;
            System.out.println(name + ": " + percentage + "%");
        }
    }

    // Подсчет всех выпавших призовых игрушек
    public HashMap<Integer, Integer> toySum() {
        HashMap<Integer, Integer> result = new HashMap<>();
        while (!prizeQueue.isEmpty()) {
            Toy toy = prizeQueue.poll();
            int id = toy.getId();
            result.put(id, result.getOrDefault(id, 0) + 1);
        }
        // System.out.println(result);
        return result;

    }

    // обновление списка игрушек в магазине после розыгрыша
    public List<Toy> updateToyStore(List<Toy> toys, Map<Integer, Integer> countPrize) {
        List<Toy> newListStore = new ArrayList<>();

        for (Toy toy : toys) {
            int toyId = toy.getId();
            if (countPrize.containsKey(toyId)) {
                int quantityPrize = countPrize.get(toyId);
                int newQuantity = toy.getQuantity() - quantityPrize;
                if (newQuantity < 0) {
                    newQuantity = 0;
                }
                newListStore.add(new Toy(toyId, toy.getName(), newQuantity));
            } else {
                newListStore.add(toy);
            }
        }

        return newListStore;
    }

    // сохранение обновленного списка в файл
    public void saveNewToys(List<Toy> toyList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("toys.txt"))) {
            for (Toy toy : toyList) {
                writer.write(toy.getId() + " " + toy.getName() + " " + toy.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

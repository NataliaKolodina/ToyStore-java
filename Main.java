
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        ToyRaffle toyRaffle = new ToyRaffle();

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("Меню:");
                System.out.println("1. Создать игрушку");
                System.out.println("2. Поиск игрушки");
                System.out.println("3. Провести розыгрыш игрушек");
                System.out.println("4. Обновить количество игрушек в магазине");
                System.out.println("5. Вывести список игрушек");
                System.out.println("6. Рассчитать %% выпадения игрушек");
                System.out.println("0. выход");
                System.out.print("Сделайте свой выбор: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Введите ID: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Некорректный ввод. Введите число!");
                            System.out.print("Введите ID: ");
                            scanner.next();
                        }
                        int id = scanner.nextInt();
                        System.out.print("Введите название: ");
                        String name = scanner.next();

                        System.out.print("Введите количество: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Некорректный ввод. Введите число!");
                            System.out.print("Введите количество: ");
                            scanner.next();
                        }
                        int quantity = scanner.nextInt();

                        toyStore.createToy(id, name, quantity);
                        toyStore.writeToFile("toys.txt");
                        break;

                    case 2:
                        System.out.print("Введите ID для поиска: ");
                        id = scanner.nextInt();
                        toyStore.getToyById(id).toString();
                        break;
                    case 3:
                        toyRaffle.randomSelection(toyStore.getToyList());
                        System.out.println("Игрушки разыграны");
                        toyRaffle.writePrizesToFile("prize_toys.txt");
                        break;
                    case 4:
                        toyRaffle.saveNewToys(toyRaffle.updateToyStore(toyStore.getToyList(), toyRaffle.toySum()));
                        System.out.print("Файл с игрушками успешно обновлен! ");
                        break;
                    case 5:
                        toyStore.displayToys();
                        break;
                    case 6:
                        toyRaffle.calculatePercentage();
                        break;
                    case 0:
                        System.out.println("До свидания!");
                        break;
                    default:
                        System.out.println("Неверный ввод, попробуйте еще раз!");
                        break;
                }

                System.out.println();
            } while (choice != 0);
        }
    }
}

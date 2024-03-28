package Homework6;


import java.util.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] brands = {"Lenovo", "HP", "ASUS", "Acer", "MSI"}; //Название ноутбука
        String[] cpus = {"AMD", "Intel"}; //Процессор
        String[] graphic_cards = {"AMD", "Intel", "NVIDIA"}; // Видео карта
        String[] screen_sizes = {"13.3 дюйма", "14 дюймов", "15.6 дюймов", "15 дюймов"}; //Размер экрана
        String[] hd_capacities = {"128 Гб", "256 Гб", "512 Гб", "1024 Гб", "2048 Гб"}; // Объем SSD
        String[] ram_capacities = {"4 Гб", "8 Гб", "16 Гб", "32 Гб"}; //Объем оперативной памяти
        String[] ocs = {"Windows 10", "Windows 11", "Linux", "Ubuntu"}; //Операционная система
        String[] colors = {"Серебристый", "Серый", "Синий", "Черный"};
        String[] stocks = {"В наличии", "Нет на складе"};

        int notes = 1000;
        System.out.print("Введите целое число в диапазоне от 2 до " + notes + ": ");

        int number = input_number(2, notes);

        Set<Notebook> notebooks = new HashSet<>();
        // Цикл для создания объектов Notebook на основе данных из массивов
        for (int i = 0; i < number; i++) {
            Notebook notebook = new Notebook();
            notebooks.add(notebook);
            notebook.brand = brands[get_random_value(brands.length)];
            notebook.cpu = cpus[get_random_value(cpus.length)];
            notebook.id = "" + get_random_value(100000);
            notebook.oc = ocs[get_random_value(ocs.length)];
            notebook.color = colors[get_random_value(colors.length)];
            notebook.graphic_card = graphic_cards[get_random_value(graphic_cards.length)];
            notebook.screen_size = screen_sizes[get_random_value(screen_sizes.length)];
            notebook.hd_capacity = hd_capacities[get_random_value(hd_capacities.length)];
            notebook.ram_capacity = ram_capacities[get_random_value(ram_capacities.length)];
            notebook.in_stock = get_random_value(2) != 0;
            notebook.price = get_random_value(100000);

        }
        printSet(notebooks);
        int menu_points = show_menu() - 1;
        Map<Integer, String> criterion = new HashMap<>();

        while (number != menu_points) {
            int criteria_choice;
            String criteria_value = "";
            System.out.print("Введите цифру, соответствующую критерию, или цифру " + menu_points +
                    " для вывода ноутбуков, соответствующих выбранным критериям: ");
            number = input_number(1, menu_points);

            switch (number) {
                case 1 -> {
                    print_criteria_enum(brands);
                    criteria_choice = input_number(1, brands.length) - 1;
                    criteria_value = brands[criteria_choice];
                }
                case 2 -> {
                    print_criteria_enum(ocs);
                    criteria_choice = input_number(1, ocs.length) - 1;
                    criteria_value = ocs[criteria_choice];
                }
                case 3 -> {
                    print_criteria_enum(ram_capacities);
                    System.out.println("Выберите минимально допустимое значение!");
                    criteria_choice = input_number(1, ram_capacities.length) - 1;
                    criteria_value = ram_capacities[criteria_choice];
                }
                case 4 -> {
                    print_criteria_enum(hd_capacities);
                    System.out.println("Выберите минимально допустимое значение!");
                    criteria_choice = input_number(1, hd_capacities.length) - 1;
                    criteria_value = hd_capacities[criteria_choice];
                }
                case 5 -> {
                    print_criteria_enum(colors);
                    criteria_choice = input_number(1, colors.length) - 1;
                    criteria_value = colors[criteria_choice];
                }
                case 6 -> {
                    print_criteria_enum(stocks);
                    criteria_choice = input_number(1, stocks.length) - 1;
                    criteria_value = stocks[criteria_choice];
                }
                case 7 -> {
                    System.out.println("Введите минимально допустимую цену:");
                    criteria_choice = input_number(0, 1000000);
                    criteria_value = "" + criteria_choice;
                }

            }


            if (number == menu_points) {
                System.out.println("Выводим ноутбуки, соответствующие выбранным критериям:");
            } else {
                criterion.put(number, criteria_value);
                int blank = show_menu();
            }
        }

        System.out.println("Вы выбрали следующие критерии:");
        for (Map.Entry<Integer, String> item : criterion.entrySet()) {
            System.out.println(item.getKey() + ". " + item.getValue());
        }
        System.out.println();

        for (Map.Entry<Integer, String> item : criterion.entrySet()) {

            switch (item.getKey()) {
                case 1 -> notebooks = findByBrand(item.getValue(), notebooks);
                case 2 -> notebooks = findByOC(item.getValue(), notebooks);
                case 3 -> notebooks = findByRAM(item.getValue(), notebooks);
                case 4 -> notebooks = findByHD(item.getValue(), notebooks);
                case 5 -> notebooks = findByColor(item.getValue(), notebooks);
                case 6 -> notebooks = findByStock(item.getValue(), notebooks);
                case 7 -> notebooks = findByPrice(item.getValue(), notebooks);
            }
        }
        if (notebooks.isEmpty())
            System.out.println("Не найдено ноутбуков под заданные критерии!");
        else {
        System.out.println("Список из "+ notebooks.size()+ " ноутбуков под заданные критерии:");
        printSet(notebooks);
        }


    }

    static Set<Notebook> findByColor(String color, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        for (Notebook notebook : notebooks) {
            if (notebook.color.equals(color)) {
                res.add(notebook);
            }
        }
        return res;
    }

    static Set<Notebook> findByBrand(String brand, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        for (Notebook notebook : notebooks) {
            if (notebook.brand.equals(brand)) {
                res.add(notebook);
            }
        }
        return res;
    }

    static Set<Notebook> findByOC(String oc, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        for (Notebook notebook : notebooks) {
            if (notebook.oc.equals(oc)) {
                res.add(notebook);
            }
        }
        return res;
    }

    static Set<Notebook> findByRAM(String ram, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        int volume=Integer.parseInt(ram.substring(0,ram.length()-3));
        for (Notebook notebook : notebooks) {
           int cur_volume = Integer.parseInt(notebook.ram_capacity.substring(0,notebook.ram_capacity.length()-3));
            if (cur_volume>=volume) {
                res.add(notebook);
            }
        }
        return res;
    }

    static Set<Notebook> findByHD(String ssd, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        int volume=Integer.parseInt(ssd.substring(0,ssd.length()-3));
        for (Notebook notebook : notebooks) {
            int cur_volume = Integer.parseInt(notebook.hd_capacity.substring(0,notebook.hd_capacity.length()-3));
            if (cur_volume>=volume) {
                res.add(notebook);
            }
        }
        return res;
    }

    static Set<Notebook> findByStock(String stock, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        boolean in_stock = !stock.equalsIgnoreCase("true");
        System.out.println(in_stock);

        for (Notebook notebook : notebooks) {
            System.out.println(notebook.in_stock);
            if (notebook.in_stock == in_stock) {
                res.add(notebook);
            }
        }
        return res;
    }

    static Set<Notebook> findByPrice(String price, Set<Notebook> notebooks) {
        Set<Notebook> res = new HashSet<>();
        int notebook_price=Integer.parseInt(price);
        for (Notebook notebook : notebooks) {
            if (notebook.price>=notebook_price) {
                res.add(notebook);
            }
        }
        return res;
    }

    static void printSet(Set<Notebook> notebooks) {
        for (Notebook notebook : notebooks) {
            System.out.println(notebook);
        }
    }

    private static void print_criteria_enum(String[] criteria) {
        System.out.println("Выберите параметр критерия:");
        for (int i = 1; i <= criteria.length; i++) {
            System.out.println(i + ". " + criteria[i - 1]);
        }


    }


    public static int get_random_value(int length) {
        int value;
        Random random = new Random();
        value = random.nextInt(0, length);
        return value;

    }

    public static int input_number(int start, int end) {
        int number = 0;
        boolean Valid = false;


        while (!Valid) {
            try {
                number = scanner.nextInt();
                if (number >= start && number <= end) {
                    Valid = true;
                } else {
                    System.out.print("Пожалуйста, введите целое число в диапазоне от " + start + " до " + end + ": ");
                }

            } catch (InputMismatchException e) {
                System.out.print("Пожалуйста, введите целое число в диапазоне от " + start + " до " + end + ": ");
                scanner.next();
            }
        }
        System.out.println("Вы ввели число: " + number);

        return number;
    }

    public static int show_menu() {
        String[] main_menu = {"Меню критериев выбора:",
                "Бренд",
                "Операционная система",
                "Объем оперативной памяти",
                "Объем жесткого диска",
                "Цвет ноутбука",
                "В наличии",
                "Цена",
                "Вывод ноутбуков по выбранным критериям"};

        Map<Integer, String> menu = new HashMap<>();
        System.out.println();
        for (int i = 0; i < main_menu.length; i++) {
            menu.put(i, main_menu[i]);
            if (i == 0) {
                System.out.println(menu.get(i));
            } else {
                System.out.println(i + ". " + menu.get(i));
            }
        }

        return main_menu.length;
    }
}





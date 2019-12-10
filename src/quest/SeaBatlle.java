
package quest;

import java.util.Scanner;


public class SeaBatlle extends Ouests {

    //0 - пустая клетка
    //1 - есть корабль
    //2 - попал по кораблю
    //3 - мимо
    int[][] my_field;
    int[][] enemy_field;
    int my_ships;
    int enemy_ships;

    public SeaBatlle(String filename) {
        super(filename);
        my_field = new int[5][5];
        enemy_field = new int[5][5];
    }

    

    void init() {
        //начальное заполнение моего поля
        for (int i = 0; i < my_field.length; i++) {
            for (int j = 0; j < my_field[i].length; j++) {
                my_field[i][j] = 0;
            }
        }
        //начальное заполнение вражеского поля
        for (int i = 0; i < enemy_field.length; i++) {
            for (int j = 0; j < enemy_field[i].length; j++) {
                enemy_field[i][j] = 0;
            }
        }

        my_ships = 4;
        enemy_ships = 4;

        //случайное размещение моих кораблей (проставление единиц)
        for (int n = 0; n < my_ships; n++) {
            int i = (int) (Math.random() * my_field.length - 1);
            int j = (int) (Math.random() * my_field.length - 1);

            //если в поле уже стоит 1, повторяем итерацию
            if (my_field[i][j] == 1) {
                n--;
            } else {
                my_field[i][j] = 1;
            }
        }

        //случайное размещение вражеских кораблей
        for (int n = 0; n < enemy_ships; n++) {
            int i = (int) (Math.random() * enemy_field.length - 1);
            int j = (int) (Math.random() * enemy_field.length - 1);

            //если в поле уже стоит 1, повторяем итерацию
            if (enemy_field[i][j] == 1) {
                n--;
            } else {
                enemy_field[i][j] = 1;
            }
        }
    }

    //распечатка полей
    void print_field() {
        System.out.println("Мое поле");
        System.out.println();
        for (int i = 0; i < my_field.length; i++) {
            for (int j = 0; j < my_field[i].length; j++) {
                System.out.print(my_field[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();

        System.out.println("Поле врага");
        System.out.println();
        for (int i = 0; i < enemy_field.length; i++) {

            for (int j = 0; j < enemy_field[i].length; j++) {
                if (enemy_field[i][j] == 2) {
                    System.out.print(enemy_field[i][j] + " ");
                } else if (enemy_field[i][j] == 3) {
                    System.out.print("0" + " ");
                } else {
                    System.out.print("_" + " ");
                }
            }
            System.out.println();
        }

    }

    @Override
    void print_description_game() {
        System.out.println("Морской бой");
    }
//ходит человек

    void player_go() {
        System.out.println("Ход игрока");
        Scanner sc = new Scanner(System.in);
        System.out.println("Чтобы выстрелить введите 2 координаты");

        int i = 0;
        int j = 0;

        while (true) {
            i = sc.nextInt();
            j = sc.nextInt();

            if (i >= 0 && i <= enemy_field.length && j >= 0 && j <= enemy_field.length) {
                break;
            }
            System.out.println("Введите корректные данные");
        }

        if (enemy_field[i][j] == 1) {
            enemy_ships--;
            enemy_field[i][j] = 2;
        } else if (enemy_field[i][j] == 0) {
            enemy_field[i][j] = 3;
        } else if (enemy_field[i][j] == 2) {
            System.out.println("Сюда уже стреляли");
            player_go();
        }
        print_field();
    }

    //ходит комп
    void ai_go() {
        System.out.println("Ход компьютера");
        int i;
        int j;
        while (true) {

            i = (int) (Math.random() * my_field.length - 1);
            j = (int) (Math.random() * my_field.length - 1);

            if (i >= 0 && i <= my_field.length && j >= 0 && j <= my_field.length) {
                break;
            }
            System.out.println("Введите корректные данные");
        }

        if (my_field[i][j] == 1) {
            my_ships--;
            my_field[i][j] = 2;
        } else if (my_field[i][j] == 0) {
            my_field[i][j] = 3;
        } else if (my_field[i][j] == 2) {
            System.out.println("Сюда уже стреляли");
            ai_go();
        }
        print_field();
    }

    int winner_search(Player p) {

        if (my_ships == 0) {
            System.out.println("Победил компьютер");
            return 1;
        }
        if (enemy_ships == 0) {
            System.out.println("Победил игрок");
            p.money += 300;
            return 1;
        }
        return 0;
    }

    @Override
    void run(Player p) {
        init();
        while (true) {
            print_field();
            player_go();
            ai_go();
            int result = winner_search(p);
            if (result == 1) {
                break;
            }
        }
    }
}

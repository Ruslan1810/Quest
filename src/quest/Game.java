
package quest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

    ArrayList<Ouests> ar_quest = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void start() throws FileNotFoundException {

        Player p = new Player();
        ar_quest.add(new Puzzle("puzzle.txt"));
        ar_quest.add(new Shop("shop.txt"));
        ar_quest.add(new ZeroCross("zerocross.txt"));
        ar_quest.add(new SeaBatlle("seabattle.txt"));
        while (true) {
            for (int i = 0; i < ar_quest.size(); i++) {
                System.out.print(i + " " + "-" + " ");
                ar_quest.get(i).print_description_game();
                System.out.println();
            }
            System.out.println("Введите число для выбора игры");
            System.out.println();
            int num = sc.nextInt();
            System.out.println();
            ar_quest.get(num).run(p);
            ar_quest.get(num).save(ar_quest.get(num).filename);
            p.print();

        }
    }
}

class Player {

    double money = 100;
    ArrayList<String> goods = new ArrayList();

    void print() {
        System.out.println("Мои товары");
        for (int i = 0; i < goods.size(); i++) {
            System.out.println(goods.get(i));
        }
        System.out.println("Количество монет: " + money);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Puzzle extends Ouests {

    String right_ans = "lamp";

    public Puzzle(String filename) {
        super(filename);
    }

    @Override
    void print_description_game() {
        System.out.println("Загадка");
    }

    @Override
    void run(Player p) {
        System.out.println("Угадай загадку: висит груша - нельзя скушать");
        Scanner sc = new Scanner(System.in);

        String ans = sc.next();

        if (right_ans.equals(ans)) {
            System.out.println("Верно, держи 100 монет");
            p.money += 100;
            System.out.println("Количество монет: " + p.money);
        } else {
            System.out.println("Не верно");
        }
        System.out.println();

    }

    @Override
    void save(String filename) throws FileNotFoundException {
        File f = new File(filename);
        PrintWriter pw = new PrintWriter(f);
        pw.print(right_ans);
        pw.println();
        pw.flush();
    }

    @Override
    void load(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner sc = new Scanner(f);
        String right_ans = sc.next();

    }

}

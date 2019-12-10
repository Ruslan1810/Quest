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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruslan
 */
public class ZeroCross extends Ouests {

    char[][] ar = new char[3][3];

    public ZeroCross(String filename) {
        super(filename);
    }

    void creating_field() {

        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                ar[i][j] = '_';
                System.out.print(ar[i][j]);
            }
            System.out.println();
        }
    }

    void print() {

        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                System.out.print(ar[i][j]);
            }
            System.out.println();
        }
    }

    boolean comparison(char c) { //запускать дважды - отдельно не выиграл ли 'X' и отдельно, не выиграл ли 'O'
        //horizontal               
        for (int i = 0; i < ar.length; i++) {
            boolean res = false;
            for (int j = 0; j < ar[i].length; j++) {
                if (ar[i][j] != c) {
                    res = true;
                    break;
                }
            }
            if (res == false) { // нашлась строчка, в которой полностью стоят только c
                System.out.println("horiz");
                return false; // игрок c выиграл и мы сигнализируем именно об этом                    
            }
        }

        //vertical        
        for (int j = 0; j < ar[0].length; j++) {
            boolean res = false;
            for (int i = 0; i < ar.length; i++) {
                if (ar[i][j] != c) {
                    res = true;
                    break;
                }
            }
            if (res == false) // нашелся столбик, в котором полностью стоят только c
            {
                System.out.println("vert");
                return false; // игрок c выиграл и мы сигнализируем именно об этом                    
            }
        }

        //diagonal
        boolean res = false;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i][i] != c) {
                res = true;
                break;
            }
        }
        if (res == false) // на диагонали полностью стоят только с
        {
            System.out.println("diag1");
            return false; // игрок с выиграл и мы сигнализируем именно об этом                    
        }
        //diagonal
        res = false;
        int N = ar.length;

        for (int i = 0; i < ar.length; i++) {
            if (ar[N - i - 1][i] != c) { //считаем поле квадратным, иначе понятие диагонали размывается
                res = true;
                break;
            }
        }
        if (res == false) { // на диагонали полностью стоят только с
            System.out.println("diag2");
            return false; // игрок с выиграл и мы сигнализируем именно об этом                    
        }

        return true; //игрок c не выиграл.
    }

    @Override
    void print_description_game() {
        System.out.println("Крестики-Нолики");
    }

    //void expand(int n; int k){
    //}
    @Override
    void run(Player p) {

        creating_field();
        while (true) {

            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Первый игрок");

                String str = sc.next();
                if ("q".equals(str)) {
                    System.exit(0);
                    try {
                        save("zerocross.txt");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ZeroCross.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ("l".equals(str)) {
                    try {
                        load("zerocross.txt");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ZeroCross.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                int x1 = 0;

                try {
                    x1 = Integer.parseInt(str);
                } catch (NumberFormatException exc) {
                    System.out.println("Not a command, and not a number - closing!");
                    System.exit(0);
                }

                int y1 = sc.nextInt();

                if (x1 >= ar.length || y1 >= ar[0].length) {
                    int n1 = Math.max(x1, ar.length) + 1;
                    int n2 = Math.max(y1, ar[0].length) + 1;

                    char[][] ar2 = new char[n1][n2];

                    for (int i = 0; i < ar2.length; i++) {
                        for (int j = 0; j < ar2[i].length; j++) {
                            ar2[i][j] = '_';
                        }
                    }

                    for (int i = 0; i < ar.length; i++) {
                        for (int j = 0; j < ar[i].length; j++) {
                            ar2[i][j] = ar[i][j];
                        }
                    }

                    ar = ar2;
                }

                if (ar[x1][y1] == '_') {
                    ar[x1][y1] = 'X';
                    break;
                }
                System.out.println("Поле занято");
            }

            print();
            if (comparison('X') == false) {
                System.out.println("Вы победили");
                p.money += 100;
                break;
            }

            while (true) {
                System.out.println("Второй игрок");
                int x2 = (int) (Math.random() * ar.length);
                int y2 = (int) (Math.random() * ar[0].length);

                if (ar[x2][y2] == '_') {
                    ar[x2][y2] = 'O';
                    break;
                }
                //System.out.println("Поле занято");
            }
            print();
            if (comparison('O') == false) {
                System.out.println("Компьютер победил!");
                break;
            }
        }
    }

    @Override
    void save(String filename) throws FileNotFoundException {
        File f = new File(filename);
        PrintWriter pw = new PrintWriter(f);
        pw.println(ar.length);
        pw.println(ar[0].length);
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                pw.print(ar[i][j]);
                pw.print(" ");
            }
            pw.println();
        }
        pw.flush();
    }

    @Override
    void load(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner sc = new Scanner(f);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        ar = new char[num1][num2];

        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                String str = sc.next();
                ar[i][j] = str.charAt(0);
            }
        }
    }

}

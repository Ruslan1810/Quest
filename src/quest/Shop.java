/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ruslan
 */
public class Shop extends Ouests {

    ArrayList<Goods> shop_goods = new ArrayList();

    public Shop(String filename) {
        super(filename);
    }

    @Override
    void print_description_game() {
        System.out.println("Магазин");
    }

    @Override
    void save(String filename) throws FileNotFoundException {
        File f = new File(filename);
        PrintWriter pw = new PrintWriter(f);
        pw.println();
        for (int i = 0; i < shop_goods.size(); i++) {
            pw.println(shop_goods.get(i).good);
            pw.println(shop_goods.get(i).price);

            pw.println();
        }
        pw.flush();
    }

    @Override
    void load(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner sc = new Scanner(f);

        for (int i = 0; i < shop_goods.size(); i++) {
            shop_goods.get(i).good = sc.next();
            shop_goods.get(i).price = sc.nextDouble();

        }
    }

    @Override
    void run(Player p) {
        shop_goods.add(new Goods("odejda", 20));
        shop_goods.add(new Goods("eda", 10));
        System.out.println("Ассортимент");
//распечатка атоваров
        for (int i = 0; i < shop_goods.size(); i++) {
            System.out.println(shop_goods.get(i).good);
            System.out.println(shop_goods.get(i).price);
        }
        Scanner sc = new Scanner(System.in);
        String sale_good = sc.next();
//сравнение запроса с наличными товарами
        for (int i = 0; i < shop_goods.size(); i++) {
            if (shop_goods.get(i).good.equals(sale_good)) {
                System.out.println("Хороший выбор");
                p.goods.add(sale_good);
                p.money -= shop_goods.get(i).price;
            } else {
                System.out.println("Такого товара нет");
            }
            System.out.println();

        }

    }
}
class Goods {

    String good;
    double price;

    public Goods(String good, double price) {
        this.good = good;
        this.price = price;
    }

}

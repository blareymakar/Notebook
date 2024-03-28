package Homework6;

import java.util.Random;

public class Notebook {


    String id; //Заказной номер
    String brand; //Название ноутбука*
    String cpu; //Процессор
    String graphic_card; // Видео карта
    String screen_size; //Размер экрана
    String hd_capacity; // Объем SSD*
    String ram_capacity; //Объем оперативной памяти*
    String oc; //Операционная система*
    String color;//Цвет ноутбука*
    boolean in_stock;//Наличие на складе
    int price;//Цена*

    @Override
    public String toString() {
        return "Notebook{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", cpu='" + cpu + '\'' +
                ", graphic_card='" + graphic_card + '\'' +
                ", screen_size='" + screen_size + '\'' +
                ", hd_capacity='" + hd_capacity + '\'' +
                ", ram_capacity='" + ram_capacity + '\'' +
                ", oc='" + oc + '\'' +
                ", color='" + color + '\'' +
                ", in_stock=" + in_stock +
                ", price=" + price +
                '}';
    }
}


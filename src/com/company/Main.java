package com.company;
import java.util.Scanner;

public class Main {
    static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        int meny_point = 0;

        do {
            //главное меню
            System.out.println("###Главное меню###");
            System.out.println("1. Шифрование с помощью операции XOR");
            System.out.println("2. Шифрование с помощью  циклического сдвига");
            System.out.println("3. Выход");

            meny_point = in.nextInt();

            if (meny_point == 1) {//шифрование методом XOR
                System.out.print("Введите сообщение: ");
                in.nextLine();
                String message = in.nextLine();//Ввод сообщения
                message = encode(message,"Code");//шифрование
                System.out.println("Шифрованное "+message);//вывод шифровки
                System.out.println("Расшифрованное "+encode(message,"Code"));//дешифрование и вывод
            }
            else if (meny_point == 2) {//код Цезаря
                System.out.print("Введите сообщение: ");
                in.nextLine();
                String message = in.nextLine();//Ввод сообщения
                System.out.print("Введите сдвиг: ");
                int shift = in.nextInt();//Ввод сдвига
                message = shift_code(message,shift);//шифрование
                System.out.println("Шифрованное "+message);//вывод шифровки
                System.out.println("Расшифрованное "+shift_code(message,-shift));//дешифрование и вывод
            }

        } while (meny_point < 3 && meny_point > 0);

    }

    public static String shift_code(String message, int shift) {//Циклический сдвиг
        String res = "";
        int k;
        for (int i = 0; i < message.length(); i++) {
            res+=(char)detect_alfa((int)message.charAt(i),(int)message.charAt(i)+shift);
            //repeat(((int)message.charAt(i)+shift),(int)'A',(int)'Z');
        }
        return res;
    }

    public static int detect_alfa(int k, int new_index) {//определение алфавита и циклический сдвиг в его пределах
        if (k >= (int)'0' && k <= (int)'9') {
            return repeat(new_index,(int)'0',(int)'9');
        }
        else if (k >= (int)'a' && k <= (int)'z') {
            return repeat(new_index,(int)'a',(int)'z');
        }
        else if (k >= (int)'а' && k <= (int)'я') {
            return repeat(new_index,(int)'а',(int)'я');
        }
        else if (k >= (int)'А' && k <= (int)'Я') {
            return repeat(new_index,(int)'А',(int)'Я');
        }
        else if (k >= (int)'A' && k <= (int)'Z') {
            return repeat(new_index,(int)'A',(int)'Z');
        }
        else {
            return k;
        }
    }

    public static String encode(String message, String key) {//посимвольное сложение по модулю два сообщнеия с ключём
        String res = "";
        for (int i = 0; i < message.length(); i++) {
            char sumb = (char)detect_alfa((int)message.charAt(i),((int)message.charAt(i))^(int)(key.charAt(repeat(i,0, key.length()-1))));
            res += sumb;
        }
        return res;
    }

    public static int repeat(int num, int start, int end) {//циклический сдвиг
        int res = num;
        int base = Math.abs(end-start)+1;
        if (res > end) {
            res -=  (1+((res-end-1)/base))*base;
        }
        else if (res < start) {
            res +=  (1+((start-res-1)/base))*base;
        }

        return res;
    }
}
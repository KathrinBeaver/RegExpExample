package ru.mai;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    (точка|\\w+|символ)

    Метасимволы

  ^     - (крышка, цирркумфлекс) начало проверяемой строки
  $     - (доллар) конец проверяемой строки
  .     - (точка) представляет собой сокращенную форму записи для символьного класса, совпадающего с любым символом
  |     -  означает «или». Подвыражения, объединенные этим способом, называются альтернативами (alternatives)
  ?     - (знак вопроса) означает, что предшествующий ему символ является необязательным
  +     -  обозначает «один или несколько экземпляров непосредственно предшествующего элемента
  *     –  любое количество экземпляров элемента (в том числе и нулевое)
  \\d   –  цифровой символ
  \\D   –  не цифровой символ
  \\s   –  пробельный символ
  \\S   –  не пробельный символ
  \\w   –  буквенный или цифровой символ или знак подчёркивания
  \\W   –  любой символ, кроме буквенного или цифрового символа или знака подчёркивания

  Квантификаторы

    +     - Одно или более {1,}
    *     - Ноль или более {,}
    ?     - Ноль или одно {0,1}
    {n}   - Ровно n раз
    {m,n} - От m до n включительно
    {m,}  - Не менее m
    {,n}  - Не более n

    Символьные классы
    [vvvj8787hkjhl]+

    Москва
    москва

    (м|М)осква
    [мМ]осква

    МоСкВа
    е ё

    [^ABC]
    [0-9A-Z]

    Группы ()
    (  (  )  ) (  (  )  )
    Группы нумеруются по порядку ( скобок

    IP - подумать над регулярным выражением для проверки
    127.0.0.1
    192.83.20.1

    [a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+.[a-z]{2,3}
    [0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}

 */


public class Main {

    public static void main(String[] args) {


        String str = "станция \"Молодежная\"";
        String str1 = "C:\\Projects";
        String regExp = "[vvvj8787hkjhl]+";

//        userNamesCheck();
//        deleteDuplicates();

        // Входные данные для задачи про перевод из см в м
//        String testString = "Длина доски 1 равна 30см, длина другой доски - "
//                + "55 см, а еще одной 56.476 см. И еще есть 45,7 см.";
        regExpTester();
    }

    /**
     * Валидация логинов
     */
    private static void userNamesCheck()
    {
        System.out.println("Проверка регулярным выражением:");
        System.out.println(checkWithRegExp("_@Masha"));
        System.out.println(checkWithRegExp("_Ivan"));
        System.out.println(checkWithRegExp("vo"));
        System.out.println(checkWithRegExp("vova"));
        System.out.println(checkWithRegExp("Вася"));

        System.out.println("\nПроверка стандартными средствами:");
        System.out.println(dumbCheck("_@Masha"));
        System.out.println(dumbCheck("_Ivan"));
        System.out.println(dumbCheck("vo"));
        System.out.println(dumbCheck("vova"));
        System.out.println(dumbCheck("Iv@n"));
    }

    /**
     * Валидация логинов (средствами регулярных выражений)
     * @param userNameString - строка  логина
     * @return - результат валидации
     */
    private static boolean checkWithRegExp(String userNameString){
//        Pattern p = Pattern.compile("^[a-z0-9_]+ - [a-z0-9_]{3,16} - [a-z0-9_]{3,}$");
//        Pattern p1 = Pattern.compile("^abc-[0-9]{3}$");
//        Pattern p = Pattern.compile("^\\w{3,16}$");
        Pattern p = Pattern.compile("^[a-z0-9_]{3,7}$");
//        Matcher m = p.matcher(userNameString, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    /**
     * Валидация логинов (строковыми методами)
     * @param userNameString - строка  логина
     * @return - результат валидации
     */
    private static boolean dumbCheck(String userNameString) {

        if(userNameString.length() < 3 || userNameString.length() > 16) {
            return false;
        }

        char[] symbols = userNameString.toCharArray();

        String validationString = "abcdefghijklmnopqrstuvwxyz0123456789_";
        for(char ch : symbols) {
            if(validationString.indexOf(ch) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Удаление повторяющихся символов
     */
    private static void deleteDuplicates() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the character string:");
        String str = in.nextLine();

        String result = new StringBuilder(str).reverse().toString();
        result = result.replaceAll("(.)(?=.*\\1)", "");
        result = new StringBuilder(result).reverse().toString();
        System.out.println(result);
    }

    /**
     * Тестер регулярных выражений
     */
    private static void regExpTester() {
        Scanner sc = new Scanner(System.in);
        String exit = "";
        while(!exit.equals("exit")) {
            System.out.println("Введите шаблон: ");
            Pattern pattern = Pattern.compile(sc.nextLine());

            System.out.println("Введите строку для поиска: ");
            Matcher matcher = pattern.matcher(sc.nextLine());

            boolean found = false;
            while(matcher.find()) {
                System.out.println(String.format("Найдено совпадение:" +
                    " \"%s\" начинается с " +
                    "индекса %d и заканчивается индексом %d.",
                    matcher.group(),
                    matcher.start(),
                    matcher.end()));

                    System.out.println("Group count = " + matcher.groupCount());

                    for (int i = 0; i <= matcher.groupCount(); i++) {
                        System.out.println(matcher.group(i));
                    }
                found = true;
            }
            if(!found) {
                System.out.println("Совпадения не найдены.");
            }
            System.out.println("Введите \"exit\" для выхода: ");
            exit = sc.nextLine();
        }
    }
}

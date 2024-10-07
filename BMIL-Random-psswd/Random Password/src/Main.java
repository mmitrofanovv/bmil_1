import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
Реализовать программу для генерации паролей пользователей.
Программа должна формировать случайную последовательность символов
длины L, при этом должен использоваться алфавит из A символов.
Составить частотный словарь вхождения символов алфавита в парольную
фразу.
 */



public class Main {
    public static void shuffleStringBuilder(StringBuilder sb) {
        int length = sb.length();
        char[] charArray = new char[length];
        sb.getChars(0, length, charArray, 0);

        Random random = new Random();
        for (int i = length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }

        sb.setLength(0); // Очищаем StringBuilder
        sb.append(charArray); // Добавляем перемешанные символы обратно
    }

    public static void main(String[] args) {
        String A = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+";
        char[] AA = A.toCharArray();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String number = "1234567890";
        String spec_symbol = "!@#$%^&*()_+";

        char[] uppers = upper.toCharArray();
        char[] lowers = lower.toCharArray();
        char[] numbers = number.toCharArray();
        char[] spec_symbols = spec_symbol.toCharArray();



        Scanner scanner = new Scanner(System.in);
        System.out.print("Длинна пароля: ");
        int L = scanner.nextInt();

        Random rand = new Random();

        StringBuilder passwd = new StringBuilder();

        boolean[] used = new boolean[AA.length];

        for (int i = 0; i < L - 4; i++) {
            if (L > A.length()) {
                System.out.println("Ошибка длинны");
                break;
            } else {
                int index;

                // Генерация уникального символа, который ещё не использован
                do {
                    index = rand.nextInt(AA.length);
                } while (used[index]); // Повторяем, если символ уже использован

                passwd.append(AA[index]);
                used[index] = true; // Отмечаем символ как использованный
            }
        }


        passwd.append(uppers[rand.nextInt(uppers.length)]);
        passwd.append(lowers[rand.nextInt(lowers.length)]);
        passwd.append(numbers[rand.nextInt(numbers.length)]);
        passwd.append(spec_symbols[rand.nextInt(spec_symbols.length)]);

        shuffleStringBuilder(passwd);

        String password = passwd.toString();
        System.out.println("Сгенерированный пароль: " + passwd);

        int[] frequencyArray = new int[AA.length];
        for (char c : password.toCharArray()) {
            for (int i = 0; i < AA.length; i++) {
                if (AA[i] == c) {
                    frequencyArray[i]++;
                    break;
                }
            }
        }

        System.out.println("Частотный словарь вхождения с   имволов:");
        for (int i = 0; i < AA.length; i++) {
                System.out.println("Символ: '" + AA[i] + "' - Частота: " + frequencyArray[i]);
        }
    }
}
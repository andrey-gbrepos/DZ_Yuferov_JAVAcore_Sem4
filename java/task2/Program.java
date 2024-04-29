
import java.util.List;

public class Program {

    static public void main(String[] args){
        StendForTest stendForTest = new StendForTest();
        System.out.println
                ("Генерация попыток заведения счета (демонстрация IllegalArgumentException): ");
        List <Account> listAccounts = stendForTest.stendCreateListAccounts();
        System.out.println(listAccounts);
        System.out.println();

        System.out.println("Вывод перечня попыток добавления средств на счет (IllegalArgumentException): ");
        for (int i = 0; i < 5; i++) {
            stendForTest.stendAddToDeposit(listAccounts);
        }
        System.out.println(listAccounts);
        System.out.println();

        System.out.println("Вывод перечня попыток списания со счета (InsufficientFundsException): ");
        for (int i = 0; i < 5; i++) {
            stendForTest.stendOutFromDeposit(listAccounts);
        }
        System.out.println(listAccounts);
        System.out.println();

        System.out.println("Блокировка счетов и вывод списка");
        stendForTest.randomAccountBlock(listAccounts);
        System.out.println(listAccounts);
        System.out.println();

        System.out.println("n-е количество транзакций");
        int n =10;
        for (int i = 0; i < n; i++) {
            stendForTest.stendTransaction(stendForTest.accountGenerator(listAccounts),
                                        stendForTest.accountGenerator(listAccounts),
                                        10000.0);
        }
    }
}

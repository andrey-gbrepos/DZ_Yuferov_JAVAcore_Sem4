import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StendForTest {
    Random random = new Random();
    Service service = new Service();

    /**
     * Создает  тестовый список банковских счетов счетов
     */
    public List<Account> stendCreateListAccounts () {
        List <Account> listAccounts = new ArrayList<>();
        String[] names = new String[]
                {"Artur", "Adel", "Bruno", "Bella", "Henry", "Inga", "Marta",
                        "Martin", "Jek", "Maik", "Oskar", "Timur", "Felix", "German"};

        for (int i = 0; i < 10; i++) {
            Double tempCountSum = random.nextDouble(-3.0,9.0)*1000;// может быть отрицательным
            String name = names[random.nextInt(names.length)];
            try{
                System.out.printf("%s %.2f %s \n", "Сумма для открытия счета: ", tempCountSum, " ");
                service.createInitalAccount(listAccounts, name, tempCountSum);
            }
            catch (IllegalArgumentException e){
                System.out.println(e);
                System.out.println("Счет не открыт.");
            }
        }
        return listAccounts;
    }

    /**
     * Эмулирует процесс добавления на счета случайно сгенерированных сумм
     * @param listAccounts
     * @return
     */
    public List <Account> stendAddToDeposit (List <Account> listAccounts){
        // получаем случайный номер в списке
        int tempNum = random.nextInt(listAccounts.size());
        Account account = listAccounts.get(tempNum);
        // случайная сумма (может бытть отрицательной)
        Double addSumToDeposit = random.nextDouble(-3.0,9.0)*1000;
        System.out.printf("%s %d %s %s %s %.2f","Внесение средств на счет ", account.getIdAccount(), " ",
                account.getNameClient(), " остаток: ", account.getDepositAccountValue());
        System.out.printf("%s %.2f \n"," сумма внесния: ", addSumToDeposit);
        try{
            service.addSumToDeposit(listAccounts.get(tempNum), addSumToDeposit);
            System.out.printf("%s %d %s %.2f \n","Остаток на счете: ", account.getIdAccount(), " ",
                    listAccounts.get(tempNum).getDepositAccountValue());
        }catch (IllegalArgumentException e){
            System.out.println(e);
            System.out.println("Зачисление отклонено");
        }
        return listAccounts;
    }

    public List <Account> stendOutFromDeposit (List <Account> listAccounts){
        int tempNum = random.nextInt(listAccounts.size());
        Double outSumFromDeposit = random.nextDouble(1000.0,20000.0);
        try{
            service.outFromDeposit(listAccounts.get(tempNum), outSumFromDeposit);
        }catch (InsufficientFundsException ex){
            System.out.println(ex);
        }
        return listAccounts;
    }

}

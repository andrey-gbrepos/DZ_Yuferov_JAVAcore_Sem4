import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StendForTest {
    Random random = new Random();
    Service service = new Service();
    Transaction transaction = new Transaction();

    /**
     * Создает  тестовый список банковских счетов счетов
     */
    public List<Account> stendCreateListAccounts () {
        List <Account> listAccounts = new ArrayList<>();
        String[] names = new String[]
                {"Artur", "Adel", "Bruno", "Bella", "Henry", "Inga", "Marta",
                        "Martin", "Jek", "Maik", "Oskar", "Timur", "Felix", "German"};

        for (int i = 0; i < 10; i++) {
            Double tempCountSum = random.nextDouble(-3.0,15.0)*1000;// может быть отрицательным
            String name = names[random.nextInt(names.length)];
            try{
                System.out.printf("%s %.2f %s \n", "Сумма для открытия счета: ", tempCountSum, " ");
                service.createInitalAccount(listAccounts, name, tempCountSum, true);
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
        Double addSumToDeposit = random.nextDouble(-3.0,15.0)*1000;
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

    /**
     * Снимает случайную сумму со счета
     * @param listAccounts
     * @return
     */
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

    /**
     * Осуществляет транзакцию между двумя счетами
     * @param accountFrom
     * @param accountTo
     * @param sum
     */
    public void stendTransaction (Account accountFrom, Account accountTo, Double sum){
        System.out.println("Транзакция со счета " + accountFrom);
        System.out.println("на счет " + accountFrom + " суммы: " + sum);
        try{
            if (transaction.conformAccountTransaction(accountFrom, accountTo,sum)){
                accountFrom.setDepositAccountValue(accountFrom.getDepositAccountValue()- sum);
                accountTo.setDepositAccountValue(accountTo.getDepositAccountValue()+ sum);
                System.out.println("Перечислено: " + sum);
                System.out.println("Со счета " + accountFrom);
                System.out.println("На счет " + accountTo);
            }
        }catch (IllegalAccountExeption e) {
            System.out.println(e);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }catch (InsufficientFundsException e){
            System.out.println(e);
        }
    }

    /**
     * Выбирает случайный счет из списка
     * @param accountList
     * @return
     */
    public Account accountGenerator (List <Account> accountList){
         return accountList.get(random.nextInt(accountList.size()));
    }

    /**
     * блокирует счета по случайному закону
     * @param accountList
     */
    public void randomAccountBlock (List<Account> accountList){
        for (int i = 0; i < accountList.size(); i++) {
            accountList.get(i).setSignCountLegal(random.nextBoolean());
        }
    }

}

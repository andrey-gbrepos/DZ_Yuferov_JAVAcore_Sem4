import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {

    /**
     * Создает новый банковский счет
     * @param listAccounts
     * @param name Имя вкладчика
     * @param initialAccountSum
     * @throws IllegalArgumentException
     */
    public void createInitalAccount (List <Account> listAccounts, String name,
                                     Double initialAccountSum, Boolean signAccounLegal)  throws IllegalArgumentException {
        Account account = new Account();
        account.findIdAccount(listAccounts);
        System.out.println("Открывается счет " +  account.getIdAccount() +
                " для вкладчика "+ name);
        if (initialAccountSum < 0)
            throw new IllegalArgumentException("Вклад при заведении счета не может быть отрицательным!", initialAccountSum);
        account.setNameClient(name);
        account.setDepositAccountValue(initialAccountSum);
        listAccounts.add(account);
        System.out.printf("%s %d %s %.2f \n", "-> Счет ", account.getIdAccount(),
                " открыт. Остаток на депозите ", account.getDepositAccountValue());
    }

    /**
     * Добавляет вклад на существующий счет
     * @param account
     * @param sumToAddDeposit
     * @throws IllegalArgumentException
     */
    public void  addSumToDeposit (Account account, Double sumToAddDeposit)
                                                    throws IllegalArgumentException{
        if (sumToAddDeposit < 0)
            throw new IllegalArgumentException(" Вносимая сумма не может быть отрицательной!", sumToAddDeposit);
        account.setDepositAccountValue(account.getDepositAccountValue()+sumToAddDeposit);
    }

    /**
     * Уменьшает значение account.depositAccountValue на величину outFromDepositSum
     * и пробрасывает исключение на следующий уровень.
     * @param account
     * @param outFromDepositSum
     * @throws InsufficientFundsException
     */
    public void outFromDeposit(Account account, Double outFromDepositSum)
                                                throws InsufficientFundsException{
        System.out.print("Снятие со счета " + account + " суммы ");
        System.out.printf("%.2f \n",outFromDepositSum);

        if (account.getDepositAccountValue() < outFromDepositSum)
            throw new InsufficientFundsException(outFromDepositSum, account.getDepositAccountValue());
        account.setDepositAccountValue(account.getDepositAccountValue()-outFromDepositSum);
        System.out.printf("%s %.2f \n","Текущее состояние счета: ", account.getDepositAccountValue());
    }
    public void setCountBlock(Account account){
        account.setSignCountLegal(false);
    }
    public void setCountAllow(Account account){
        account.setSignCountLegal(true);
    }
}

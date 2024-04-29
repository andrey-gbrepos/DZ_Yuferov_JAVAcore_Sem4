import java.util.List;

public class Transaction {
    private Account accountFrom;
    private Account accountTo;

public  Boolean conformAccountTransaction(Account accountFrom,
                                          Account accountTo, Double sum)
        throws IllegalAccountExeption, IllegalArgumentException, InsufficientFundsException{
    Boolean tempBool1 = false;
    Boolean tempBool2 = false;
    if (accountFrom.getSignCountLegal() == false) throw new IllegalAccountExeption(accountFrom.getIdAccount());
    if (accountTo.getSignCountLegal() == false) throw new IllegalAccountExeption(accountTo.getIdAccount());
    if (sum < 0) throw new IllegalArgumentException("Сумма перечисления не может быть отрицательной! Транзакция не возможна!", sum);
    if (accountFrom.getDepositAccountValue() < sum) throw new InsufficientFundsException(sum, accountFrom.getDepositAccountValue());
    return true;
}


    public Account getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }
    public Account getAccountTo() {
        return accountTo;
    }
    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }
}

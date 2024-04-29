import java.util.List;

public class Account {
    private Integer idAccount;
    private String nameClient;
    private Double depositAccountValue;
    private Boolean signCountLegal = true;

    /**
     * Возвращает ID вклада
     * @return
     */
    public Integer getIdAccount() {
        return idAccount;
    }
    /**
     * Устанавливает ID счета как следующий за максимальным ID в списке счетов
     * @param accounts
     */
    public void findIdAccount(List <Account> accounts) {
        int max = 0;
        for (int i = 0; i < accounts.size(); i++) {
           if (accounts.get(i).idAccount > max) max = accounts.get(i).idAccount;
        }
        this.idAccount = max+1;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Boolean getSignCountLegal() {
        return signCountLegal;
    }

    public void setSignCountLegal(Boolean signCountLegal) {
        this.signCountLegal = signCountLegal;
    }

    /**
     * Возвращает значение суммы вклада  на счету
     * @return
     */
    public Double getDepositAccountValue() {
        return depositAccountValue;
    }
    /**
     * Устанавливает значение вклада вносиомой на свой счет
     */
    public void setDepositAccountValue(Double valueToDeposit) {
            this.depositAccountValue = valueToDeposit;
    }

    @Override
    public String toString(){
        return String.format("\n %10d \t %10s \t %10.2f \t %s",
                idAccount, nameClient, depositAccountValue, signCountLegal? "allowed":"blocked");
    }
}

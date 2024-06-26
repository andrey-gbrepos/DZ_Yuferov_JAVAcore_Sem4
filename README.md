# DZ_Yuferov_JAVAcore_Sem4
# Задание 1
- ##  Создать программу управления банковским счетом (Account). Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств. 

Создан класс **Account**, полями которого являются:
- **Integer idAccount**
- **String nameClient**
- **Double depositAccountValue**

Для работы с ними созданы соответствующие **getters()** и **setters()**.

Переопределен метод **toString()** для вывода на печать объектов класса **Account**. 
```
@Override
    public String toString(){
        return String.format("\n %10d \t %10s \t %10.2f",
                idAccount, nameClient, depositAccountValue);
    }
```
- ## Программа должна обрабатывать следующие исключительные ситуации:
    - ## Попытка создать счет с отрицательным начальным балансом должна вызывать исключение --**IllegalArgumentException**-- с соответствующим сообщением.
    - ## Попытка внести депозит с отрицательной суммой должна вызывать исключение --**IllegalArgumentException**-- с соответствующим сообщением.
    - ## Попытка снять средства, сумма которых превышает текущий баланс, должна вызывать исключение --**InsufficientFundsException**-- с сообщением о недостаточных средствах и текущим балансом.

Созданы два класса для обработки исключений: 
- Класс **IllegalArgumentException** для обработки случаев внести отрицательную сумму на счет или завести счет с отрицательной суммой.
```
public class IllegalArgumentException extends Exception{
    Double clientAccountSum;
    public IllegalArgumentException(String message, Double clientAccountValue) {
        super(message);
        this.clientAccountSum = clientAccountValue;
    }
}
```
- Класс **InsufficientFundsException** для обработки случаев снятия со счета средств, в количестве превышаемом средства на банковском счете.
```
public class InsufficientFundsException extends Exception{
   Double outClientAccountSum;
   Double clientAccountSum;
    public InsufficientFundsException(Double outClientAccountSum, Double clientAccountSum) {
        super();
        this.outClientAccountSum = outClientAccountSum;
        this.clientAccountSum = clientAccountSum;
    }
    @Override
    public String getMessage(){
            return String.format("Недостаточно средств. Попытка снять %.2f, Остаток на счете: %.2f",
                    outClientAccountSum, clientAccountSum);
    }
}
```
Оба класса являются наследниками класса **Exeption** - проверяемого типа исключений.
Для класса  **InsufficientFundsException** переопределен метод **getMessage()** с соответствующим сообщением.

Для обработки банковских операций: 
- Заведения счета; 
- Зачисления средств на существующий счет;
- Снятия средств со счета

создан класс **Service** с соответствующими методами:
**createInitalAccount**, **addSumToDeposit**, **outFromDeposit**.

Причем в методах **createInitalAccount** и **addSumToDeposit** проброшено исключение **IllegalArgumentException** (Не допустима отрицательная сумма вклада).
```
public void createInitalAccount (List <Account> listAccounts, String name, Double initialAccountSum)  throws IllegalArgumentException {
    ...
 if (initialAccountSum < 0) throw new IllegalArgumentException("Вклад при заведении счета не может быть отрицательным!", initialAccountSum);
 ...}
```
```
public void  addSumToDeposit (Account account, Double, sumToAddDeposit) throws IllegalArgumentException{
 if (sumToAddDeposit < 0) throw new IllegalArgumentException(" Вносимая сумма не может быть отрицательной!", sumToAddDeposit);
...
}
```
Отличие, между ними, заключается в разных сообщениях, соответствующих смыслу методов и передоваемого как параметр, объекту типа **IllegalArgumentException**.

А в методе **outFromDeposit** реализовано исключение **InsufficientFundsException** (Не допустимо снять со счета больше чем там лежит), и поскольку, в нем переопределен метод **getMessage()** со своим форматированным сообщением, то сообщение, как параметр, из метода **outFromDeposit** не передается.
```
public void outFromDeposit(Account account, Double outFromDepositSum) throws InsufficientFundsException{
    ...
if (account.getDepositAccountValue() < outFromDepositSum) throw new InsufficientFundsException(outFromDepositSum, account.getDepositAccountValue());
...
}
```
 В классе **StendForTest** реализованы тестовые методы:
 - для заполнения списка счетов -  метод **stendCreateListAccounts()**;
 - для перечисления средств на существующий счет - метод **stendAddToDeposit**
 - для снятия средств со счета - метод **stendOutFromDeposit**

Методы используют генератор псевдослучайных чисел. 

Кроме того, с помощью конструкции **try-catch** обрабатывают исключения соответствующих вызываемых методов из класса **Service**.

Для демонстрации работы методов создан класс **Program** с методом **Main()** как точкой входа.
# Задание 2*
- ##  Создать несколько типов счетов, унаследованных от **Account**, например: **CreditAcciunt**, **DebitAccount**. Создать класс **Transaction**, позволяющий проводить транзакции между счетами (переводить сумму с одного счета на другой). Класс **Transaction** должен возбуждать исключение в случае неудачной попытки перевести деньги с одного счета на другой. Продемонстрируйте работу вашего приложения: Программа должна обрабатывать все исключения с помощью конструкции **try-catch**, выводя соответствующие сообщения об ошибках.

В рамках второй задачи созданы два класса наследников от класса **Account** это **CreditAcciunt** и **DebitAccount**.

Создан дополнительный класс  **IllegalAccountExeption** который будет обрабатывать признак блокировки счета.


 Создан класс **Transaction**. Имеет два поля: **Account accountFrom** -счет, с которого перечисляются средства и  **Account accountTo** счет, на который перечисляются, а так же метод **conformAccountTransaction()** который возвращает true при проверки условий транзакции и пробрасывает три исключения:
 - **IllegalAccountExeption**
 - **IllegalArgumentException**
 - **InsufficientFundsException**

 
В класс **Account** введено дополнительное поле **Boolean signCountLegal** для обозначения: заблокирован данный счет или нет.
В классе **StendForTest** созданы три доп. метода: **accountGenerator()** выбирает случайный счет из списка, 
**randomAccountBlock()** - блокирует счета из списка по случайному закону и **stendTransaction()** - в котором осуществляется операция вычитания суммы с одного счета и прибавления к другому. В этом же методе происходит обработка исключений через структуру **try-cath**.


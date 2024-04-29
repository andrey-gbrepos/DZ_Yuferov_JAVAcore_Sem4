import java.util.List;

public class IllegalAccountExeption extends Exception{
Integer countNum; // номер счета
    public IllegalAccountExeption(Integer countNum) {
        super();
        this.countNum = countNum;
    }
    @Override
    public String getMessage(){
        return String.format("Номер счета -  %d, заблокирован! Транзакция не возможна!",
                countNum);
    }
}

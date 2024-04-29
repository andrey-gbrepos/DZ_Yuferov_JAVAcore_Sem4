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

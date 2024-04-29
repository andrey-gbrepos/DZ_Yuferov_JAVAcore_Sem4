public class IllegalArgumentException extends Exception{
    Double clientAccountSum;
    public IllegalArgumentException(String message, Double clientAccountValue) {
        super(message);
        this.clientAccountSum = clientAccountValue;
    }
}

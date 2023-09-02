package exceptions;

public class DriverNotSupportedException extends Exception {

  public DriverNotSupportedException(String browser) {
    super(String.format("Браузер %s не поддерживается", browser));
  }

}

package fr.pantheonsorbonne.exception;

public class WateringFailed extends RuntimeException {
  public WateringFailed(String message) {
    super(message);
  }
}

package ar.edu.unrc.dc.patterns;

public interface Subject {

   public void attach(DisplayObserver observer); 
   public void deattach(DisplayObserver observer); 
   public void notifyObservers(String command);
}

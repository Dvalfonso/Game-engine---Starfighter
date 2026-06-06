package ar.edu.unrc.dc.tdd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import ar.edu.unrc.dc.controllers.StarfighterGameEngine;

public class StarfighterGameEngineTest {
   @Test 
   public void checkInvalidRowsArgument() {
       StarfighterGameEngine sfGameEngine = new StarfighterGameEngine();

       assertThrows(IllegalArgumentException.class, () -> sfGameEngine.play(2,10));
       assertThrows(IllegalArgumentException.class, () -> sfGameEngine.play(20,10));
   }

   @Test 
   public void checkInvalidColsArgument() {
       StarfighterGameEngine sfGameEngine = new StarfighterGameEngine();

       assertThrows(IllegalArgumentException.class, () -> sfGameEngine.play(10,4));
       assertThrows(IllegalArgumentException.class, () -> sfGameEngine.play(3,31));
   }

   @Test 
   public void checkInvalidAbort() {
       StarfighterGameEngine sfGameEngine = new StarfighterGameEngine();

       assertThrows(RuntimeException.class, () -> sfGameEngine.abort());
   }
}

package ar.edu.unrc.dc.patterns.projectileList;

import java.util.*;
import ar.edu.unrc.dc.models.cell.Projectile;

public interface ProjectileList {

   public void add(Projectile projectile); 
   public void remove(Projectile projectile); 
   public void removeAll();
   public void move();
   public List<Projectile> getList(); 
}

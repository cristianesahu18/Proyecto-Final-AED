/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;
import java.awt.Point;
/**
 *
 * @author abel
 */
public class Jugada {
Ficha f;
Point p;
public Jugada(Ficha f,Point p ){this.f=f;this.p=p;} 
        
public String toString(){
return f.icon+"  "+p.getX()+","+p.getY();
}  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author abel
 */
public class Peon extends Ficha{
   
      @Override
  public Ficha clone(){
  return new Peon(tipo,getX(),getY(),icon,(Rey)rey.clone(),ajedrez);
  } 
    
    public Peon(int tipo,int x, int y, String icon, Rey rey,AjedrezApp ajedrez) {
        super(tipo,x, y, icon,rey,ajedrez); }
  
    public boolean puede(int posx,int posy){
  
   if (tipo==1&&posx==getX()&&         
       ((0<posy/95-getY()/95&&posy/95-getY()/95<2)||
       (0<posy/95-getY()/95&&posy/95-getY()/95<3&&posy/95==3))    
           ) return true;
  
   if (tipo==2&&posx==getX()&&
       ((0<getY()/95-posy/95&&getY()/95-posy/95<2)||
        (0<getY()/95-posy/95&&getY()/95-posy/95<3&&posy/95==4))
           
           ) return true;
   
   return false;
  }

public boolean come(Ficha f){
if (tipo==2) return (tipo!=f.tipo&&f.icon!=""&&Math.abs(getX()/95-f.getX()/95)==1&&getY()/95-f.getY()/95==1);
        else return (tipo!=f.tipo&&f.icon!=""&&Math.abs(getX()/95-f.getX()/95)==1&&f.getY()/95-getY()/95==1);
}

}


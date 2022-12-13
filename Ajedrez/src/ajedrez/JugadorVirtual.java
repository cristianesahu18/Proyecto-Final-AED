/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import javax.swing.JPanel;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.www.content.image.jpeg;
/**
 *
 * @author abel
 */
public class JugadorVirtual {
    
    int tipo;
    AjedrezApp ajedrez;
    Rey rey;
    Random ran=new Random();
    public JugadorVirtual(Rey rey,AjedrezApp ajedrez){
    this.tipo=rey.tipo;
    this.rey=rey;
    this.ajedrez=ajedrez;
    }

  public void juega(){
 if (ajedrez.blanco.jakemate()||ajedrez.negro.jakemate()) return;  
      
 ArrayList todasjugadas=new ArrayList();
  Component [] w=ajedrez.tablero.getComponents();
  for(int i=0;i<w.length;i++)
  {Ficha fi=(Ficha)w[i];
   if (fi.tipo==tipo&&fi.jugadas().size()!=0)  {
       
       for(int j=0;j<fi.jugadas().size();j++)
       todasjugadas.add(new Jugada(fi,(Point)(fi.jugadas().get(j))));}
 
  }
  
 
 
 Jugada jaux=null;
 for(int i=0;i<todasjugadas.size();i++)
  {Jugada j=(Jugada)todasjugadas.get(i);
  if (((Ficha)(ajedrez.tablero.getComponentAt(j.p))).icon!="") jaux=j;
      
  }
  
 if (jaux!=null&&jaux.f.mecomen((int)jaux.p.getX(),(int)jaux.p.getY())&&!(jaux.f instanceof Peon))
     
 jaux=null;
 
 ArrayList fichas=new ArrayList();
  w=ajedrez.tablero.getComponents();
  for(int i=0;i<w.length;i++)
  {Ficha fi=(Ficha)w[i];
   if (fi.tipo==tipo&&fi.jugadas().size()!=0)  fichas.add(fi);
  }    
  
  for(int i=0;i<fichas.size()&&jaux==null;i++)
  {Ficha fi=(Ficha)fichas.get(i);
  if (fi.mecomen(fi.getX(),fi.getY())&&!(fi instanceof Peon))
                                      {ArrayList ar=fi.jugadas();
                                       for(int j=0;j<10&&jaux==null;j++)
                                          { jaux=new Jugada(fi,(Point)(ar.get((int)(ran.nextFloat()*ar.size()))));
  
                                            if (jaux.f.mecomen((int)jaux.p.getX(),(int)jaux.p.getY())) jaux=null;
                                          }  
  
                                      }
  
  }  
   
 
 for(int i=0;i<10&&jaux==null;i++)
  { jaux=(Jugada)(todasjugadas.get((int)(ran.nextFloat()*todasjugadas.size())));
  
     if (jaux.f.mecomen((int)jaux.p.getX(),(int)jaux.p.getY())) jaux=null;
  }
 
  if (jaux==null) jaux=(Jugada)(todasjugadas.get((int)(ran.nextFloat()*todasjugadas.size())));
  
  Ficha f=jaux.f;
  Point p=jaux.p;
  int x=(int)p.getX();
  int y=(int)p.getY();
  f.auxx=f.getX();
  f.auxy=f.getY();
  
  
  Ficha c=(Ficha)ajedrez.tablero.getComponentAt(x, y);
            
                                            
           if (c.icon==""){ 
                                         
              
                       ajedrez.tablero.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(f.getX(),f.getY(), 95, 95));
                       ajedrez.tablero.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 95, 95));
                       Color aux=f.getBackground();
                       f.setBackground(c.getBackground());
                       c.setBackground(aux);
                       ajedrez.texto.setText(ajedrez.texto.getText()+f.icon+"  "+f.letras[x/95]+","+(y/95+1)+"\n");
                       ajedrez.turno=!ajedrez.turno;
                        }
      
             
           else if  (!(c instanceof Rey)){                         
                        ajedrez.tablero.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(f.getX(),f.getY(), 95, 95));
                        ajedrez.tablero.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 95, 95));
                        Color aux=f.getBackground();
                        f.setBackground(c.getBackground());
                        c.setBackground(aux);
                        
                      
                        if (!c.equals(f)) {ajedrez.tablero.remove(c);boolean t=ajedrez.turno;
                                             Ficha nula=new NULA(0,f.getX()/95,f.getY()/95,"",rey,ajedrez);
                                             ajedrez.turno=t;}
                      ajedrez.texto.setText(ajedrez.texto.getText()+f.icon+" come "+c.icon+"\n");
                      ajedrez.texto.setText(ajedrez.texto.getText()+f.icon+"  "+f.letras[x/95]+","+(y/95+1)+"\n");
                      ajedrez.turno=!ajedrez.turno;
                                        
                    if (ajedrez.turno){ajedrez.jblanco.setBackground(Color.GREEN);
                                ajedrez.jnegro.setBackground(Color.red); }
                    else      {ajedrez.jblanco.setBackground(Color.red);
                                ajedrez.jnegro.setBackground(Color.GREEN);} 
                       
                    }
     
      ajedrez.tablero.repaint();
      ajedrez.tablero.validate();
        
      f.libera(new MouseEvent(f,0,0,0,x,y,0,false));
    
  }
}

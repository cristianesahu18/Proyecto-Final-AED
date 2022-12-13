/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;
import com.sun.jndi.url.iiop.iiopURLContext;
import com.sun.xml.internal.bind.v2.WellKnownNamespace;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;
import java.awt.Point;

/**
 *
 * @author abel
 */


public abstract class Ficha extends JButton{
    int auxx,auxy,anteriorx,anteriory;
    int relesedx;
    int relesedy;
    String []letras= {"A","B","C","D","E","F","G","H"};
   
    String icon;
    JPanel tablero;
   JEditorPane texto;
    int tipo;
    Rey rey;
    AjedrezApp ajedrez;
   Ficha temp=null;
  
  
public Ficha clone(){return null;}

  public Ficha(int tipo,int x,int y,String icon,Rey rey,AjedrezApp ajedrez){
  this.ajedrez=ajedrez;
  this.tablero=ajedrez.tablero;
  this.texto=ajedrez.texto;
  this.tipo=tipo;
  this.rey=rey;
  relesedx=anteriorx=x*95;
  relesedy=anteriory=y*95;
  
  this.icon=icon;
  if (icon!="") this.setIcon(new ImageIcon(getClass().getResource("/figuras/"+icon+".png")));
  
  if ((x+y)%2==0) this.setBackground(Color.black);
  else this.setBackground(Color.white);
  tablero.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(x*95, y*95,95,95));
  
  addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                arrastre(evt);
                actualiza(evt);
              }
        });
  addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               libera(evt);
            }
        });
  addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               ArrayList aux=jugadas();
               System.out.println("*************");
               
               for (int i=0;i<aux.size();i++)
               {Point p=(Point)aux.get(i);
               
               System.out.println(letras[(int)p.getX()/95]+","+(p.getY()/95+1));
                
               }
               System.out.println("*************");
               
            }
        });
 } 
  
   public boolean jakemate(){
   Component []c=tablero.getComponents();
   boolean jakemate=true;
   for(int i=0;i<c.length;i++)
    {Ficha f=(Ficha)c[i];
      if (f.tipo==1&&f.jugadas().size()!=0) jakemate=false;
     }
    if (jakemate&&ajedrez.blanco.jake()) {texto.setText(texto.getText()+"Jake mate al rey blanco\n"+ "Fin del juego.");
                                           ajedrez.ganan=2;   
                                         
                                            }
    else  if (jakemate&&!ajedrez.blanco.jake()) {texto.setText(texto.getText()+"Tabla\n"+ "Fin del juego.");
                                           ajedrez.ganan=0;
                                                   
                                            }  
    else{
          c=tablero.getComponents();
          jakemate=true;
          for(int i=0;i<c.length;i++)
          {Ficha f=(Ficha)c[i];
            if (f.tipo==2&&f.jugadas().size()!=0) jakemate=false;
          }
          if (jakemate&&ajedrez.negro.jake()) {texto.setText(texto.getText()+"Jake mate al rey negro\n"+ "Fin del juego.");
                                               ajedrez.ganan=1; 
                                             
          
                                              }
          else if (jakemate&&!ajedrez.negro.jake()) {texto.setText(texto.getText()+"Tabla\n"+ "Fin del juego.");
                                                  ajedrez.ganan=0;
                                                  }
               
        }
    
   
    return jakemate;
   
   }
   
   public void libera(java.awt.event.MouseEvent evt) {
    
       
     
       if (jakemate()) { try{ajedrez.TN.cancel();}catch(Exception ex){;}
                       try{ajedrez.TB.cancel();}catch(Exception ex){;}
                       return;}
    
     
     
     if (ajedrez.pasosreyblanco==22) {texto.setText(texto.getText()+"El rey blanco da 22 pasos Tabla\n"+ "Fin del juego.");
                                                             try{ajedrez.TN.cancel();}catch(Exception ex){;}
                                                            try{ajedrez.TB.cancel();}catch(Exception ex){;}
                                                            ajedrez.ganan=0;
                                                            return;
                                                              }
     if (ajedrez.pasosreynegro==22) {texto.setText(texto.getText()+"El rey negro da 22 pasos Tabla\n"+ "Fin del juego.");
                                                             try{ajedrez.TN.cancel();}catch(Exception ex){;}
                                                             try{ajedrez.TB.cancel();}catch(Exception ex){;}
                                                             ajedrez.ganan=0;
                                                             return;
                                                              }
    
                                        
       if (this==ajedrez.blanco) ajedrez.pasosreyblanco=ajedrez.pasosreyblanco+1;
         else if(tipo==1)  ajedrez.pasosreyblanco=0;
       if (this==ajedrez.negro)  ajedrez.pasosreynegro=ajedrez.pasosreynegro+1;
          else if (tipo==2) ajedrez.pasosreynegro=0; 
     
     
      if ((relesedx!=getX()||relesedy!=getY())
                      &&
                 ((ajedrez.turno&&tipo==1)||(!ajedrez.turno&&tipo==2)))
                      
                                         {   if (icon!="") texto.setText(texto.getText()+icon+"  "+letras[getX()/95]+","+(getY()/95+1)+"\n");
             
                                             ajedrez.turno=!ajedrez.turno;relesedx=getX();relesedy=getY();
                                                     
                                        
                                         
                                         }
 
              if (ajedrez.turno){ajedrez.jblanco.setBackground(Color.GREEN);
                            ajedrez.jnegro.setBackground(Color.red); }
              else      {ajedrez.jblanco.setBackground(Color.red);
                            ajedrez.jnegro.setBackground(Color.GREEN);}
               
               
               actualiza(evt);
              
               if (ajedrez.turno&&tipo==2) {texto.setText(texto.getText()+"Es el turno de las blancas!!!!!\n");
                                    TimerTask tkb=new TimerTask() {public void run() {ajedrez.tb++;
                                                                                      ajedrez.tiempoB.setText(ajedrez.tb+" seg");}};
                                    try{ajedrez.TN.cancel();}catch(Exception ex){;}
                                    try{ajedrez.TB.cancel();}catch(Exception ex){;}
                                   
                                    ajedrez.TB=new Timer();
                                    ajedrez.TB.scheduleAtFixedRate(tkb,0,1000);
                                    
                                    }
               if (!ajedrez.turno&&tipo==1){ texto.setText(texto.getText()+"Es el turno de las negras!!!!!\n");
                                    TimerTask tkn=new TimerTask() {public void run() {ajedrez.tn++;
                                                                                      ajedrez.tiempoN.setText(ajedrez.tn+" seg");}};
                                    try{ajedrez.TN.cancel();}catch(Exception ex){;}
                                    try{ajedrez.TB.cancel();}catch(Exception ex){;}
                                   
                                    ajedrez.TN=new Timer();
                                    ajedrez.TN.scheduleAtFixedRate(tkn,0,1000);
                                                                        
                                   }
   
   
    }
    
   public void actualiza(java.awt.event.MouseEvent evt) {
   
   anteriorx=getX();
   anteriory=getY();
   tablero.repaint();
   tablero.validate();
   }
   
   public boolean seinterpone(int posx,int posy, Ficha x){
   if (this instanceof Peon||this instanceof Caballo) return false;
   else if (this instanceof Torre){  
                                     
                              if (this.getX()==x.getX()&&posx==x.getX())
                                  { if ((this.getY()<posy&&posy<x.getY())
                                                        ||
                                      (x.getY()<posy&&posy<this.getY()))                                          
                                       
                                      return true;
                                     
                                  }
                    
                              if (this.getY()==x.getY()&&posy==x.getY())
                                  { if ((this.getX()<posx&&posx<x.getX())
                                                        ||
                                      (this.getX()<posx&&posx<x.getX()))                                          
                                  
                                      return true;
                                      
                                  
                                  }
                    
                             return false;
            }
   
   else if (this instanceof Alfil){
                             if (Math.abs(this.getX()-x.getX())==Math.abs(this.getY()-x.getY())&&
                                  Math.abs(posx-x.getX())==Math.abs(posy-x.getY()))
                                  { if (((this.getY()<posy&&posy<x.getY())
                                                        ||
                                      (x.getY()<posy&&posy<this.getY()))                                          
                                                         &&
                                      
                                      ((this.getX()<posx&&posx<x.getX())
                                                        ||
                                      (x.getX()<posx&&posx<this.getX())))                           
                                      return true;
                                  }
                              return false;
             }
   else if (this instanceof Reina){
                     
                    
                              if (Math.abs(this.getX()-x.getX())==Math.abs(this.getY()-x.getY())&&
                                  Math.abs(posx-x.getX())==Math.abs(posy-x.getY()))
                                  { if (((this.getY()<posy&&posy<x.getY())
                                                        ||
                                      (x.getY()<posy&&posy<this.getY()))                                          
                                                         &&
                                      
                                      ((this.getX()<posx&&posx<x.getX())
                                                        ||
                                      (x.getX()<posx&&posx<this.getX())))                           
                                      return true;
                                  }
                    
                      
                              if (this.getX()==x.getX()&&posx==x.getX())
                                  { if ((this.getY()<posy&&posy<x.getY())
                                                        ||
                                      (x.getY()<posy&&posy<this.getY()))                                          
                                       
                                      return true;
                                     
                                  }
                    
                              if (this.getY()==x.getY()&&posy==x.getY())
                                  { if ((this.getX()<posx&&posx<x.getX())
                                                        ||
                                      (x.getX()<posx&&posx<this.getX()))                                          
                                      
                                      return true;
                                      
                                  }
                          return false;    
           }
   
   return true;
   }
   public boolean ningunaFichaseinterpone(Ficha x){
   if (this instanceof Peon||this instanceof Caballo) return true;
   else if (this instanceof Torre){  
                   Component []z=tablero.getComponents();
                   for(int u=0;u<z.length;u++)
                   {Ficha g=((Ficha)z[u]);
      
                    if (g.icon!=""&&g!=this&&g!=x){
                              if (this.getX()==x.getX()&&g.getX()==x.getX())
                                  { if ((this.getY()<g.getY()&&g.getY()<x.getY())
                                                        ||
                                      (x.getY()<g.getY()&&g.getY()<this.getY()))                                          
                                       
                                      return false;
                                     
                                  }
                    
                              if (this.getY()==x.getY()&&g.getY()==x.getY())
                                  { if ((this.getX()<g.getX()&&g.getX()<x.getX())
                                                        ||
                                      (this.getX()<g.getX()&&g.getX()<x.getX()))                                          
                                  
                                      return false;
                                      
                                  
                                  }
                    
                              }
                            
                   }  
   
            return true;
            }
   
   else if (this instanceof Alfil){
                   Component []z=tablero.getComponents();
                   for(int u=0;u<z.length;u++)
                   {Ficha g=((Ficha)z[u]);
                    
                     if (g.icon!=""&&g!=this&&g!=x){
                              if (Math.abs(this.getX()-x.getX())==Math.abs(this.getY()-x.getY())&&
                                  Math.abs(g.getX()-x.getX())==Math.abs(g.getY()-x.getY()))
                                  { if (((this.getY()<g.getY()&&g.getY()<x.getY())
                                                        ||
                                      (x.getY()<g.getY()&&g.getY()<this.getY()))                                          
                                                         &&
                                      
                                      ((this.getX()<g.getX()&&g.getX()<x.getX())
                                                        ||
                                      (x.getX()<g.getX()&&g.getX()<this.getX())))                           
                                      return false;
                                  }
                              }
                   }     
   
   
             return true;
             }
   else if (this instanceof Reina){
                   Component []z=tablero.getComponents();
                   for(int u=0;u<z.length;u++)
                   {Ficha g=((Ficha)z[u]);
                     
                    if (g.icon!=""&&g!=this&&g!=x){
                              if (Math.abs(this.getX()-x.getX())==Math.abs(this.getY()-x.getY())&&
                                  Math.abs(g.getX()-x.getX())==Math.abs(g.getY()-x.getY()))
                                  { if (((this.getY()<g.getY()&&g.getY()<x.getY())
                                                        ||
                                      (x.getY()<g.getY()&&g.getY()<this.getY()))                                          
                                                         &&
                                      
                                      ((this.getX()<g.getX()&&g.getX()<x.getX())
                                                        ||
                                      (x.getX()<g.getX()&&g.getX()<this.getX())))                           
                                      return false;
                                  }
                              }
                    
                      if (g.icon!=""&&g!=this&&g!=x){
                              if (this.getX()==x.getX()&&g.getX()==x.getX())
                                  { if ((this.getY()<g.getY()&&g.getY()<x.getY())
                                                        ||
                                      (x.getY()<g.getY()&&g.getY()<this.getY()))                                          
                                       
                                      return false;
                                     
                                  }
                    
                              if (this.getY()==x.getY()&&g.getY()==x.getY())
                                  { if ((this.getX()<g.getX()&&g.getX()<x.getX())
                                                        ||
                                      (x.getX()<g.getX()&&g.getX()<this.getX()))                                          
                                      
                                      return false;
                                      
                                  }
                             }
                    }     
   
   
           return true;    
           }
   
   return true;
   }
   
   
    public ArrayList jugadas(){
   ArrayList res=new  ArrayList();
   if (icon=="") return null;
   
    if (jake()&&!(this instanceof Rey)){
              Component []z=tablero.getComponents();
              for(int u=0;u<z.length;u++)
              {Ficha g=((Ficha)z[u]);
                     
               if (g!=this&&g.come(rey)&&g.ningunaFichaseinterpone(rey)&&
                 come(g)&&ningunaFichaseinterpone(g))
                      {res.add(new Point(g.getX(),g.getY()));}
                            
               if (g!=this&&g.come(rey)&&g.ningunaFichaseinterpone(rey))
                      {ArrayList aux=jugadas1();
                        for(int i=0;i<aux.size();i++)  
                        {Point p=(Point)aux.get(i);
                         if (g.seinterpone((int)p.getX(),(int)p.getY(),rey))
                           res.add(p);   
                        }
                      }
               
               
              }
              return res;
              }
   if (this instanceof Rey){ 
                                res=jugadas1();
                                ArrayList aux=new  ArrayList();
                                for(int i=0;i<res.size();i++)      
                                {Point p=(Point)res.get(i);
                                if (!mecomen((int)p.getX(),(int)p.getY()))
                                {aux.add(p);}
                                            
                                }
                               return aux;
                               }
   return jugadas1();
   }
   
  public boolean mecomen(int posx,int posy){
       Ficha  aux=(Ficha)tablero.getComponentAt(posx, posy);
       Component []z=tablero.getComponents();
                      for(int u=0;u<z.length;u++)
                      {Ficha g=((Ficha)z[u]);
                       if ((g instanceof Caballo)&&g.puede(posx, posy)&&g.tipo!=this.tipo) return true;
                       if (g instanceof Peon) {
                         if ((g.tipo==2)&&(tipo!=g.tipo&&Math.abs(g.getX()/95-posx/95)==1&&g.getY()/95-posy/95==1)) return true;
                         if ((g.tipo==1)&&(tipo!=g.tipo&&Math.abs(g.getX()/95-posx/95)==1&&posy/95-g.getY()/95==1)) return true;
                                              }                          
                       else if (g!=aux&&g!=this&&g.puede(posx, posy)&&g.tipo!=this.tipo&&g.ningunaFichaseinterpone(aux))return true;
                      }
   return false;
   } 
   
   private ArrayList jugadas1(){
   ArrayList res=new  ArrayList();
   if (icon=="") return res;
   
      Component []z=tablero.getComponents();
              for(int u=0;u<z.length;u++)
              {Ficha g=((Ficha)z[u]);
                     
               if ((g!=this&&come(g)&&ningunaFichaseinterpone(g))||
                    ((this instanceof Peon)&&g!=this&&puede(g.getX(),g.getY())&&g.icon=="")
                       )
                      {res.add(new Point(g.getX(),g.getY()));}
           
              }
   
      
  return res;     
   }
   
   
   
   
   
   
   
   
   
   
   public void arrastre(java.awt.event.MouseEvent evt) {
  if (ajedrez.turno&&tipo==2) return;
   if (!ajedrez.turno&&tipo==1)return;
   
   if (ajedrez.turno){ajedrez.jblanco.setBackground(Color.GREEN);
              ajedrez.jnegro.setBackground(Color.red); }
   else{ajedrez.jblanco.setBackground(Color.red);
              ajedrez.jnegro.setBackground(Color.GREEN);}    
   
       auxx=getX();
       auxy=getY();
      
     int x =(getX() + evt.getX() )/95*95;
     int y= (getY()+ evt.getY() )/95*95;
    
     ArrayList jaux=jugadas();
    for(int i=0;i<jaux.size();i++)
    {Point p=(Point)jaux.get(i);
    if (p.getX()==x&&p.getY()==y){
     
      Ficha c=(tablero.getComponentAt(x,y) instanceof Ficha)?(Ficha)tablero.getComponentAt(x, y) :null;
          
          if (c.icon==""){ 
                                         
              
                       tablero.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(getX(),getY(), 95, 95));
                       tablero.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 95, 95));
                       Color aux=getBackground();
                       setBackground(c.getBackground());
                       c.setBackground(aux);
                                        
                       }
      
             
          else if(!(c instanceof Rey)) {                         
                        tablero.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(getX(),getY(), 95, 95));
                        tablero.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 95, 95));
                        Color aux=getBackground();
                        setBackground(c.getBackground());
                        c.setBackground(aux);
                        
                      if (!c.equals(this)) {tablero.remove(c);boolean t=ajedrez.turno;
                                             Ficha nula=new NULA(0,getX()/95,getY()/95,"",rey,ajedrez);
                                             ajedrez.turno=t;}
                      texto.setText(texto.getText()+icon+" come "+c.icon+"\n");
                      texto.setText(texto.getText()+icon+"  "+letras[x/95]+","+(y/95+1)+"\n");
                      ajedrez.turno=!ajedrez.turno;
                                        
                    if (ajedrez.turno){ajedrez.jblanco.setBackground(Color.GREEN);
                                ajedrez.jnegro.setBackground(Color.red); }
                    else      {ajedrez.jblanco.setBackground(Color.red);
                                ajedrez.jnegro.setBackground(Color.GREEN);} 
                    
                     
                    libera(evt);
                    }
       
      tablero.repaint();
      tablero.validate();
  
       }  
    }
   
   if ((!puede(relesedx,relesedy)&&!(this instanceof Peon))
                   ||
         ((this instanceof Peon)&&tipo==2&&relesedy/95-getY()/95>2&&getY()/95!=4) 
                   ||            
         ((this instanceof Peon)&&tipo==1&&getY()/95-relesedy/95>2&&getY()/95!=5)  
                   ||
         ((this instanceof Peon)&&Math.abs(getY()/95-relesedy/95)>1&&tipo==1&&getY()/95>4)
                   ||           
         ((this instanceof Peon)&&Math.abs(getY()/95-relesedy/95)>1&&tipo==2&&getY()/95<4) 
           )   {
       
                               Ficha f=(Ficha)tablero.getComponentAt(anteriorx, anteriory);
                               tablero.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(getX(),getY(), 95, 95));
                               tablero.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(anteriorx,anteriory, 95, 95));
                               f.anteriorx=f.getX();
                               f.anteriory=f.getY();
                              
                               Color aux=getBackground();
                               setBackground(f.getBackground());
                               f.setBackground(aux);
                               anteriorx=getX();
                               anteriory=getY(); 
                               tablero.repaint();
                               tablero.validate();
       
                               libera(evt);
   
                                    
                                    } 
    
   if ((this instanceof Rey)&&mecomen(x, y)) ajedrez.texto.setText(ajedrez.texto.getText()+"Jake al "+icon);
   }
  

public abstract boolean puede(int posx,int posy);

public boolean come(Ficha f){return (tipo!=f.tipo&&puede(f.getX(),f.getY()));}

public boolean jake(){
    
    
    Component []c=tablero.getComponents();
      for(int j=0;j<c.length;j++)
         { 
           Ficha f1=(Ficha)c[j];
           if (f1.icon!=""&&f1!=rey&&f1.come(rey)) {
                   if (f1 instanceof Caballo) { texto.setText(texto.getText()+f1.icon+" Jake al "); return true;}
                   if (f1 instanceof Peon)  {texto.setText(texto.getText()+f1.icon+" Jake al ");return true;}
           }
         }          
      /////////////////////////////////////////////////////////////////////////////////////
      c=tablero.getComponents();
      for(int j=0;j<c.length;j++)
         { 
           Ficha f1=(Ficha)c[j];
           if (f1.icon!=""&&f1!=rey&&f1.come(rey)) {
                   if (f1 instanceof Torre) {
                   
                   Component []z=tablero.getComponents();
                   for(int u=0;u<z.length;u++)
                   {Ficha g=((Ficha)z[u]);
      
                    if (g.icon!=""&&g!=f1&&g!=rey){
                              if (f1.getX()==rey.getX()&&g.getX()==rey.getX())
                                  { if ((f1.getY()<g.getY()&&g.getY()<rey.getY())
                                                        ||
                                      (rey.getY()<g.getY()&&g.getY()<f1.getY()))                                          
                                       
                                      return false;
                                     
                                  }
                    
                              if (f1.getY()==rey.getY()&&g.getY()==rey.getY())
                                  { if ((f1.getX()<g.getX()&&g.getX()<rey.getX())
                                                        ||
                                      (rey.getX()<g.getX()&&g.getX()<f1.getX()))                                          
                                  
                                      return false;
                                      
                                  
                                  }
                    
                              }
                            
                   }  
                       
                       
                       
                       
                       
                       texto.setText(texto.getText()+f1.icon+" Jake al "); return true;}
           }
         }   
    
     
    ////////////////////////////////////////////////////////////////////////////////////
     c=tablero.getComponents();
      for(int j=0;j<c.length;j++)
         { 
           Ficha f1=(Ficha)c[j];
           if (f1.icon!=""&&f1!=rey&&f1.come(rey)) {
                   if (f1 instanceof Alfil){
                       
                   
                   Component []z=tablero.getComponents();
                   for(int u=0;u<z.length;u++)
                   {Ficha g=((Ficha)z[u]);
                    
                     if (g.icon!=""&&g!=f1&&g!=rey){
                              if (Math.abs(f1.getX()-rey.getX())==Math.abs(f1.getY()-rey.getY())&&
                                  Math.abs(g.getX()-rey.getX())==Math.abs(g.getY()-rey.getY()))
                                  { if (((f1.getY()<g.getY()&&g.getY()<rey.getY())
                                                        ||
                                      (rey.getY()<g.getY()&&g.getY()<f1.getY()))                                          
                                                         &&
                                      
                                      ((f1.getX()<g.getX()&&g.getX()<rey.getX())
                                                        ||
                                      (rey.getX()<g.getX()&&g.getX()<f1.getX())))                           
                                      return false;
                                  }
                              }
                   }                  
                     
                 texto.setText(texto.getText()+f1.icon+" Jake al "); return true;}
           }
          }
     
   ////////////////////////////////////////////////////////////////////////////////////////
    c=tablero.getComponents();
      for(int j=0;j<c.length;j++)
         { 
           Ficha f1=(Ficha)c[j];
           if (f1.icon!=""&&f1!=rey&&f1.come(rey)) {
                   if (f1 instanceof Reina){
                       
                   
                   Component []z=tablero.getComponents();
                   for(int u=0;u<z.length;u++)
                   {Ficha g=((Ficha)z[u]);
                     
                    if (g.icon!=""&&g!=f1&&g!=rey){
                              if (Math.abs(f1.getX()-rey.getX())==Math.abs(f1.getY()-rey.getY())&&
                                  Math.abs(g.getX()-rey.getX())==Math.abs(g.getY()-rey.getY()))
                                  { if (((f1.getY()<g.getY()&&g.getY()<rey.getY())
                                                        ||
                                      (rey.getY()<g.getY()&&g.getY()<f1.getY()))                                          
                                                         &&
                                      
                                      ((f1.getX()<g.getX()&&g.getX()<rey.getX())
                                                        ||
                                      (rey.getX()<g.getX()&&g.getX()<f1.getX())))                           
                                      return false;
                                  }
                              }
                    
                      if (g.icon!=""&&g!=f1&&g!=rey){
                              if (f1.getX()==rey.getX()&&g.getX()==rey.getX())
                                  { if ((f1.getY()<g.getY()&&g.getY()<rey.getY())
                                                        ||
                                      (rey.getY()<g.getY()&&g.getY()<f1.getY()))                                          
                                       
                                      return false;
                                     
                                  }
                    
                              if (f1.getY()==rey.getY()&&g.getY()==rey.getY())
                                  { if ((f1.getX()<g.getX()&&g.getX()<rey.getX())
                                                        ||
                                      (rey.getX()<g.getX()&&g.getX()<f1.getX()))                                          
                                      
                                      return false;
                                      
                                  }
                             }
                    }                  
                     
                 texto.setText(texto.getText()+f1.icon+" Jake al "); return true;}
           }
          }
      
       
return false;
}
}

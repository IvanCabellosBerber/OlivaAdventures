package olivaAdventures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GameEngine implements KeyListener {

    private boolean gameOver=false,par=false,saltando=false;
    private int contador=0,ejeX=0,ejeY=0,contadorSalto=0;
    MyPanel panel = new MyPanel();
    JFrame frame = new JFrame("Oliva Adventures");

    public GameEngine(){

        frame.setSize(800,800);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add panel to main frame
        frame.add(panel);

        frame.setVisible(true);

        secuencia();

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch(keyEvent.getKeyCode()){

            case KeyEvent.VK_D:

                ejeX+=20;

                break;
            case KeyEvent.VK_A:

                ejeX-=20;

                break;

            case KeyEvent.VK_W:

                if(!saltando) {

                    ejeY+=60;
                    saltar();

                }


                break;
            default:;
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    class MyPanel extends JPanel {
        public void paint(Graphics g) {

            g.setColor(Color.gray);
            g.fillRect(0,0,1000,1000);

            if(!par) {
                g.setColor(Color.red);
                g.fillRect(0, 720, 1500, 50);
            }
            else{
                g.setColor(Color.blue);
                g.fillRect(0, 720, 1500, 50);
            }

            g.setColor(Color.black);
            g.fillRect(900-ejeX,300,50,100);

            g.setColor(Color.yellow);
            g.fillRect(350,700-ejeY,20,35);
        };
    }

    public void saltar(){

        contadorSalto=0;

        saltando=true;

    }

    public void fps(){

        if(saltando&&contador%2==0){

            if(contadorSalto>1&&contadorSalto<5) {

                ejeY += 35;

            }
            else if(contadorSalto>4&&contadorSalto<8){

                ejeY+=20;

            }
            else if(contadorSalto>7&&contadorSalto<12){

                ejeY+=10;

            }
            else if(contadorSalto>11&&contadorSalto<15){

                ejeY+=5;

            }
            else if(contadorSalto==15){

                //TE JODES

            }
            else if (contadorSalto > 15){

                ejeY-=10;
            }

            if(contadorSalto>43){

                saltando=false;

            }

            contadorSalto++;
        }

        panel.repaint();

    }

    public void secuencia(){

        while(!gameOver){

            fps();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //HA PETAO
            }

//            if(contador%2==0){
//                par=true;
//            }
//            else{
//                par=false;
//            }

            contador++;

        }

    }
}

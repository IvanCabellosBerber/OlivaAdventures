package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * @author kryon
 */
public class GameEngineLVL1 implements KeyListener {

    private boolean saltando=false;
    private int contador=0,ejeX=0,ejeY=0,contadorSalto=0,prevY=0;
    private PanelLVL1 panel = new PanelLVL1();
    private Player player = new Player();

    GameEngineLVL1(){

        JFrame frame = new JFrame("Oliva Adventures");
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(panel);

        frame.setVisible(true);

        arrancar();

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch(keyEvent.getKeyCode()){

            case KeyEvent.VK_D:

                ejeX+=20;

                if(!saltando) {

                    contadorSalto = 16;
                    saltando = true;
                }

                break;
            case KeyEvent.VK_A:

                ejeX-=20;

                if(!saltando) {

                    contadorSalto = 16;
                    saltando = true;
                }

                break;

            case KeyEvent.VK_W:

                if(!saltando) {

                    ejeY+=60;
                    saltar();

                }
                break;

            default:
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


    private void saltar(){
        contadorSalto=0;
        saltando=true;
    }

    private void fps(){

        if(saltando&&contador%2==0){

            if(contadorSalto>=0&&contadorSalto<5) {
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

                prevY=700-ejeY;

            }
            else if((contadorSalto>15&&contadorSalto<20)&& !panel.isGround(ejeX, ejeY, prevY)){

                prevY=700-ejeY;
                ejeY-=5;

            }
            else if((contadorSalto>19&&contadorSalto<25)&& !panel.isGround(ejeX, ejeY, prevY)){

                prevY=700-ejeY;
                ejeY-=10;

            }
            else if(contadorSalto>24&& !panel.isGround(ejeX, ejeY, prevY)){

                prevY=700-ejeY;
                ejeY-=20;

            }
            else{

                saltando=false;

            }

            if(/*panel.isGround(ejeX,ejeY,prevY)==true || */700+ejeY<=700){

                saltando=false;
                ejeY=0;

            }

            contadorSalto++;
        }

        panel.setX(ejeX);
        panel.setY(ejeY);
        panel.repaint();

    }

    /*
    public void gravedad(){

        if(!saltando){
            if(panel.isGround(0,700-panel.getY()+35,700-panel.getY()-35)){

                contadorSalto=16;
                saltando=true;
            }
        }
    }

    public void recibirDisparo(){
        boolean tocado = false;

        if (ejeX == (800 - ejeX)){
            tocado = true;
        }

        if (tocado){
            player.getHurt();
        }

    }
    */

    private void arrancar(){

        boolean gameOver = false;
        int puntuacion = 360;

        while(!gameOver){

//          gravedad();
            fps();
            if (player.isDead()){
                gameOver = true;
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.err.println("HA PETADO");
            }
            if(contador%50==0){
                puntuacion--;
                System.out.println(puntuacion);
            }

            contador++;
        }

    }
}
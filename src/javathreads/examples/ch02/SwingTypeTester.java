package javathreads.examples.ch02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author csq
 * @date 2020/1/15 11:21
 * @description
 **/
public class SwingTypeTester extends JFrame implements  CharacterSource {

    protected RandomCharacterGenerator producer;
    private CharacterDisplayCanvas displayCanvas;
    private CharacterDisplayCanvas feedbackCanvas;
    private JButton quitButton;
    private JButton startButton;
    private CharacterEventHandler handler;

    public SwingTypeTester(){
        initComponents();
    }

    private void initComponents() {
        handler = new CharacterEventHandler();
        displayCanvas = new CharacterDisplayCanvas();
        feedbackCanvas = new CharacterDisplayCanvas();
        quitButton = new JButton();
        startButton = new JButton();
        add(displayCanvas, BorderLayout.NORTH);
        add(feedbackCanvas, BorderLayout.CENTER);
        JPanel p = new JPanel();
        startButton.setLabel("Start");
        quitButton.setLabel("Quit");
        p.add(startButton);
        p.add(quitButton);

        add(p, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });

        feedbackCanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if(c != KeyEvent.CHAR_UNDEFINED){
                    newCharacter((int) c);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                producer = new RandomCharacterGenerator();
                displayCanvas.setCharacterSource(producer);
                producer.start();
                startButton.setEnabled(false);
                feedbackCanvas.setEnabled(true);
                feedbackCanvas.requestFocus();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        pack();
    }

    private void quit(){
        System.exit(0);
    }

    @Override
    public void addCharacterListener(CharacterListener cl) {
        handler.addCharacterListener(cl);
    }

    @Override
    public void removeCharacterListener(CharacterListener cl) {
        handler.removeCharacterListener(cl);
    }

    public void newCharacter(int c){
        handler.fireNewCharacter(this, c);
    }

    @Override
    public void nextCharacter() {
        throw new IllegalStateException("We don't produce on demand");
    }

    public static void main(String[] args){
        new SwingTypeTester().show();
    }
}

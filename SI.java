import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class SI extends JFrame {
    private SIPanel panel = new SIPanel();
    public SI() {
        super("Space Invaders");
        add(panel);
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu game = new JMenu("Game");
        JMenu about = new JMenu("About");
        menubar.add(game);
        menubar.add(about);
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem resume = new JMenuItem("Resume");
        JMenuItem quit = new JMenuItem("Quit");
        
        game.add(newGame);
        game.addSeparator();
        game.add(pause);
        game.add(resume);
        game.addSeparator();
        game.add(quit);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String ObjButtons[] = { "Yes", "No" };
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Start a new game?", "Online Examination System",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, ObjButtons,
                        ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    getContentPane().remove(panel);
                    panel = new SIPanel();
                    add(panel);
                    getContentPane().invalidate();
                    getContentPane().validate();
                    pause.setEnabled(true);
                    resume.setEnabled(false);
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ObjButtons[] = { "Yes", "No" };
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Dare to Quit?", "Online Examination System",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, ObjButtons,
                        ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String ObjButtons[] = { "Yes", "No" };
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Dare to Quit?", "Online Examination System",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, ObjButtons,
                        ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        about.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SI.this,
                        new JLabel(
                                "<html><b>SIinvader</b> <br/> <p>by Sam Allison and Tyre King</p><html>"),
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
            
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.timerStop();
                resume.setEnabled(true);
                pause.setEnabled(false);
            }
        });
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.timerresume();
                resume.setEnabled(false);
                pause.setEnabled(true);
            }
        });
        resume.setEnabled(false);
        pause.setEnabled(false);
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    protected void askForClosing() {
        int result = JOptionPane.showConfirmDialog(this, "Dare to Quit?");
        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
    public static void main(String[] args) {
        JFrame f = new SI();
        f.setVisible(true);
    }
    private class SIPanel extends JPanel {
        private boolean right, left;
        private SIbottom bot;
        private SImiddle middle;
        private SItop tp;
        private ArrayList<SItop> top = new ArrayList<>();
        private ArrayList<SImiddle> mid = new ArrayList<>();
        private ArrayList<SIbottom> bottom = new ArrayList<>();
        private SIbase base;
        private SImissile missile;
        private SIMystery mystery;
        private int pace = 40;
        private int location = 0;
        private int points = 0;
        private int puls = 0;
        private int mpuls = 0;
        private int down = 0;
        private int dir = 5;
        private int alienCount = 0;
        private int mysterydir;
        private Timer timer;
        private int count;
        private SImissile alien1;
        private SImissile alien2;
        private SImissile alien3;
        public SIPanel() {
            super();
            setBackground(Color.BLACK);
            right = left = false;
            base = new SIbase(300, 300);
            alien1 = alien2 = alien3 = null;
            mystery = null;
            mystery = null;
            int yspot = 80;
            for (int row = 0; row < 1; row++) {
                int xspot = 35;
                for (int col = 0; col < 10; col++) {
                    if (col == 10) {
                        col = 0;
                        row++;
                        xspot = 0;
                    }
                    tp = new SItop(xspot, yspot);
                    xspot += 35;
                    top.add(tp);
                }
                yspot += 25;
            }
            for (int row = 0; row < 2; row++) {
                int xspot = 35;
                for (int col = 0; col < 10; col++) {
                    if (col == 10) {
                        col = 0;
                        row++;
                        xspot = 0;
                    }
                    middle = new SImiddle(xspot, yspot);
                    xspot += 35;
                    mid.add(middle);
                }
                yspot += 25;
            }
            for (int row = 0; row < 2; row++) {
                int xspot = 35;
                for (int col = 0; col < 10; col++) {
                    if (col == 10) {
                        col = 0;
                        row++;
                        xspot = 0;
                    }
                    bot = new SIbottom(xspot, yspot);
                    xspot += 35;
                    bottom.add(bot);
                }
                yspot += 25;
            }
            missile = null;
            setFocusable(true);
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (left && base.getX() != 0)
                        base.moveXby(-5);
                    if (right && base.getX() != 455)
                        base.moveXby(5);
                    if (missile != null) {
                        missile.moveUp();
                    }
                    if (missile != null) {
                        for (SItop i : top) {
                            if (missile.getX() <= i.getX() + 20
                                    && missile.getX() >= i.getX()
                                    && missile.getY() <= i.getY() + 5
                                    && missile.getY() >= i.getY()
                                    && i.isHit() == false) {
                                i.wasHit();
                                tp.playmeSound();
                                points += tp.getPoints();
                                missile = null;
                               // System.out.println("hit");
                                break;
                            }
                        }
                        if (missile != null) {
                            for (SImiddle i : mid) {
                                if (missile.getX() <= i.getX() + 20
                                        && missile.getX() >= i.getX()
                                        && missile.getY() <= i.getY() + 5
                                        && missile.getY() >= i.getY()
                                        && i.isHit() == false) {
                                    i.wasHit();
                                    middle.playmeSound();
                                    points += middle.getPoints();
                                    missile = null;
                                    //System.out.println("hit");
                                    break;
                                }
                            }
                        }
                        if (missile != null) {
                            for (SIbottom i : bottom) {
                                if (missile.getX() <= i.getX() + 20
                                        && missile.getX() >= i.getX()
                                        && missile.getY() <= i.getY() + 5
                                        && missile.getY() >= i.getY()
                                        && i.isHit() == false) {
                                    i.wasHit();
                                    bot.playmeSound();
                                    missile = null;
                                    points += bot.getPoints();
                                    // System.out.println("hit");
                                    break;
                                }
                            }
                        }
                        if (missile != null && mystery != null) {
                            if (missile.getX() <= mystery.getX() + 20
                                    && missile.getX() >= mystery.getX()
                                    && missile.getY() <= mystery.getY() + 5
                                    && missile.getY() >= mystery.getY()
                                    && mystery.isHit() == false) {
                                mystery.wasHit();
                                points += mystery.getPoints();
                                missile = null;
                                System.out.println("hit");
                                mystery = null;
                            }
                        }
                    }
                    if(alienCount == 5){
                        alienCount = 0;
                    if (alien1 == null || alien1.getY() >= 500) {
                        int Alien = new Random().nextInt(bottom.size() - 1);
                        if (Alien >= 5) {
                            if (bottom.get(Alien).isHit() == false) {
                                alien1 = new SImissile(
                                        bottom.get(Alien).getX() + 5,
                                        bottom.get(Alien).getY());
                            }
                        }
                        else if (Alien < 5) {
                            if (bottom.get(Alien).isHit() == false
                                    && bottom.get(Alien + 5).isHit() == true) {
                                alien1 = new SImissile(
                                        bottom.get(Alien).getX() + 5,
                                        bottom.get(Alien).getY());
                            }
                        }
                        else if (Alien >= 5) {
                            if (mid.get(Alien).isHit() == false
                                    && bottom.get(Alien).isHit() == true) {
                                alien1 = new SImissile(
                                        mid.get(Alien).getX() + 5,
                                        mid.get(Alien).getY());
                            }
                            else if (Alien < 5) {
                                if (mid.get(Alien).isHit() == false
                                        && bottom.get(Alien).isHit() == true
                                        && mid.get(Alien + 5)
                                                .isHit() == true) {
                                    alien1 = new SImissile(
                                            mid.get(Alien).getX() + 5,
                                            mid.get(Alien).getY());
                                }
                            }
                            else if (Alien > 10) {
                                Alien = Alien / 2;
                                if (top.get(Alien).isHit() == false
                                        && bottom.get(Alien).isHit() == true
                                        && mid.get(Alien).isHit() == true) {
                                    alien1 = new SImissile(
                                            mid.get(Alien).getX() + 5,
                                            mid.get(Alien).getY());
                                }
                            }
                        }
                    }
                    if (alien2 == null || alien2.getY() >= 500) {
                        int Alien = new Random().nextInt(bottom.size() - 1);
                        if (Alien >= 5) {
                            if (bottom.get(Alien).isHit() == false) {
                                alien2 = new SImissile(
                                        bottom.get(Alien).getX() + 5,
                                        bottom.get(Alien).getY());
                            }
                        }
                        else if (Alien < 5) {
                            if (bottom.get(Alien).isHit() == false
                                    && bottom.get(Alien + 5).isHit() == true) {
                                alien2 = new SImissile(
                                        bottom.get(Alien).getX() + 5,
                                        bottom.get(Alien).getY());
                            }
                        }
                        else if (Alien >= 5) {
                            if (mid.get(Alien).isHit() == false
                                    && bottom.get(Alien).isHit() == true) {
                                alien2 = new SImissile(
                                        mid.get(Alien).getX() + 5,
                                        mid.get(Alien).getY());
                            }
                            else if (Alien < 5) {
                                if (mid.get(Alien).isHit() == false
                                        && bottom.get(Alien).isHit() == true
                                        && mid.get(Alien + 5)
                                                .isHit() == true) {
                                    alien2 = new SImissile(
                                            mid.get(Alien).getX() + 5,
                                            mid.get(Alien).getY());
                                }
                            }
                            else if (Alien > 10) {
                                Alien = Alien / 2;
                                if (top.get(Alien).isHit() == false
                                        && bottom.get(Alien).isHit() == true
                                        && mid.get(Alien).isHit() == true) {
                                    alien2 = new SImissile(
                                            mid.get(Alien).getX() + 5,
                                            mid.get(Alien).getY());
                                }
                            }
                        }
                    }
                    if (alien3 == null || alien3.getY() >= 500) {
                        int Alien = new Random().nextInt(bottom.size() - 1);
                        if (Alien >= 5) {
                            if (bottom.get(Alien).isHit() == false) {
                                alien3 = new SImissile(
                                        bottom.get(Alien).getX() + 5,
                                        bottom.get(Alien).getY());
                            }
                        }
                        else if (Alien < 5) {
                            if (bottom.get(Alien).isHit() == false
                                    && bottom.get(Alien + 5).isHit() == true) {
                                alien3 = new SImissile(
                                        bottom.get(Alien).getX() + 5,
                                        bottom.get(Alien).getY());
                            }
                        }
                        else if (Alien >= 5) {
                            if (mid.get(Alien).isHit() == false
                                    && bottom.get(Alien).isHit() == true) {
                                alien3 = new SImissile(
                                        mid.get(Alien).getX() + 5,
                                        mid.get(Alien).getY());
                            }
                            else if (Alien < 5) {
                                if (mid.get(Alien).isHit() == false
                                        && bottom.get(Alien).isHit() == true
                                        && mid.get(Alien + 5)
                                                .isHit() == true) {
                                    alien3 = new SImissile(
                                            mid.get(Alien).getX() + 5,
                                            mid.get(Alien).getY());
                                }
                            }
                            else if (Alien > 10) {
                                Alien = Alien / 2;
                                if (top.get(Alien).isHit() == false
                                        && bottom.get(Alien).isHit() == true
                                        && mid.get(Alien).isHit() == true) {
                                    alien3 = new SImissile(
                                            mid.get(Alien).getX() + 5,
                                            mid.get(Alien).getY());
                                }
                            }
                        }
                    }
                    if (alien1 != null) {
                        alien1.moveDown();
                        if (alien1.getX() <= base.getX() + 20
                                && alien1.getX() >= base.getX()
                                && alien1.getY() <= base.getY() + 5
                                && alien1.getY() >= base.getY()) {
                            base.setbaseboom();
                            base.playmeSound();
                            timerStop();
                        }
                    }
                    if (alien2 != null) {
                        alien2.moveDown();
                        if (alien2.getX() <= base.getX() + 20
                                && alien2.getX() >= base.getX()
                                && alien2.getY() <= base.getY() + 5
                                && alien2.getY() >= base.getY()) {
                            base.setbaseboom();
                            base.playmeSound();
                            timerStop();
                        }
                    }
                    if (alien3 != null) {
                        alien3.moveDown();
                        if (alien3.getX() <= base.getX() + 20
                                && alien3.getX() >= base.getX()
                                && alien3.getY() <= base.getY() + 5
                                && alien3.getY() >= base.getY()) {
                            base.setbaseboom();
                            base.playmeSound();
                            timerStop();
                        }
                    }
                    if (mpuls == 2) {
                        mpuls = 0;
                        if (mystery == null) {
                            if (Math.random() > .997) {
                                if (Math.random() > .5) {
                                    mystery = new SIMystery(0, 50);
                                    mysterydir = 5;
                                }
                                else {
                                    mystery = new SIMystery(450, 50);
                                    mysterydir = -5;
                                }
                                mystery.playmeSound();
                            }
                        }
                        else {
                            if (mystery.isNotVisible()) {
                                mystery = null;
                            }
                        }
                    }
                    }
                    if (puls == pace) {
                        puls = 0;
                        if (mid.get(9).getX() == 455) {
                            pace = (int) (pace * .8);
                           
                            puls = 0;
                        }
                        if (mid.get(9).getX() == 320) {
                            pace = (int) (pace * .8);
                           
                            puls = 0;
                        }
                        for (location = 0; location < top.size(); location++) {
                            top.get(location).moveXby(dir);
                            if (mid.get(9).getX() == 455) {
                                dir = -5;
                                top.get(location).moveYby(down + 12);
                            }
                            if (mid.get(9).getX() == 320) {
                                dir = 5;
                                top.get(location).moveYby(down + 12);
                            }
                        }
                        for (location = 0; location < mid.size(); location++) {
                            mid.get(location).moveXby(dir);
                            if (mid.get(9).getX() == 455) {
                                mid.get(location).moveYby(down + 12);
                            }
                            if (mid.get(9).getX() == 320) {
                                mid.get(location).moveYby(down + 12);
                            }
                        }
                        for (location = 0; location < bottom
                                .size(); location++) {
                            bottom.get(location).moveXby(dir);
                            if (mid.get(9).getX() == 455) {
                                bottom.get(location).moveYby(down + 12);
                                if (bottom.get(location).getY() == 300
                                        && bottom.get(location).isHit()) {
                                }
                            }
                            if (mid.get(9).getX() == 320) {
                                bottom.get(location).moveYby(down + 12);
                            }
                        }
                    }
                    for (location = 0; location < bottom.size(); location++) {
                        if (bottom.get(location).isHit()) {
                            count++;
                        }
                    }
                    for (location = 0; location < mid.size(); location++) {
                        if (mid.get(location).isHit()) {
                            count++;
                        }
                    }
                    for (location = 0; location < top.size(); location++) {
                        if (top.get(location).isHit()) {
                            count++;
                        }
                    }
                    if(count == 50){
                        getContentPane().remove(panel);
                        getContentPane().add(panel);
                        //getContentPane().repaint();
                    }
                    
                    
                    
                    
                    
                   alienCount ++;
                    puls++;
                    mpuls++;
                    repaint();
                }
            });
            timer.start();
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            right = true;
                            break;
                        case KeyEvent.VK_LEFT:
                            left = true;
                            break;
                        case KeyEvent.VK_SPACE:
                            createMissile();
                            break;
                    }
                }
                protected void createMissile() {
                    if (missile == null || missile.getY() < 0) {
                        base.playmeSound();
                        missile = new SImissile(base.getX() + 12, base.getY());
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            right = false;
                            break;
                        case KeyEvent.VK_LEFT:
                            left = false;
                            break;
                    }
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            base.paintMe(g2);
            g2.setColor(Color.GREEN);
            g2.drawString(toString(), 400, 20);
            if (base.baseboom) {
                g2.drawString("GAME OVER", 250, 300);
            }
            for (location = 0; location < bottom.size(); location++) {
                if (!bottom.get(location).isHit() && bottom.get(location).getY()== base.getY()) {
                    g2.drawString("GAME OVER", 250, 100);
                    timerStop();
                }
            }
            for (location = 0; location < mid.size(); location++) {
                if (!mid.get(location).isHit() && mid.get(location).getY()== base.getY()) {
                    
                    g2.drawString("GAME OVER", 250, 100);
                    timerStop();
                }
            }
            for (location = 0; location < top.size(); location++) {
                if (!top.get(location).isHit()&&top.get(location).getY()== base.getY()) {
                    g2.drawString("GAME OVER", 250, 100);
                    timerStop();
                }
            }
            if (mystery != null) {
                mystery.paintMe(g2);
                mystery.moveXby(mysterydir);
            }
            for (location = 0; location < top.size(); location++) {
                top.set(location, top.get(location)).paintMe(g2);
            }
            for (location = 0; location < mid.size(); location++) {
                mid.set(location, mid.get(location)).paintMe(g2);
            }
            for (location = 0; location < bottom.size(); location++) {
                bottom.set(location, bottom.get(location)).paintMe(g2);
            }
            if (missile != null) {
                missile.paintMe(g2);
            }
            if (alien1 != null) {
                alien1.paintMe(g2);
            }
            if (alien2 != null) {
                alien2.paintMe(g2);
            }
            if (alien3 != null) {
                alien3.paintMe(g2);
            }
        }
        public void timerStop() {
            timer.stop();
        }
        public void timerresume() {
            timer.restart();
        }
        public int getPoints() {
            return points;
        }
        public String toString() {
            String s = "Points " + getPoints();
            return s;
        }
    }
    private static class SImissile {
        private int x, y;
        public SImissile(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void paintMe(Graphics2D g2) {
            Rectangle2D.Double r = new Rectangle2D.Double(x, y, 2, 10);
            g2.setColor(Color.WHITE);
            g2.fill(r);
        }
        public void moveUp() {
            y -= 5;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void moveDown() {
            y += 5;
        }
    }
    private static class SIbase {
        private boolean baseboom;
        private Image image;
        private Image boom;
        private AudioClip sound;
        private int x;
        private int y;
        public SIbase(int x, int y) {
            this.image = getImage("SIbase.gif");
            this.sound = getSound("SIshipHit.wav");
            this.boom = getImage("SIbaseBlast.gif");
            this.x = x;
            this.y = y;
            this.baseboom = false;
        }
        private Image getImage(String name) {
            URL file = getClass().getResource(name);
            ImageIcon icon = new ImageIcon(file);
            return icon.getImage();
        }
        private AudioClip getSound(String name) {
            URL file = getClass().getResource(name);
            return Applet.newAudioClip(file);
        }
        public void moveXby(int delta) {
            x += delta;
        }
        public void paintMe(Graphics2D g2) {
            Image toDraw;
            if (baseboom) {
                toDraw = this.boom;
            }
            else {
                toDraw = image;
                g2.drawImage(toDraw, x, y, null);
            }
        }
        public void setbaseboom() {
            baseboom = true;
        }
       
        public void playmeSound() {
            sound.play();
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
    private abstract class SIinvader {
        private boolean isHit = false;
        protected boolean flip = false;
        private boolean isGone = false;
        protected boolean isHit() {
            return isHit;
        }
        protected void wasHit() {
            isHit = true;
        }
        protected boolean isGone() {
            return isGone;
        }
        
    }
    private class SItop extends SIinvader {
        private Image image1;
        private Image image2;
        private Image image3;
        private AudioClip boom;
        private int x;
        private int y;
        private final int points = 30;
        SItop(int x, int y) {
            this.x = x;
            this.y = y;
            image1 = getImage("SItop0.gif");
            image2 = getImage("SItop1.gif");
            image3 = getImage("SIinvaderBlast.gif");
            boom = getSound("SIshipHit.wav");
        }
        private Image getImage(String name) {
            URL file = getClass().getResource(name);
            ImageIcon icon = new ImageIcon(file);
            return icon.getImage();
        }
        public void moveXby(int delta) {
            x += delta;
            flip = !flip;
        }
        public void moveYby(int delta) {
            y += delta;
        }
        public void paintMe(Graphics2D g2) {
            Image toDraw;
            if (isHit()) {
                toDraw = image3;
            }
            else {
                toDraw = flip ? image1 : image2;
            }
            g2.drawImage(toDraw, x, y, null);
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public int getPoints() {
            return points;
        }
        public void playmeSound() {
            boom.play();
        }
        private AudioClip getSound(String name) {
            URL file = getClass().getResource(name);
            return Applet.newAudioClip(file);
        }
    }
    private class SImiddle extends SIinvader {
        private Image image1;
        private Image image2;
        private Image image3;
        private AudioClip boom;
        private final int points = 20;
        private int x;
        private int y;
        SImiddle(int x, int y) {
            this.x = x;
            this.y = y;
            image1 = getImage("SImiddle0.gif");
            image2 = getImage("SImiddle1.gif");
            image3 = getImage("SIinvaderBlast.gif");
            boom = getSound("SIshipHit.wav");
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        private Image getImage(String name) {
            URL file = getClass().getResource(name);
            ImageIcon icon = new ImageIcon(file);
            return icon.getImage();
        }
        public void moveXby(int delta) {
            x += delta;
            flip = !flip;
        }
        public void moveYby(int delta) {
            y += delta;
        }
        public void paintMe(Graphics2D g2) {
            Image toDraw;
            if (isHit()) {
                toDraw = image3;
            }
            else {
                toDraw = flip ? image1 : image2;
            }
            g2.drawImage(toDraw, x, y, null);
        }
        private AudioClip getSound(String name) {
            URL file = getClass().getResource(name);
            return Applet.newAudioClip(file);
        }
        public void playmeSound() {
            boom.play();
        }
        public int getPoints() {
            return points;
        }
    }
    private class SIbottom extends SIinvader {
        private Image image1;
        private Image image2;
        private Image image3;
        private AudioClip boom;
        private final int points = 10;
        private int x;
        private int y;
        SIbottom(int x, int y) {
            this.x = x;
            this.y = y;
            image1 = getImage("SIbottom0.gif");
            image2 = getImage("SIbottom1.gif");
            image3 = getImage("SIinvaderBlast.gif");
            boom = getSound("SIshipHit.wav");
        }
        private Image getImage(String name) {
            URL file = getClass().getResource(name);
            ImageIcon icon = new ImageIcon(file);
            return icon.getImage();
        }
        public void moveXby(int delta) {
            x += delta;
            flip = !flip;
        }
        public void moveYby(int delta) {
            y += delta;
        }
        public void paintMe(Graphics2D g2) {
            Image toDraw;
            if (isHit()) {
                toDraw = image3;
                g2.drawImage(toDraw, x, y, null);
            }
            else {
                toDraw = flip ? image1 : image2;
                g2.drawImage(toDraw, x, y, null);
            }
            if (isHit() && isGone()) {
                g2.setColor(Color.black);
                g2.fillRect(x, y, 30, 25);
            }
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public int getPoints() {
            return points;
        }
        private AudioClip getSound(String name) {
            URL file = getClass().getResource(name);
            return Applet.newAudioClip(file);
        }
        public void playmeSound() {
            boom.play();
        }
    }
    private class SIMystery extends SIinvader {
        private Image image;
        private Image boom;
        private AudioClip sound;
        private int x;
        private int y;
        private int[] points = new int[] { 50, 100, 150, 300 };
        public SIMystery(int x, int y) {
            image = getImage("SImystery.gif");
            sound = getSound("SImystery.wav");
            boom = getImage("SIinvaderBlast.gif");
            this.x = x;
            this.y = y;
        }
        private Image getImage(String name) {
            URL file = getClass().getResource(name);
            ImageIcon icon = new ImageIcon(file);
            return icon.getImage();
        }
        private AudioClip getSound(String name) {
            URL file = getClass().getResource(name);
            return Applet.newAudioClip(file);
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void moveXby(int delta) {
            x += delta;
        }
        public void paintMe(Graphics2D g2) {
            Image toDraw;
            if (isHit()) {
                toDraw = boom;
            }
            else {
                toDraw = image;
            }
            g2.drawImage(toDraw, x, y, null);
        }
        public void playmeSound() {
            sound.play();
        }
        public int getPoints() {
            int rnd = new Random().nextInt(points.length - 1);
            return points[rnd];
        }
        public boolean isNotVisible() {
            if (x > 455 || x < 0) {
                return true;
            }
            else
                return false;
        }
    }
}
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

/**
 *
 * @author Justin Dowell
 */
public class GWindow extends JFrame {

    // ----- Variables ==========================
    private GPanel gpanel;
    private Timer timer;
    private Integer levelIndex;

    // ----- Constructors =======================
    private GWindow() {
        timer = null;
        levelIndex = 0;
    }//---

    // ----- Builder ============================
    public static GWindow newWindow() {
        GWindow.setDefaultLookAndFeelDecorated(true);
        GWindow rv = new GWindow();
        
        rv.setDefaultCloseOperation(GWindow.EXIT_ON_CLOSE);
        rv.setSize(Constants.getWindowDim());
        rv.initMenuBar();
        rv.gpanel = GPanel.newGPanel(rv);
        rv.add(rv.gpanel);
        rv.setLocationRelativeTo(null);
        rv.setVisible(true);
        rv.newGame();

        return rv;
    }// ---

    // ----- Overrides ==========================

    // ----- Methods ============================
    private void initMenuBar() {
        JMenuBar mb = new JMenuBar();
        {
            JMenu fileMenu = new JMenu("File");
            {
                JMenuItem newMI = new JMenuItem("New Game");
                {
                    newMI.addActionListener(a -> {
                       this.newGame();
                    });
                }
                fileMenu.add(newMI);

                JMenuItem quitMI = new JMenuItem("Quit");
                {
                    quitMI.addActionListener(a -> {
                        System.exit(0);
                    });
                }
                fileMenu.add(quitMI);
            }
            mb.add(fileMenu);
        }
        this.setJMenuBar(mb);
    }// ---

    private void initTimer() {
        // initialize timer if needed
        if(null == timer) {
            Integer speed = Constants.getDropSpeed() - ((this.levelIndex) * (Constants.getDropSpeed() / Constants.getLevels().size()));
            timer = new Timer(speed, (ActionEvent e) -> {
                this.update();
            });
        }

        // start timer
        if(!timer.isRunning()) {
            timer.start();
        }
    }// ---

    private void killTimer() {
        // stop timer
        if(null != timer && timer.isRunning()) {
            timer.stop();
            timer = null;
        }
    }// ---

    private void newGame() {
        this.killTimer();
        this.gpanel.newGame();
        this.levelIndex = 0;
        this.initTimer();
    }// ---

    private void update() {
        this.gpanel.update();
        if(this.gpanel.gameover()) {
            this.killTimer();
        }
        if(this.gpanel.levelUp()) {
            this.levelIndex++;
            this.killTimer();
            this.initTimer();
        }
        this.repaint();
    }// ---

    public void pauseGame() {
        if(this.timer.isRunning()) {
            this.timer.stop();
        } else {
            this.timer.start();
        }
    }
}

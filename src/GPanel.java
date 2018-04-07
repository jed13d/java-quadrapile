import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
*
* @author Justin Dowell
*/
public class GPanel extends JPanel {

   // ----- Variables ==========================
   private GManager manager;
   private final GWindow PARENT;
   private Boolean paused;
   private final GMusic THEME_A;
   private final GMusic THEME_B;

   // ----- Constructors =======================
   private GPanel(GWindow parent) {
      this.PARENT = parent;
      this.paused = Boolean.FALSE;
      this.THEME_A = GMusic.newGMusic( Constants.getThemeALocation() );
      this.THEME_B = GMusic.newGMusic( Constants.getThemeBLocation() );
   }// ---

   // ----- Builder ============================
   public static GPanel newGPanel(GWindow parent) {
      GPanel rv = new GPanel(parent);

      rv.setSize(Constants.getPanelDim());

      return rv;
   }// ---

   // ----- Overrides ==========================
   @Override
   public void paintComponent(Graphics g) {
      this.manager.paint(g);
   }// ---

   // ----- Private Methods ====================
   private void initControls() {
      GPanel self = this;
      this.setFocusable(Boolean.TRUE);
      this.addKeyListener(new KeyAdapter() {

         @Override
         public void keyPressed(KeyEvent ke) {

               // get key pressed
               Integer keyPressed = ke.getKeyCode();
               Boolean moved = Boolean.FALSE;

               // take action based on button pressed
               if(!self.paused) {
                  switch(keyPressed) {
                     case KeyEvent.VK_DOWN:
                     case KeyEvent.VK_S:
                           manager.movePieceDown(Boolean.TRUE);
                           moved = Boolean.TRUE;
                           break;
                     case KeyEvent.VK_LEFT:
                     case KeyEvent.VK_A:
                           if(manager.movePieceHorizontally(Boolean.TRUE)) {
                              moved = Boolean.TRUE;
                           }
                           break;
                     case KeyEvent.VK_RIGHT:
                     case KeyEvent.VK_D:
                           if(manager.movePieceHorizontally(Boolean.FALSE)) {
                              moved = Boolean.TRUE;
                           }
                           break;
                     case KeyEvent.VK_UP:
                     case KeyEvent.VK_W:
                           if(manager.rotatePiece()) {
                              moved = Boolean.TRUE;
                           }
                           break;
                     case KeyEvent.VK_SPACE:
                           self.PARENT.pauseGame();
                           self.paused = Boolean.TRUE;
                           break;
                     default:
                           break;
                  }
               } else {
                  if(keyPressed == KeyEvent.VK_SPACE) {
                     self.PARENT.pauseGame();
                     self.paused = Boolean.FALSE;
                  }
               }

               // repaint if things have changed
               if(moved) {
                  self.getParent().repaint();
               }
         }
      });
   }

   // ----- Public Methods =====================
   public Boolean gameover() {
      Boolean rv = this.manager.gameover();

      if( rv ) {
         this.THEME_A.stopMusic();
         this.THEME_B.stopMusic();
         this.removeKeyListener( this.getKeyListeners()[0] );
      }

      return rv;
   }// ---

   public Boolean levelUp() {
      return this.manager.levelUp();
   }// ---

   public void newGame() {
      this.manager = GManager.newManager();
      this.initControls();
      this.THEME_A.stopMusic();
      this.THEME_B.stopMusic();
      this.THEME_A.startMusic();
   }// ---

   public void update() {
      this.manager.update();
      if( this.manager.isUnderPressure() && THEME_A.isRunning() ) {
         this.THEME_A.stopMusic();
         this.THEME_B.startMusic();
      } else if( !this.manager.isUnderPressure() && THEME_B.isRunning() ) {
         this.THEME_B.stopMusic();
         this.THEME_A.startMusic();
      }
   }// ---

}// =============================================

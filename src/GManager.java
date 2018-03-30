import java.awt.Graphics;

/**
 *  Top Level of the fields, handles communication between the two.
 * @author Justin Dowell
 */
public class GManager {

    // ----- Variables ==========================
    private GField gfield;
    private IField ifield;
    private Integer level;

    // ----- Constructors =======================
    private GManager() {}// ---

    // ----- Builder ============================
    public static GManager newManager() {
        GManager rv = new GManager();

        rv.initialize();

        return rv;
    }// ---

    // ----- Overrides ==========================

    // ----- Private Methods ====================
    private void initialize() {
        this.gfield = GField.newField();
        this.ifield = IField.newField();
    }// ---

    private void paintBG(Graphics g) {
        g.setColor(Constants.getLevels().get(this.ifield.getLevelIndex()).getBGColor());
        g.fillRect(0, 0, Constants.getPanelWidth(), Constants.getPanelHeight());

        // draw grid lines for background
        g.setColor(Constants.getLevels().get(this.ifield.getLevelIndex()).getBGLineColor());
        for(int i = 0; i <= Constants.getPanelWidth(); i += Constants.getSquareSize()) {
            g.drawLine(i, 0, i, Constants.getPanelHeight());
        }
        for(int i = 0; i <= Constants.getPanelHeight(); i += Constants.getSquareSize()) {
            g.drawLine(0, i, Constants.getPanelWidth(), i);
        }
    }// ---

    // ----- Public Methods =====================
    public Boolean gameover() {
        Boolean rv = this.gfield.gameover();
        if(rv) {
            this.ifield.gameOver();
        }
        return rv;
    }// ---

    public Boolean levelUp() {
        return this.ifield.levelUp();
    }// ---

    public void movePieceDown(Boolean manual) {
        this.gfield.movePieceDown(manual);
    }// ---

    public Boolean movePieceHorizontally(Boolean left) {
        return this.gfield.movePieceHorizontally(left);
    }// ---

    public Boolean rotatePiece() {
        return this.gfield.rotatePiece();
    }

    public void paint(Graphics g) {
        this.paintBG(g);
        this.gfield.paint(g);
        this.ifield.paint(g);
    }// ---

    public void update() {
        if(this.gfield.needNewPiece()) {
            this.gfield.setActivePiece(this.ifield.getNextPiece());
        } else {
            this.gfield.update();
        }
        this.ifield.addToScore(this.gfield.getPoints());
    }// ---

}// =============================================

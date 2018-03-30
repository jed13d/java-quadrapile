import java.awt.Graphics;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;

/**
 *  Information field next to GField
 * @author Justin Dowell
 */
public class IField {

    // ----- Variables ==========================
    private final LinkedList< Constants.Piece > BAG;
    private static final EnumSet ALL_PIECES = EnumSet.allOf(Constants.Piece.class);
    private Integer score;
    private Integer level;
    private Boolean gameover;

    // ----- Constructors =======================
    private IField() {
        BAG = new LinkedList< Constants.Piece >();
        score = 0;
        level = 1;
        gameover = Boolean.FALSE;
    }// ---

    // ----- Builder ============================
    public static IField newField() {
        IField rv = new IField();

        rv.fillBag();

        return rv;
    }// ---

    // ----- Private Methods ====================
    private void fillBag() {
        if(!this.BAG.isEmpty()) {
            this.BAG.clear();
        }
        ALL_PIECES.forEach(p -> {
            this.BAG.add((Constants.Piece)p);
            this.BAG.add((Constants.Piece)p);
            this.BAG.add((Constants.Piece)p);
        });
        Collections.shuffle(this.BAG);
    }// ---

    private void paintBG(Graphics g) {
        // -- Level
        g.setColor(Constants.getLevelFieldBGColor());
        g.fillRect(Constants.getLevelFieldX(), Constants.getLevelFieldY(), Constants.getLevelFieldWidth(), Constants.getLevelFieldHeight());
        g.setColor(Constants.getLevelFieldBorderColor());
        g.drawRect(Constants.getLevelFieldX(), Constants.getLevelFieldY(), Constants.getLevelFieldWidth(), Constants.getLevelFieldHeight());

        // -- score
        g.setColor(Constants.getScoreFieldBGColor());
        g.fillRect(Constants.getScoreFieldX(), Constants.getScoreFieldY(), Constants.getScoreFieldWidth(), Constants.getScoreFieldHeight());
        g.setColor(Constants.getScoreFieldBorderColor());
        g.drawRect(Constants.getScoreFieldX(), Constants.getScoreFieldY(), Constants.getScoreFieldWidth(), Constants.getScoreFieldHeight());

        // piece preview
        g.setColor(Constants.getPreviewFieldBGColor());
        g.fillRect(Constants.getPreviewFieldX(), Constants.getPreviewFieldY(), Constants.getPreviewFieldWidth(), Constants.getPreviewFieldHeight());
        g.setColor(Constants.getPreviewFieldBorderColor());
        g.drawRect(Constants.getPreviewFieldX(), Constants.getPreviewFieldY(), Constants.getPreviewFieldWidth(), Constants.getPreviewFieldHeight());
    }// ---

    private void paintPiece(Graphics g) {
        if(this.BAG.isEmpty()) {
            this.fillBag();
        }

        Integer[][] topShape = this.BAG.peek().getShape(Constants.getDefaultRotation());
        Integer startX, startY;
        switch(topShape[0].length) {
            case 2:     // square
                startX = Constants.getPreviewFieldPiecesX() - Constants.getSquareSize();
                startY = Constants.getPreviewFieldPiecesY() - Constants.getSquareSize();
                break;
            case 4:     // line
                startX = Constants.getPreviewFieldPiecesX() - (2 * Constants.getSquareSize());
                startY = Constants.getPreviewFieldPiecesY() - (Constants.getSquareSize() / 2);
                break;
            default:
                startX = Constants.getPreviewFieldPiecesX() - (Constants.getSquareSize() + (Constants.getSquareSize() / 2));
                startY = Constants.getPreviewFieldPiecesY() - Constants.getSquareSize();
                break;
        }
        for(int i = 0; i < topShape.length; i++) {
            for(int j = 0; j < topShape[i].length; j++) {
                if(topShape[i][j] != 0) {
                    g.setColor(BAG.peek().getColor());
                    g.fillRect((j * Constants.getSquareSize() + startX), (i * Constants.getSquareSize() + startY), Constants.getSquareSize(), Constants.getSquareSize());
                    g.setColor(Constants.getPieceBorderColor());
                    g.drawRect((j * Constants.getSquareSize() + startX), (i * Constants.getSquareSize() + startY), Constants.getSquareSize(), Constants.getSquareSize());
                }
            }
        }
    }// ---

    private void paintLevel(Graphics g) {
        g.setFont(Constants.getLevelFont());
        g.setColor(Constants.getLevelFontColor());
        if(!gameover) {
            g.drawString(String.format("  Level:     %03d", this.level), Constants.getLevelFontX(), Constants.getLevelFontY());
        } else {
            g.drawString(String.format("Game Over  %02d", this.level), Constants.getLevelFontX(), Constants.getLevelFontY());
        }
    }// ---

    private void paintScore(Graphics g) {
        g.setFont(Constants.getScoreFont());
        g.setColor(Constants.getScoreFontColor());
        g.drawString(String.format("%012d", this.score), Constants.getScoreFontX(), Constants.getScoreFontY());
    }// ---

    // ----- Public Methods =====================
    public void addToScore(Integer amount) {
        this.score += amount;
    }// ---

    public void gameOver() {
        this.gameover = Boolean.TRUE;
    }

    public Integer getLevelIndex() {
        return this.level - 1;
    }// ---

    public Constants.Piece getNextPiece() {
        return(BAG.poll());
    }// ---

    public Boolean levelUp() {
        Boolean rv = Boolean.FALSE;
        if(this.level < Constants.getLevels().size() && this.score > this.level * Constants.getScoreLevelIncrement()) {
            rv = Boolean.TRUE;
            level++;
        }
        return rv;
    }// ---

    public void paint(Graphics g) {
        paintBG(g);
        paintLevel(g);
        paintScore(g);
        paintPiece(g);
    }// ---

}// =============================================

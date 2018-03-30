import java.awt.Graphics;

/**
 *  Where the action takes place.
 * @author Justin Dowell
 */
public class GField {

    // ----- Variables ==========================
    private Constants.Piece active;
    private Integer activeShape[][];
    private Integer rotation;
    private Integer activeX;
    private Integer activeY;
    private final Integer[][] GAME_FIELD;
    private Boolean activePieceLanded;
    private Integer consecFullLines;
    private Integer firstFullLine;
    private Boolean gameover;
    private Integer pointsEarned;
    private Boolean fastDrop;

    // ----- Constructors =======================
    private GField() {
        // initialize blank game field
        GAME_FIELD = new Integer[Constants.getGameFieldRows()][Constants.getGameFieldCols()];
        active = null;
        activeX = activeY = rotation = firstFullLine = pointsEarned = consecFullLines = 0;
        gameover = fastDrop = activePieceLanded = Boolean.FALSE;
    }// ---

    // ----- Builder ============================
    public static GField newField() {
        GField rv = new GField();

        rv.initField();

        return rv;
    }// ---

    // ----- Private Methods ====================
    /**
     * Removes filled lines and moves down all the lines above the removed lines
     */
    private void clearFullLines() {
        // remove the lines
        for(int i = firstFullLine; i >= 0 && i > (this.firstFullLine - this.consecFullLines); i--) {
            for(int j = 0; j < this.GAME_FIELD[i].length; j++) {
                this.GAME_FIELD[i][j] = 0;
            }
        }

        // move rest of everything down
        for(int i = firstFullLine - this.consecFullLines; i >= 0; i--) {
            for(int j = 0; j < this.GAME_FIELD[i].length; j++) {
                if(this.GAME_FIELD[i][j] != 0) {
                    this.GAME_FIELD[i + this.consecFullLines][j] = this.GAME_FIELD[i][j];
                    this.GAME_FIELD[i][j] = 0;
                }
            }
        }

        this.updatePoints();
    }// ---

    /**
     * Clear the active shape off the field, usually before moving the shape.
     */
    private void clearShapeFromField() {
        for(int i = 0; i < this.activeShape.length; i++) {
            for(int j = 0; j < this.activeShape[i].length; j++) {
                if(this.activeShape[i][j] != 0) {
                    this.GAME_FIELD[this.activeY + i][this.activeX + j] = 0;
                }
            }
        }
    }// ---

    /**
     * Copy shape array to variable local to the field
     */
    private void copyShape() {
        this.activeShape = this.active.getShape(this.rotation);
    }// ---

    /**
     * Finds, marks, and reports the existance of filled lines
     * Called once a shape lands.
     * @return
     */
    private Boolean filledLines() {
        Boolean rv = Boolean.FALSE;
        Boolean counting = Boolean.FALSE;
        Boolean doneCounting = Boolean.FALSE;

        // start from bottom, work up *** outer loop
        for(int i = this.GAME_FIELD.length - 1; i >= 0; i--) {

            // either max lines reached, or a non-full line reached after a full line found
            if(doneCounting) {
                break;
            }

            // left to right *** inner loop
            for(int j = 0; j < this.GAME_FIELD[i].length; j++) {
                // if an empty space is found, done with this line
                if(this.GAME_FIELD[i][j] == 0) {
                    if(counting) {
                        doneCounting = Boolean.TRUE;
                    }
                    break;
                }

                // full line discovered
                if(j == this.GAME_FIELD[i].length - 1) {
                    //increment counter
                    this.consecFullLines++;

                    if(counting) {
                        // if max number of consec lines, done counting
                        if(this.consecFullLines == 4) {
                            doneCounting = Boolean.TRUE;
                            break;
                        }
                    } else {
                        this.firstFullLine = i;
                        counting = Boolean.TRUE;
                        rv = Boolean.TRUE;
                    }
                }
            }// done inner loop
        }// done outter loop

        return rv;
    }// ---

    /**
     * Simply zeroes out all the spaces in the field.
     */
    private void initField() {
        for(int i = 0; i < Constants.getGameFieldRows(); i++) {
            for(int j = 0; j < Constants.getGameFieldCols(); j++) {
                GAME_FIELD[i][j] = 0;
            }
        }
    }// ---

    /**
     * Paints the background and border of the field
     * @param g The Graphics object to paint to.
     */
    private void paintBG(Graphics g) {
        g.setColor(Constants.getGameFieldBGColor());
        g.fillRect(Constants.getGameFieldX(), Constants.getGameFieldY(), Constants.getGameFieldWidth(), Constants.getGameFieldHeight());
        g.setColor(Constants.getGameFieldBorderColor());
        g.drawRect(Constants.getGameFieldX(), Constants.getGameFieldY(), Constants.getGameFieldWidth(), Constants.getGameFieldHeight());
    }// ---

    /**
     * Paints all the colored squares on the field left by shapes landing.
     * @param g The Graphics object to paint to.
     */
    private void paintPieces(Graphics g) {
        for(int i = 0; i < this.GAME_FIELD.length; i++) {
            for(int j = 0; j < this.GAME_FIELD[i].length; j++) {
                if(GAME_FIELD[i][j] != 0) {
                    g.setColor(Constants.Piece.getColor(GAME_FIELD[i][j]));
                    g.fillRect((j * Constants.getSquareSize() + Constants.getGameFieldPiecesX()), (i * Constants.getSquareSize() + Constants.getGameFieldPiecesY()), Constants.getSquareSize(), Constants.getSquareSize());
                    g.setColor(Constants.getPieceBorderColor());
                    g.drawRect((j * Constants.getSquareSize() + Constants.getGameFieldPiecesX()), (i * Constants.getSquareSize() + Constants.getGameFieldPiecesY()), Constants.getSquareSize(), Constants.getSquareSize());
                }
            }
        }
    }// ---

    /**
     * Used for debugging purposes. Prints out all the values of the field.
     */
    private void printField() {
        for(Integer[] i: this.GAME_FIELD) {
            System.out.print("{ ");
            for(Integer j: i) {
                System.out.print(j +", ");
            }
            System.out.println("}");
        }
        System.out.println();
    }// ---

    /**
     * Spawns a piece if able, reports success;
     * @return
     */
    private Boolean spawnPiece() {
        Boolean rv = Boolean.TRUE;

        // Check for available space
        for(int i = 0; i < this.activeShape.length; i++) {
            for(int j = 0; j < this.activeShape[i].length; j++) {
                if(this.activeShape[i][j] != 0 && (this.activeY + i >= this.GAME_FIELD.length || this.activeX + j < 0 || this.activeX + j >= this.GAME_FIELD[i].length || this.GAME_FIELD[this.activeY + i][this.activeX + j] != 0)) {
                    rv = Boolean.FALSE;
                    break;
                }
            }
            if(!rv) {
                break;
            }
        }

        // If available, spawn
        if(rv) {
            for(int i = 0; i < this.activeShape.length; i++) {
                for(int j = 0; j < this.activeShape[i].length; j++) {
                    if(this.activeShape[i][j] != 0) {
                        this.GAME_FIELD[this.activeY + i][this.activeX + j] = this.activeShape[i][j];
                    }
                }
            }
        }

        return rv;
    }// ---

    /**
     * Called upon circumstances which require points to accumulate
     */
    private void updatePoints() {
        // consecutive lines filled
        if(this.consecFullLines > 0) {
            this.pointsEarned += ((int)Math.pow(2.0, this.consecFullLines.doubleValue() - 1.0)) * Constants.getPointsPerLine();
            this.consecFullLines = 0;
        }

        // consecutive speed drops
        if(this.fastDrop) {
            this.pointsEarned += Constants.getPointsPerFastDropIncrement();
            this.fastDrop = Boolean.FALSE;
        }

        // landed a piece
        if(this.activePieceLanded) {
            this.pointsEarned += Constants.getPointsPerLanding();
        }
    }// ---

    // ----- Public Methods =====================
    /**
     *
     * @return True if there's a gameover condition.
     */
    public Boolean gameover() {
        return this.gameover;
    }// ---

    /**
     * Returns the accumulated points and resets the stored value to 0.
     * @return Points accumulated
     */
    public Integer getPoints() {
        Integer rv = this.pointsEarned;
        this.pointsEarned = 0;
        return rv;
    }// ---

    /**
     * Moves the active piece down on the field.
     * @param manually Moves the piece down on Player's command, and makes sure
     *              they get additional points for it.
     */
    public void movePieceDown(Boolean manually) {

        // make sure there is an active piece available
        if(this.active != null && this.activeShape != null) {

            // if manually dropping, add points
            if(manually && !this.activePieceLanded && this.active != null) {
                this.fastDrop = Boolean.TRUE;
                this.updatePoints();
            }

            // if the piece isn't already on the bottom
            if((this.activeY + this.activeShape.length) < this.GAME_FIELD.length) {

                // delete the piece from the field
                this.clearShapeFromField();

                // check the row below the piece, make sure it's empty
                for(int i = 0; i < this.activeShape.length; i++) {
                    for(int j = 0; j < this.activeShape[i].length; j++) {
                        // for every spot in piece not empty, check the space below is available
                        //      or mark the piece as landed
                        if(this.activeShape[i][j] != 0 && this.activeY + 1 + i >= this.GAME_FIELD.length) {
                            this.activePieceLanded = Boolean.TRUE;
                            break;
                            // && ((this) && (this.GAME_FIELD[this.activeY + 1 + i][this.activeX + j] != 0)) {
                        } else if(this.activeShape[i][j] != 0 && (this.GAME_FIELD[this.activeY + 1 + i][this.activeX + j] != 0)) {
                            this.activePieceLanded = Boolean.TRUE;
                            break;
                        }
                    }
                    if(this.activePieceLanded) {
                        break;
                    }
                }

                // place piece in new position if able to move, and update position
                if(!this.activePieceLanded) {
                    this.activeY++;
                }

                // place piece
                for(int i = 0; i < this.activeShape.length; i++) {
                    for(int j = 0; j < this.activeShape[i].length; j++) {
                        if(this.activeShape[i][j] != 0) {
                            this.GAME_FIELD[this.activeY + i][this.activeX + j] = this.activeShape[i][j];
                        }
                    }
                }

            } else {
                this.activePieceLanded = Boolean.TRUE;
            }

            if(this.activePieceLanded) {
                this.active = null;
                this.activeShape = null;
                this.updatePoints();
                while(this.filledLines()) {
                    this.clearFullLines();
                }
            }

        } else {
            // do nothing
        }

    }// ---

    /**
     * Moves the piece horizontally based on the parameter.
     * @param left Direction to move, if not left, then right.
     * @return success
     */
    public Boolean movePieceHorizontally(Boolean left) {
        Boolean rv = Boolean.TRUE;

        if(this.active != null && this.activeShape != null) {

            // horizontal movement direction
            Integer direction = 0;
            if(left) {
                direction--;
            } else {
                direction++;
            }

            // if the piece isn't already on the wall
            if((left && this.activeX >= 0) || (!left &&(this.activeX + this.activeShape[0].length) < this.GAME_FIELD[0].length)) {

                // delete the piece from the field
                this.clearShapeFromField();

                // check the row below the piece, make sure it's empty
                for(int i = 0; i < this.activeShape.length; i++) {
                    for(int j = 0; j < this.activeShape[i].length; j++) {
                        // if bumping into a wall, move unavailable
                        if(this.activeShape[i][j] != 0 && (this.activeX + direction + j >= this.GAME_FIELD[i].length
                                                            || this.activeX + direction + j < 0)) {
                            rv = Boolean.FALSE;
                            break;
                        // if bumping into another piece, move unavailable
                        } else if(this.activeShape[i][j] != 0 && (this.GAME_FIELD[this.activeY + i][this.activeX + direction + j] != 0)) {
                            rv = Boolean.FALSE;
                            break;
                        }
                    }
                    if(!rv) {
                        break;
                    }
                }

                // place piece in new position if able to move, and update position
                if(rv) {
                    this.activeX += direction;
                } else { // or place piece back in old position if unable to move
                    rv = Boolean.FALSE;
                }

                // draw the piece
                for(int i = 0; i < this.activeShape.length; i++) {
                    for(int j = 0; j < this.activeShape[i].length; j++) {
                        if(this.activeShape[i][j] != 0) {
                            this.GAME_FIELD[this.activeY + i][this.activeX + j] = this.activeShape[i][j];
                        }
                    }
                }
            }

        } else {
            rv = Boolean.FALSE;
        }

        // return success
        return rv;
    }// ---

    /**
     * Used by manager.
     * @return Whether a piece exists or not.
     */
    public Boolean needNewPiece() {
        return(active == null ? Boolean.TRUE : Boolean.FALSE);
    }// ---

    /**
     * Top level paint method for the field.
     * @param g Grahpics component to paint to.
     */
    public void paint(Graphics g) {
        this.paintBG(g);
        this.paintPieces(g);
    }// ---

    /**
     * Rotates the piece if able
     * @return success
     */
    public Boolean rotatePiece() {
        Boolean rv = Boolean.TRUE;

        if(this.active != null && this.activeShape != null) {

            Integer[][] backup = this.activeShape;
            this.rotation = (this.rotation + 1) % 4;
            this.clearShapeFromField();

            this.copyShape();
            if(!this.spawnPiece()) {
                rv = Boolean.FALSE;
                this.activeShape = backup;
                this.rotation = (this.rotation - 1) % 4;
                this.spawnPiece();
            }

        } else {
            rv = Boolean.FALSE;
        }

        return rv;
    }// ---

    /**
     * Adds the piece to the field from outside
     * @param p
     */
    public void setActivePiece(Constants.Piece p) {
        if(this.active == null && this.activeShape == null) {
            this.active = p;
            this.rotation = Constants.getDefaultRotation();
            this.activeX = Constants.getDefaultSpawnX();
            this.activeY = Constants.getDefaultSpawnY();
            this.activePieceLanded = Boolean.FALSE;
            this.copyShape();
            if(!this.spawnPiece()) {
                this.gameover = Boolean.TRUE;
            }
        }
    }// ---

    /**
     * Called each tick, makes changes as needed.
     */
    public void update() {
        this.movePieceDown(Boolean.FALSE);
    }// ---


}// =============================================
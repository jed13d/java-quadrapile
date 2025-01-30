import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

/**
 *
 * @author Justin Dowell
 */
public class Constants {

   // ----- Variables ==========================

   // -- Window Settings
   private static final Integer WINDOW_HEIGHT = 657;
   private static final Integer WINDOW_WIDTH = 561;
   private static final Dimension WINDOW_DIM = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

   // -- Level Settings
   private static final Integer DROP_SPEED = 1000;
   private static final Integer SCORE_LEVEL_INCREMENT = 2000;

   // -- Panel Settings
   private static final Integer GPANEL_HEIGHT = 600;
   private static final Integer GPANEL_WIDTH = 550;
   private static final Dimension GPANEL_DIM = new Dimension(GPANEL_WIDTH, GPANEL_HEIGHT);

   // -- Game Field Settings
   private static final Integer HIGH_STRESS_ROW = 4;

   private static final Color GAME_FIELD_BGCOLOR = new Color(0, 0, 0);
   private static final Color GAME_FIELD_BORDER_COLOR = new Color(255, 255, 0);

   private static final Integer GAME_FIELD_X = 270;
   private static final Integer GAME_FIELD_Y = 45;
   private static final Integer GAME_FIELD_HEIGHT = 510;
   private static final Integer GAME_FIELD_WIDTH = 260;

   private static final Integer GAME_FIELD_COLS = 10;
   private static final Integer GAME_FIELD_ROWS = 20;
   private static final Integer GAME_FIELD_PIECES_X = 275;
   private static final Integer GAME_FIELD_PIECES_Y = 50;

   // -- Level Field Settings
   private static final Color LEVEL_FIELD_BGCOLOR = new Color(0, 0, 0);
   private static final Color LEVEL_FIELD_BORDER_COLOR = new Color(255, 255, 0);

   private static final Integer LEVEL_FIELD_X = 20;
   private static final Integer LEVEL_FIELD_Y = 95;
   private static final Integer LEVEL_FIELD_HEIGHT = 35;
   private static final Integer LEVEL_FIELD_WIDTH = 210;

   private static final Font LEVEL_FONT = new Font("Comic Sans", Font.BOLD, 25);
   private static final Color LEVEL_FONT_COLOR = new Color(200, 0, 0);
   private static final Integer LEVEL_FONT_X = 28;
   private static final Integer LEVEL_FONT_Y = 122;

   // -- Score Field Settings
   private static final Color SCORE_FIELD_BGCOLOR = new Color(0, 0, 0);
   private static final Color SCORE_FIELD_BORDER_COLOR = new Color(255, 255, 0);

   private static final Integer SCORE_FIELD_X = 20;
   private static final Integer SCORE_FIELD_Y = 195;
   private static final Integer SCORE_FIELD_HEIGHT = 35;
   private static final Integer SCORE_FIELD_WIDTH = 210;

   private static final Font SCORE_FONT = new Font("Comic Sans", Font.BOLD, 25);
   private static final Color SCORE_FONT_COLOR = new Color(128, 128 ,0);
   private static final Integer SCORE_FONT_X = 28;
   private static final Integer SCORE_FONT_Y = 222;

   // -- Score Points Settings
   private static final Integer POINTS_PER_LINE = 100;
   private static final Integer POINTS_PER_FAST_DROP_INCREMENT = 5;
   private static final Integer POINTS_PER_LANDING = 25;

   // -- Preview Field Settings
   private static final Color PREVIEW_FIELD_BGCOLOR = new Color(0, 0, 0);
   private static final Color PREVIEW_FIELD_BORDER_COLOR = new Color(255, 255, 0);

   private static final Integer PREVIEW_FIELD_X = 57;
   private static final Integer PREVIEW_FIELD_Y = 295;
   private static final Integer PREVIEW_FIELD_HEIGHT = 110;
   private static final Integer PREVIEW_FIELD_WIDTH = 135;

   private static final Integer PREVIEW_FIELD_PIECES_X = 125;      // center of preview field horizontal
   private static final Integer PREVIEW_FIELD_PIECES_Y = 350;      // center of preview field vertical

   // -- Piece Settings
   private static final Integer SQUARE_SIZE = 25;
   private static final Color PIECE_BORDER_COLOR = new Color(200, 200, 200);
   private static final Integer DEFAULT_SPAWN_X = 4;
   private static final Integer DEFAULT_SPAWN_Y = 0;
   private static final Integer DEFAULT_ROTATION = 0;

   // -- Music
   private static final String THEME_A_LOCATION = "src/resources/ThemeA.midi";
   private static final String THEME_B_LOCATION = "src/resources/ThemeB.midi";

   // ----- Enums ==============================
   public static enum Level {
      ONE(new Color(100, 100, 100), new Color(0, 0, 0)),
      TWO(new Color(200, 50, 50), new Color(0, 0, 0)),
      THREE(new Color(50, 200, 50), new Color(0, 0, 0)),
      FOUR(new Color(50, 50, 200), new Color(0, 0, 0)),
      FIVE(new Color(150, 150, 50), new Color(0, 0, 0)),
      SIX(new Color(50, 150, 150), new Color(0, 0, 0)),
      SEVEN(new Color(75, 75, 75), new Color(0, 0, 0)),
      EIGHT(new Color(123, 123, 123), new Color(0, 0, 0)),
      NINE(new Color(0, 230, 230), new Color(0, 0, 0)),
      TEN(new Color(200, 100, 200), new Color(0, 0, 0)),
      ELEVEN(new Color(230, 0, 0), new Color(0, 0, 0)),
      TWELVE(new Color(0, 230, 0), new Color(0, 0, 0)),
      THIRTEEN(new Color(0, 0, 230), new Color(0, 0, 0)),
      FOURTEEN(new Color(75, 75, 75), new Color(0, 0, 0)),
      FIFTEEN(new Color(200, 100, 200), new Color(0, 0, 0)),
      SIXTEEN(new Color(238, 122, 35), new Color(0, 0, 0)),
      SEVENTEEN(new Color(122, 238, 35), new Color(0, 0, 0)),
      EIGHTEEN(new Color(35, 122, 238), new Color(0, 0, 0)),
      NINETEEN(new Color(200, 100, 200), new Color(0, 0, 0)),
      TWENTY(new Color(35, 238, 122), new Color(0, 0, 0)),
      ;

      // ----- Variables ----------------------
      private final Color BG, BGLINE;

      // ----- Constructors -------------------
      private Level(Color backgroundC, Color bgLineC) {
         BG = backgroundC;
         BGLINE = bgLineC;
      }// -

      // ----- Methods ------------------------
      public final Color getBGColor() {
         return this.BG;
      }// -

      public final Color getBGLineColor() {
         return this.BGLINE;
      }// -

   }// =====================--------------------

   private static final ArrayList< Level > LEVELS = new ArrayList<>();
   static {
      LEVELS.add(Level.ONE);
      LEVELS.add(Level.TWO);
      LEVELS.add(Level.THREE);
      LEVELS.add(Level.FOUR);
      LEVELS.add(Level.FIVE);
      LEVELS.add(Level.SIX);
      LEVELS.add(Level.SEVEN);
      LEVELS.add(Level.EIGHT);
      LEVELS.add(Level.NINE);
      LEVELS.add(Level.TEN);
      LEVELS.add(Level.ELEVEN);
      LEVELS.add(Level.TWELVE);
      LEVELS.add(Level.THIRTEEN);
      LEVELS.add(Level.FOURTEEN);
      LEVELS.add(Level.FIFTEEN);
      LEVELS.add(Level.SIXTEEN);
      LEVELS.add(Level.SEVENTEEN);
      LEVELS.add(Level.EIGHTEEN);
      LEVELS.add(Level.NINETEEN);
      LEVELS.add(Level.TWENTY);
   }// ---

   public static enum Piece {
      LINE, BACK_L, FORW_L, SQUARE, S, Z, T
      ;

      private static final Color LINE_COLOR = new Color(0, 230, 230);
      private static final Color BACK_L_COLOR = new Color(102, 102, 255);
      private static final Color FORW_L_COLOR = new Color(255, 128, 0);
      private static final Color SQUARE_COLOR = new Color(238, 222, 0);
      private static final Color S_COLOR = new Color(0, 128, 0);
      private static final Color Z_COLOR = new Color(180, 0, 0);
      private static final Color T_COLOR = new Color(128, 80, 128);

      // ----- Constructor --------------------
      private Piece() {}// -

      // ----- Accessor Methods ---------------
      public final Integer[][] getShape(Integer r) {
         Integer[][] rv;
         switch(this) {
            case LINE:
               switch (r) {
                  case 1:
                  case 3:
                     rv = new Integer[][] {{1},
                     {1},
                     {1},
                     {1}};
                     break;
                  default:
                     rv = new Integer[][] {{1, 1, 1, 1}};
                     break;
               }
               break;
            case BACK_L:
               switch (r) {
                  case 1:
                     rv = new Integer[][] {{2, 2},
                     {2, 0},
                     {2, 0}};
                     break;
                  case 2:
                     rv = new Integer[][] {{2, 2, 2},
                     {0, 0, 2}};
                     break;
                  case 3:
                     rv = new Integer[][] {{0, 2},
                     {0, 2},
                     {2, 2}};
                     break;
                  default:
                     rv = new Integer[][] {{2, 0, 0},
                     {2, 2, 2}};
                     break;
               }
               break;
            case FORW_L:
               switch (r) {
                  case 1:
                     rv = new Integer[][] {{3, 0},
                     {3, 0},
                     {3, 3}};
                     break;
                  case 2:
                     rv = new Integer[][] {{3, 3, 3},
                     {3, 0, 0}};
                     break;
                  case 3:
                     rv = new Integer[][] {{3, 3},
                     {0, 3},
                     {0, 3}};
                     break;
                  default:
                     rv = new Integer[][] {{0, 0, 3},
                     {3, 3, 3}};
                     break;
               }
               break;
            case SQUARE:
               rv = new Integer[][] {{4, 4},
               {4, 4}};
               break;
            case S:
               switch (r) {
                  case 1:
                  case 3:
                     rv = new Integer[][] {{5, 0},
                     {5, 5},
                     {0, 5}};
                     break;
                  default:
                     rv = new Integer[][] {{0, 5, 5},
                     {5, 5, 0}};
                     break;
               }
               break;
            case Z:
               switch (r) {
                  case 1:
                  case 3:
                     rv = new Integer[][] {{0, 6},
                     {6, 6},
                     {6, 0}};
                     break;
                  default:
                     rv = new Integer[][] {{6, 6, 0},
                     {0, 6, 6}};
                     break;
               }
               break;
            case T:
               switch (r) {
                  case 1:
                     rv = new Integer[][] {{7, 0},
                     {7, 7},
                     {7, 0}};
                     break;
                  case 2:
                     rv = new Integer[][] {{7, 7, 7},
                     {0, 7, 0}};
                     break;
                  case 3:
                     rv = new Integer[][] {{0, 7},
                     {7, 7},
                     {0, 7}};
                     break;
                  default:
                     rv = new Integer[][] {{0, 7, 0},
                     {7, 7, 7}};
                     break;
               }
               break;
            default:
               rv = new Integer[0][0];
               break;
         }
         return rv;
      }// -

      public final Color getColor() {
         switch(this) {
            case LINE:
               return LINE_COLOR;
            case BACK_L:
               return BACK_L_COLOR;
            case FORW_L:
               return FORW_L_COLOR;
            case SQUARE:
               return SQUARE_COLOR;
            case S:
               return S_COLOR;
            case Z:
               return Z_COLOR;
            case T:
               return T_COLOR;
         }
         return null;
      }// -

      public static final Color getColor(Integer v) {
         switch(v) {
            case 1:
               return LINE_COLOR;
            case 2:
               return BACK_L_COLOR;
            case 3:
               return FORW_L_COLOR;
            case 4:
               return SQUARE_COLOR;
            case 5:
               return S_COLOR;
            case 6:
               return Z_COLOR;
            case 7:
               return T_COLOR;
            default:
               return Color.BLACK;
         }
      }// -

   }// =====================--------------------

   // ----- Constructors =======================
   private Constants() {}// ---

   // ----- Getter Methods =====================
   public static Integer getWindowHeight() {
      return WINDOW_HEIGHT;
   }// ---

   public static Integer getWindowWidth() {
      return WINDOW_WIDTH;
   }// ---

   public static Dimension getWindowDim() {
      return WINDOW_DIM;
   }// ---

   public static Integer getDropSpeed() {
      return DROP_SPEED;
   }

   public static Integer getScoreLevelIncrement() {
      return SCORE_LEVEL_INCREMENT;
   }// ---

   public static Integer getPanelHeight() {
      return GPANEL_HEIGHT;
   }// ---

   public static Integer getPanelWidth() {
      return GPANEL_WIDTH;
   }// ---

   public static Dimension getPanelDim() {
      return GPANEL_DIM;
   }// ---

   public static Integer getSquareSize() {
      return SQUARE_SIZE;
   }// ---

   public static Integer getHighStressRow() {
      return HIGH_STRESS_ROW;
   }

   public static Color getGameFieldBGColor() {
      return GAME_FIELD_BGCOLOR;
   }// ---

   public static Color getGameFieldBorderColor() {
      return GAME_FIELD_BORDER_COLOR;
   }// ---

   public static Integer getGameFieldX() {
      return GAME_FIELD_X;
   }// ---

   public static Integer getGameFieldY() {
      return GAME_FIELD_Y;
   }// ---

   public static Integer getGameFieldWidth() {
      return GAME_FIELD_WIDTH;
   }// ---

   public static Integer getGameFieldHeight() {
      return GAME_FIELD_HEIGHT;
   }// ---

   public static Integer getGameFieldCols() {
      return GAME_FIELD_COLS;
   }// ---

   public static Integer getGameFieldRows() {
      return GAME_FIELD_ROWS;
   }// ---

   public static Integer getGameFieldPiecesX() {
      return GAME_FIELD_PIECES_X;
   }// ---

   public static Integer getGameFieldPiecesY() {
      return GAME_FIELD_PIECES_Y;
   }// ---

   public static Color getLevelFieldBGColor() {
      return LEVEL_FIELD_BGCOLOR;
   }// ---

   public static Color getLevelFieldBorderColor() {
      return LEVEL_FIELD_BORDER_COLOR;
   }// ---

   public static Integer getLevelFieldX() {
      return LEVEL_FIELD_X;
   }// ---

   public static Integer getLevelFieldY() {
      return LEVEL_FIELD_Y;
   }// ---

   public static Integer getLevelFieldHeight() {
      return LEVEL_FIELD_HEIGHT;
   }// ---

   public static Integer getLevelFieldWidth() {
      return LEVEL_FIELD_WIDTH;
   }// ---

   public static Font getLevelFont() {
      return LEVEL_FONT;
   }// ---

   public static Color getLevelFontColor() {
      return LEVEL_FONT_COLOR;
   }// ---

   public static Integer getLevelFontX() {
      return LEVEL_FONT_X;
   }// ---

   public static Integer getLevelFontY() {
      return LEVEL_FONT_Y;
   }// ---

   public static Color getScoreFieldBGColor() {
      return SCORE_FIELD_BGCOLOR;
   }// ---

   public static Color getScoreFieldBorderColor() {
      return SCORE_FIELD_BORDER_COLOR;
   }// ---

   public static Integer getScoreFieldX() {
      return SCORE_FIELD_X;
   }// ---

   public static Integer getScoreFieldY() {
      return SCORE_FIELD_Y;
   }// ---

   public static Integer getScoreFieldHeight() {
      return SCORE_FIELD_HEIGHT;
   }// ---

   public static Integer getScoreFieldWidth() {
      return SCORE_FIELD_WIDTH;
   }// ---

   public static Font getScoreFont() {
      return SCORE_FONT;
   }// ---

   public static Color getScoreFontColor() {
      return SCORE_FONT_COLOR;
   }// ---

   public static Integer getScoreFontX() {
      return SCORE_FONT_X;
   }// ---

   public static Integer getScoreFontY() {
      return SCORE_FONT_Y;
   }// ---

   public static Integer getPointsPerLine() {
      return POINTS_PER_LINE;
   }// ---

   public static Integer getPointsPerFastDropIncrement() {
      return POINTS_PER_FAST_DROP_INCREMENT;
   }// ---

   public static Integer getPointsPerLanding() {
      return POINTS_PER_LANDING;
   }// ---

   public static Color getPreviewFieldBGColor() {
      return PREVIEW_FIELD_BGCOLOR;
   }// ---

   public static Color getPreviewFieldBorderColor() {
      return PREVIEW_FIELD_BORDER_COLOR;
   }// ---

   public static Integer getPreviewFieldX() {
      return PREVIEW_FIELD_X;
   }// ---

   public static Integer getPreviewFieldY() {
      return PREVIEW_FIELD_Y;
   }// ---

   public static Integer getPreviewFieldHeight() {
      return PREVIEW_FIELD_HEIGHT;
   }// ---

   public static Integer getPreviewFieldWidth() {
      return PREVIEW_FIELD_WIDTH;
   }// ---

   public static Integer getPreviewFieldPiecesX() {
      return PREVIEW_FIELD_PIECES_X;
   }// ---

   public static Integer getPreviewFieldPiecesY() {
      return PREVIEW_FIELD_PIECES_Y;
   }// ---

   public static Color getPieceBorderColor() {
      return PIECE_BORDER_COLOR;
   }// ---

   public static Integer getDefaultSpawnX() {
      return DEFAULT_SPAWN_X;
   }// ---

   public static Integer getDefaultSpawnY() {
      return DEFAULT_SPAWN_Y;
   }// ---

   public static Integer getDefaultRotation() {
      return DEFAULT_ROTATION;
   }// ---

   public static String getThemeALocation() {
      return THEME_A_LOCATION;
   }// ---

   public static String getThemeBLocation() {
      return THEME_B_LOCATION;
   }// ---

   public static ArrayList< Level > getLevels() {
      return LEVELS;
   }// ---

}// =============================================

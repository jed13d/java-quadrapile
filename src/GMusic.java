import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class GMusic {

   // ----- Vatiables ==========================
   private File musicFile;
   private Sequencer SEQUENCER;

   // ----- Constructors =======================
   private GMusic( String fileLocation ) {
      musicFile = null;
      SEQUENCER = null;
      try{
         musicFile = new File( fileLocation );
         SEQUENCER = MidiSystem.getSequencer();
         SEQUENCER.setSequence( MidiSystem.getSequence(musicFile) );
         SEQUENCER.open();
      } catch( IOException | InvalidMidiDataException | MidiUnavailableException e ) {
         System.err.println( "Music file load error: " );
         System.err.println( e.toString() );
      }
   }// ---

   // ----- Builders ===========================
   public static GMusic newGMusic( String fileLocation ) {
      GMusic rv = new GMusic( fileLocation );

      return rv;
   }// ---

   // ----- Private Methods ====================

   // ----- Public Methods =====================
   public Boolean isRunning() {
      return SEQUENCER.isRunning();
   }// ---

   public void startMusic() {
      if( !SEQUENCER.isRunning() ) {
         if( SEQUENCER.getLoopCount() != Sequencer.LOOP_CONTINUOUSLY ) {
            SEQUENCER.setLoopCount( Sequencer.LOOP_CONTINUOUSLY );
         }
         SEQUENCER.start();
      }
   }// ---

   public void stopMusic() {
      if( SEQUENCER.isRunning() ) {
         SEQUENCER.stop();
         SEQUENCER.setTickPosition( 0 );
      }
   }// ---

}
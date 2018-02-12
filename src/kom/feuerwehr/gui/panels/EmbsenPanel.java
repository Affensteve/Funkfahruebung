
package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class EmbsenPanel {

   private JPanel embsenPanel;
   private Checkbox mtwEmbsen;

   public EmbsenPanel( ItemListener listener ) {
      embsenPanel = new JPanel();
      embsenPanel.setMinimumSize( new Dimension( 100, 10 ) );
      embsenPanel.setLayout( new MigLayout( "wrap 1" ) );
      embsenPanel.add( new JLabel( "Embsen" ) );
      embsenPanel.add( new JLabel( new ImageIcon( "res/Wappen_Embsen.png" ) ) );
      Checkbox rw1Embsen = new Checkbox( "18-51-30" );
      rw1Embsen.addItemListener( listener );
      embsenPanel.add( rw1Embsen );
      Checkbox lfEmbsen = new Checkbox( "18-47-30" );
      lfEmbsen.addItemListener( listener );
      embsenPanel.add( lfEmbsen );
      this.mtwEmbsen = new Checkbox( "18-17-30" );
      this.mtwEmbsen.setEnabled( false );
      this.mtwEmbsen.addItemListener( listener );
      embsenPanel.add( this.mtwEmbsen );
      embsenPanel.add( new JLabel( " " ), "wrap 8" );
   }

   public JPanel getEmbsenPanel( ) {
      return embsenPanel;
   }

   public void enableCheckBoxMTWEmbsen( boolean enable ) {
      this.mtwEmbsen.setEnabled( enable );
   }
}

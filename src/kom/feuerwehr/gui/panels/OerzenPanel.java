
package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class OerzenPanel {

   private JPanel oerzenPanel;
   private Checkbox mtwOerzen;

   public OerzenPanel( ItemListener listener ) {
      oerzenPanel = new JPanel();
      oerzenPanel.setMinimumSize( new Dimension( 100, 10 ) );
      oerzenPanel.setLayout( new MigLayout( "wrap 1" ) );
      oerzenPanel.add( new JLabel( "Oerzen" ) );
      oerzenPanel.add( new JLabel( new ImageIcon( "res/Wappen_Embsen.png" ) ) );
      Checkbox tlfOerzen = new Checkbox( "18-47-32" );
      tlfOerzen.addItemListener( listener );
      oerzenPanel.add( tlfOerzen );
      Checkbox tsfOerzen = new Checkbox( "18-40-32" );
      tsfOerzen.addItemListener( listener );
      oerzenPanel.add( tsfOerzen );
      mtwOerzen = new Checkbox( "18-17-32" );
      mtwOerzen.setEnabled( false );
      mtwOerzen.addItemListener( listener );
      oerzenPanel.add( mtwOerzen );
      oerzenPanel.add( new JLabel( " " ), "wrap 8" );
   }

   public JPanel getOerzenPanel( ) {
      return oerzenPanel;
   }

   public void enableCheckBoxMTWOerzen( boolean enable ) {
      this.mtwOerzen.setEnabled( enable );
   }
}

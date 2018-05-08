
package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class DeutschEvernPanel {

   private JPanel deutschEvernPanel;
   private Checkbox mtwDE;

   public DeutschEvernPanel( ItemListener listener ) {
      deutschEvernPanel = new JPanel();
      deutschEvernPanel.setMinimumSize( new Dimension( 100, 10 ) );
      deutschEvernPanel.setLayout( new MigLayout( "wrap 1" ) );
      deutschEvernPanel.add( new JLabel( "Deutsch Evern" ) );
      deutschEvernPanel.add( new JLabel( new ImageIcon( "res/Wappen_Deutsch_Evern.png" ) ) );
      Checkbox lf8DE = new Checkbox( "18-45-20" );
      lf8DE.addItemListener( listener );
      deutschEvernPanel.add( lf8DE );
      Checkbox tlfDE = new Checkbox( "18-24-20" );
      tlfDE.addItemListener( listener );
      deutschEvernPanel.add( tlfDE );
      this.mtwDE = new Checkbox( "18-17-20" );
      this.enableCheckBoxMTWDeutschEvern( false );
      this.mtwDE.addItemListener( listener );
      deutschEvernPanel.add( this.mtwDE );
      deutschEvernPanel.add( new JLabel( " " ), "wrap 8" );
   }

   public JPanel getDeutschEvernPanel( ) {
      return deutschEvernPanel;
   }

   public void enableCheckBoxMTWDeutschEvern( boolean enable ) {
      this.mtwDE.setEnabled( enable );
   }

}


package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class BarnstedtPanel {

   private JPanel barnstedtPanel;
   private Checkbox mtwBarnstedt;

   public BarnstedtPanel( ItemListener listener ) {
      barnstedtPanel = new JPanel();
      barnstedtPanel.setMinimumSize( new Dimension( 100, 10 ) );
      barnstedtPanel.setLayout( new MigLayout( "wrap 1" ) );
      barnstedtPanel.add( new JLabel( "Barnstedt" ) );
      barnstedtPanel.add( new JLabel( new ImageIcon( "res/Wappen_Barnstedt.png" ) ) );
      Checkbox tlfBarnstedt = new Checkbox( "18-23-40" );
      tlfBarnstedt.addItemListener( listener );
      barnstedtPanel.add( tlfBarnstedt );
      Checkbox tsfbarnstedt = new Checkbox( "18-40-40" );
      tsfbarnstedt.addItemListener( listener );
      barnstedtPanel.add( tsfbarnstedt );
      mtwBarnstedt = new Checkbox( "18-17-40" );
      mtwBarnstedt.setEnabled( false );
      mtwBarnstedt.addItemListener( listener );
      barnstedtPanel.add( mtwBarnstedt );
      barnstedtPanel.add( new JLabel( " " ), "wrap 8" );
   }

   public JPanel getBarnstedtPanel( ) {
      return barnstedtPanel;
   }

   public void enableCheckBoxMTWBarnstedt( boolean enable ) {
      this.mtwBarnstedt.setEnabled( enable );
   }
}

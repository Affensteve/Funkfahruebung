
package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MelbeckPanel {

   private JPanel melbeckPanel;
   private Checkbox mtwMelbeck;

   public MelbeckPanel(ItemListener listener ) {
      melbeckPanel = new JPanel();
      melbeckPanel.setMinimumSize( new Dimension( 100, 10 ) );
      melbeckPanel.setLayout( new MigLayout( "wrap 1" ) );
      melbeckPanel.add( new JLabel( "Melbeck" ) );
      melbeckPanel.add( new JLabel( new ImageIcon( "res/Wappen_Melbeck.png" ) ) );
      Checkbox tlfMelbeck = new Checkbox( "18-24-10" );
      tlfMelbeck.addItemListener( listener );
      melbeckPanel.add( tlfMelbeck );
      Checkbox lf16Melbeck = new Checkbox( "18-44-10" );
      lf16Melbeck.addItemListener( listener );
      melbeckPanel.add( lf16Melbeck );
//      Checkbox lf8Melbeck = new Checkbox( "18-43-10" );
//      lf8Melbeck.addItemListener( listener );
//      melbeckPanel.add( lf8Melbeck );
      this.mtwMelbeck = new Checkbox( "18-17-10" );
      enableCheckBoxMTWMelbeck( true );
      this.mtwMelbeck.addItemListener( listener );
      melbeckPanel.add( this.mtwMelbeck );
      melbeckPanel.add( new JLabel( " " ), "wrap 8" );
   }

   public JPanel getMelbeckPanel( ) {
      return melbeckPanel;
   }

   public void enableCheckBoxMTWMelbeck( boolean enable ) {
      this.mtwMelbeck.setEnabled( enable );
   }

}


package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class KolkhagenPanel {

   private JPanel kolkhagenPanel;

   public KolkhagenPanel( ItemListener listener ) {
      kolkhagenPanel = new JPanel();
      kolkhagenPanel.setMinimumSize( new Dimension( 100, 10 ) );
      kolkhagenPanel.setLayout( new MigLayout( "wrap 1" ) );
      kolkhagenPanel.add( new JLabel( "Kolkhagen" ) );
      kolkhagenPanel.add( new JLabel( new ImageIcon( "res/Wappen_Kolkhagen.png" ) ) );
      Checkbox lf8Kolkhagen = new Checkbox( "18-43-42" );
      lf8Kolkhagen.addItemListener( listener );
      kolkhagenPanel.add( lf8Kolkhagen );
      kolkhagenPanel.add( new JLabel( " " ), "wrap 32" );
      kolkhagenPanel.add( new JLabel( " " ), "wrap 10" );
   }

   public JPanel getKolkhagenPanel( ) {
      return kolkhagenPanel;
   }
}

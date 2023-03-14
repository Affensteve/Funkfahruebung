package kom.feuerwehr.gui.panels;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class OerzenPanel {

   private final JPanel oerzenPanel;
   private final Checkbox mtwOerzen;

   public String gast1Label = "Gast1";

   public OerzenPanel( ItemListener listener ) {
      oerzenPanel = new JPanel();
      oerzenPanel.setMinimumSize( new Dimension( 100, 10 ) );
      oerzenPanel.setLayout( new MigLayout( "wrap 1" ) );
      oerzenPanel.add( new JLabel( "Oerzen" ) );
      oerzenPanel.add( new JLabel( new ImageIcon( "res/Wappen_Embsen.png" ) ) );
      Checkbox tlfOerzen = new Checkbox( "18-23-32" );
      tlfOerzen.addItemListener( listener );
      oerzenPanel.add( tlfOerzen );
//      Checkbox tsfOerzen = new Checkbox( "18-40-32" );
//      tsfOerzen.addItemListener( listener );
//      oerzenPanel.add( tsfOerzen );
      mtwOerzen = new Checkbox( "18-17-32" );
      mtwOerzen.setEnabled( true );
      mtwOerzen.addItemListener( listener );
      oerzenPanel.add( mtwOerzen );
      Checkbox gast1 = new Checkbox( getGast1Label() );
      gast1.addItemListener( listener );
      oerzenPanel.add( gast1 );
      oerzenPanel.add( new JLabel( " " ), "wrap 8" );
   }

   public JPanel getOerzenPanel( ) {
      return oerzenPanel;
   }

   public void enableCheckBoxMTWOerzen( boolean enable ) {
      this.mtwOerzen.setEnabled( enable );
   }

   public String getGast1Label( ) {
      return gast1Label;
   }

   public void setGast1Label( String gast1Label ) {
      this.gast1Label = gast1Label;
   }
}

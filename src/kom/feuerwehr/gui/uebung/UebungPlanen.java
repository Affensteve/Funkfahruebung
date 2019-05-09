package kom.feuerwehr.gui.uebung;

import kom.feuerwehr.gui.external.VerticalLabelUI;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class UebungPlanen extends JDialog {

   public UebungPlanen( ) {
      createAndShowView();
   }

   public void createAndShowView( ) {
      try {
         UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
      } catch( ClassNotFoundException e ) {
         e.printStackTrace();
      } catch( InstantiationException e ) {
         e.printStackTrace();
      } catch( IllegalAccessException e ) {
         e.printStackTrace();
      } catch( UnsupportedLookAndFeelException e ) {
         e.printStackTrace();
      }
      JPanel uebungPlanenPanel = new JPanel();
      uebungPlanenPanel.setLayout( new MigLayout( "wrap 10", "[][][30px][30px][30px][30px][30px][30px][][]",
            "[][][][][][][][][][][][][][][][][][][][][][][]" ) );

      uebungPlanenPanel.add( new JLabel( "Rufname" ), "cell 1 0" );
      uebungPlanenPanel.add( new JLabel( "1" ), "cell 2 0" );
      uebungPlanenPanel.add( new JLabel( "2" ), "cell 3 0" );
      uebungPlanenPanel.add( new JLabel( "3" ), "cell 4 0" );
      uebungPlanenPanel.add( new JLabel( "4" ), "cell 5 0" );
      uebungPlanenPanel.add( new JLabel( "5" ), "cell 6 0" );
      uebungPlanenPanel.add( new JLabel( "6" ), "cell 7 0" );
      uebungPlanenPanel.add( new JLabel( "Kanal 1" ), "cell 8 0" );
      uebungPlanenPanel.add( new JLabel( "Kanal 2" ), "cell 9 0" );

      createOverviewLabels( uebungPlanenPanel, "Melbeck", "cell 0 1", false );
      createOverviewLabels( uebungPlanenPanel, "Deutsch Evern", "cell 0 6", false );
      createOverviewLabels( uebungPlanenPanel, "Embsen", "cell 0 10", false );
      createOverviewLabels( uebungPlanenPanel, "Oerzen", "cell 0 14", false );
      createOverviewLabels( uebungPlanenPanel, "Barnstedt", "cell 0 18", false );
      uebungPlanenPanel.add( new JLabel( "Kolkhagen" ), "cell 0 22" );
      uebungPlanenPanel.add( new JButton( "Abbrechen" ), "cell 8 23" );
      uebungPlanenPanel.add( new JButton( "�bernehmen" ), "cell 9 23" );

      uebungsplaner( uebungPlanenPanel, "18-47-10", "1" );
      uebungsplaner( uebungPlanenPanel, "18-44-10", "2" );
      uebungsplaner( uebungPlanenPanel, "18-43-10", "3" );
      uebungsplaner( uebungPlanenPanel, "18-17-10", "4" );
      uebungsplaner( uebungPlanenPanel, "18-43-20", "5" );
      uebungsplaner( uebungPlanenPanel, "18-24-20", "6" );
      uebungsplaner( uebungPlanenPanel, "18-17-20", "7" );
      uebungsplaner( uebungPlanenPanel, "18-51-30", "8" );
      uebungsplaner( uebungPlanenPanel, "18-47-30", "9" );
      uebungsplaner( uebungPlanenPanel, "18-17-30", "10" );
      uebungsplaner( uebungPlanenPanel, "18-47-32", "11" );
      uebungsplaner( uebungPlanenPanel, "18-40-32", "12" );
      uebungsplaner( uebungPlanenPanel, "18-17-32", "13" );
      uebungsplaner( uebungPlanenPanel, "18-47-40", "14" );
      uebungsplaner( uebungPlanenPanel, "18-40-40", "15" );
      uebungsplaner( uebungPlanenPanel, "18-17-40", "16" );
      uebungsplaner( uebungPlanenPanel, "18-43-42", "17" );

      setTitle( "Funkfahr�bung - �bung planen" );
      setDefaultCloseOperation( JDialog.HIDE_ON_CLOSE );
      setModal( true );
      add( uebungPlanenPanel );

      setSize( 500, 600 );
      setVisible( true );

   }

   private void uebungsplaner( JPanel uebungPlanenPanel, String rufname, String row ) {
      uebungPlanenPanel.add( new JLabel( rufname ), "cell 1 " + row );
      uebungPlanenPanel.add( new JTextField(), "cell 2 " + row );
      uebungPlanenPanel.add( new JTextField(), "cell 3 " + row );
      uebungPlanenPanel.add( new JTextField(), "cell 4 " + row );
      uebungPlanenPanel.add( new JTextField(), "cell 5 " + row );
      uebungPlanenPanel.add( new JTextField(), "cell 6 " + row );
      uebungPlanenPanel.add( new JTextField(), "cell 7 " + row );
      uebungPlanenPanel.add( new JRadioButton(), "cell 8 " + row );
      uebungPlanenPanel.add( new JRadioButton(), "cell 9 " + row );
   }

   private void createOverviewLabels( JPanel uebungPlanenPanel, String label, String cell, boolean clockwise ) {
      JLabel melbeckLabel = new JLabel( label );
      melbeckLabel.setUI( new VerticalLabelUI( clockwise ) );
      uebungPlanenPanel.add( melbeckLabel, cell );
   }

}

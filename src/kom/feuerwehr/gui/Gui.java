package kom.feuerwehr.gui;

import kom.feuerwehr.gui.external.Switch;
import kom.feuerwehr.gui.panels.BarnstedtPanel;
import kom.feuerwehr.gui.panels.DeutschEvernPanel;
import kom.feuerwehr.gui.panels.EmbsenPanel;
import kom.feuerwehr.gui.panels.KolkhagenPanel;
import kom.feuerwehr.gui.panels.MelbeckPanel;
import kom.feuerwehr.gui.panels.OerzenPanel;
import kom.feuerwehr.task.ExportMap;
import kom.feuerwehr.task.ReadExcel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings( "serial" )
public class Gui extends JFrame implements ItemListener {

   private final JLabel rufname_lbl = new JLabel( "18-11-30" );
   private final ReadExcel reader;
   private final JButton btnRichtig;
   private final JButton buttonBack;
   private final JLabel lblAuto;
   private final JLabel lblKoordinaten;
   private final JTextArea lblFrage;
   private final JTextArea lblBemerkung;
   private final JTextArea lblAntwort;
   private final JPanel mtwMelbeckQueuePanel;
   private final JPanel lf16MelbeckQueuePanel;
   private final JPanel tlfMelbeckQueuePanel;
   private final JPanel tlfDeutschEvernQueuePanel;
   private final JPanel lf8DeutschEvernQueuePanel;
   private final JPanel mtwDeutschEvernQueuePanel;
   private final JPanel rw1EmbsenQueuePanel;
   private final JPanel lfEmbsenQueuePanel;
   private final JPanel mtwEmbsenQueuePanel;
   private final JPanel mtwOerzenQueuePanel;
   private final JPanel tlfOerzenQueuePanel;
   private final JPanel mtwBarnstedtQueuePanel;
   private final JPanel tlfBarnstedtQueuePanel;
   private final JPanel lf8KolkhagenQueuePanel;
   private final JPanel gast1QueuePanel;
   private final JPanel gast2QueuePanel;
   private final JPanel gast3QueuePanel;
   private final JPanel gast4QueuePanel;
   private final JPanel gast5QueuePanel;
   private final JPanel vehiclePanel;
   private final Switch btnSwitchConnection;
   private final JTextField textField;
   private Socket socketServer;
   private int tlfBarnstedtIndex = 0;
   private int tsfBarnstedtIndex = 0;
   private int mtwBarnstedtIndex = 0;
   private int tlfOerzenIndex = 0;
   private int tsfOerzenIndex = 0;
   private int mtwOerzenIndex = 0;
   private int lf8KolkhagenIndex = 0;
   private int rwEmbsenIndex = 0;
   private int lfEmbsenIndex = 0;
   private int mtwEmbsenIndex = 0;
   private int tlfMelbeckIndex = 0;
   private int lf16MelbeckIndex = 0;
   private int mtwMelbeckIndex = 0;
   private int tlfDeutschEvernIndex = 0;
   private int lf8DeutschEvernIndex = 0;
   private int mtwDeutschEvernIndex = 0;
   private int gast1Index = 0;
   private int gast2Index = 0;
   private int gast3Index = 0;
   private int gast4Index = 0;
   private int gast5Index = 0;
   private String button;
   private JPanel lf8MelbeckQueuePanel;
   private JPanel tsfOerzenQueuePanel;
   private JPanel tsfBarnstedtQueuePanel;
   private boolean offlineArbeiten = true;
   private MelbeckPanel melbeckPanel;
   private DeutschEvernPanel deutschEvernPanel;
   private EmbsenPanel embsenPanel;
   private OerzenPanel oerzenPanel;
   private BarnstedtPanel barnstedtPanel;
   private KolkhagenPanel kolkhagenPanel;

   public Gui( ) {
      reader = new ReadExcel();
      reader.setInputFile( "Koordinaten/Koordinaten.xls" );
      try {
         reader.read();
      } catch( IOException e ) {
         e.printStackTrace();
      }

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
      vehiclePanel = new JPanel();
      vehiclePanel.setLayout( new MigLayout( "wrap 6", "[115][115][115][115][115][115][]", "[][35][35][35][][][][][][][][][]" ) );

      setupPanel();

      JPanel panel = new JPanel();

      JLabel lblVerbunden = new JLabel( "verbunden:" );
      panel.add( lblVerbunden );

      btnSwitchConnection = new Switch();
      btnSwitchConnection.setOnOff( false );
      panel.add( btnSwitchConnection, new GridBagLayout() );
      panel.setLayout( new MigLayout( "wrap 1" ) );
      vehiclePanel.add( panel, "hidemode 3,cell 6 0,aligny top" );

      JPanel queuePanel = new JPanel();
      JPanel label = new JPanel();
      label.add( new JLabel( "sortiert: " ) );
      queuePanel.add( label );
      vehiclePanel.add( queuePanel, "cell 0 1" );

      tlfMelbeckQueuePanel = new JPanel();
      tlfMelbeckQueuePanel.addMouseListener( mouseListener( tlfMelbeckQueuePanel ) );
      tlfMelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent e ) {
            handleSortedVehicleQueue( tlfMelbeckIndex, "tlfMelbeckIndex", "18-24-10" );
         }
      } );
      JLabel label_18 = new JLabel( "18-24-10" );
      label_18.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfMelbeckQueuePanel.add( label_18 );
      tlfMelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( tlfMelbeckQueuePanel, "hidemode 3" );

      lf16MelbeckQueuePanel = new JPanel();
      lf16MelbeckQueuePanel.addMouseListener( mouseListener( lf16MelbeckQueuePanel ) );
      lf16MelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent e ) {
            handleSortedVehicleQueue( lf16MelbeckIndex, "lf16MelbeckIndex", "18-44-10" );
         }
      } );
      JLabel label_19 = new JLabel( "18-44-10" );
      label_19.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf16MelbeckQueuePanel.add( label_19 );
      lf16MelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( lf16MelbeckQueuePanel, "hidemode 3" );

//      lf8MelbeckQueuePanel = new JPanel();
//      lf8MelbeckQueuePanel.addMouseListener( mouseListener( lf8MelbeckQueuePanel ) );
//      lf8MelbeckQueuePanel.addMouseListener( new MouseAdapter() {
//         @Override
//         public void mouseClicked( MouseEvent arg0 ) {
//            handleSortedVehicleQueue( lf8MelbeckIndex, "lf8MelbeckIndex", "18-43-10" );
//         }
//      } );
//      JLabel label_20 = new JLabel( "18-43-10" );
//      label_20.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
//      lf8MelbeckQueuePanel.add( label_20 );
//      lf8MelbeckQueuePanel.setVisible( false );
//      vehiclePanel.add( lf8MelbeckQueuePanel, "hidemode 3" );

      mtwMelbeckQueuePanel = new JPanel();
      mtwMelbeckQueuePanel.addMouseListener( mouseListener( mtwMelbeckQueuePanel ) );
      mtwMelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwMelbeckIndex, "mtwMelbeckIndex", "18-17-10" );
         }
      } );
      JLabel label_30 = new JLabel( "18-17-10" );
      label_30.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwMelbeckQueuePanel.add( label_30 );
      mtwMelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( mtwMelbeckQueuePanel, "hidemode 3" );

      lf8DeutschEvernQueuePanel = new JPanel();
      lf8DeutschEvernQueuePanel.addMouseListener( mouseListener( lf8DeutschEvernQueuePanel ) );
      lf8DeutschEvernQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lf8DeutschEvernIndex, "lf8DeutschEvernIndex", "18-45-20" );
         }
      } );
      JLabel label_31 = new JLabel( "18-45-20" );
      label_31.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf8DeutschEvernQueuePanel.add( label_31 );
      lf8DeutschEvernQueuePanel.setVisible( false );
      vehiclePanel.add( lf8DeutschEvernQueuePanel, "hidemode 3" );

      tlfDeutschEvernQueuePanel = new JPanel();
      tlfDeutschEvernQueuePanel.addMouseListener( mouseListener( tlfDeutschEvernQueuePanel ) );
      tlfDeutschEvernQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfDeutschEvernIndex, "tlfDeutschEvernIndex", "18-24-20" );
         }
      } );
      tlfDeutschEvernQueuePanel.setMinimumSize( new Dimension( 40, 10 ) );
      JLabel label_21 = new JLabel( "18-24-20" );
      label_21.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfDeutschEvernQueuePanel.add( label_21 );
      tlfDeutschEvernQueuePanel.setVisible( false );

      vehiclePanel.add( tlfDeutschEvernQueuePanel, "hidemode 3" );

      mtwDeutschEvernQueuePanel = new JPanel();
      mtwDeutschEvernQueuePanel.addMouseListener( mouseListener( mtwDeutschEvernQueuePanel ) );
      mtwDeutschEvernQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwDeutschEvernIndex, "mtwDeutschEvernIndex", "18-17-20" );
         }
      } );
      JLabel label_22 = new JLabel( "18-17-20" );
      label_22.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwDeutschEvernQueuePanel.add( label_22 );
      mtwDeutschEvernQueuePanel.setVisible( false );
      vehiclePanel.add( mtwDeutschEvernQueuePanel, "hidemode 3" );

      rw1EmbsenQueuePanel = new JPanel();
      rw1EmbsenQueuePanel.addMouseListener( mouseListener( rw1EmbsenQueuePanel ) );
      rw1EmbsenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( rwEmbsenIndex, "rwEmbsenIndex", "18-51-30" );
         }
      } );
      JLabel label_23 = new JLabel( "18-51-30" );
      label_23.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      rw1EmbsenQueuePanel.add( label_23 );
      rw1EmbsenQueuePanel.setVisible( false );
      vehiclePanel.add( rw1EmbsenQueuePanel, "hidemode 3" );

      lfEmbsenQueuePanel = new JPanel();
      lfEmbsenQueuePanel.addMouseListener( mouseListener( lfEmbsenQueuePanel ) );
      lfEmbsenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lfEmbsenIndex, "lfEmbsenIndex", "18-47-30" );
         }
      } );
      JLabel label_24 = new JLabel( "18-47-30" );
      label_24.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lfEmbsenQueuePanel.add( label_24 );
      lfEmbsenQueuePanel.setVisible( false );
      vehiclePanel.add( lfEmbsenQueuePanel, "hidemode 3" );

      mtwEmbsenQueuePanel = new JPanel();
      mtwEmbsenQueuePanel.addMouseListener( mouseListener( mtwEmbsenQueuePanel ) );
      mtwEmbsenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwEmbsenIndex, "mtwEmbsenIndex", "18-17-30" );
         }
      } );
      JLabel label_32 = new JLabel( "18-17-30" );
      label_32.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwEmbsenQueuePanel.add( label_32 );
      mtwEmbsenQueuePanel.setVisible( false );
      vehiclePanel.add( mtwEmbsenQueuePanel, "hidemode 3" );

      tlfOerzenQueuePanel = new JPanel();
      tlfOerzenQueuePanel.addMouseListener( mouseListener( tlfOerzenQueuePanel ) );
      tlfOerzenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfOerzenIndex, "tlfOerzenIndex", "18-23-32" );
         }
      } );
      JLabel label_33 = new JLabel( "18-23-32" );
      label_33.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfOerzenQueuePanel.add( label_33 );
      tlfOerzenQueuePanel.setVisible( false );
      vehiclePanel.add( tlfOerzenQueuePanel, "hidemode 3" );

//      tsfOerzenQueuePanel = new JPanel();
//      tsfOerzenQueuePanel.addMouseListener( mouseListener( tsfOerzenQueuePanel ) );
//      tsfOerzenQueuePanel.addMouseListener( new MouseAdapter() {
//         @Override
//         public void mouseClicked( MouseEvent arg0 ) {
//            handleSortedVehicleQueue( tsfOerzenIndex, "tsfOerzenIndex", "18-40-32" );
//         }
//      } );
//      JLabel label_25 = new JLabel( "18-40-32" );
//      label_25.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
//      tsfOerzenQueuePanel.add( label_25 );
//      tsfOerzenQueuePanel.setVisible( false );
//      vehiclePanel.add( tsfOerzenQueuePanel, "hidemode 3" );

      mtwOerzenQueuePanel = new JPanel();
      mtwOerzenQueuePanel.addMouseListener( mouseListener( mtwOerzenQueuePanel ) );
      mtwOerzenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwOerzenIndex, "mtwOerzenIndex", "18-17-32" );
         }
      } );
      JLabel label_26 = new JLabel( "18-17-32" );
      label_26.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwOerzenQueuePanel.add( label_26 );
      mtwOerzenQueuePanel.setVisible( false );
      vehiclePanel.add( mtwOerzenQueuePanel, "hidemode 3" );

      tlfBarnstedtQueuePanel = new JPanel();
      tlfBarnstedtQueuePanel.addMouseListener( mouseListener( tlfBarnstedtQueuePanel ) );
      tlfBarnstedtQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfBarnstedtIndex, "tlfBarnstedtIndex", "18-23-40" );
         }
      } );
      JLabel label_27 = new JLabel( "18-23-40" );
      label_27.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfBarnstedtQueuePanel.add( label_27 );
//      tlfBarnstedtQueuePanel.setVisible( false );
      vehiclePanel.add( tlfBarnstedtQueuePanel, "hidemode 3" );

//      tsfBarnstedtQueuePanel = new JPanel();
//      tsfBarnstedtQueuePanel.addMouseListener( mouseListener( tsfBarnstedtQueuePanel ) );
//      tsfBarnstedtQueuePanel.addMouseListener( new MouseAdapter() {
//         @Override
//         public void mouseClicked( MouseEvent arg0 ) {
//            handleSortedVehicleQueue( tsfBarnstedtIndex, "tsfBarnstedtIndex", "18-40-40" );
//         }
//      } );
//      JLabel label_28 = new JLabel( "18-40-40" );
//      label_28.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
//      tsfBarnstedtQueuePanel.add( label_28 );
//      tsfBarnstedtQueuePanel.setVisible( false );
//      vehiclePanel.add( tsfBarnstedtQueuePanel, "hidemode 3" );

      mtwBarnstedtQueuePanel = new JPanel();
      mtwBarnstedtQueuePanel.addMouseListener( mouseListener( mtwBarnstedtQueuePanel ) );
      mtwBarnstedtQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwBarnstedtIndex, "mtwBarnstedtIndex", "18-17-40" );
         }
      } );
      JLabel label_29 = new JLabel( "18-17-40" );
      label_29.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwBarnstedtQueuePanel.add( label_29 );
      mtwBarnstedtQueuePanel.setVisible( false );
      vehiclePanel.add( mtwBarnstedtQueuePanel, "hidemode 3" );

      lf8KolkhagenQueuePanel = new JPanel();
      JLabel label_34 = new JLabel( "18-43-42" );
      label_34.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf8KolkhagenQueuePanel.add( label_34 );
      lf8KolkhagenQueuePanel.addMouseListener( mouseListener( lf8KolkhagenQueuePanel ) );
      lf8KolkhagenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lf8KolkhagenIndex, "lf8KolkhagenIndex", "18-43-42" );
         }
      } );
      lf8KolkhagenQueuePanel.setVisible( false );
      vehiclePanel.add( lf8KolkhagenQueuePanel, "hidemode 3" );


      // ############################################################
      gast1QueuePanel = new JPanel();
      JLabel label_98 = new JLabel( "  Gast1  " );
      label_98.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      gast1QueuePanel.add( label_98 );
      gast1QueuePanel.addMouseListener( mouseListener( gast1QueuePanel ) );
      gast1QueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( gast1Index, "gast1Index", "Gast1" );
         }
      } );
      gast1QueuePanel.setVisible( false );
      vehiclePanel.add( gast1QueuePanel, "hidemode 3" );

      gast2QueuePanel = new JPanel();
      JLabel label_99 = new JLabel( "  Gast2  " );
      label_99.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      gast2QueuePanel.add( label_99 );
      gast2QueuePanel.addMouseListener( mouseListener( gast2QueuePanel ) );
      gast2QueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( gast2Index, "gast2Index", "Gast2" );
         }
      } );
      gast2QueuePanel.setVisible( false );
      vehiclePanel.add( gast2QueuePanel, "hidemode 3" );
// ##############################################################################################
      gast3QueuePanel = new JPanel();
      JLabel label_gast3 = new JLabel( "  Gast3  " );
      label_gast3.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      gast3QueuePanel.add( label_gast3 );
      gast3QueuePanel.addMouseListener( mouseListener( gast3QueuePanel ) );
      gast3QueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( gast3Index, "gast3Index", "Gast3" );
         }
      } );
      gast3QueuePanel.setVisible( false );
      vehiclePanel.add( gast3QueuePanel, "hidemode 3" );

      gast4QueuePanel = new JPanel();
      JLabel label_gast4 = new JLabel( "  Gast4  " );
      label_gast4.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      gast4QueuePanel.add( label_gast4 );
      gast4QueuePanel.addMouseListener( mouseListener( gast4QueuePanel ) );
      gast4QueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( gast4Index, "gast4Index", "Gast4" );
         }
      } );
      gast4QueuePanel.setVisible( false );
      vehiclePanel.add( gast4QueuePanel, "hidemode 3" );

      gast5QueuePanel = new JPanel();
      JLabel label_gast5 = new JLabel( "  Gast5  " );
      label_gast5.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      gast5QueuePanel.add( label_gast5 );
      gast5QueuePanel.addMouseListener( mouseListener( gast5QueuePanel ) );
      gast5QueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( gast5Index, "gast5Index", "Gast5" );
         }
      } );
      gast5QueuePanel.setVisible( false );
      vehiclePanel.add( gast5QueuePanel, "hidemode 3" );
      // ############################################################

      final JFrame frame = new JFrame( "Funkfahrübung by ©S.Kunz" );
      frame.setResizable( false );

      JLabel colorImage = new JLabel( new ImageIcon( "res/gradient.png" ) );
      vehiclePanel.add( colorImage, "hidemode 3,cell 0 5 18 1" );

      JPanel carPanel = new JPanel();
      JPanel auto = new JPanel();
      auto.add( new JLabel( "Auto: " ) );
      carPanel.add( auto );
      vehiclePanel.add( carPanel, "cell 0 6" );

      lblAuto = new JLabel();
      lblAuto.setFont( new Font( "Tahoma", Font.PLAIN, 16 ) );
      vehiclePanel.add( lblAuto, "cell 1 6 5 1" );

      JPanel taskPanel = new JPanel();
      JPanel koordinate = new JPanel();
      koordinate.add( new JLabel( "Koordinate: " ) );
      taskPanel.add( koordinate );
      vehiclePanel.add( taskPanel, "cell 0 7" );

      lblKoordinaten = new JLabel();
      lblKoordinaten.setFont( new Font( "Tahoma", Font.BOLD, 22 ) );

      if( lblKoordinaten.getText().startsWith( "ND" ) ) {
         lblKoordinaten.setToolTipText( "Nordpol Dora" );
      } else if( lblKoordinaten.getText().startsWith( "PE" ) ) {
         lblKoordinaten.setToolTipText( "Paula Emil" );
      } else if( lblKoordinaten.getText().startsWith( "NE" ) ) {
         lblKoordinaten.setToolTipText( "Nordpol Emil" );
      } else if( lblKoordinaten.getText().startsWith( "PD" ) ) {
         lblKoordinaten.setToolTipText( "Paula Dora" );
      }

      vehiclePanel.add( lblKoordinaten, "cell 1 7 5 1" );

      JPanel taskPanel4 = new JPanel();
      JPanel bemerkung = new JPanel();
      bemerkung.add( new JLabel( "Bemerkung: " ) );
      taskPanel4.add( bemerkung );
      vehiclePanel.add( taskPanel4, "cell 0 8" );
      lblBemerkung = new JTextArea();
      lblBemerkung.setLineWrap( true );
      lblBemerkung.setBackground( new Color( 240, 240, 240 ) );
      lblBemerkung.setOpaque( true );
      lblBemerkung.setSize( 500, 18 );
      lblBemerkung.setWrapStyleWord( true );
      lblBemerkung.setEditable( false );
      lblBemerkung.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      vehiclePanel.add( lblBemerkung, "cell 1 8 5 1" );

      JPanel taskPanel2 = new JPanel();
      JPanel frage = new JPanel();
      frage.add( new JLabel( "Frage: " ) );
      taskPanel2.add( frage );
      vehiclePanel.add( taskPanel2, "cell 0 9" );
      lblFrage = new JTextArea();
      lblFrage.setLineWrap( true );
      lblFrage.setBackground( new Color( 240, 240, 240 ) );
      lblFrage.setOpaque( true );
      lblFrage.setSize( 500, 18 );
      lblFrage.setWrapStyleWord( true );
      lblFrage.setEditable( false );
      lblFrage.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      vehiclePanel.add( lblFrage, "cell 1 9 5 1" );

      JPanel taskPanel3 = new JPanel();
      JPanel antwort = new JPanel();
      antwort.add( new JLabel( "Antwort: " ) );
      taskPanel3.add( antwort );
      vehiclePanel.add( taskPanel3, "cell 0 10" );
      lblAntwort = new JTextArea();
      lblAntwort.setLineWrap( true );
      lblAntwort.setBackground( new Color( 240, 240, 240 ) );
      lblAntwort.setOpaque( true );
      lblAntwort.setSize( 500, 18 );
      lblAntwort.setWrapStyleWord( true );
      lblAntwort.setEditable( false );
      lblAntwort.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      vehiclePanel.add( lblAntwort, "cell 1 10 5 1" );

      btnRichtig = new JButton( "Anmelden" );
      btnRichtig.addActionListener( btnRichtigActionListener() );

      buttonBack = new JButton( "<<" );
      buttonBack.addActionListener( btnBackActionListener() );

      buttonBack.setHorizontalTextPosition( SwingConstants.RIGHT );
      buttonBack.setHorizontalAlignment( SwingConstants.RIGHT );
      JPanel buttonPanel = new JPanel();
      buttonPanel.add( buttonBack );
      buttonPanel.add( btnRichtig );
      vehiclePanel.add( buttonPanel, "cell 5 11" );

      JMenuBar menuBar = new JMenuBar();

      JMenu mainMenu = new JMenu( "Menü" );
      menuBar.add( mainMenu );

      JMenuItem editVehiclesMenuItem = new JMenuItem( "Fahrzeuge bearbeiten" );
      editVehiclesMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            // TODO
         }
      } );
      editVehiclesMenuItem.setEnabled( false );
      // mainMenu.add(editVehiclesMenuItem);

      JMenuItem editQuestionsMenuItem = new JMenuItem( "Fragen bearbeiten" );
      editQuestionsMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            // TODO
         }
      } );
      editQuestionsMenuItem.setEnabled( false );
      // mainMenu.add(editQuestionsMenuItem);

      JMenuItem editFFU = new JMenuItem( "Übung planen" );
      editFFU.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            // TODO
         }
      } );
      editFFU.setEnabled( false );
      // mainMenu.add(editFFU);

      JMenuItem editRufname = new JMenuItem( "Rufnamen editieren" );
      editRufname.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            String str = JOptionPane.showInputDialog( frame, "Geben Sie den Rufnamen an: ", "Funkfahrübung by ©S.Kunz\n", 1 );
            rufname_lbl.setText( str );
         }
      } );
      mainMenu.add( editRufname );

      JMenuItem connectToServer = new JMenuItem( "Server verbinden" );
      connectToServer.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            String str = JOptionPane.showInputDialog( null, "Geben Sie die Ip-Adresse des Servers ein: ", "Funkfahrübung by ©S.Kunz\n", 1 );
            if( str != null ) {
               setIPAdresse( str );
            }
         }
      } );
      mainMenu.add( connectToServer );

      JMenuItem exitMenuItem = new JMenuItem( "Beenden" );
      exitMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            if( !offlineArbeiten ) {
               try {
                  socketServer.close();
               } catch( IOException e ) {
                  e.printStackTrace();
               }
            }
            System.exit( 1 );
         }
      } );
      mainMenu.add( exitMenuItem );

      // a submenu
      JMenu submenu = new JMenu( "Ansicht" );

      ButtonGroup group = new ButtonGroup();
      JRadioButtonMenuItem alphaMenuItem = new JRadioButtonMenuItem( "Alphabetisch" );
      alphaMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent e ) {
            clearVehiclePanel();
            vehiclePanel.add( barnstedtPanel.getBarnstedtPanel(), "hidemode 3, cell 0 0" );
            vehiclePanel.add( deutschEvernPanel.getDeutschEvernPanel(), "hidemode 3, cell 1 0" );
            vehiclePanel.add( embsenPanel.getEmbsenPanel(), "hidemode 3, cell 2 0" );
            vehiclePanel.add( kolkhagenPanel.getKolkhagenPanel(), "hidemode 3, cell 3 0" );
            vehiclePanel.add( melbeckPanel.getMelbeckPanel(), "hidemode 3, cell 4 0" );
            vehiclePanel.add( oerzenPanel.getOerzenPanel(), "hidemode 3, cell 5 0" );
         }
      } );
      group.add( alphaMenuItem );
      submenu.add( alphaMenuItem );

      JRadioButtonMenuItem chronMenuItem = new JRadioButtonMenuItem( "Numerisch" );
      chronMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            clearVehiclePanel();
            vehiclePanel.add( melbeckPanel.getMelbeckPanel(), "hidemode 3, cell 0 0" );
            vehiclePanel.add( deutschEvernPanel.getDeutschEvernPanel(), "hidemode 3, cell 1 0" );
            vehiclePanel.add( embsenPanel.getEmbsenPanel(), "hidemode 3, cell 2 0" );
            vehiclePanel.add( oerzenPanel.getOerzenPanel(), "hidemode 3, cell 3 0" );
            vehiclePanel.add( barnstedtPanel.getBarnstedtPanel(), "hidemode 3, cell 4 0" );
            vehiclePanel.add( kolkhagenPanel.getKolkhagenPanel(), "hidemode 3, cell 5 0" );
         }
      } );
      chronMenuItem.setSelected( true );
      group.add( chronMenuItem );
      submenu.add( chronMenuItem );

      final JCheckBoxMenuItem chckbxmntmMtwZugriff = new JCheckBoxMenuItem( "MTW erlauben" );
      chckbxmntmMtwZugriff.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent e ) {
            if( chckbxmntmMtwZugriff.isSelected() ) {
               melbeckPanel.enableCheckBoxMTWMelbeck( true );
               deutschEvernPanel.enableCheckBoxMTWDeutschEvern( true );
               embsenPanel.enableCheckBoxMTWEmbsen( true );
               oerzenPanel.enableCheckBoxMTWOerzen( true );
               barnstedtPanel.enableCheckBoxMTWBarnstedt( true );
               chckbxmntmMtwZugriff.setText( "MTW verbieten" );
            } else {
               melbeckPanel.enableCheckBoxMTWMelbeck( false );
               deutschEvernPanel.enableCheckBoxMTWDeutschEvern( false );
               embsenPanel.enableCheckBoxMTWEmbsen( false );
               oerzenPanel.enableCheckBoxMTWOerzen( false );
               barnstedtPanel.enableCheckBoxMTWBarnstedt( false );
               chckbxmntmMtwZugriff.setText( "MTW erlauben" );
            }
         }
      } );
      chckbxmntmMtwZugriff.setState( true );
      submenu.add( chckbxmntmMtwZugriff );
      menuBar.add( submenu );

      JMenu helpMenu = new JMenu( "Hilfe" );

      JMenuItem aboutMenuItem = new JMenuItem( "Über" );
      aboutMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            JOptionPane.showMessageDialog( frame,
                  "Funkfahrübung by ©S.Kunz\n" + "Developer: Steffen Kunz\n" + "Contributor: Yannik Lüdemann\n" + "Version: 1.1 \n" + "\n©S.Kunz. All rights reserved",
                  "Über Funkfahrübung by ©S.Kunz", JOptionPane.INFORMATION_MESSAGE );
         }
      } );
      helpMenu.add( aboutMenuItem );

      JMenu toolsmenu = new JMenu( "Tools" );
      menuBar.add( toolsmenu );

      JMenuItem mntmToolsMenuItem = new JMenuItem( "Kartenexport" );
      mntmToolsMenuItem.setEnabled( false );
      mntmToolsMenuItem.addActionListener( new ActionListener() {
         public void actionPerformed( ActionEvent e ) {
            ExportMap exporter = new ExportMap();
            try {
               exporter.export();
            } catch( IOException e1 ) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      } );
      toolsmenu.add( mntmToolsMenuItem );

      menuBar.add( helpMenu );
      frame.setJMenuBar( menuBar );

      JPanel helpPanel = new JPanel();
      helpPanel.setLayout( new MigLayout( "wrap 1", "[grow]", "[][][][]" ) );

      JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, vehiclePanel, helpPanel );

      JLabel label_35 = rufname_lbl;
      label_35.setFont( new Font( "Tahoma", Font.PLAIN, 28 ) );
      helpPanel.add( label_35, "cell 0 0" );

      JLabel lblBosFunkalphabet = new JLabel( "BOS Funk-Alphabet" );
      helpPanel.add( lblBosFunkalphabet, "cell 0 1" );
      final JLabel label_36 = new JLabel( "" );
      label_36.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      helpPanel.add( label_36, "cell 0 3" );

      textField = new JTextField();
      textField.addKeyListener( new KeyListener() {

         @Override
         public void keyTyped( KeyEvent e ) {
            // TODO Auto-generated method stub

         }

         @Override
         public void keyReleased( KeyEvent e ) {
            String[] split = textField.getText().split( "" );
            String stringbuilder = "<html>";
            for( int i = 0; i < split.length; i++ ) {
               switch( split[i] ) {
                  case "A":
                  case "a":
                     stringbuilder = stringbuilder + "<b>A</b>nton <br>";
                     break;
                  case "Ä":
                  case "ä":
                     stringbuilder = stringbuilder + "<b>Ä</b>rger <br>";
                     break;
                  case "B":
                  case "b":
                     stringbuilder = stringbuilder + "<b>B</b>erta <br>";
                     break;
                  case "C":
                  case "c":
                     stringbuilder = stringbuilder + "<b>C</b>äsar <br>";
                     break;
                  case "D":
                  case "d":
                     stringbuilder = stringbuilder + "<b>D</b>ora <br>";
                     break;
                  case "E":
                  case "e":
                     stringbuilder = stringbuilder + "<b>E</b>mil <br>";
                     break;
                  case "F":
                  case "f":
                     stringbuilder = stringbuilder + "<b>F</b>riedrich <br>";
                     break;
                  case "G":
                  case "g":
                     stringbuilder = stringbuilder + "<b>G</b>ustav <br>";
                     break;
                  case "H":
                  case "h":
                     if( split.length >= 3 && i >= 2 && split[i - 1].equals( "C" ) | split[i - 1].equals( "c" ) && split[i - 2].equals(
                           "S" ) | split[i - 2].equals( "s" ) ) {
                        stringbuilder = stringbuilder.replace( "<b>S</b>amuel <br><b>C</b>äsar <br>", "<b>SCH</b>ule <br>" );
                     } else if( split.length >= 2 && i >= 1 && split[i - 1].equals( "C" ) | split[i - 1].equals( "c" ) ) {
                        stringbuilder = stringbuilder.replace( "<b>C</b>äsar <br>", "<b>CH</b>arlotte <br>" );
                     } else {
                        stringbuilder = stringbuilder + "<b>H</b>einrich <br>";
                     }
                     break;
                  case "I":
                  case "i":
                     stringbuilder = stringbuilder + "<b>I</b>da <br>";
                     break;
                  case "J":
                  case "j":
                     stringbuilder = stringbuilder + "<b>J</b>ulius <br>";
                     break;
                  case "K":
                  case "k":
                     stringbuilder = stringbuilder + "<b>K</b>aufmann <br>";
                     break;
                  case "L":
                  case "l":
                     stringbuilder = stringbuilder + "<b>L</b>udwig <br>";
                     break;
                  case "M":
                  case "m":
                     stringbuilder = stringbuilder + "<b>M</b>artha <br>";
                     break;
                  case "N":
                  case "n":
                     stringbuilder = stringbuilder + "<b>N</b>ordpol <br>";
                     break;
                  case "O":
                  case "o":
                     stringbuilder = stringbuilder + "<b>O</b>tto <br>";
                     break;
                  case "Ö":
                  case "ö":
                     stringbuilder = stringbuilder + "<b>Ö</b>konom <br>";
                     break;
                  case "P":
                  case "p":
                     stringbuilder = stringbuilder + "<b>P</b>aula <br>";
                     break;
                  case "Q":
                  case "q":
                     stringbuilder = stringbuilder + "<b>Q</b>uelle <br>";
                     break;
                  case "R":
                  case "r":
                     stringbuilder = stringbuilder + "<b>R</b>ichard <br>";
                     break;
                  case "S":
                  case "s":
                     stringbuilder = stringbuilder + "<b>S</b>amuel <br>";
                     break;
                  case "ß":
                     stringbuilder = stringbuilder + "<b>E</b>szett <br>";
                     break;
                  case "T":
                  case "t":
                     stringbuilder = stringbuilder + "<b>T</b>heodor <br>";
                     break;
                  case "U":
                  case "u":
                     stringbuilder = stringbuilder + "<b>U</b>lrich <br>";
                     break;
                  case "Ü":
                  case "ü":
                     stringbuilder = stringbuilder + "<b>Ü</b>bermut <br>";
                     break;
                  case "V":
                  case "v":
                     stringbuilder = stringbuilder + "<b>V</b>iktor <br>";
                     break;
                  case "W":
                  case "w":
                     stringbuilder = stringbuilder + "<b>W</b>ilhelm <br>";
                     break;
                  case "X":
                  case "x":
                     stringbuilder = stringbuilder + "<b>X</b>anthippe <br>";
                     break;
                  case "Y":
                  case "y":
                     stringbuilder = stringbuilder + "<b>Y</b>psilon <br>";
                     break;
                  case "Z":
                  case "z":
                     stringbuilder = stringbuilder + "<b>Z</b>acharias <br>";
                     break;
                  default:
                     stringbuilder = stringbuilder + "<br>";
                     break;
               }
            }
            stringbuilder = stringbuilder + "</html>";
            label_36.setText( stringbuilder );
         }

         @Override
         public void keyPressed( KeyEvent e ) {
         }
      } );

      helpPanel.add( textField, "cell 0 2,growx" );

      frame.getContentPane().add( splitPane );
      splitPane.setContinuousLayout( true );

      frame.setSize( 1100, 800 );
      frame.setVisible( true );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }

   private MouseAdapter mouseListener( final JPanel panel ) {
      return new MouseAdapter() {
         @Override
         public void mouseEntered( MouseEvent e ) {
            panel.setBorder( BorderFactory.createLineBorder( Color.black ) );
         }

         @Override
         public void mouseExited( MouseEvent e ) {
            panel.setBorder( BorderFactory.createEmptyBorder() );
         }
      };
   }

   private ActionListener btnBackActionListener( ) {
      return new ActionListener() {
         public void actionPerformed( ActionEvent e ) {
            if( button != null ) {
               switch( button ) {
                  case "tlfMelbeckIndex":
                     tlfMelbeckIndex = btnBackBehavior( tlfMelbeckIndex, tlfMelbeckQueuePanel, "18-24-10", "tlfMelbeckIndex" );
                     break;
                  case "lf16MelbeckIndex":
                     lf16MelbeckIndex = btnBackBehavior( lf16MelbeckIndex, lf16MelbeckQueuePanel, "18-44-10", "lf16MelbeckIndex" );
                     break;
                  case "mtwMelbeckIndex":
                     mtwMelbeckIndex = btnBackBehavior( mtwMelbeckIndex, mtwMelbeckQueuePanel, "18-17-10", "mtwMelbeckIndex" );
                     break;
                  case "lf8DeutschEvernIndex":
                     lf8DeutschEvernIndex = btnBackBehavior( lf8DeutschEvernIndex, lf8DeutschEvernQueuePanel, "18-45-20", "lf8DeutschEvernIndex" );
                     break;
                  case "tlfDeutschEvernIndex":
                     tlfDeutschEvernIndex = btnBackBehavior( tlfDeutschEvernIndex, tlfDeutschEvernQueuePanel, "18-24-20", "tlfDeutschEvernIndex" );
                     break;
                  case "mtwDeutschEvernIndex":
                     mtwDeutschEvernIndex = btnBackBehavior( mtwDeutschEvernIndex, mtwDeutschEvernQueuePanel, "18-17-20", "mtwDeutschEvernIndex" );
                     break;
                  case "rwEmbsenIndex":
                     rwEmbsenIndex = btnBackBehavior( rwEmbsenIndex, rw1EmbsenQueuePanel, "18-51-30", "rwEmbsenIndex" );
                     break;
                  case "lfEmbsenIndex":
                     lfEmbsenIndex = btnBackBehavior( lfEmbsenIndex, lfEmbsenQueuePanel, "18-47-30", "lfEmbsenIndex" );
                     break;
                  case "mtwEmbsenIndex":
                     mtwEmbsenIndex = btnBackBehavior( mtwEmbsenIndex, mtwEmbsenQueuePanel, "18-17-30", "mtwEmbsenIndex" );
                     break;
                  case "tlfOerzenIndex":
                     tlfOerzenIndex = btnBackBehavior( tlfOerzenIndex, tlfOerzenQueuePanel, "18-23-32", "tlfOerzenIndex" );
                     break;
                  case "tsfOerzenIndex":
                     tsfOerzenIndex = btnBackBehavior( tsfOerzenIndex, tsfOerzenQueuePanel, "18-40-32", "tsfOerzenIndex" );
                     break;
                  case "mtwOerzenIndex":
                     mtwOerzenIndex = btnBackBehavior( mtwOerzenIndex, mtwOerzenQueuePanel, "18-17-32", "mtwOerzenIndex" );
                     break;
                  case "tlfBarnstedtIndex":
                     tlfBarnstedtIndex = btnBackBehavior( tlfBarnstedtIndex, tlfBarnstedtQueuePanel, "18-23-40", "tlfBarnstedtIndex" );
                     break;
                  case "tsfBarnstedtIndex":
                     tsfBarnstedtIndex = btnBackBehavior( tsfBarnstedtIndex, tsfBarnstedtQueuePanel, "18-40-40", "tsfBarnstedtIndex" );
                     break;
                  case "mtwBarnstedtIndex":
                     mtwBarnstedtIndex = btnBackBehavior( mtwBarnstedtIndex, mtwBarnstedtQueuePanel, "18-17-40", "mtwBarnstedtIndex" );
                     break;
                  case "lf8KolkhagenIndex":
                     lf8KolkhagenIndex = btnBackBehavior( lf8KolkhagenIndex, lf8KolkhagenQueuePanel, "18-43-42", "lf8KolkhagenIndex" );
                     break;

                  case "gast1Index":
                     gast1Index = btnBackBehavior( gast1Index, gast1QueuePanel, "Gast1", "gast1Index" );
                     break;
                  case "gast2Index":
                     gast2Index = btnBackBehavior( gast2Index, gast2QueuePanel, "Gast2", "gast2Index" );
                     break;

                  case "gast3Index":
                     gast3Index = btnBackBehavior( gast3Index, gast3QueuePanel, "Gast3", "gast3Index" );
                     break;
                  case "gast4Index":
                     gast4Index = btnBackBehavior( gast4Index, gast4QueuePanel, "Gast4", "gast4Index" );
                     break;
                  case "gast5Index":
                     gast5Index = btnBackBehavior( gast5Index, gast5QueuePanel, "Gast5", "gast5Index" );
                     break;

                  default:
                     System.out.println( "<< Unknown Button: " + button );
                     break;
               }
            }
         }

         private int btnBackBehavior( int vehicleIndex, JPanel panel, String rufname, String auto ) {
            vehicleIndex--;
            if( vehicleIndex <= 0 ) {
               btnRichtig.setText( "Anmelden" );
               buttonBack.setText( "<<" );
               vehicleIndex = 0;
               setColor( panel, getBackground() );
               setLabel( "", "", "Wollen Sie Florian Lüneburg " + rufname + " zur Funkfahrübung anmelden?", "", auto, rufname, vehicleIndex );
               return vehicleIndex;
            } else if( vehicleIndex > 0 && vehicleIndex <= 6 ) {
               if( vehicleIndex == 1 ) {
                  buttonBack.setText( "Abmelden" );
                  setColor( panel, Color.RED );
               } else if( vehicleIndex == 2 ) {
                  setColor( panel, Color.YELLOW );
               } else if( vehicleIndex == 3 ) {
                  setColor( panel, Color.GREEN );
               } else if( vehicleIndex == 4 ) {
                  setColor( panel, Color.CYAN );
               } else if( vehicleIndex == 5 ) {
                  setColor( panel, Color.ORANGE );
               } else if( vehicleIndex == 6 ) {
                  btnRichtig.setText( "Richtig" );
                  setColor( panel, Color.MAGENTA );
               }
               setLabel( reader.getKoordinaten( rufname )[vehicleIndex], reader.getBemerkung( rufname )[vehicleIndex],
                     reader.getFragen( rufname )[vehicleIndex], reader.getAntworten( rufname )[vehicleIndex], auto, rufname, vehicleIndex );
               return vehicleIndex;
            } else {
               btnRichtig.setText( "Abmelden" );
               setLabel( reader.getSammelplatzKoordinaten()[1], "", reader.getSammelplatz()[1], "", auto, rufname, vehicleIndex );
               setColor( panel, Color.LIGHT_GRAY );
               return vehicleIndex;
            }
         }

         private void setColor( JPanel panel, Color color ) {
            panel.setBackground( color );
         }
      };
   }

   private ActionListener btnRichtigActionListener( ) {
      return new ActionListener() {
         public void actionPerformed( ActionEvent e ) {
            if( button != null ) {
               switch( button ) {
                  case "tlfMelbeckIndex":
                     tlfMelbeckIndex = btnCorrectBehavior( tlfMelbeckIndex, tlfMelbeckQueuePanel, "18-24-10", "tlfMelbeckIndex" );
                     break;
                  case "lf16MelbeckIndex":
                     lf16MelbeckIndex = btnCorrectBehavior( lf16MelbeckIndex, lf16MelbeckQueuePanel, "18-44-10", "lf16MelbeckIndex" );
                     break;
                  case "mtwMelbeckIndex":
                     mtwMelbeckIndex = btnCorrectBehavior( mtwMelbeckIndex, mtwMelbeckQueuePanel, "18-17-10", "mtwMelbeckIndex" );
                     break;
                  case "lf8DeutschEvernIndex":
                     lf8DeutschEvernIndex = btnCorrectBehavior( lf8DeutschEvernIndex, lf8DeutschEvernQueuePanel, "18-45-20", "lf8DeutschEvernIndex" );
                     break;
                  case "tlfDeutschEvernIndex":
                     tlfDeutschEvernIndex = btnCorrectBehavior( tlfDeutschEvernIndex, tlfDeutschEvernQueuePanel, "18-24-20", "tlfDeutschEvernIndex" );
                     break;
                  case "mtwDeutschEvernIndex":
                     mtwDeutschEvernIndex = btnCorrectBehavior( mtwDeutschEvernIndex, mtwDeutschEvernQueuePanel, "18-17-20", "mtwDeutschEvernIndex" );
                     break;
                  case "rwEmbsenIndex":
                     rwEmbsenIndex = btnCorrectBehavior( rwEmbsenIndex, rw1EmbsenQueuePanel, "18-51-30", "rwEmbsenIndex" );
                     break;
                  case "lfEmbsenIndex":
                     lfEmbsenIndex = btnCorrectBehavior( lfEmbsenIndex, lfEmbsenQueuePanel, "18-47-30", "lfEmbsenIndex" );
                     break;
                  case "mtwEmbsenIndex":
                     mtwEmbsenIndex = btnCorrectBehavior( mtwEmbsenIndex, mtwEmbsenQueuePanel, "18-17-30", "mtwEmbsenIndex" );
                     break;
                  case "tlfOerzenIndex":
                     tlfOerzenIndex = btnCorrectBehavior( tlfOerzenIndex, tlfOerzenQueuePanel, "18-23-32", "tlfOerzenIndex" );
                     break;
                  case "tsfOerzenIndex":
                     tsfOerzenIndex = btnCorrectBehavior( tsfOerzenIndex, tsfOerzenQueuePanel, "18-40-32", "tsfOerzenIndex" );
                     break;
                  case "mtwOerzenIndex":
                     mtwOerzenIndex = btnCorrectBehavior( mtwOerzenIndex, mtwOerzenQueuePanel, "18-17-32", "mtwOerzenIndex" );
                     break;
                  case "tlfBarnstedtIndex":
                     tlfBarnstedtIndex = btnCorrectBehavior( tlfBarnstedtIndex, tlfBarnstedtQueuePanel, "18-23-40", "tlfBarnstedtIndex" );
                     break;
                  case "tsfBarnstedtIndex":
                     tsfBarnstedtIndex = btnCorrectBehavior( tsfBarnstedtIndex, tsfBarnstedtQueuePanel, "18-40-40", "tsfBarnstedtIndex" );
                     break;
                  case "mtwBarnstedtIndex":
                     mtwBarnstedtIndex = btnCorrectBehavior( mtwBarnstedtIndex, mtwBarnstedtQueuePanel, "18-17-40", "mtwBarnstedtIndex" );
                     break;
                  case "lf8KolkhagenIndex":
                     lf8KolkhagenIndex = btnCorrectBehavior( lf8KolkhagenIndex, lf8KolkhagenQueuePanel, "18-43-42", "lf8KolkhagenIndex" );
                     break;
                  case "gast1Index":
                     gast1Index = btnCorrectBehavior( gast1Index, gast1QueuePanel, "Gast1", "gast1Index" );
                     break;
                  case "gast2Index":
                     gast2Index = btnCorrectBehavior( gast2Index, gast2QueuePanel, "Gast2", "gast2Index" );
                     break;
                  case "gast3Index":
                     gast3Index = btnCorrectBehavior( gast3Index, gast3QueuePanel, "Gast3", "gast3Index" );
                     break;
                  case "gast4Index":
                     gast4Index = btnCorrectBehavior( gast4Index, gast4QueuePanel, "Gast4", "gast4Index" );
                     break;
                  case "gast5Index":
                     gast5Index = btnCorrectBehavior( gast5Index, gast5QueuePanel, "Gast5", "gast5Index" );
                     break;

                  default:
                     System.out.println( "Unknown Button: " + button );
                     break;
               }
            }
         }

         private int btnCorrectBehavior( int vehicleIndex, JPanel panel, String rufname, String auto ) {
            if( vehicleIndex < 6 ) {
               vehicleIndex++;
               if( vehicleIndex == 1 ) {
                  btnRichtig.setText( "Richtig" );
                  buttonBack.setText( "Abmelden" );
                  setColor( panel, Color.RED );
               } else if( vehicleIndex == 2 ) {
                  buttonBack.setText( "<<" );
                  setColor( panel, Color.YELLOW );
               } else if( vehicleIndex == 3 ) {
                  setColor( panel, Color.GREEN );
               } else if( vehicleIndex == 4 ) {
                  setColor( panel, Color.CYAN );
               } else if( vehicleIndex == 5 ) {
                  setColor( panel, Color.ORANGE );
               } else if( vehicleIndex == 6 ) {
                  setColor( panel, Color.MAGENTA );
               }
               setLabel( reader.getKoordinaten( rufname )[vehicleIndex], reader.getBemerkung( rufname )[vehicleIndex],
                     reader.getFragen( rufname )[vehicleIndex], reader.getAntworten( rufname )[vehicleIndex], auto, rufname, vehicleIndex );
               return vehicleIndex;
            } else {
               vehicleIndex++;
               if( vehicleIndex == 7 ) {
                  btnRichtig.setText( "Abmelden" );
                  setLabel( reader.getSammelplatzKoordinaten()[1], "", reader.getSammelplatz()[1], "", auto, rufname, vehicleIndex );
                  setColor( panel, Color.LIGHT_GRAY );
               } else {
                  vehicleIndex = 8;
                  setLabel( "", "Am Sammelplatz eingetroffen!", "", "", auto, rufname, vehicleIndex );
                  setColor( panel, Color.BLUE );
               }
               return vehicleIndex;
            }
         }

         private void setColor( JPanel panel, Color farbe ) {
            panel.setBackground( farbe );
         }
      };
   }

   private void setupPanel( ) {
      melbeckPanel = new MelbeckPanel( this );
      deutschEvernPanel = new DeutschEvernPanel( this );
      embsenPanel = new EmbsenPanel( this );
      oerzenPanel = new OerzenPanel( this );
      barnstedtPanel = new BarnstedtPanel( this );
      kolkhagenPanel = new KolkhagenPanel( this );

      vehiclePanel.add( melbeckPanel.getMelbeckPanel(), "hidemode 3, cell 0 0" );
      vehiclePanel.add( deutschEvernPanel.getDeutschEvernPanel(), "hidemode 3, cell 1 0" );
      vehiclePanel.add( embsenPanel.getEmbsenPanel(), "hidemode 3, cell 2 0" );
      vehiclePanel.add( oerzenPanel.getOerzenPanel(), "hidemode 3, cell 3 0" );
      vehiclePanel.add( barnstedtPanel.getBarnstedtPanel(), "hidemode 3, cell 4 0" );
      vehiclePanel.add( kolkhagenPanel.getKolkhagenPanel(), "hidemode 3, cell 5 0" );
   }

   private void clearVehiclePanel( ) {
      vehiclePanel.remove( barnstedtPanel.getBarnstedtPanel() );
      vehiclePanel.remove( deutschEvernPanel.getDeutschEvernPanel() );
      vehiclePanel.remove( embsenPanel.getEmbsenPanel() );
      vehiclePanel.remove( kolkhagenPanel.getKolkhagenPanel() );
      vehiclePanel.remove( melbeckPanel.getMelbeckPanel() );
      vehiclePanel.remove( oerzenPanel.getOerzenPanel() );

      vehiclePanel.revalidate();
      vehiclePanel.repaint();
   }

   private void handleSortedVehicleQueue( int vehicleIndex, String auto, String rufname ) {
      if( vehicleIndex == 0 ) {
         btnRichtig.setText( "Anmelden" );
         setLabel( "", "", "Wollen Sie Florian Lüneburg " + rufname + " zur Funkfahrübung anmelden?", "", auto, rufname, vehicleIndex );
      } else if( vehicleIndex >= 1 && vehicleIndex <= 6 ) {

         setLabel( reader.getKoordinaten( rufname )[vehicleIndex], reader.getBemerkung( rufname )[vehicleIndex], reader.getFragen( rufname )[vehicleIndex],
               reader.getAntworten( rufname )[vehicleIndex], auto, rufname, vehicleIndex );
      } else if( vehicleIndex > 6 ) {
         if( vehicleIndex == 7 ) {
            btnRichtig.setText( "Abmelden" );
            setLabel( reader.getSammelplatzKoordinaten()[1], "", reader.getSammelplatz()[1], "", auto, rufname, vehicleIndex );
         } else {
            vehicleIndex = 8;
            setLabel( "", "", "", "", auto, rufname, vehicleIndex );
         }
      }
   }

   /**
    *
    */
   @Override
   public void itemStateChanged( ItemEvent e ) {
      switch( e.getItem().toString() ) {
         // Barnstedt
         case "18-23-40":
            tlfBarnstedtQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-40-40":
            tsfBarnstedtQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-17-40":
            mtwBarnstedtQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         // Deutsch Evern
         case "18-24-20":
            tlfDeutschEvernQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-45-20":
            lf8DeutschEvernQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-17-20":
            mtwDeutschEvernQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         // Embsen
         case "18-51-30":
            rw1EmbsenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-47-30":
            lfEmbsenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-17-30":
            mtwEmbsenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         // Kolkhagen
         case "18-43-42":
            lf8KolkhagenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         // Melbeck
         case "18-17-10":
            mtwMelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-44-10":
            lf16MelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-24-10":
            tlfMelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         // Oerzen
         case "18-40-32":
            tsfOerzenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-23-32":
            tlfOerzenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "18-17-32":
            mtwOerzenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "Gast1":
            gast1QueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "Gast2":
            gast2QueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "Gast3":
            gast3QueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "Gast4":
            gast4QueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         case "Gast5":
            gast5QueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED );
            break;
         default:
            System.out.println( "Something going wrong: " + e.getItem().toString() );
            break;
      }
   }

   /**
    * @param koordinate
    * @param bemerkung
    * @param frage
    * @param antwort
    * @param auto
    * @param rufname
    * @param aktuellerStand
    */
   public void setLabel( String koordinate, String bemerkung, String frage, String antwort, String auto, String rufname, int aktuellerStand ) {
      if( lblKoordinaten.getText().startsWith( "ND" ) ) {
         lblKoordinaten.setToolTipText( "Nordpol Dora" );
      } else if( lblKoordinaten.getText().startsWith( "PE" ) ) {
         lblKoordinaten.setToolTipText( "Paula Emil" );
      } else if( lblKoordinaten.getText().startsWith( "NE" ) ) {
         lblKoordinaten.setToolTipText( "Nordpol Emil" );
      } else if( lblKoordinaten.getText().startsWith( "PD" ) ) {
         lblKoordinaten.setToolTipText( "Paula Dora" );
      }
      lblAuto.setText( rufname );
      lblBemerkung.setText( bemerkung );
      lblKoordinaten.setText( koordinate );
      lblFrage.setText( frage );
      lblAntwort.setText( antwort );
      button = auto;
      if( !offlineArbeiten ) {
         String sendingContent = auto + " " + aktuellerStand;
         try {
            // Eingabe-Reader/Ausgabe-Writer erzeugen:
            PrintWriter out = new PrintWriter( socketServer.getOutputStream(), true );
            // Ab zum Server:
            out.println( sendingContent );
            out.flush();
            // User hat "X" eingegeben: Socket dichtmachen.
         } catch( IOException e ) {
            System.out.println( "IOException: " + e.getMessage() );
            System.exit( -1 );
         }
      }
   }

   /**
    * @param ipAdress
    */
   public void setIPAdresse( String ipAdress ) {
      if( ipAdress.equals( "" ) ) {
         offlineArbeiten = true;
      } else {
         offlineArbeiten = false;
         // Server-Verbindung aufbauen:
         try {
            socketServer = new Socket( ipAdress, 13112 );
         } catch( UnknownHostException ex ) {
            System.out.println( "UnknownHostException bei Verbindung zu Host 'localhost', Port 13112: " + ex.getMessage() );
         } catch( IOException ex ) {
            System.out.println( "IOException bei Verbindung zu Host 'localhost', Port 13112: " + ex.getMessage() );
         }
      }
      btnSwitchConnection.setOnOff( !offlineArbeiten );
   }
}

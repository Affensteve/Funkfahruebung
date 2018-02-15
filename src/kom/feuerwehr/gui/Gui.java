
package kom.feuerwehr.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kom.feuerwehr.gui.external.Switch;
import kom.feuerwehr.gui.panels.BarnstedtPanel;
import kom.feuerwehr.gui.panels.DeutschEvernPanel;
import kom.feuerwehr.gui.panels.EmbsenPanel;
import kom.feuerwehr.gui.panels.KolkhagenPanel;
import kom.feuerwehr.gui.panels.MelbeckPanel;
import kom.feuerwehr.gui.panels.OerzenPanel;
import kom.feuerwehr.task.ReadExcel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings( "serial" )
public class Gui extends JFrame implements ItemListener {

   private int queueWaitingList = 0;
   private Socket socketServer;
   private ReadExcel reader;

   private JButton btnRichtig;
   private JButton buttonBack;

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
   private int lf8MelbeckIndex = 0;
   private int mtwMelbeckIndex = 0;
   private int tlfDeutschEvernIndex = 0;
   private int lf8DeutschEvernIndex = 0;
   private int mtwDeutschEvernIndex = 0;

   private String button;
   private JLabel lblAuto;
   private JLabel lblKoordinaten;
   private JTextArea lblFrage;
   private JTextArea lblBemerkung;
   private JTextArea lblAntwort;
   private JPanel mtwMelbeckQueuePanel;
   private JPanel lf16MelbeckQueuePanel;
   private JPanel tlfMelbeckQueuePanel;
   private JPanel lf8MelbeckQueuePanel;
   private JPanel tlfDeutschEvernQueuePanel;
   private JPanel lf8DeutschEvernQueuePanel;
   private JPanel mtwDeutschEvernQueuePanel;
   private JPanel rw1EmbsenQueuePanel;
   private JPanel lfEmbsenQueuePanel;
   private JPanel mtwEmbsenQueuePanel;
   private JPanel mtwOerzenQueuePanel;
   private JPanel tsfOerzenQueuePanel;
   private JPanel tlfOerzenQueuePanel;
   private JPanel mtwBarnstedtQueuePanel;
   private JPanel tsfBarnstedtQueuePanel;
   private JPanel tlfBarnstedtQueuePanel;
   private JPanel lf8KolkhagenQueuePanel;

   private int tlfMelbeckWaiting = 0;
   final JLabel label_1;
   private int lf16MelbeckWaiting = 0;
   final JLabel label_2;
   private int lf8MelbeckWaiting = 0;
   final JLabel label_3;
   private int mtwMelbeckWaiting = 0;
   final JLabel label_4;
   private int lf8DeWaiting = 0;
   final JLabel label_5;
   private int tlfDeWaiting = 0;
   final JLabel label_6;
   private int mtwDeWaiting = 0;
   final JLabel label_7;
   private int rwEmbsenWaiting = 0;
   final JLabel label_8;
   private int tlfEmbsenWaiting = 0;
   final JLabel label_9;
   private int mtwEmbsenWaiting = 0;
   final JLabel label_10;
   private int tlfOerzenWaiting = 0;
   final JLabel label_11;
   private int tsfOerzenWaiting = 0;
   final JLabel label_12;
   private int mtwOerzenWaiting = 0;
   final JLabel label_13;
   private int tlfBarnstedtWaiting = 0;
   final JLabel label_14;
   private int tsfBarnstedtWaiting = 0;
   final JLabel label_15;
   private int mtwBarnstedtWaiting = 0;
   final JLabel label_16;
   private int lf8KolkhagenWaiting = 0;
   final JLabel label_17;

   boolean offlineArbeiten;

   private JPanel vehiclePanel;

   private MelbeckPanel melbeckPanel;
   private DeutschEvernPanel deutschEvernPanel;
   private EmbsenPanel embsenPanel;
   private OerzenPanel oerzenPanel;
   private BarnstedtPanel barnstedtPanel;
   private KolkhagenPanel kolkhagenPanel;

   private Switch btnSwitchConnection;
   private JTextField textField;

   public Gui( ) {
      reader = new ReadExcel();
      reader.setInputFile( "Koordinaten/Koordinaten.xls" );
      try {
         reader.read();
      }
      catch( IOException e ) {
         e.printStackTrace();
      }

      try {
         UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
      }
      catch( ClassNotFoundException e ) {
         e.printStackTrace();
      }
      catch( InstantiationException e ) {
         e.printStackTrace();
      }
      catch( IllegalAccessException e ) {
         e.printStackTrace();
      }
      catch( UnsupportedLookAndFeelException e ) {
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
      JLabel label_18 = new JLabel( "18-47-10" );
      label_18.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfMelbeckQueuePanel.add( label_18 );
      JButton tlfMelbeckQueue = new JButton();
      tlfMelbeckQueue.addMouseListener( mouseListener( tlfMelbeckQueuePanel ) );
      configureButtonForQueue( tlfMelbeckQueue );

      tlfMelbeckQueuePanel.add( tlfMelbeckQueue );
      label_1 = new JLabel();
      label_1.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tlfMelbeckQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tlfMelbeck" );
         }
      } );
      tlfMelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfMelbeckIndex, "tlfMelbeckIndex", "18-47-10", label_1 );
         }
      } );
      tlfMelbeckQueuePanel.add( label_1 );
      tlfMelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( tlfMelbeckQueuePanel, "hidemode 3" );

      lf16MelbeckQueuePanel = new JPanel();
      lf16MelbeckQueuePanel.addMouseListener( mouseListener( lf16MelbeckQueuePanel ) );
      lf16MelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent e ) {
            handleSortedVehicleQueue( lf16MelbeckIndex, "lf16MelbeckIndex", "18-44-10", label_2 );
         }
      } );
      JLabel label_19 = new JLabel( "18-44-10" );
      label_19.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf16MelbeckQueuePanel.add( label_19 );
      JButton lf16MelbeckQueue = new JButton();
      configureButtonForQueue( lf16MelbeckQueue );
      label_2 = new JLabel();
      label_2.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      lf16MelbeckQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "lf16Melbeck" );
         }
      } );
      lf16MelbeckQueue.addMouseListener( mouseListener( lf16MelbeckQueuePanel ) );
      lf16MelbeckQueuePanel.add( lf16MelbeckQueue );
      lf16MelbeckQueuePanel.add( label_2 );
      lf16MelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( lf16MelbeckQueuePanel, "hidemode 3" );

      lf8MelbeckQueuePanel = new JPanel();
      lf8MelbeckQueuePanel.addMouseListener( mouseListener( lf8MelbeckQueuePanel ) );
      lf8MelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lf8MelbeckIndex, "lf8MelbeckIndex", "18-43-10", label_3 );
         }
      } );
      JLabel label_20 = new JLabel( "18-43-10" );
      label_20.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf8MelbeckQueuePanel.add( label_20 );
      JButton lf8MelbeckQueue = new JButton();
      configureButtonForQueue( lf8MelbeckQueue );
      label_3 = new JLabel();
      label_3.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      lf8MelbeckQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "lf8Melbeck" );
         }
      } );
      lf8MelbeckQueue.addMouseListener( mouseListener( lf8MelbeckQueuePanel ) );
      lf8MelbeckQueuePanel.add( lf8MelbeckQueue );
      lf8MelbeckQueuePanel.add( label_3 );
      lf8MelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( lf8MelbeckQueuePanel, "hidemode 3" );

      mtwMelbeckQueuePanel = new JPanel();
      mtwMelbeckQueuePanel.addMouseListener( mouseListener( mtwMelbeckQueuePanel ) );
      mtwMelbeckQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwMelbeckIndex, "mtwMelbeckIndex", "18-17-10", label_4 );
         }
      } );
      JLabel label_30 = new JLabel( "18-17-10" );
      label_30.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwMelbeckQueuePanel.add( label_30 );
      JButton mtwMelbeckQueue = new JButton();
      configureButtonForQueue( mtwMelbeckQueue );
      label_4 = new JLabel();
      label_4.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      mtwMelbeckQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "mtwMelbeck" );
         }
      } );
      mtwMelbeckQueue.addMouseListener( mouseListener( mtwMelbeckQueuePanel ) );
      mtwMelbeckQueuePanel.add( mtwMelbeckQueue );
      mtwMelbeckQueuePanel.add( label_4 );
      mtwMelbeckQueuePanel.setVisible( false );
      vehiclePanel.add( mtwMelbeckQueuePanel, "hidemode 3" );

      lf8DeutschEvernQueuePanel = new JPanel();
      lf8DeutschEvernQueuePanel.addMouseListener( mouseListener( lf8DeutschEvernQueuePanel ) );
      lf8DeutschEvernQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lf8DeutschEvernIndex, "lf8DeutschEvernIndex", "18-43-20", label_5 );
         }
      } );
      JLabel label_31 = new JLabel( "18-43-20" );
      label_31.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf8DeutschEvernQueuePanel.add( label_31 );
      JButton lf8DeutschEvernQueue = new JButton();
      configureButtonForQueue( lf8DeutschEvernQueue );
      label_5 = new JLabel();
      label_5.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      lf8DeutschEvernQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "lf8De" );
         }
      } );
      lf8DeutschEvernQueue.addMouseListener( mouseListener( lf8DeutschEvernQueuePanel ) );
      lf8DeutschEvernQueuePanel.add( lf8DeutschEvernQueue );
      lf8DeutschEvernQueuePanel.add( label_5 );
      lf8DeutschEvernQueuePanel.setVisible( false );
      vehiclePanel.add( lf8DeutschEvernQueuePanel, "hidemode 3" );

      tlfDeutschEvernQueuePanel = new JPanel();
      tlfDeutschEvernQueuePanel.addMouseListener( mouseListener( tlfDeutschEvernQueuePanel ) );
      tlfDeutschEvernQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfDeutschEvernIndex, "tlfDeutschEvernIndex", "18-24-20", label_6 );
         }
      } );
      tlfDeutschEvernQueuePanel.setMinimumSize( new Dimension( 40, 10 ) );
      JLabel label_21 = new JLabel( "18-24-20" );
      label_21.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfDeutschEvernQueuePanel.add( label_21 );
      JButton tlfDeutschEvernQueue = new JButton();
      configureButtonForQueue( tlfDeutschEvernQueue );
      label_6 = new JLabel();
      label_6.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tlfDeutschEvernQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tlfDe" );
         }
      } );
      tlfDeutschEvernQueue.addMouseListener( mouseListener( tlfDeutschEvernQueuePanel ) );
      tlfDeutschEvernQueuePanel.add( tlfDeutschEvernQueue );
      tlfDeutschEvernQueuePanel.add( label_6 );
      tlfDeutschEvernQueuePanel.setVisible( false );

      vehiclePanel.add( tlfDeutschEvernQueuePanel, "hidemode 3" );

      mtwDeutschEvernQueuePanel = new JPanel();
      mtwDeutschEvernQueuePanel.addMouseListener( mouseListener( mtwDeutschEvernQueuePanel ) );
      mtwDeutschEvernQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwDeutschEvernIndex, "mtwDeutschEvernIndex", "18-17-20", label_7 );
         }
      } );
      JLabel label_22 = new JLabel( "18-17-20" );
      label_22.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwDeutschEvernQueuePanel.add( label_22 );
      JButton mtwDeutschEvernQueue = new JButton();
      configureButtonForQueue( mtwDeutschEvernQueue );
      label_7 = new JLabel();
      label_7.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      mtwDeutschEvernQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "mtwDe" );
         }
      } );
      mtwDeutschEvernQueue.addMouseListener( mouseListener( mtwDeutschEvernQueuePanel ) );
      mtwDeutschEvernQueuePanel.add( mtwDeutschEvernQueue );
      mtwDeutschEvernQueuePanel.add( label_7 );
      mtwDeutschEvernQueuePanel.setVisible( false );
      vehiclePanel.add( mtwDeutschEvernQueuePanel, "hidemode 3" );

      rw1EmbsenQueuePanel = new JPanel();
      rw1EmbsenQueuePanel.addMouseListener( mouseListener( rw1EmbsenQueuePanel ) );
      rw1EmbsenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( rwEmbsenIndex, "rwEmbsenIndex", "18-51-30", label_8 );
         }
      } );
      JLabel label_23 = new JLabel( "18-51-30" );
      label_23.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      rw1EmbsenQueuePanel.add( label_23 );
      JButton rw1EmbsenQueue = new JButton();
      configureButtonForQueue( rw1EmbsenQueue );
      label_8 = new JLabel();
      label_8.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      rw1EmbsenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "rwEmbsen" );
         }
      } );
      rw1EmbsenQueue.addMouseListener( mouseListener( rw1EmbsenQueuePanel ) );
      rw1EmbsenQueuePanel.add( rw1EmbsenQueue );
      rw1EmbsenQueuePanel.add( label_8 );
      rw1EmbsenQueuePanel.setVisible( false );
      vehiclePanel.add( rw1EmbsenQueuePanel, "hidemode 3" );

      lfEmbsenQueuePanel = new JPanel();
      lfEmbsenQueuePanel.addMouseListener( mouseListener( lfEmbsenQueuePanel ) );
      lfEmbsenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lfEmbsenIndex, "lfEmbsenIndex", "18-47-30", label_9 );
         }
      } );
      JLabel label_24 = new JLabel( "18-47-30" );
      label_24.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lfEmbsenQueuePanel.add( label_24 );
      JButton tlfEmbsenQueue = new JButton();
      configureButtonForQueue( tlfEmbsenQueue );
      label_9 = new JLabel();
      label_9.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tlfEmbsenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tlfEmbsen" );
         }
      } );
      tlfEmbsenQueue.addMouseListener( mouseListener( lfEmbsenQueuePanel ) );
      lfEmbsenQueuePanel.add( tlfEmbsenQueue );
      lfEmbsenQueuePanel.add( label_9 );
      lfEmbsenQueuePanel.setVisible( false );
      vehiclePanel.add( lfEmbsenQueuePanel, "hidemode 3" );

      mtwEmbsenQueuePanel = new JPanel();
      mtwEmbsenQueuePanel.addMouseListener( mouseListener( mtwEmbsenQueuePanel ) );
      mtwEmbsenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwEmbsenIndex, "mtwEmbsenIndex", "18-17-30", label_10 );
         }
      } );
      JLabel label_32 = new JLabel( "18-17-30" );
      label_32.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwEmbsenQueuePanel.add( label_32 );
      JButton mtwEmbsenQueue = new JButton();
      configureButtonForQueue( mtwEmbsenQueue );
      label_10 = new JLabel();
      label_10.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      mtwEmbsenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "mtwEmbsen" );
         }
      } );
      mtwEmbsenQueue.addMouseListener( mouseListener( mtwEmbsenQueuePanel ) );
      mtwEmbsenQueuePanel.add( mtwEmbsenQueue );
      mtwEmbsenQueuePanel.add( label_10 );
      mtwEmbsenQueuePanel.setVisible( false );
      vehiclePanel.add( mtwEmbsenQueuePanel, "hidemode 3" );

      tlfOerzenQueuePanel = new JPanel();
      tlfOerzenQueuePanel.addMouseListener( mouseListener( tlfOerzenQueuePanel ) );
      tlfOerzenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfOerzenIndex, "tlfOerzenIndex", "18-47-32", label_11 );
         }
      } );
      JLabel label_33 = new JLabel( "18-47-32" );
      label_33.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfOerzenQueuePanel.add( label_33 );
      JButton tlfOerzenQueue = new JButton();
      configureButtonForQueue( tlfOerzenQueue );
      label_11 = new JLabel();
      label_11.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tlfOerzenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tlfOerzen" );
         }
      } );
      tlfOerzenQueue.addMouseListener( mouseListener( tlfOerzenQueuePanel ) );
      tlfOerzenQueuePanel.add( tlfOerzenQueue );
      tlfOerzenQueuePanel.add( label_11 );

      tlfOerzenQueuePanel.setVisible( false );
      vehiclePanel.add( tlfOerzenQueuePanel, "hidemode 3" );

      tsfOerzenQueuePanel = new JPanel();
      tsfOerzenQueuePanel.addMouseListener( mouseListener( tsfOerzenQueuePanel ) );
      tsfOerzenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tsfOerzenIndex, "tsfOerzenIndex", "18-40-32", label_12 );
         }
      } );
      JLabel label_25 = new JLabel( "18-40-32" );
      label_25.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tsfOerzenQueuePanel.add( label_25 );
      JButton tsfOerzenQueue = new JButton();
      configureButtonForQueue( tsfOerzenQueue );
      label_12 = new JLabel();
      label_12.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tsfOerzenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tsfOerzen" );
         }
      } );
      tsfOerzenQueue.addMouseListener( mouseListener( tsfOerzenQueuePanel ) );
      tsfOerzenQueuePanel.add( tsfOerzenQueue );
      tsfOerzenQueuePanel.add( label_12 );
      tsfOerzenQueuePanel.setVisible( false );
      vehiclePanel.add( tsfOerzenQueuePanel, "hidemode 3" );

      mtwOerzenQueuePanel = new JPanel();
      mtwOerzenQueuePanel.addMouseListener( mouseListener( mtwOerzenQueuePanel ) );
      mtwOerzenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwOerzenIndex, "mtwOerzenIndex", "18-17-32", label_13 );
         }
      } );
      JLabel label_26 = new JLabel( "18-17-32" );
      label_26.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwOerzenQueuePanel.add( label_26 );
      JButton mtwOerzenQueue = new JButton();
      configureButtonForQueue( mtwOerzenQueue );
      label_13 = new JLabel();
      label_13.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      mtwOerzenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "mtwOerzen" );
         }
      } );
      mtwOerzenQueue.addMouseListener( mouseListener( mtwOerzenQueuePanel ) );
      mtwOerzenQueuePanel.add( mtwOerzenQueue );
      mtwOerzenQueuePanel.add( label_13 );
      mtwOerzenQueuePanel.setVisible( false );
      vehiclePanel.add( mtwOerzenQueuePanel, "hidemode 3" );

      tlfBarnstedtQueuePanel = new JPanel();
      tlfBarnstedtQueuePanel.addMouseListener( mouseListener( tlfBarnstedtQueuePanel ) );
      tlfBarnstedtQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tlfBarnstedtIndex, "tlfBarnstedtIndex", "18-47-40", label_14 );
         }
      } );
      JLabel label_27 = new JLabel( "18-47-40" );
      label_27.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tlfBarnstedtQueuePanel.add( label_27 );
      JButton tlfBarnstedtQueue = new JButton();
      configureButtonForQueue( tlfBarnstedtQueue );
      label_14 = new JLabel();
      label_14.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tlfBarnstedtQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tlfBarnstedt" );
         }
      } );
      tlfBarnstedtQueue.addMouseListener( mouseListener( tlfBarnstedtQueuePanel ) );
      tlfBarnstedtQueuePanel.add( tlfBarnstedtQueue );
      tlfBarnstedtQueuePanel.add( label_14 );
      tlfBarnstedtQueuePanel.setVisible( false );
      vehiclePanel.add( tlfBarnstedtQueuePanel, "hidemode 3" );

      tsfBarnstedtQueuePanel = new JPanel();
      tsfBarnstedtQueuePanel.addMouseListener( mouseListener( tsfBarnstedtQueuePanel ) );
      tsfBarnstedtQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( tsfBarnstedtIndex, "tsfBarnstedtIndex", "18-40-40", label_15 );
         }
      } );
      JLabel label_28 = new JLabel( "18-40-40" );
      label_28.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      tsfBarnstedtQueuePanel.add( label_28 );
      JButton tsfBarnstedtQueue = new JButton();
      configureButtonForQueue( tsfBarnstedtQueue );
      label_15 = new JLabel();
      label_15.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      tsfBarnstedtQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "tsfBarnstedt" );
         }
      } );
      tsfBarnstedtQueue.addMouseListener( mouseListener( tsfBarnstedtQueuePanel ) );
      tsfBarnstedtQueuePanel.add( tsfBarnstedtQueue );
      tsfBarnstedtQueuePanel.add( label_15 );
      tsfBarnstedtQueuePanel.setVisible( false );
      vehiclePanel.add( tsfBarnstedtQueuePanel, "hidemode 3" );

      mtwBarnstedtQueuePanel = new JPanel();
      mtwBarnstedtQueuePanel.addMouseListener( mouseListener( mtwBarnstedtQueuePanel ) );
      mtwBarnstedtQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( mtwBarnstedtIndex, "mtwBarnstedtIndex", "18-17-40", label_16 );
         }
      } );
      JLabel label_29 = new JLabel( "18-17-40" );
      label_29.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      mtwBarnstedtQueuePanel.add( label_29 );
      JButton mtwBarnstedtQueue = new JButton();
      configureButtonForQueue( mtwBarnstedtQueue );
      label_16 = new JLabel();
      label_16.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      mtwBarnstedtQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "mtwBarnstedt" );
         }
      } );
      mtwBarnstedtQueue.addMouseListener( mouseListener( mtwBarnstedtQueuePanel ) );
      mtwBarnstedtQueuePanel.add( mtwBarnstedtQueue );
      mtwBarnstedtQueuePanel.add( label_16 );
      mtwBarnstedtQueuePanel.setVisible( false );
      vehiclePanel.add( mtwBarnstedtQueuePanel, "hidemode 3" );

      lf8KolkhagenQueuePanel = new JPanel();
      JLabel label_34 = new JLabel( "18-43-42" );
      label_34.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
      lf8KolkhagenQueuePanel.add( label_34 );
      JButton lf8KolkhagenQueue = new JButton();
      configureButtonForQueue( lf8KolkhagenQueue );
      label_17 = new JLabel();
      label_17.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
      lf8KolkhagenQueue.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            inkrementQueueWaiting( "lf8Kolkhagen" );
         }
      } );
      lf8KolkhagenQueue.addMouseListener( mouseListener( lf8KolkhagenQueuePanel ) );
      lf8KolkhagenQueuePanel.add( lf8KolkhagenQueue );
      lf8KolkhagenQueuePanel.addMouseListener( mouseListener( lf8KolkhagenQueuePanel ) );
      lf8KolkhagenQueuePanel.addMouseListener( new MouseAdapter() {
         @Override
         public void mouseClicked( MouseEvent arg0 ) {
            handleSortedVehicleQueue( lf8KolkhagenIndex, "lf8KolkhagenIndex", "18-43-42", label_17 );
         }
      } );
      lf8KolkhagenQueuePanel.add( label_17 );
      lf8KolkhagenQueuePanel.setVisible( false );
      vehiclePanel.add( lf8KolkhagenQueuePanel, "hidemode 3" );

      final JFrame frame = new JFrame( "Funkfahrübung by ©S.Kunz" );
      frame.setResizable( false );
      frame.setAlwaysOnTop( true );

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
      }
      else if( lblKoordinaten.getText().startsWith( "PE" ) ) {
         lblKoordinaten.setToolTipText( "Paula Emil" );
      }
      else if( lblKoordinaten.getText().startsWith( "NE" ) ) {
         lblKoordinaten.setToolTipText( "Nordpol Emil" );
      }
      else if( lblKoordinaten.getText().startsWith( "PD" ) ) {
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
      mainMenu.add( editVehiclesMenuItem );

      JMenuItem editQuestionsMenuItem = new JMenuItem( "Fragen bearbeiten" );
      editQuestionsMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            // TODO
         }
      } );
      editQuestionsMenuItem.setEnabled( false );
      mainMenu.add( editQuestionsMenuItem );

      JMenuItem editFFU = new JMenuItem( "Übung planen" );
      editFFU.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            // TODO
         }
      } );
      editFFU.setEnabled( false );
      mainMenu.add( editFFU );

      JMenuItem exitMenuItem = new JMenuItem( "Beenden" );
      exitMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            if( !offlineArbeiten ) {
               try {
                  socketServer.close();
               }
               catch( IOException e ) {
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
      JRadioButtonMenuItem alphaMenuItem = new JRadioButtonMenuItem( "Aphabetisch" );
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
            }
            else {
               melbeckPanel.enableCheckBoxMTWMelbeck( false );
               deutschEvernPanel.enableCheckBoxMTWDeutschEvern( false );
               embsenPanel.enableCheckBoxMTWEmbsen( false );
               oerzenPanel.enableCheckBoxMTWOerzen( false );
               barnstedtPanel.enableCheckBoxMTWBarnstedt( false );
               chckbxmntmMtwZugriff.setText( "MTW erlauben" );
            }
         }
      } );
      submenu.add( chckbxmntmMtwZugriff );
      menuBar.add( submenu );

      JMenu helpMenu = new JMenu( "Hilfe" );

      JMenuItem aboutMenuItem = new JMenuItem( "Über" );
      aboutMenuItem.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed( ActionEvent arg0 ) {
            JOptionPane.showMessageDialog( frame, "Funkfahrübung by ©S.Kunz\n" + "Developer: Steffen Kunz\n" + "Version: 1.0 \n"
                  + "\n©S.Kunz. All rights reserved", "Über Funkfahrübung by ©S.Kunz", JOptionPane.INFORMATION_MESSAGE );
         }
      } );
      helpMenu.add( aboutMenuItem );
      menuBar.add( helpMenu );
      frame.setJMenuBar( menuBar );

      JPanel helpPanel = new JPanel();
      helpPanel.setLayout( new MigLayout( "wrap 1", "[grow]", "[][][][]" ) );

      JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, vehiclePanel, helpPanel );

      JLabel label_35 = new JLabel( "18-11-30" );
      label_35.setFont( new Font( "Tahoma", Font.PLAIN, 28 ) );
      helpPanel.add( label_35, "cell 0 0" );

      JLabel lblBosFunkalphabet = new JLabel( "BOS Funk-Alphabet" );
      helpPanel.add( lblBosFunkalphabet, "cell 0 1" );
      final JLabel label_36 = new JLabel( "" );
      label_36.setFont(new Font("Tahoma", Font.PLAIN, 18));
      helpPanel.add( label_36, "cell 0 3" );

      textField = new JTextField();
      textField.addKeyListener( new KeyListener() {

         @Override
         public void keyTyped( KeyEvent e ) {
            // TODO Auto-generated method stub

         }

         @Override
         public void keyReleased( KeyEvent e ) {
            System.out.println( textField.getText() );
            String[] split = textField.getText().split( "" );
            String stringbuilder = "<html>";
            for( int i = 0; i < split.length; i++ ) {
               switch( split[ i ] ) {
                  case "a":
                     stringbuilder = stringbuilder + "<b>A</b>nton <br>";
                     break;
                  case "b":
                     stringbuilder = stringbuilder + "<b>B</b>erta <br>";
                     break;
                  default:
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

   private void configureButtonForQueue( JButton button ) {
      button.setOpaque( false );
      button.setContentAreaFilled( false );
      button.setFocusable( false );
      button.setBorderPainted( false );
      button.setSize( new Dimension( 15, 35 ) );
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
                     tlfMelbeckIndex = btnBackBehavior( tlfMelbeckIndex, tlfMelbeckQueuePanel, "18-47-10", "tlfMelbeckIndex" );
                     break;
                  case "lf16MelbeckIndex":
                     lf16MelbeckIndex = btnBackBehavior( lf16MelbeckIndex, lf16MelbeckQueuePanel, "18-44-10", "lf16MelbeckIndex" );
                     break;
                  case "lf8MelbeckIndex":
                     lf8MelbeckIndex = btnBackBehavior( lf8MelbeckIndex, lf8MelbeckQueuePanel, "18-43-10", "lf8MelbeckIndex" );
                     break;
                  case "mtwMelbeckIndex":
                     mtwMelbeckIndex = btnBackBehavior( mtwMelbeckIndex, mtwMelbeckQueuePanel, "18-17-10", "mtwMelbeckIndex" );
                     break;
                  case "lf8DeutschEvernIndex":
                     lf8DeutschEvernIndex = btnBackBehavior( lf8DeutschEvernIndex, lf8DeutschEvernQueuePanel, "18-43-20", "lf8DeutschEvernIndex" );
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
                     tlfOerzenIndex = btnBackBehavior( tlfOerzenIndex, tlfOerzenQueuePanel, "18-47-32", "tlfOerzenIndex" );
                     break;
                  case "tsfOerzenIndex":
                     tsfOerzenIndex = btnBackBehavior( tsfOerzenIndex, tsfOerzenQueuePanel, "18-40-32", "tsfOerzenIndex" );
                     break;
                  case "mtwOerzenIndex":
                     mtwOerzenIndex = btnBackBehavior( mtwOerzenIndex, mtwOerzenQueuePanel, "18-17-32", "mtwOerzenIndex" );
                     break;
                  case "tlfBarnstedtIndex":
                     tlfBarnstedtIndex = btnBackBehavior( tlfBarnstedtIndex, tlfBarnstedtQueuePanel, "18-47-40", "tlfBarnstedtIndex" );
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
            }
            else if( vehicleIndex > 0 && vehicleIndex <= 6 ) {
               if( vehicleIndex == 1 ) {
                  buttonBack.setText( "Abmelden" );
                  setColor( panel, Color.RED );
               }
               else if( vehicleIndex == 2 ) {
                  setColor( panel, Color.YELLOW );
               }
               else if( vehicleIndex == 3 ) {
                  setColor( panel, Color.GREEN );
               }
               else if( vehicleIndex == 4 ) {
                  setColor( panel, Color.CYAN );
               }
               else if( vehicleIndex == 5 ) {
                  setColor( panel, Color.ORANGE );
               }
               else if( vehicleIndex == 6 ) {
                  btnRichtig.setText( "Richtig" );
                  setColor( panel, Color.MAGENTA );
               }
               setLabel( reader.getKoordinaten( rufname )[ vehicleIndex ], reader.getBemerkung( rufname )[ vehicleIndex ], reader.getFragen(
                     rufname )[ vehicleIndex ], reader.getAntworten( rufname )[ vehicleIndex ], auto, rufname, vehicleIndex );
               return vehicleIndex;
            }
            else {
               btnRichtig.setText( "Abmelden" );
               setLabel( reader.getSammelplatzKoordinaten()[ 1 ], "", reader.getSammelplatz()[ 1 ], "", auto, rufname, vehicleIndex );
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
                     tlfMelbeckIndex = btnCorrectBehavior( tlfMelbeckIndex, tlfMelbeckQueuePanel, "18-47-10", "tlfMelbeckIndex" );
                     break;
                  case "lf16MelbeckIndex":
                     lf16MelbeckIndex = btnCorrectBehavior( lf16MelbeckIndex, lf16MelbeckQueuePanel, "18-44-10", "lf16MelbeckIndex" );
                     break;
                  case "lf8MelbeckIndex":
                     lf8MelbeckIndex = btnCorrectBehavior( lf8MelbeckIndex, lf8MelbeckQueuePanel, "18-43-10", "lf8MelbeckIndex" );
                     break;
                  case "mtwMelbeckIndex":
                     mtwMelbeckIndex = btnCorrectBehavior( mtwMelbeckIndex, mtwMelbeckQueuePanel, "18-17-10", "mtwMelbeckIndex" );
                     break;
                  case "lf8DeutschEvernIndex":
                     lf8DeutschEvernIndex = btnCorrectBehavior( lf8DeutschEvernIndex, lf8DeutschEvernQueuePanel, "18-43-20", "lf8DeutschEvernIndex" );
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
                     tlfOerzenIndex = btnCorrectBehavior( tlfOerzenIndex, tlfOerzenQueuePanel, "18-47-32", "tlfOerzenIndex" );
                     break;
                  case "tsfOerzenIndex":
                     tsfOerzenIndex = btnCorrectBehavior( tsfOerzenIndex, tsfOerzenQueuePanel, "18-40-32", "tsfOerzenIndex" );
                     break;
                  case "mtwOerzenIndex":
                     mtwOerzenIndex = btnCorrectBehavior( mtwOerzenIndex, mtwOerzenQueuePanel, "18-17-32", "mtwOerzenIndex" );
                     break;
                  case "tlfBarnstedtIndex":
                     tlfBarnstedtIndex = btnCorrectBehavior( tlfBarnstedtIndex, tlfBarnstedtQueuePanel, "18-47-40", "tlfBarnstedtIndex" );
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
               }
               else if( vehicleIndex == 2 ) {
                  buttonBack.setText( "<<" );
                  setColor( panel, Color.YELLOW );
               }
               else if( vehicleIndex == 3 ) {
                  setColor( panel, Color.GREEN );
               }
               else if( vehicleIndex == 4 ) {
                  setColor( panel, Color.CYAN );
               }
               else if( vehicleIndex == 5 ) {
                  setColor( panel, Color.ORANGE );
               }
               else if( vehicleIndex == 6 ) {
                  setColor( panel, Color.MAGENTA );
               }
               setLabel( reader.getKoordinaten( rufname )[ vehicleIndex ], reader.getBemerkung( rufname )[ vehicleIndex ], reader.getFragen(
                     rufname )[ vehicleIndex ], reader.getAntworten( rufname )[ vehicleIndex ], auto, rufname, vehicleIndex );
               return vehicleIndex;
            }
            else {
               vehicleIndex++;
               if( vehicleIndex == 7 ) {
                  btnRichtig.setText( "Abmelden" );
                  setLabel( reader.getSammelplatzKoordinaten()[ 1 ], "", reader.getSammelplatz()[ 1 ], "", auto, rufname, vehicleIndex );
                  setColor( panel, Color.LIGHT_GRAY );
               }
               else {
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

   private void handleSortedVehicleQueue( int vehicleIndex, String auto, String rufname, JLabel waitingQueueLabel ) {
      if( vehicleIndex == 0 ) {
         btnRichtig.setText( "Anmelden" );
         setLabel( "", "", "Wollen Sie Florian Lüneburg" + rufname + " zur Funkfahrübung anmelden?", "", auto, rufname, vehicleIndex );
      }
      else if( vehicleIndex >= 1 && vehicleIndex <= 6 ) {

         setLabel( reader.getKoordinaten( rufname )[ vehicleIndex ], reader.getBemerkung( rufname )[ vehicleIndex ], reader.getFragen(
               rufname )[ vehicleIndex ], reader.getAntworten( rufname )[ vehicleIndex ], auto, rufname, vehicleIndex );
      }
      else if( vehicleIndex > 6 ) {
         if( vehicleIndex == 7 ) {
            btnRichtig.setText( "Abmelden" );
            setLabel( reader.getSammelplatzKoordinaten()[ 1 ], "", reader.getSammelplatz()[ 1 ], "", auto, rufname, vehicleIndex );
         }
         else {
            vehicleIndex = 8;
            setLabel( "", "", "", "", auto, rufname, vehicleIndex );
         }
      }
      if( waitingQueueLabel.getText() != "" ) {
         dekrementQueueWaiting();
      }
   }

   /**
    * 
    * @param auto
    */
   protected void inkrementQueueWaiting( String auto ) {
      queueWaitingList++;
      switch( auto ) {
         case "tlfMelbeck":
            tlfMelbeckWaiting = queueWaitingList;
            label_1.setText( String.valueOf( tlfMelbeckWaiting ) );
            break;
         case "lf16Melbeck":
            lf16MelbeckWaiting = queueWaitingList;
            label_2.setText( String.valueOf( lf16MelbeckWaiting ) );
            break;
         case "lf8Melbeck":
            lf8MelbeckWaiting = queueWaitingList;
            label_3.setText( String.valueOf( lf8MelbeckWaiting ) );
            break;
         case "mtwMelbeck":
            mtwMelbeckWaiting = queueWaitingList;
            label_4.setText( String.valueOf( mtwMelbeckWaiting ) );
            break;
         case "lf8De":
            lf8DeWaiting = queueWaitingList;
            label_5.setText( String.valueOf( lf8DeWaiting ) );
            break;
         case "tlfDe":
            tlfDeWaiting = queueWaitingList;
            label_6.setText( String.valueOf( tlfDeWaiting ) );
            break;
         case "mtwDe":
            mtwDeWaiting = queueWaitingList;
            label_7.setText( String.valueOf( mtwDeWaiting ) );
            break;
         case "rwEmbsen":
            rwEmbsenWaiting = queueWaitingList;
            label_8.setText( String.valueOf( rwEmbsenWaiting ) );
            break;
         case "tlfEmbsen":
            tlfEmbsenWaiting = queueWaitingList;
            label_9.setText( String.valueOf( tlfEmbsenWaiting ) );
            break;
         case "mtwEmbsen":
            mtwEmbsenWaiting = queueWaitingList;
            label_10.setText( String.valueOf( mtwEmbsenWaiting ) );
            break;
         case "tlfOerzen":
            tlfOerzenWaiting = queueWaitingList;
            label_11.setText( String.valueOf( tlfOerzenWaiting ) );
            break;
         case "tsfOerzen":
            tsfOerzenWaiting = queueWaitingList;
            label_12.setText( String.valueOf( tsfOerzenWaiting ) );
            break;
         case "mtwOerzen":
            mtwOerzenWaiting = queueWaitingList;
            label_13.setText( String.valueOf( mtwOerzenWaiting ) );
            break;
         case "tlfBarnstedt":
            tlfBarnstedtWaiting = queueWaitingList;
            label_14.setText( String.valueOf( tlfBarnstedtWaiting ) );
            break;
         case "tsfBarnstedt":
            tsfBarnstedtWaiting = queueWaitingList;
            label_15.setText( String.valueOf( tsfBarnstedtWaiting ) );
            break;
         case "mtwBarnstedt":
            mtwBarnstedtWaiting = queueWaitingList;
            label_16.setText( String.valueOf( mtwBarnstedtWaiting ) );
            break;
         case "lf8Kolkhagen":
            lf8KolkhagenWaiting = queueWaitingList;
            label_17.setText( String.valueOf( lf8KolkhagenWaiting ) );
            break;
         default:
            System.out.println( "Unknown Auto: " + auto );
            break;
      }

   }

   /**
    * 
    */
   protected void dekrementQueueWaiting( ) {
      queueWaitingList--;
      if( queueWaitingList <= 0 ) {
         queueWaitingList = 0;
      }
      label_1.setText( tlfMelbeckWaiting <= 0 ? "" : String.valueOf( --tlfMelbeckWaiting ) );
      label_2.setText( lf16MelbeckWaiting <= 0 ? "" : String.valueOf( --lf16MelbeckWaiting ) );
      label_3.setText( lf8MelbeckWaiting <= 0 ? "" : String.valueOf( --lf8MelbeckWaiting ) );
      label_4.setText( mtwMelbeckWaiting <= 0 ? "" : String.valueOf( --mtwMelbeckWaiting ) );
      label_5.setText( lf8DeWaiting <= 0 ? "" : String.valueOf( --lf8DeWaiting ) );
      label_6.setText( tlfDeWaiting <= 0 ? "" : String.valueOf( --tlfDeWaiting ) );
      label_7.setText( mtwDeWaiting <= 0 ? "" : String.valueOf( --mtwDeWaiting ) );
      label_8.setText( rwEmbsenWaiting <= 0 ? "" : String.valueOf( --rwEmbsenWaiting ) );
      label_9.setText( tlfEmbsenWaiting <= 0 ? "" : String.valueOf( --tlfEmbsenWaiting ) );
      label_10.setText( mtwEmbsenWaiting <= 0 ? "" : String.valueOf( --mtwEmbsenWaiting ) );
      label_11.setText( tlfOerzenWaiting <= 0 ? "" : String.valueOf( --tlfOerzenWaiting ) );
      label_12.setText( tsfOerzenWaiting <= 0 ? "" : String.valueOf( --tsfOerzenWaiting ) );
      label_13.setText( mtwOerzenWaiting <= 0 ? "" : String.valueOf( --mtwOerzenWaiting ) );
      label_14.setText( tlfBarnstedtWaiting <= 0 ? "" : String.valueOf( --tlfBarnstedtWaiting ) );
      label_15.setText( tsfBarnstedtWaiting <= 0 ? "" : String.valueOf( --tsfBarnstedtWaiting ) );
      label_16.setText( mtwBarnstedtWaiting <= 0 ? "" : String.valueOf( --mtwBarnstedtWaiting ) );
      label_17.setText( lf8KolkhagenWaiting <= 0 ? "" : String.valueOf( --lf8KolkhagenWaiting ) );
   }

   /**
    * 
    */
   @Override
   public void itemStateChanged( ItemEvent e ) {
      switch( e.getItem().toString() ) {
         // Barnstedt
         case "18-47-40":
            tlfBarnstedtQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-40-40":
            tsfBarnstedtQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-17-40":
            mtwBarnstedtQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         // Deutsch Evern
         case "18-24-20":
            tlfDeutschEvernQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-43-20":
            lf8DeutschEvernQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-17-20":
            mtwDeutschEvernQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         // Embsen
         case "18-51-30":
            rw1EmbsenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-47-30":
            lfEmbsenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-17-30":
            mtwEmbsenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         // Kolkhagen
         case "18-43-42":
            lf8KolkhagenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         // Melbeck
         case "18-17-10":
            mtwMelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-43-10":
            lf8MelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-44-10":
            lf16MelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-47-10":
            tlfMelbeckQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         // Oerzen
         case "18-40-32":
            tsfOerzenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-47-32":
            tlfOerzenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         case "18-17-32":
            mtwOerzenQueuePanel.setVisible( e.getStateChange() == ItemEvent.SELECTED ? true : false );
            break;
         default:
            System.out.println( "Something going wrong: " + e.getItem().toString() );
            break;
      }
   };

   /**
    * 
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
      }
      else if( lblKoordinaten.getText().startsWith( "PE" ) ) {
         lblKoordinaten.setToolTipText( "Paula Emil" );
      }
      else if( lblKoordinaten.getText().startsWith( "NE" ) ) {
         lblKoordinaten.setToolTipText( "Nordpol Emil" );
      }
      else if( lblKoordinaten.getText().startsWith( "PD" ) ) {
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
         }
         catch( IOException e ) {
            System.out.println( "IOException: " + e.getMessage() );
            System.exit( -1 );
         }
      }
   }

   /**
    * 
    * @param ipAdress
    */
   public void setIPAdresse( String ipAdress ) {
      if( ipAdress.equals( "" ) ) {
         offlineArbeiten = true;
      }
      else {
         offlineArbeiten = false;
         // Server-Verbindung aufbauen:
         try {
            socketServer = new Socket( ipAdress, 13112 );
         }
         catch( UnknownHostException ex ) {
            System.out.println( "UnknownHostException bei Verbindung zu Host 'localhost', Port 13000: " + ex.getMessage() );
         }
         catch( IOException ex ) {
            System.out.println( "IOException bei Verbindung zu Host 'localhost', Port 13000: " + ex.getMessage() );
         }
      }
      btnSwitchConnection.setOnOff( !offlineArbeiten );
   }
}

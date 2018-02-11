package kom.feuerwehr.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import com.feuerwehr.task.ReadExcel;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ItemListener {

	private int queueWaitingList = 0;
	Socket socketServer = null;
	private ReadExcel reader = new ReadExcel();

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

	private Checkbox mtwMelbeck;
	private Checkbox mtwDE;
	private Checkbox mtwEmbsen;
	private Checkbox mtwOerzen;
	private Checkbox mtwbarnstedt;

	private Checkbox mtwAlphabetischMelbeck;
	private Checkbox mtwAlphabetischDE;
	private Checkbox mtwAlphabetischEmbsen;
	private Checkbox mtwAlphabetischOerzen;
	private Checkbox mtwAlphabetischBarnstedt;

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

	public Gui() {
		reader.setInputFile("Koordinaten/Koordinaten.xls");
		try {
			reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		final JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new MigLayout("wrap 6",
				"[grow][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][]"));

		// Numerisch
		final JPanel melbeckPanel = new JPanel();
		melbeckPanel.setMinimumSize(new Dimension(100, 10));
		melbeckPanel.setLayout(new MigLayout("wrap 1"));
		melbeckPanel.add(new JLabel("Melbeck"));
		melbeckPanel.add(new JLabel(new ImageIcon("res/Wappen_Melbeck.png")));
		Checkbox tlfMelbeck = new Checkbox("18-47-10");
		tlfMelbeck.addItemListener(this);
		melbeckPanel.add(tlfMelbeck);
		Checkbox lf16Melbeck = new Checkbox("18-44-10");
		lf16Melbeck.addItemListener(this);
		melbeckPanel.add(lf16Melbeck);
		Checkbox lf8Melbeck = new Checkbox("18-43-10");
		lf8Melbeck.addItemListener(this);
		melbeckPanel.add(lf8Melbeck);
		mtwMelbeck = new Checkbox("18-17-10");
		mtwMelbeck.setEnabled(false);
		mtwMelbeck.addItemListener(this);
		melbeckPanel.add(mtwMelbeck);
		vehiclePanel.add(melbeckPanel, "hidemode 3, cell 0 0");

		final JPanel deutschEvernPanel = new JPanel();
		deutschEvernPanel.setMinimumSize(new Dimension(100, 10));
		deutschEvernPanel.setLayout(new MigLayout("wrap 1"));
		deutschEvernPanel.add(new JLabel("Deutsch Evern"));
		deutschEvernPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Deutsch_Evern.png")));
		Checkbox lf8DE = new Checkbox("18-43-20");
		lf8DE.addItemListener(this);
		deutschEvernPanel.add(lf8DE);
		Checkbox tlfDE = new Checkbox("18-24-20");
		tlfDE.addItemListener(this);
		deutschEvernPanel.add(tlfDE);
		mtwDE = new Checkbox("18-17-20");
		mtwDE.setEnabled(false);
		mtwDE.addItemListener(this);
		deutschEvernPanel.add(mtwDE);
		deutschEvernPanel.add(new JLabel(" "), "wrap 8");
		vehiclePanel.add(deutschEvernPanel, "hidemode 3, cell 1 0");

		final JPanel embsenPanel = new JPanel();
		embsenPanel.setMinimumSize(new Dimension(100, 10));
		embsenPanel.setLayout(new MigLayout("wrap 1"));
		embsenPanel.add(new JLabel("Embsen"));
		embsenPanel.add(new JLabel(new ImageIcon("res/Wappen_Embsen.png")));
		Checkbox rw1Embsen = new Checkbox("18-51-30");
		rw1Embsen.addItemListener(this);
		embsenPanel.add(rw1Embsen);
		Checkbox lfEmbsen = new Checkbox("18-47-30");
		lfEmbsen.addItemListener(this);
		embsenPanel.add(lfEmbsen);
		mtwEmbsen = new Checkbox("18-17-30");
		mtwEmbsen.setEnabled(false);
		mtwEmbsen.addItemListener(this);
		embsenPanel.add(mtwEmbsen);
		embsenPanel.add(new JLabel(" "), "wrap 8");
		vehiclePanel.add(embsenPanel, "hidemode 3, cell 2 0");

		final JPanel oerzenPanel = new JPanel();
		oerzenPanel.setMinimumSize(new Dimension(100, 10));
		oerzenPanel.setLayout(new MigLayout("wrap 1"));
		oerzenPanel.add(new JLabel("Oerzen"));
		oerzenPanel.add(new JLabel(new ImageIcon("res/Wappen_Embsen.png")));

		Checkbox tlfOerzen = new Checkbox("18-47-32");
		tlfOerzen.addItemListener(this);
		oerzenPanel.add(tlfOerzen);
		Checkbox tsfOerzen = new Checkbox("18-40-32");
		tsfOerzen.addItemListener(this);
		oerzenPanel.add(tsfOerzen);
		mtwOerzen = new Checkbox("18-17-32");
		mtwOerzen.setEnabled(false);
		mtwOerzen.addItemListener(this);
		oerzenPanel.add(mtwOerzen);
		oerzenPanel.add(new JLabel(" "), "wrap 8");
		vehiclePanel.add(oerzenPanel, "hidemode 3, cell 3 0");

		final JPanel barnstedtPanel = new JPanel();
		barnstedtPanel.setMinimumSize(new Dimension(100, 10));
		barnstedtPanel.setLayout(new MigLayout("wrap 1"));
		barnstedtPanel.add(new JLabel("Barnstedt"));
		barnstedtPanel
				.add(new JLabel(new ImageIcon("res/Wappen_Barnstedt.png")));
		Checkbox tlfBarnstedt = new Checkbox("18-47-40");
		tlfBarnstedt.addItemListener(this);
		barnstedtPanel.add(tlfBarnstedt);
		Checkbox tsfbarnstedt = new Checkbox("18-40-40");
		tsfbarnstedt.addItemListener(this);
		barnstedtPanel.add(tsfbarnstedt);
		mtwbarnstedt = new Checkbox("18-17-40");
		mtwbarnstedt.setEnabled(false);
		mtwbarnstedt.addItemListener(this);
		barnstedtPanel.add(mtwbarnstedt);
		barnstedtPanel.add(new JLabel(" "), "wrap 8");
		vehiclePanel.add(barnstedtPanel, "hidemode 3, cell 4 0");

		final JPanel kolkhagenPanel = new JPanel();
		kolkhagenPanel.setMinimumSize(new Dimension(100, 10));
		kolkhagenPanel.setLayout(new MigLayout("wrap 1"));
		kolkhagenPanel.add(new JLabel("Kolkhagen"));
		kolkhagenPanel
				.add(new JLabel(new ImageIcon("res/Wappen_Kolkhagen.png")));
		Checkbox lf8Kolkhagen = new Checkbox("18-43-42");
		lf8Kolkhagen.addItemListener(this);
		kolkhagenPanel.add(lf8Kolkhagen);
		kolkhagenPanel.add(new JLabel(" "), "wrap 32");
		kolkhagenPanel.add(new JLabel(" "), "wrap 10");
		vehiclePanel.add(kolkhagenPanel, "hidemode 3, cell 5 0");

		// Alphabetisch
		final JPanel barnstedtAlphabetischPanel = new JPanel();
		barnstedtAlphabetischPanel.setMinimumSize(new Dimension(100, 10));
		barnstedtAlphabetischPanel.setLayout(new MigLayout("wrap 1"));
		barnstedtAlphabetischPanel.add(new JLabel("Barnstedt"));
		barnstedtAlphabetischPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Barnstedt.png")));
		Checkbox tlfAlphabetischBarnstedt = new Checkbox("18-47-40");
		tlfAlphabetischBarnstedt.addItemListener(this);
		barnstedtAlphabetischPanel.add(tlfAlphabetischBarnstedt);
		Checkbox tsfAlphabetischBarnstedt = new Checkbox("18-40-40");
		tsfAlphabetischBarnstedt.addItemListener(this);
		barnstedtAlphabetischPanel.add(tsfAlphabetischBarnstedt);
		mtwAlphabetischBarnstedt = new Checkbox("18-17-40");
		mtwAlphabetischBarnstedt.setEnabled(false);
		mtwAlphabetischBarnstedt.addItemListener(this);
		barnstedtAlphabetischPanel.add(mtwAlphabetischBarnstedt);
		barnstedtAlphabetischPanel.add(new JLabel(" "), "wrap 8");
		barnstedtAlphabetischPanel.setVisible(false);
		vehiclePanel.add(barnstedtAlphabetischPanel, "hidemode 3, cell 0 0");

		final JPanel deutschEvernAlphabetischPanel = new JPanel();
		deutschEvernAlphabetischPanel.setMinimumSize(new Dimension(100, 10));
		deutschEvernAlphabetischPanel.setLayout(new MigLayout("wrap 1"));
		deutschEvernAlphabetischPanel.add(new JLabel("Deutsch Evern"));
		deutschEvernAlphabetischPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Deutsch_Evern.png")));
		Checkbox lf8AlphabetischDE = new Checkbox("18-43-20");
		lf8AlphabetischDE.addItemListener(this);
		deutschEvernAlphabetischPanel.add(lf8AlphabetischDE);
		Checkbox tlfAlphabetischDE = new Checkbox("18-24-20");
		tlfAlphabetischDE.addItemListener(this);
		deutschEvernAlphabetischPanel.add(tlfAlphabetischDE);
		mtwAlphabetischDE = new Checkbox("18-17-20");
		mtwAlphabetischDE.setEnabled(false);
		mtwAlphabetischDE.addItemListener(this);
		deutschEvernAlphabetischPanel.add(mtwAlphabetischDE);
		deutschEvernAlphabetischPanel.add(new JLabel(" "), "wrap 8");
		deutschEvernAlphabetischPanel.setVisible(false);
		vehiclePanel.add(deutschEvernAlphabetischPanel, "hidemode 3, cell 1 0");

		final JPanel embsenAlphabetischPanel = new JPanel();
		embsenAlphabetischPanel.setMinimumSize(new Dimension(100, 10));
		embsenAlphabetischPanel.setLayout(new MigLayout("wrap 1"));
		embsenAlphabetischPanel.add(new JLabel("Embsen"));
		embsenAlphabetischPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Embsen.png")));
		Checkbox rw1AlphabetischEmbsen = new Checkbox("18-51-30");
		rw1AlphabetischEmbsen.addItemListener(this);
		embsenAlphabetischPanel.add(rw1AlphabetischEmbsen);
		Checkbox lfAlphabetischEmbsen = new Checkbox("18-47-30");
		lfAlphabetischEmbsen.addItemListener(this);
		embsenAlphabetischPanel.add(lfAlphabetischEmbsen);
		mtwAlphabetischEmbsen = new Checkbox("18-17-30");
		mtwAlphabetischEmbsen.setEnabled(false);
		mtwAlphabetischEmbsen.addItemListener(this);
		embsenAlphabetischPanel.add(mtwAlphabetischEmbsen);
		embsenAlphabetischPanel.add(new JLabel(" "), "wrap 8");
		embsenAlphabetischPanel.setVisible(false);
		vehiclePanel.add(embsenAlphabetischPanel, "hidemode 3, cell 2 0");

		final JPanel kolkhagenAlphabetischPanel = new JPanel();
		kolkhagenAlphabetischPanel.setMinimumSize(new Dimension(100, 10));
		kolkhagenAlphabetischPanel.setLayout(new MigLayout("wrap 1"));
		kolkhagenAlphabetischPanel.add(new JLabel("Kolkhagen"));
		kolkhagenAlphabetischPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Kolkhagen.png")));
		Checkbox lf8AlphabetischKolkhagen = new Checkbox("18-43-42");
		lf8AlphabetischKolkhagen.addItemListener(this);
		kolkhagenAlphabetischPanel.add(lf8AlphabetischKolkhagen);
		kolkhagenAlphabetischPanel.add(new JLabel(" "), "wrap 32");
		kolkhagenAlphabetischPanel.add(new JLabel(" "), "wrap 10");
		kolkhagenAlphabetischPanel.setVisible(false);
		vehiclePanel.add(kolkhagenAlphabetischPanel, "hidemode 3, cell 3 0");

		final JPanel melbeckAlphabetischPanel = new JPanel();
		melbeckAlphabetischPanel.setMinimumSize(new Dimension(100, 10));
		melbeckAlphabetischPanel.setLayout(new MigLayout("wrap 1"));
		melbeckAlphabetischPanel.add(new JLabel("Melbeck"));
		melbeckAlphabetischPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Melbeck.png")));
		Checkbox tlfAlphabetischMelbeck = new Checkbox("18-47-10");
		tlfAlphabetischMelbeck.addItemListener(this);
		melbeckAlphabetischPanel.add(tlfAlphabetischMelbeck);
		Checkbox lf16AlphabetischMelbeck = new Checkbox("18-44-10");
		lf16AlphabetischMelbeck.addItemListener(this);
		melbeckAlphabetischPanel.add(lf16AlphabetischMelbeck);
		Checkbox lf8AlphabtischMelbeck = new Checkbox("18-43-10");
		lf8AlphabtischMelbeck.addItemListener(this);
		melbeckAlphabetischPanel.add(lf8AlphabtischMelbeck);
		mtwAlphabetischMelbeck = new Checkbox("18-17-10");
		mtwAlphabetischMelbeck.setEnabled(false);
		mtwAlphabetischMelbeck.addItemListener(this);
		melbeckAlphabetischPanel.add(mtwAlphabetischMelbeck);
		melbeckAlphabetischPanel.setVisible(false);
		vehiclePanel.add(melbeckAlphabetischPanel, "hidemode 3, cell 4 0");

		final JPanel oerzenAlphabetischPanel = new JPanel();
		oerzenAlphabetischPanel.setMinimumSize(new Dimension(100, 10));
		oerzenAlphabetischPanel.setLayout(new MigLayout("wrap 1"));
		oerzenAlphabetischPanel.add(new JLabel("Oerzen"));
		oerzenAlphabetischPanel.add(new JLabel(new ImageIcon(
				"res/Wappen_Embsen.png")));
		Checkbox tlfAlphabetischOerzen = new Checkbox("18-47-32");
		tlfAlphabetischOerzen.addItemListener(this);
		oerzenAlphabetischPanel.add(tlfAlphabetischOerzen);
		Checkbox tsfAlphabetischOerzen = new Checkbox("18-40-32");
		tsfAlphabetischOerzen.addItemListener(this);
		oerzenAlphabetischPanel.add(tsfAlphabetischOerzen);
		mtwAlphabetischOerzen = new Checkbox("18-17-32");
		mtwAlphabetischOerzen.setEnabled(false);
		mtwAlphabetischOerzen.addItemListener(this);
		oerzenAlphabetischPanel.add(mtwAlphabetischOerzen);
		oerzenAlphabetischPanel.add(new JLabel(" "), "wrap 8");
		oerzenAlphabetischPanel.setVisible(false);
		vehiclePanel.add(oerzenAlphabetischPanel, "hidemode 3, cell 5 0");

		JPanel queuePanel = new JPanel();
		JPanel label = new JPanel();
		label.add(new JLabel("sortiert: "));
		queuePanel.add(label);
		vehiclePanel.add(queuePanel, "cell 0 1");

		tlfMelbeckQueuePanel = new JPanel();
		tlfMelbeckQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfMelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfMelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		JLabel label_18 = new JLabel("18-47-10");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tlfMelbeckQueuePanel.add(label_18);
		Button tlfMelbeckQueue = new Button();
		tlfMelbeckQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfMelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfMelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		tlfMelbeckQueue.setForeground(Color.GRAY);
		tlfMelbeckQueuePanel.add(tlfMelbeckQueue);
		label_1 = new JLabel();
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		tlfMelbeckQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tlfMelbeck");
			}
		});
		tlfMelbeckQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tlfMelbeckIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-47-10 zur Funkfahrübung anmelden?",
							"", "tlfMelbeckIndex", "18-47-10", tlfMelbeckIndex);
				} else if (tlfMelbeckIndex >= 1 && tlfMelbeckIndex <= 6) {
					setLabel(reader.getKoordinaten184710()[tlfMelbeckIndex],
							reader.getBemerkung184710()[tlfMelbeckIndex],
							reader.getFragen184710()[tlfMelbeckIndex],
							reader.getAntworten184710()[tlfMelbeckIndex],
							"tlfMelbeckIndex", "18-47-10", tlfMelbeckIndex);
				} else if (tlfMelbeckIndex > 6) {
					if (tlfMelbeckIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"tlfMelbeckIndex", "18-47-10", tlfMelbeckIndex);
					} else {
						tlfMelbeckIndex = 8;
						setLabel("", "", "", "", "tlfMelbeckIndex", "18-47-10",
								tlfMelbeckIndex);
					}
				}
				if (label_1.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		tlfMelbeckQueuePanel.add(label_1);
		tlfMelbeckQueuePanel.setVisible(false);
		vehiclePanel.add(tlfMelbeckQueuePanel, "hidemode 3");

		lf16MelbeckQueuePanel = new JPanel();
		lf16MelbeckQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf16MelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf16MelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (lf16MelbeckIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-44-10 zur Funkfahrübung anmelden?",
							"", "lf16MelbeckIndex", "18-44-10",
							lf16MelbeckIndex);
				} else if (lf16MelbeckIndex >= 1 && lf16MelbeckIndex <= 6) {
					setLabel(reader.getKoordinaten184410()[lf16MelbeckIndex],
							reader.getBemerkung184410()[lf16MelbeckIndex],
							reader.getFragen184410()[lf16MelbeckIndex],
							reader.getAntworten184410()[lf16MelbeckIndex],
							"lf16MelbeckIndex", "18-44-10", lf16MelbeckIndex);
				} else if (lf16MelbeckIndex > 6) {
					if (lf16MelbeckIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"lf16MelbeckIndex", "18-44-10",
								lf16MelbeckIndex);
					} else {
						setLabel("", "", "", "", "lf16MelbeckIndex",
								"18-44-10", lf16MelbeckIndex);
					}
				}
				if (label_2.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_19 = new JLabel("18-44-10");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lf16MelbeckQueuePanel.add(label_19);
		Button lf16MelbeckQueue = new Button();
		label_2 = new JLabel();
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lf16MelbeckQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("lf16Melbeck");
			}
		});
		lf16MelbeckQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf16MelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf16MelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		lf16MelbeckQueuePanel.add(lf16MelbeckQueue);
		lf16MelbeckQueuePanel.add(label_2);
		lf16MelbeckQueuePanel.setVisible(false);
		vehiclePanel.add(lf16MelbeckQueuePanel, "hidemode 3");

		lf8MelbeckQueuePanel = new JPanel();
		lf8MelbeckQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf8MelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf8MelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (lf8MelbeckIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-43-10 zur Funkfahrübung anmelden?",
							"", "lf8MelbeckIndex", "18-43-10", lf8MelbeckIndex);
				} else if (lf8MelbeckIndex >= 1 && lf8MelbeckIndex <= 6) {
					setLabel(reader.getKoordinaten184310()[lf8MelbeckIndex],
							reader.getBemerkung184310()[lf8MelbeckIndex],
							reader.getFragen184310()[lf8MelbeckIndex],
							reader.getAntworten184310()[lf8MelbeckIndex],
							"lf8MelbeckIndex", "18-43-10", lf8MelbeckIndex);
				} else if (lf8MelbeckIndex > 6) {
					if (lf8MelbeckIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"lf8MelbeckIndex", "18-43-10", lf8MelbeckIndex);
					} else {
						setLabel("", "", "", "", "lf8MelbeckIndex", "18-43-10",
								lf8MelbeckIndex);
					}
				}
				if (label_3.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_20 = new JLabel("18-43-10");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lf8MelbeckQueuePanel.add(label_20);
		Button lf8MelbeckQueue = new Button();
		label_3 = new JLabel();
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lf8MelbeckQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("lf8Melbeck");
			}
		});
		lf8MelbeckQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf8MelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf8MelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		lf8MelbeckQueuePanel.add(lf8MelbeckQueue);
		lf8MelbeckQueuePanel.add(label_3);
		lf8MelbeckQueuePanel.setVisible(false);
		vehiclePanel.add(lf8MelbeckQueuePanel, "hidemode 3");

		mtwMelbeckQueuePanel = new JPanel();
		mtwMelbeckQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwMelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwMelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (mtwMelbeckIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-17-10 zur Funkfahrübung anmelden?",
							"", "mtwMelbeckIndex", "18-17-10", mtwMelbeckIndex);
				} else if (mtwMelbeckIndex >= 1 && mtwMelbeckIndex <= 6) {
					setLabel(reader.getKoordinaten181710()[mtwMelbeckIndex],
							reader.getBemerkung181710()[mtwMelbeckIndex],
							reader.getFragen181710()[mtwMelbeckIndex],
							reader.getAntworten181710()[mtwMelbeckIndex],
							"mtwMelbeckIndex", "18-17-10", mtwMelbeckIndex);
				} else if (mtwMelbeckIndex > 6) {
					if (mtwMelbeckIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"mtwMelbeckIndex", "18-17-10", mtwMelbeckIndex);
					} else {
						setLabel("", "", "", "", "mtwMelbeckIndex", "18-17-10",
								mtwMelbeckIndex);
					}
				}
				if (label_4.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_30 = new JLabel("18-17-10");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mtwMelbeckQueuePanel.add(label_30);
		Button mtwMelbeckQueue = new Button();
		label_4 = new JLabel();
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		mtwMelbeckQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("mtwMelbeck");
			}
		});
		mtwMelbeckQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwMelbeckQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwMelbeckQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		mtwMelbeckQueuePanel.add(mtwMelbeckQueue);
		mtwMelbeckQueuePanel.add(label_4);
		mtwMelbeckQueuePanel.setVisible(false);
		vehiclePanel.add(mtwMelbeckQueuePanel, "hidemode 3");

		lf8DeutschEvernQueuePanel = new JPanel();
		lf8DeutschEvernQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf8DeutschEvernQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf8DeutschEvernQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (lf8DeutschEvernIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-43-20 zur Funkfahrübung anmelden?",
							"", "lf8DeutschEvernIndex", "18-43-20",
							lf8DeutschEvernIndex);
				} else if (lf8DeutschEvernIndex >= 1
						&& lf8DeutschEvernIndex <= 6) {
					setLabel(
							reader.getKoordinaten184320()[lf8DeutschEvernIndex],
							reader.getBemerkung184320()[lf8DeutschEvernIndex],
							reader.getFragen184320()[lf8DeutschEvernIndex],
							reader.getAntworten184320()[lf8DeutschEvernIndex],
							"lf8DeutschEvernIndex", "18-43-20",
							lf8DeutschEvernIndex);
				} else if (lf8DeutschEvernIndex > 6) {
					if (lf8DeutschEvernIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"lf8DeutschEvernIndex", "18-43-20",
								lf8DeutschEvernIndex);
					} else {
						setLabel("", "", "", "", "lf8DeutschEvernIndex",
								"18-43-20", lf8DeutschEvernIndex);
					}
				}
				if (label_5.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_31 = new JLabel("18-43-20");
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lf8DeutschEvernQueuePanel.add(label_31);
		Button lf8DeutschEvernQueue = new Button();
		label_5 = new JLabel();
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lf8DeutschEvernQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("lf8De");
			}
		});
		lf8DeutschEvernQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf8DeutschEvernQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf8DeutschEvernQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		lf8DeutschEvernQueuePanel.add(lf8DeutschEvernQueue);
		lf8DeutschEvernQueuePanel.add(label_5);
		lf8DeutschEvernQueuePanel.setVisible(false);
		vehiclePanel.add(lf8DeutschEvernQueuePanel, "hidemode 3");

		tlfDeutschEvernQueuePanel = new JPanel();
		tlfDeutschEvernQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfDeutschEvernQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfDeutschEvernQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tlfDeutschEvernIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-24-20 zur Funkfahrübung anmelden?",
							"", "tlfDeutschEvernIndex", "18-24-20",
							tlfDeutschEvernIndex);
				} else if (tlfDeutschEvernIndex >= 1
						&& tlfDeutschEvernIndex <= 6) {
					setLabel(
							reader.getKoordinaten182420()[tlfDeutschEvernIndex],
							reader.getBemerkung182420()[tlfDeutschEvernIndex],
							reader.getFragen182420()[tlfDeutschEvernIndex],
							reader.getAntworten182420()[tlfDeutschEvernIndex],
							"tlfDeutschEvernIndex", "18-24-20",
							tlfDeutschEvernIndex);
				} else if (tlfDeutschEvernIndex > 6) {
					if (tlfDeutschEvernIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"tlfDeutschEvernIndex", "18-24-20",
								tlfDeutschEvernIndex);
					} else {
						setLabel("", "", "", "", "tlfDeutschEvernIndex",
								"18-24-20", tlfDeutschEvernIndex);
					}
				}

				if (label_6.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		tlfDeutschEvernQueuePanel.setMinimumSize(new Dimension(40, 10));
		JLabel label_21 = new JLabel("18-24-20");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tlfDeutschEvernQueuePanel.add(label_21);
		Button tlfDeutschEvernQueue = new Button();
		label_6 = new JLabel();
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		tlfDeutschEvernQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tlfDe");
			}
		});
		tlfDeutschEvernQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfDeutschEvernQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfDeutschEvernQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		tlfDeutschEvernQueuePanel.add(tlfDeutschEvernQueue);
		tlfDeutschEvernQueuePanel.add(label_6);
		tlfDeutschEvernQueuePanel.setVisible(false);
		vehiclePanel.add(tlfDeutschEvernQueuePanel, "hidemode 3");

		mtwDeutschEvernQueuePanel = new JPanel();
		mtwDeutschEvernQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwDeutschEvernQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwDeutschEvernQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (mtwDeutschEvernIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-17-20 zur Funkfahrübung anmelden?",
							"", "mtwDeutschEvernIndex", "18-17-20",
							mtwDeutschEvernIndex);
				} else if (mtwDeutschEvernIndex >= 1
						&& mtwDeutschEvernIndex <= 6) {
					setLabel(
							reader.getKoordinaten181720()[mtwDeutschEvernIndex],
							reader.getBemerkung181720()[mtwDeutschEvernIndex],
							reader.getFragen181720()[mtwDeutschEvernIndex],
							reader.getAntworten181720()[mtwDeutschEvernIndex],
							"mtwDeutschEvernIndex", "18-17-20",
							mtwDeutschEvernIndex);
				} else if (mtwDeutschEvernIndex > 6) {
					if (mtwDeutschEvernIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"mtwDeutschEvernIndex", "18-17-20",
								mtwDeutschEvernIndex);
					} else {
						setLabel("", "", "", "", "mtwDeutschEvernIndex",
								"18-17-20", mtwDeutschEvernIndex);
					}
				}

				if (label_7.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_22 = new JLabel("18-17-20");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mtwDeutschEvernQueuePanel.add(label_22);
		Button mtwDeutschEvernQueue = new Button();
		label_7 = new JLabel();
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		mtwDeutschEvernQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("mtwDe");
			}
		});
		mtwDeutschEvernQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwDeutschEvernQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwDeutschEvernQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		mtwDeutschEvernQueuePanel.add(mtwDeutschEvernQueue);
		mtwDeutschEvernQueuePanel.add(label_7);
		mtwDeutschEvernQueuePanel.setVisible(false);
		vehiclePanel.add(mtwDeutschEvernQueuePanel, "hidemode 3");

		rw1EmbsenQueuePanel = new JPanel();
		rw1EmbsenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rw1EmbsenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rw1EmbsenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rwEmbsenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-51-30 zur Funkfahrübung anmelden?",
							"", "rwEmbsenIndex", "18-51-30", rwEmbsenIndex);
				} else if (rwEmbsenIndex >= 1 && rwEmbsenIndex <= 6) {
					setLabel(reader.getKoordinaten185130()[rwEmbsenIndex],
							reader.getBemerkung185130()[rwEmbsenIndex],
							reader.getFragen185130()[rwEmbsenIndex],
							reader.getAntworten185130()[rwEmbsenIndex],
							"rwEmbsenIndex", "18-51-30", rwEmbsenIndex);
				} else if (rwEmbsenIndex > 6) {
					if (rwEmbsenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"rwEmbsenIndex", "18-51-30", rwEmbsenIndex);
					} else {
						setLabel("", "", "", "", "rwEmbsenIndex", "18-51-30",
								rwEmbsenIndex);
					}
				}
				if (label_8.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_23 = new JLabel("18-51-30");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rw1EmbsenQueuePanel.add(label_23);
		Button rw1EmbsenQueue = new Button();
		label_8 = new JLabel();
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		rw1EmbsenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("rwEmbsen");
			}
		});
		rw1EmbsenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rw1EmbsenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rw1EmbsenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		rw1EmbsenQueuePanel.add(rw1EmbsenQueue);
		rw1EmbsenQueuePanel.add(label_8);
		rw1EmbsenQueuePanel.setVisible(false);
		vehiclePanel.add(rw1EmbsenQueuePanel, "hidemode 3");

		lfEmbsenQueuePanel = new JPanel();
		lfEmbsenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lfEmbsenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lfEmbsenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (lfEmbsenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-47-30 zur Funkfahrübung anmelden?",
							"", "lfEmbsenIndex", "18-47-30", lfEmbsenIndex);
				} else if (lfEmbsenIndex >= 1 && lfEmbsenIndex <= 6) {
					setLabel(reader.getKoordinaten184730()[lfEmbsenIndex],
							reader.getBemerkung184730()[lfEmbsenIndex],
							reader.getFragen184730()[lfEmbsenIndex],
							reader.getAntworten184730()[lfEmbsenIndex],
							"lfEmbsenIndex", "18-47-30", lfEmbsenIndex);
				} else if (lfEmbsenIndex > 6) {
					if (lfEmbsenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"lfEmbsenIndex", "18-47-30", lfEmbsenIndex);
					} else {
						setLabel("", "", "", "", "lfEmbsenIndex", "18-47-30",
								lfEmbsenIndex);
					}
				}

				if (label_9.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_24 = new JLabel("18-47-30");
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lfEmbsenQueuePanel.add(label_24);
		Button tlfEmbsenQueue = new Button();
		label_9 = new JLabel();
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		tlfEmbsenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tlfEmbsen");
			}
		});
		tlfEmbsenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lfEmbsenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lfEmbsenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		lfEmbsenQueuePanel.add(tlfEmbsenQueue);
		lfEmbsenQueuePanel.add(label_9);
		lfEmbsenQueuePanel.setVisible(false);
		vehiclePanel.add(lfEmbsenQueuePanel, "hidemode 3");

		mtwEmbsenQueuePanel = new JPanel();
		mtwEmbsenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwEmbsenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwEmbsenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (mtwEmbsenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-17-30 zur Funkfahrübung anmelden?",
							"", "mtwEmbsenIndex", "18-17-30", mtwEmbsenIndex);
				} else if (mtwEmbsenIndex >= 1 && mtwEmbsenIndex <= 6) {
					setLabel(reader.getKoordinaten181730()[mtwEmbsenIndex],
							reader.getBemerkung181730()[mtwEmbsenIndex],
							reader.getFragen181730()[mtwEmbsenIndex],
							reader.getAntworten181730()[mtwEmbsenIndex],
							"mtwEmbsenIndex", "18-17-30", mtwEmbsenIndex);
				} else if (mtwEmbsenIndex > 6) {
					if (mtwEmbsenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"mtwEmbsenIndex", "18-17-30", mtwEmbsenIndex);
					} else {
						setLabel("", "", "", "", "mtwEmbsenIndex", "18-17-30",
								mtwEmbsenIndex);
					}
				}
				if (label_10.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_32 = new JLabel("18-17-30");
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mtwEmbsenQueuePanel.add(label_32);
		Button mtwEmbsenQueue = new Button();
		label_10 = new JLabel();
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		mtwEmbsenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("mtwEmbsen");
			}
		});
		mtwEmbsenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwEmbsenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwEmbsenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		mtwEmbsenQueuePanel.add(mtwEmbsenQueue);
		mtwEmbsenQueuePanel.add(label_10);
		mtwEmbsenQueuePanel.setVisible(false);
		vehiclePanel.add(mtwEmbsenQueuePanel, "hidemode 3");

		tlfOerzenQueuePanel = new JPanel();
		tlfOerzenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfOerzenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfOerzenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tlfOerzenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-47-32 zur Funkfahrübung anmelden?",
							"", "tlfOerzenIndex", "18-47-32", tlfOerzenIndex);
				} else if (tlfOerzenIndex >= 1 && tlfOerzenIndex <= 6) {
					setLabel(reader.getKoordinaten184732()[tlfOerzenIndex],
							reader.getBemerkung184732()[tlfOerzenIndex],
							reader.getFragen184732()[tlfOerzenIndex],
							reader.getAntworten184732()[tlfOerzenIndex],
							"tlfOerzenIndex", "18-47-32", tlfOerzenIndex);
				} else if (tlfOerzenIndex > 6) {
					if (tlfOerzenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"tlfOerzenIndex", "18-47-32", tlfOerzenIndex);
					} else {
						setLabel("", "", "", "", "tlfOerzenIndex", "18-47-32",
								tlfOerzenIndex);
					}
				}
				if (label_11.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_33 = new JLabel("18-47-32");
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tlfOerzenQueuePanel.add(label_33);
		Button tlfOerzenQueue = new Button();
		label_11 = new JLabel();
		label_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		tlfOerzenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tlfOerzen");
			}
		});
		tlfOerzenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfOerzenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfOerzenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		tlfOerzenQueuePanel.add(tlfOerzenQueue);
		tlfOerzenQueuePanel.add(label_11);

		tlfOerzenQueuePanel.setVisible(false);
		vehiclePanel.add(tlfOerzenQueuePanel, "hidemode 3");

		tsfOerzenQueuePanel = new JPanel();
		tsfOerzenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tsfOerzenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tsfOerzenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tsfOerzenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-40-32 zur Funkfahrübung anmelden?",
							"", "tsfOerzenIndex", "18-40-32", tsfOerzenIndex);
				} else if (tsfOerzenIndex >= 1 && tsfOerzenIndex <= 6) {
					setLabel(reader.getKoordinaten184032()[tsfOerzenIndex],
							reader.getBemerkung184032()[tsfOerzenIndex],
							reader.getFragen184032()[tsfOerzenIndex],
							reader.getAntworten184032()[tsfOerzenIndex],
							"tsfOerzenIndex", "18-40-32", tsfOerzenIndex);
				} else if (tsfOerzenIndex > 6) {
					if (tsfOerzenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"tsfOerzenIndex", "18-40-32", tsfOerzenIndex);
					} else {
						setLabel("", "", "", "", "tsfOerzenIndex", "18-40-32",
								tsfOerzenIndex);
					}
				}
				if (label_12.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_25 = new JLabel("18-40-32");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tsfOerzenQueuePanel.add(label_25);
		Button tsfOerzenQueue = new Button();
		label_12 = new JLabel();
		label_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		tsfOerzenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tsfOerzen");
			}
		});
		tsfOerzenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tsfOerzenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tsfOerzenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		tsfOerzenQueuePanel.add(tsfOerzenQueue);
		tsfOerzenQueuePanel.add(label_12);
		tsfOerzenQueuePanel.setVisible(false);
		vehiclePanel.add(tsfOerzenQueuePanel, "hidemode 3");

		mtwOerzenQueuePanel = new JPanel();
		mtwOerzenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwOerzenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwOerzenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (mtwOerzenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-17-32 zur Funkfahrübung anmelden?",
							"", "mtwOerzenIndex", "18-17-32", mtwOerzenIndex);
				} else if (mtwOerzenIndex >= 1 && mtwOerzenIndex <= 6) {
					setLabel(reader.getKoordinaten181732()[mtwOerzenIndex],
							reader.getBemerkung181732()[mtwOerzenIndex],
							reader.getFragen181732()[mtwOerzenIndex],
							reader.getAntworten181732()[mtwOerzenIndex],
							"mtwOerzenIndex", "18-17-32", mtwOerzenIndex);
				} else if (mtwOerzenIndex > 6) {
					if (mtwOerzenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"mtwOerzenIndex", "18-17-32", mtwOerzenIndex);
					} else {
						setLabel("", "", "", "", "mtwOerzenIndex", "18-17-32",
								mtwOerzenIndex);
					}
				}
				if (label_13.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_26 = new JLabel("18-17-32");
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mtwOerzenQueuePanel.add(label_26);
		Button mtwOerzenQueue = new Button();
		label_13 = new JLabel();
		label_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		mtwOerzenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("mtwOerzen");
			}
		});
		mtwOerzenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwOerzenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwOerzenQueuePanel.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		mtwOerzenQueuePanel.add(mtwOerzenQueue);
		mtwOerzenQueuePanel.add(label_13);
		mtwOerzenQueuePanel.setVisible(false);
		vehiclePanel.add(mtwOerzenQueuePanel, "hidemode 3");

		tlfBarnstedtQueuePanel = new JPanel();
		tlfBarnstedtQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfBarnstedtQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfBarnstedtQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tlfBarnstedtIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-47-40 zur Funkfahrübung anmelden?",
							"", "tlfBarnstedtIndex", "18-47-40",
							tlfBarnstedtIndex);
				} else if (tlfBarnstedtIndex >= 1 && tlfBarnstedtIndex <= 6) {
					setLabel(reader.getKoordinaten184740()[tlfBarnstedtIndex],
							reader.getBemerkung184740()[tlfBarnstedtIndex],
							reader.getFragen184740()[tlfBarnstedtIndex],
							reader.getAntworten184740()[tlfBarnstedtIndex],
							"tlfBarnstedtIndex", "18-47-40", tlfBarnstedtIndex);
				} else if (tlfBarnstedtIndex > 6) {
					if (tlfBarnstedtIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"tlfBarnstedtIndex", "18-47-40",
								tlfBarnstedtIndex);
					} else {
						setLabel("", "", "", "", "tlfBarnstedtIndex",
								"18-47-40", tlfBarnstedtIndex);
					}
				}
				if (label_14.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_27 = new JLabel("18-47-40");
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tlfBarnstedtQueuePanel.add(label_27);
		Button tlfBarnstedtQueue = new Button();
		label_14 = new JLabel();
		label_14.setFont(new Font("Tahoma", Font.BOLD, 13));
		tlfBarnstedtQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tlfBarnstedt");
			}
		});
		tlfBarnstedtQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tlfBarnstedtQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tlfBarnstedtQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		tlfBarnstedtQueuePanel.add(tlfBarnstedtQueue);
		tlfBarnstedtQueuePanel.add(label_14);
		tlfBarnstedtQueuePanel.setVisible(false);
		vehiclePanel.add(tlfBarnstedtQueuePanel, "hidemode 3");

		tsfBarnstedtQueuePanel = new JPanel();
		tsfBarnstedtQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tsfBarnstedtQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tsfBarnstedtQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tsfBarnstedtIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-40-40 zur Funkfahrübung anmelden?",
							"", "tsfBarnstedtIndex", "18-40-40",
							tsfBarnstedtIndex);
				} else if (tsfBarnstedtIndex >= 1 && tsfBarnstedtIndex <= 6) {
					setLabel(reader.getKoordinaten184040()[tsfBarnstedtIndex],
							reader.getBemerkung184040()[tsfBarnstedtIndex],
							reader.getFragen184040()[tsfBarnstedtIndex],
							reader.getAntworten184040()[tsfBarnstedtIndex],
							"tsfBarnstedtIndex", "18-40-40", tsfBarnstedtIndex);
				} else if (tsfBarnstedtIndex > 6) {
					if (tsfBarnstedtIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"tsfBarnstedtIndex", "18-40-40",
								tsfBarnstedtIndex);
					} else {
						setLabel("", "", "", "", "tsfBarnstedtIndex",
								"18-40-40", tsfBarnstedtIndex);
					}
				}
				if (label_15.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_28 = new JLabel("18-40-40");
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tsfBarnstedtQueuePanel.add(label_28);
		Button tsfBarnstedtQueue = new Button();
		label_15 = new JLabel();
		label_15.setFont(new Font("Tahoma", Font.BOLD, 13));
		tsfBarnstedtQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("tsfBarnstedt");
			}
		});
		tsfBarnstedtQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tsfBarnstedtQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tsfBarnstedtQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		tsfBarnstedtQueuePanel.add(tsfBarnstedtQueue);
		tsfBarnstedtQueuePanel.add(label_15);
		tsfBarnstedtQueuePanel.setVisible(false);
		vehiclePanel.add(tsfBarnstedtQueuePanel, "hidemode 3");

		mtwBarnstedtQueuePanel = new JPanel();
		mtwBarnstedtQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwBarnstedtQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwBarnstedtQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (mtwBarnstedtIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-17-40 zur Funkfahrübung anmelden?",
							"", "mtwBarnstedtIndex", "18-17-40",
							mtwBarnstedtIndex);
				} else if (mtwBarnstedtIndex >= 1 && mtwBarnstedtIndex <= 6) {
					setLabel(reader.getKoordinaten181740()[mtwBarnstedtIndex],
							reader.getBemerkung181740()[mtwBarnstedtIndex],
							reader.getFragen181740()[mtwBarnstedtIndex],
							reader.getAntworten181740()[mtwBarnstedtIndex],
							"mtwBarnstedtIndex", "18-17-40", mtwBarnstedtIndex);
				} else if (mtwBarnstedtIndex > 6) {
					if (mtwBarnstedtIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"mtwBarnstedtIndex", "18-17-40",
								mtwBarnstedtIndex);
					} else {
						setLabel("", "", "", "", "mtwBarnstedtIndex",
								"18-17-40", mtwBarnstedtIndex);
					}
				}
				if (label_16.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		JLabel label_29 = new JLabel("18-17-40");
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mtwBarnstedtQueuePanel.add(label_29);
		Button mtwBarnstedtQueue = new Button();
		label_16 = new JLabel();
		label_16.setFont(new Font("Tahoma", Font.BOLD, 13));
		mtwBarnstedtQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("mtwBarnstedt");
			}
		});
		mtwBarnstedtQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mtwBarnstedtQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtwBarnstedtQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		mtwBarnstedtQueuePanel.add(mtwBarnstedtQueue);
		mtwBarnstedtQueuePanel.add(label_16);
		mtwBarnstedtQueuePanel.setVisible(false);
		vehiclePanel.add(mtwBarnstedtQueuePanel, "hidemode 3");

		lf8KolkhagenQueuePanel = new JPanel();
		JLabel label_34 = new JLabel("18-43-42");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lf8KolkhagenQueuePanel.add(label_34);
		Button lf8KolkhagenQueue = new Button();
		label_17 = new JLabel();
		label_17.setFont(new Font("Tahoma", Font.BOLD, 13));
		lf8KolkhagenQueue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queueWaitingList++;
				inkrementQueueWaiting("lf8Kolkhagen");
			}
		});
		lf8KolkhagenQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf8KolkhagenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf8KolkhagenQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}
		});
		lf8KolkhagenQueuePanel.add(lf8KolkhagenQueue);
		lf8KolkhagenQueuePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lf8KolkhagenQueuePanel.setBorder(BorderFactory
						.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lf8KolkhagenQueuePanel.setBorder(BorderFactory
						.createEmptyBorder());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (lf8KolkhagenIndex == 0) {
					btnRichtig.setText("Anmelden");
					setLabel(
							"",
							"",
							"Wollen Sie Florian Lüneburg 18-43-42 zur Funkfahrübung anmelden?",
							"", "lf8KolkhagenIndex", "18-43-42",
							lf8KolkhagenIndex);
				} else if (lf8KolkhagenIndex >= 1 && lf8KolkhagenIndex <= 6) {
					setLabel(reader.getKoordinaten184342()[lf8KolkhagenIndex],
							reader.getBemerkung184342()[lf8KolkhagenIndex],
							reader.getFragen184342()[lf8KolkhagenIndex],
							reader.getAntworten184342()[lf8KolkhagenIndex],
							"lf8KolkhagenIndex", "18-43-42", lf8KolkhagenIndex);
				} else if (lf8KolkhagenIndex > 6) {
					if (lf8KolkhagenIndex == 7) {
						btnRichtig.setText("Abmelden");
						setLabel(reader.getSammelplatzKoordinaten()[1], "",
								reader.getSammelplatz()[1], "",
								"lf8KolkhagenIndex", "18-43-42",
								lf8KolkhagenIndex);
					} else {
						setLabel("", "", "", "", "lf8KolkhagenIndex",
								"18-43-42", lf8KolkhagenIndex);
					}
				}
				if (label_17.getText() != "") {
					dekrementQueueWaiting();
				}
			}
		});
		lf8KolkhagenQueuePanel.add(label_17);
		lf8KolkhagenQueuePanel.setVisible(false);
		vehiclePanel.add(lf8KolkhagenQueuePanel, "hidemode 3");

		final JFrame frame = new JFrame("Funkfahrübung by ©S.Kunz");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().add(vehiclePanel, BorderLayout.WEST);

		JLabel colorImage = new JLabel(new ImageIcon("res/gradient.png"));
		vehiclePanel.add(colorImage, "hidemode 3, cell 0 5, span");

		JPanel carPanel = new JPanel();
		JPanel auto = new JPanel();
		auto.add(new JLabel("Auto: "));
		carPanel.add(auto);
		vehiclePanel.add(carPanel, "cell 0 6");

		lblAuto = new JLabel();
		lblAuto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		vehiclePanel.add(lblAuto, "cell 1 6 5 1");

		JPanel taskPanel = new JPanel();
		JPanel koordinate = new JPanel();
		koordinate.add(new JLabel("Koordinate: "));
		taskPanel.add(koordinate);
		vehiclePanel.add(taskPanel, "cell 0 7");

		lblKoordinaten = new JLabel();
		lblKoordinaten.setFont(new Font("Tahoma", Font.BOLD, 22));

		if (lblKoordinaten.getText().startsWith("ND")) {
			lblKoordinaten.setToolTipText("Nordpol Dora");
		} else if (lblKoordinaten.getText().startsWith("PE")) {
			lblKoordinaten.setToolTipText("Paula Emil");
		} else if (lblKoordinaten.getText().startsWith("NE")) {
			lblKoordinaten.setToolTipText("Nordpol Emil");
		} else if (lblKoordinaten.getText().startsWith("PD")) {
			lblKoordinaten.setToolTipText("Paula Dora");
		}

		vehiclePanel.add(lblKoordinaten, "cell 1 7 5 1");

		JPanel taskPanel4 = new JPanel();
		JPanel bemerkung = new JPanel();
		bemerkung.add(new JLabel("Bemerkung: "));
		taskPanel4.add(bemerkung);
		vehiclePanel.add(taskPanel4, "cell 0 8");
		lblBemerkung = new JTextArea();
		lblBemerkung.setLineWrap(true);
		lblBemerkung.setBackground(new Color(240, 240, 240));
		lblBemerkung.setOpaque(true);
		lblBemerkung.setSize(500, 18);
		lblBemerkung.setWrapStyleWord(true);
		lblBemerkung.setEditable(false);
		lblBemerkung.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vehiclePanel.add(lblBemerkung, "cell 1 8 5 1");

		JPanel taskPanel2 = new JPanel();
		JPanel frage = new JPanel();
		frage.add(new JLabel("Frage: "));
		taskPanel2.add(frage);
		vehiclePanel.add(taskPanel2, "cell 0 9");
		lblFrage = new JTextArea();
		lblFrage.setLineWrap(true);
		lblFrage.setBackground(new Color(240, 240, 240));
		lblFrage.setOpaque(true);
		lblFrage.setSize(500, 18);
		lblFrage.setWrapStyleWord(true);
		lblFrage.setEditable(false);
		lblFrage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vehiclePanel.add(lblFrage, "cell 1 9 5 1");

		JPanel taskPanel3 = new JPanel();
		JPanel antwort = new JPanel();
		antwort.add(new JLabel("Antwort: "));
		taskPanel3.add(antwort);
		vehiclePanel.add(taskPanel3, "cell 0 10");
		lblAntwort = new JTextArea();
		lblAntwort.setLineWrap(true);
		lblAntwort.setBackground(new Color(240, 240, 240));
		lblAntwort.setOpaque(true);
		lblAntwort.setSize(500, 18);
		lblAntwort.setWrapStyleWord(true);
		lblAntwort.setEditable(false);
		lblAntwort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vehiclePanel.add(lblAntwort, "cell 1 10 5 1");

		btnRichtig = new JButton("Anmelden");
		btnRichtig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button != null) {
					switch (button) {
					case "tlfMelbeckIndex":
						if (tlfMelbeckIndex < 6) {
							tlfMelbeckIndex++;
							if (tlfMelbeckIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(tlfMelbeckQueuePanel, Color.RED);
							} else if (tlfMelbeckIndex == 2) {
								buttonBack.setText("<<");
								setColor(tlfMelbeckQueuePanel, Color.YELLOW);
							} else if (tlfMelbeckIndex == 3) {
								setColor(tlfMelbeckQueuePanel, Color.GREEN);
							} else if (tlfMelbeckIndex == 4) {
								setColor(tlfMelbeckQueuePanel, Color.CYAN);
							} else if (tlfMelbeckIndex == 5) {
								setColor(tlfMelbeckQueuePanel, Color.ORANGE);
							} else if (tlfMelbeckIndex == 6) {
								setColor(tlfMelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184710()[tlfMelbeckIndex],
									reader.getBemerkung184710()[tlfMelbeckIndex],
									reader.getFragen184710()[tlfMelbeckIndex],
									reader.getAntworten184710()[tlfMelbeckIndex],
									"tlfMelbeckIndex", "18-47-10",
									tlfMelbeckIndex);
						} else {
							tlfMelbeckIndex++;
							if (tlfMelbeckIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"tlfMelbeckIndex", "18-47-10",
										tlfMelbeckIndex);
								setColor(tlfMelbeckQueuePanel, Color.LIGHT_GRAY);
							} else {
								tlfMelbeckIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "tlfMelbeckIndex", "18-47-10",
										tlfMelbeckIndex);
								setColor(tlfMelbeckQueuePanel, Color.BLUE);
							}
						}
						break;
					case "lf16MelbeckIndex":
						if (lf16MelbeckIndex < 6) {
							lf16MelbeckIndex++;
							if (lf16MelbeckIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(lf16MelbeckQueuePanel, Color.RED);
							} else if (lf16MelbeckIndex == 2) {
								buttonBack.setText("<<");
								setColor(lf16MelbeckQueuePanel, Color.YELLOW);
							} else if (lf16MelbeckIndex == 3) {
								setColor(lf16MelbeckQueuePanel, Color.GREEN);
							} else if (lf16MelbeckIndex == 4) {
								setColor(lf16MelbeckQueuePanel, Color.CYAN);
							} else if (lf16MelbeckIndex == 5) {
								setColor(lf16MelbeckQueuePanel, Color.ORANGE);
							} else if (lf16MelbeckIndex == 6) {
								setColor(lf16MelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184410()[lf16MelbeckIndex],
									reader.getBemerkung184410()[lf16MelbeckIndex],
									reader.getFragen184410()[lf16MelbeckIndex],
									reader.getAntworten184410()[lf16MelbeckIndex],
									"lf16MelbeckIndex", "18-44-10",
									lf16MelbeckIndex);
						} else {
							lf16MelbeckIndex++;
							if (lf16MelbeckIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"lf16MelbeckIndex", "18-44-10",
										lf16MelbeckIndex);
								setColor(lf16MelbeckQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								lf16MelbeckIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "lf16MelbeckIndex", "18-44-10",
										lf16MelbeckIndex);
								setColor(lf16MelbeckQueuePanel, Color.BLUE);
							}
						}
						break;
					case "lf8MelbeckIndex":
						if (lf8MelbeckIndex < 6) {
							lf8MelbeckIndex++;
							if (lf8MelbeckIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(lf8MelbeckQueuePanel, Color.RED);
							} else if (lf8MelbeckIndex == 2) {
								buttonBack.setText("<<");
								setColor(lf8MelbeckQueuePanel, Color.YELLOW);
							} else if (lf8MelbeckIndex == 3) {
								setColor(lf8MelbeckQueuePanel, Color.GREEN);
							} else if (lf8MelbeckIndex == 4) {
								setColor(lf8MelbeckQueuePanel, Color.CYAN);
							} else if (lf8MelbeckIndex == 5) {
								setColor(lf8MelbeckQueuePanel, Color.ORANGE);
							} else if (lf8MelbeckIndex == 6) {
								setColor(lf8MelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184310()[lf8MelbeckIndex],
									reader.getBemerkung184310()[lf8MelbeckIndex],
									reader.getFragen184310()[lf8MelbeckIndex],
									reader.getAntworten184310()[lf8MelbeckIndex],
									"lf8MelbeckIndex", "18-43-10",
									lf8MelbeckIndex);
						} else {
							lf8MelbeckIndex++;
							if (lf8MelbeckIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"lf8MelbeckIndex", "18-43-10",
										lf8MelbeckIndex);
								setColor(lf8MelbeckQueuePanel, Color.LIGHT_GRAY);
							} else {
								lf8MelbeckIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "lf8MelbeckIndex", "18-43-10",
										lf8MelbeckIndex);
								setColor(lf8MelbeckQueuePanel, Color.BLUE);
							}
						}
						break;
					case "mtwMelbeckIndex":
						if (mtwMelbeckIndex < 6) {
							mtwMelbeckIndex++;
							if (mtwMelbeckIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(mtwMelbeckQueuePanel, Color.RED);
							} else if (mtwMelbeckIndex == 2) {
								buttonBack.setText("<<");
								setColor(mtwMelbeckQueuePanel, Color.YELLOW);
							} else if (mtwMelbeckIndex == 3) {
								setColor(mtwMelbeckQueuePanel, Color.GREEN);
							} else if (mtwMelbeckIndex == 4) {
								setColor(mtwMelbeckQueuePanel, Color.CYAN);
							} else if (mtwMelbeckIndex == 5) {
								setColor(mtwMelbeckQueuePanel, Color.ORANGE);
							} else if (mtwMelbeckIndex == 6) {
								setColor(mtwMelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181710()[mtwMelbeckIndex],
									reader.getBemerkung181710()[mtwMelbeckIndex],
									reader.getFragen181710()[mtwMelbeckIndex],
									reader.getAntworten181710()[mtwMelbeckIndex],
									"mtwMelbeckIndex", "18-17-10",
									mtwMelbeckIndex);
						} else {
							mtwMelbeckIndex++;
							if (mtwMelbeckIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"mtwMelbeckIndex", "18-17-10",
										mtwMelbeckIndex);
								setColor(mtwMelbeckQueuePanel, Color.LIGHT_GRAY);
							} else {
								mtwMelbeckIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "mtwMelbeckIndex", "18-17-10",
										mtwMelbeckIndex);
								setColor(mtwMelbeckQueuePanel, Color.BLUE);
							}
						}
						break;
					case "lf8DeutschEvernIndex":
						if (lf8DeutschEvernIndex < 6) {
							lf8DeutschEvernIndex++;
							if (lf8DeutschEvernIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(lf8DeutschEvernQueuePanel, Color.RED);
							} else if (lf8DeutschEvernIndex == 2) {
								buttonBack.setText("<<");
								setColor(lf8DeutschEvernQueuePanel,
										Color.YELLOW);
							} else if (lf8DeutschEvernIndex == 3) {
								setColor(lf8DeutschEvernQueuePanel, Color.GREEN);
							} else if (lf8DeutschEvernIndex == 4) {
								setColor(lf8DeutschEvernQueuePanel, Color.CYAN);
							} else if (lf8DeutschEvernIndex == 5) {
								setColor(lf8DeutschEvernQueuePanel,
										Color.ORANGE);
							} else if (lf8DeutschEvernIndex == 6) {
								setColor(lf8DeutschEvernQueuePanel,
										Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184320()[lf8DeutschEvernIndex],
									reader.getBemerkung184320()[lf8DeutschEvernIndex],
									reader.getFragen184320()[lf8DeutschEvernIndex],
									reader.getAntworten184320()[lf8DeutschEvernIndex],
									"lf8DeutschEvernIndex", "18-43-20",
									lf8DeutschEvernIndex);
						} else {
							lf8DeutschEvernIndex++;
							if (lf8DeutschEvernIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"lf8DeutschEvernIndex", "18-43-20",
										lf8DeutschEvernIndex);
								setColor(lf8DeutschEvernQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								lf8DeutschEvernIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "lf8DeutschEvernIndex",
										"18-43-20", lf8DeutschEvernIndex);
								setColor(lf8DeutschEvernQueuePanel, Color.BLUE);
							}
						}
						break;
					case "tlfDeutschEvernIndex":
						if (tlfDeutschEvernIndex < 6) {
							tlfDeutschEvernIndex++;
							if (tlfDeutschEvernIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(tlfDeutschEvernQueuePanel, Color.RED);
							} else if (tlfDeutschEvernIndex == 2) {
								buttonBack.setText("<<");
								setColor(tlfDeutschEvernQueuePanel,
										Color.YELLOW);
							} else if (tlfDeutschEvernIndex == 3) {
								setColor(tlfDeutschEvernQueuePanel, Color.GREEN);
							} else if (tlfDeutschEvernIndex == 4) {
								setColor(tlfDeutschEvernQueuePanel, Color.CYAN);
							} else if (tlfDeutschEvernIndex == 5) {
								setColor(tlfDeutschEvernQueuePanel,
										Color.ORANGE);
							} else if (tlfDeutschEvernIndex == 6) {
								setColor(tlfDeutschEvernQueuePanel,
										Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten182420()[tlfDeutschEvernIndex],
									reader.getBemerkung182420()[tlfDeutschEvernIndex],
									reader.getFragen182420()[tlfDeutschEvernIndex],
									reader.getAntworten182420()[tlfDeutschEvernIndex],
									"tlfDeutschEvernIndex", "18-24-20",
									tlfDeutschEvernIndex);
						} else {
							tlfDeutschEvernIndex++;
							if (tlfDeutschEvernIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"tlfDeutschEvernIndex", "18-24-20",
										tlfDeutschEvernIndex);
								setColor(tlfDeutschEvernQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								tlfDeutschEvernIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "tlfDeutschEvernIndex",
										"18-24-20", tlfDeutschEvernIndex);
								setColor(tlfDeutschEvernQueuePanel, Color.BLUE);
							}
						}
						break;
					case "mtwDeutschEvernIndex":
						if (mtwDeutschEvernIndex < 6) {
							mtwDeutschEvernIndex++;
							if (mtwDeutschEvernIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(mtwDeutschEvernQueuePanel, Color.RED);
							} else if (mtwDeutschEvernIndex == 2) {
								buttonBack.setText("<<");
								setColor(mtwDeutschEvernQueuePanel,
										Color.YELLOW);
							} else if (mtwDeutschEvernIndex == 3) {
								setColor(mtwDeutschEvernQueuePanel, Color.GREEN);
							} else if (mtwDeutschEvernIndex == 4) {
								setColor(mtwDeutschEvernQueuePanel, Color.CYAN);
							} else if (mtwDeutschEvernIndex == 5) {
								setColor(mtwDeutschEvernQueuePanel,
										Color.ORANGE);
							} else if (mtwDeutschEvernIndex == 6) {
								setColor(mtwDeutschEvernQueuePanel,
										Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181720()[mtwDeutschEvernIndex],
									reader.getBemerkung181720()[mtwDeutschEvernIndex],
									reader.getFragen181720()[mtwDeutschEvernIndex],
									reader.getAntworten181720()[mtwDeutschEvernIndex],
									"mtwDeutschEvernIndex", "18-17-20",
									mtwDeutschEvernIndex);
						} else {
							mtwDeutschEvernIndex++;
							if (mtwDeutschEvernIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"mtwDeutschEvernIndex", "18-17-20",
										mtwDeutschEvernIndex);
								setColor(mtwDeutschEvernQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								mtwDeutschEvernIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "mtwDeutschEvernIndex",
										"18-17-20", mtwDeutschEvernIndex);
								setColor(mtwDeutschEvernQueuePanel, Color.BLUE);
							}
						}
						break;
					case "rwEmbsenIndex":
						if (rwEmbsenIndex < 6) {
							rwEmbsenIndex++;
							if (rwEmbsenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(rw1EmbsenQueuePanel, Color.RED);
							} else if (rwEmbsenIndex == 2) {
								buttonBack.setText("<<");
								setColor(rw1EmbsenQueuePanel, Color.YELLOW);
							} else if (rwEmbsenIndex == 3) {
								setColor(rw1EmbsenQueuePanel, Color.GREEN);
							} else if (rwEmbsenIndex == 4) {
								setColor(rw1EmbsenQueuePanel, Color.CYAN);
							} else if (rwEmbsenIndex == 5) {
								setColor(rw1EmbsenQueuePanel, Color.ORANGE);
							} else if (rwEmbsenIndex == 6) {
								setColor(rw1EmbsenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten185130()[rwEmbsenIndex],
									reader.getBemerkung185130()[rwEmbsenIndex],
									reader.getFragen185130()[rwEmbsenIndex],
									reader.getAntworten185130()[rwEmbsenIndex],
									"rwEmbsenIndex", "18-51-30", rwEmbsenIndex);
						} else {
							rwEmbsenIndex++;
							if (rwEmbsenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"rwEmbsenIndex", "18-51-30",
										rwEmbsenIndex);
								setColor(rw1EmbsenQueuePanel, Color.LIGHT_GRAY);
							} else {
								rwEmbsenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "rwEmbsenIndex", "18-51-30",
										rwEmbsenIndex);
								setColor(rw1EmbsenQueuePanel, Color.BLUE);
							}
						}
						break;
					case "lfEmbsenIndex":
						if (lfEmbsenIndex < 6) {
							lfEmbsenIndex++;
							if (lfEmbsenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(lfEmbsenQueuePanel, Color.RED);
							} else if (lfEmbsenIndex == 2) {
								buttonBack.setText("<<");
								setColor(lfEmbsenQueuePanel, Color.YELLOW);
							} else if (lfEmbsenIndex == 3) {
								setColor(lfEmbsenQueuePanel, Color.GREEN);
							} else if (lfEmbsenIndex == 4) {
								setColor(lfEmbsenQueuePanel, Color.CYAN);
							} else if (lfEmbsenIndex == 5) {
								setColor(lfEmbsenQueuePanel, Color.ORANGE);
							} else if (lfEmbsenIndex == 6) {
								setColor(lfEmbsenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184730()[lfEmbsenIndex],
									reader.getBemerkung184730()[lfEmbsenIndex],
									reader.getFragen184730()[lfEmbsenIndex],
									reader.getAntworten184730()[lfEmbsenIndex],
									"lfEmbsenIndex", "18-47-30", lfEmbsenIndex);
						} else {
							lfEmbsenIndex++;
							if (lfEmbsenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"lfEmbsenIndex", "18-47-30",
										lfEmbsenIndex);
								setColor(lfEmbsenQueuePanel, Color.LIGHT_GRAY);
							} else {
								lfEmbsenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "lfEmbsenIndex", "18-47-30",
										lfEmbsenIndex);
								setColor(lfEmbsenQueuePanel, Color.BLUE);
							}
						}
						break;
					case "mtwEmbsenIndex":
						if (mtwEmbsenIndex < 6) {
							mtwEmbsenIndex++;
							if (mtwEmbsenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(mtwEmbsenQueuePanel, Color.RED);
							} else if (mtwEmbsenIndex == 2) {
								buttonBack.setText("<<");
								setColor(mtwEmbsenQueuePanel, Color.YELLOW);
							} else if (mtwEmbsenIndex == 3) {
								setColor(mtwEmbsenQueuePanel, Color.GREEN);
							} else if (mtwEmbsenIndex == 4) {
								setColor(mtwEmbsenQueuePanel, Color.CYAN);
							} else if (mtwEmbsenIndex == 5) {
								setColor(mtwEmbsenQueuePanel, Color.ORANGE);
							} else if (mtwEmbsenIndex == 6) {
								setColor(mtwEmbsenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181730()[mtwEmbsenIndex],
									reader.getBemerkung181730()[mtwEmbsenIndex],
									reader.getFragen181730()[mtwEmbsenIndex],
									reader.getAntworten181730()[mtwEmbsenIndex],
									"mtwEmbsenIndex", "18-17-30",
									mtwEmbsenIndex);
						} else {
							mtwEmbsenIndex++;
							if (mtwEmbsenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"mtwEmbsenIndex", "18-17-30",
										mtwEmbsenIndex);
								setColor(mtwEmbsenQueuePanel, Color.LIGHT_GRAY);
							} else {
								mtwEmbsenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "mtwEmbsenIndex", "18-17-30",
										mtwEmbsenIndex);
								setColor(mtwEmbsenQueuePanel, Color.BLUE);
							}
						}
						break;

					case "tlfOerzenIndex":
						if (tlfOerzenIndex < 6) {
							tlfOerzenIndex++;
							if (tlfOerzenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(tlfOerzenQueuePanel, Color.RED);
							} else if (tlfOerzenIndex == 2) {
								buttonBack.setText("<<");
								setColor(tlfOerzenQueuePanel, Color.YELLOW);
							} else if (tlfOerzenIndex == 3) {
								setColor(tlfOerzenQueuePanel, Color.GREEN);
							} else if (tlfOerzenIndex == 4) {
								setColor(tlfOerzenQueuePanel, Color.CYAN);
							} else if (tlfOerzenIndex == 5) {
								setColor(tlfOerzenQueuePanel, Color.ORANGE);
							} else if (tlfOerzenIndex == 6) {
								setColor(tlfOerzenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184732()[tlfOerzenIndex],
									reader.getBemerkung184732()[tlfOerzenIndex],
									reader.getFragen184732()[tlfOerzenIndex],
									reader.getAntworten184732()[tlfOerzenIndex],
									"tlfOerzenIndex", "18-47-32",
									tlfOerzenIndex);
						} else {
							tlfOerzenIndex++;
							if (tlfOerzenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"tlfOerzenIndex", "18-47-32",
										tlfOerzenIndex);
								setColor(tlfOerzenQueuePanel, Color.LIGHT_GRAY);
							} else {
								tlfOerzenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "tlfOerzenIndex", "18-47-32",
										tlfOerzenIndex);
								setColor(tlfOerzenQueuePanel, Color.BLUE);
							}
						}
						break;
					case "tsfOerzenIndex":
						if (tsfOerzenIndex < 6) {
							tsfOerzenIndex++;
							if (tsfOerzenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(tsfOerzenQueuePanel, Color.RED);
							} else if (tsfOerzenIndex == 2) {
								buttonBack.setText("<<");
								setColor(tsfOerzenQueuePanel, Color.YELLOW);
							} else if (tsfOerzenIndex == 3) {
								setColor(tsfOerzenQueuePanel, Color.GREEN);
							} else if (tsfOerzenIndex == 4) {
								setColor(tsfOerzenQueuePanel, Color.CYAN);
							} else if (tsfOerzenIndex == 5) {
								setColor(tsfOerzenQueuePanel, Color.ORANGE);
							} else if (tsfOerzenIndex == 6) {
								setColor(tsfOerzenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184032()[tsfOerzenIndex],
									reader.getBemerkung184032()[tsfOerzenIndex],
									reader.getFragen184032()[tsfOerzenIndex],
									reader.getAntworten184032()[tsfOerzenIndex],
									"tsfOerzenIndex", "18-40-32",
									tsfOerzenIndex);
						} else {
							tsfOerzenIndex++;
							if (tsfOerzenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"tsfOerzenIndex", "18-40-32",
										tsfOerzenIndex);
								setColor(tsfOerzenQueuePanel, Color.LIGHT_GRAY);
							} else {
								tsfOerzenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "tsfOerzenIndex", "18-40-32",
										tsfOerzenIndex);
								setColor(tsfOerzenQueuePanel, Color.BLUE);
							}
						}
						break;
					case "mtwOerzenIndex":
						if (mtwOerzenIndex < 6) {
							mtwOerzenIndex++;
							if (mtwOerzenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(mtwOerzenQueuePanel, Color.RED);
							} else if (mtwOerzenIndex == 2) {
								buttonBack.setText("<<");
								setColor(mtwOerzenQueuePanel, Color.YELLOW);
							} else if (mtwOerzenIndex == 3) {
								setColor(mtwOerzenQueuePanel, Color.GREEN);
							} else if (mtwOerzenIndex == 4) {
								setColor(mtwOerzenQueuePanel, Color.CYAN);
							} else if (mtwOerzenIndex == 5) {
								setColor(mtwOerzenQueuePanel, Color.ORANGE);
							} else if (mtwOerzenIndex == 6) {
								setColor(mtwOerzenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181732()[mtwOerzenIndex],
									reader.getBemerkung181732()[mtwOerzenIndex],
									reader.getFragen181732()[mtwOerzenIndex],
									reader.getAntworten181732()[mtwOerzenIndex],
									"mtwOerzenIndex", "18-17-32",
									mtwOerzenIndex);
						} else {
							mtwOerzenIndex++;
							if (mtwOerzenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"mtwOerzenIndex", "18-17-32",
										mtwOerzenIndex);
								setColor(mtwOerzenQueuePanel, Color.LIGHT_GRAY);
							} else {
								mtwOerzenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "mtwOerzenIndex", "18-17-32",
										mtwOerzenIndex);
								setColor(mtwOerzenQueuePanel, Color.BLUE);
							}
						}
						break;
					case "tlfBarnstedtIndex":
						if (tlfBarnstedtIndex < 6) {
							tlfBarnstedtIndex++;
							if (tlfBarnstedtIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(tlfBarnstedtQueuePanel, Color.RED);
							} else if (tlfBarnstedtIndex == 2) {
								buttonBack.setText("<<");
								setColor(tlfBarnstedtQueuePanel, Color.YELLOW);
							} else if (tlfBarnstedtIndex == 3) {
								setColor(tlfBarnstedtQueuePanel, Color.GREEN);
							} else if (tlfBarnstedtIndex == 4) {
								setColor(tlfBarnstedtQueuePanel, Color.CYAN);
							} else if (tlfBarnstedtIndex == 5) {
								setColor(tlfBarnstedtQueuePanel, Color.ORANGE);
							} else if (tlfBarnstedtIndex == 6) {
								setColor(tlfBarnstedtQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184740()[tlfBarnstedtIndex],
									reader.getBemerkung184740()[tlfBarnstedtIndex],
									reader.getFragen184740()[tlfBarnstedtIndex],
									reader.getAntworten184740()[tlfBarnstedtIndex],
									"tlfBarnstedtIndex", "18-47-40",
									tlfBarnstedtIndex);
						} else {
							tlfBarnstedtIndex++;
							if (tlfBarnstedtIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"tlfBarnstedtIndex", "18-47-40",
										tlfBarnstedtIndex);
								setColor(tlfBarnstedtQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								tlfBarnstedtIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "tlfBarnstedtIndex",
										"18-47-40", tlfBarnstedtIndex);
								setColor(tlfBarnstedtQueuePanel, Color.BLUE);
							}
						}
						break;
					case "tsfBarnstedtIndex":
						if (tsfBarnstedtIndex < 6) {
							tsfBarnstedtIndex++;
							if (tsfBarnstedtIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(tsfBarnstedtQueuePanel, Color.RED);
							} else if (tsfBarnstedtIndex == 2) {
								buttonBack.setText("<<");
								setColor(tsfBarnstedtQueuePanel, Color.YELLOW);
							} else if (tsfBarnstedtIndex == 3) {
								setColor(tsfBarnstedtQueuePanel, Color.GREEN);
							} else if (tsfBarnstedtIndex == 4) {
								setColor(tsfBarnstedtQueuePanel, Color.CYAN);
							} else if (tsfBarnstedtIndex == 5) {
								setColor(tsfBarnstedtQueuePanel, Color.ORANGE);
							} else if (tsfBarnstedtIndex == 6) {
								setColor(tsfBarnstedtQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184040()[tsfBarnstedtIndex],
									reader.getBemerkung184040()[tsfBarnstedtIndex],
									reader.getFragen184040()[tsfBarnstedtIndex],
									reader.getAntworten184040()[tsfBarnstedtIndex],
									"tsfBarnstedtIndex", "18-40-40",
									tsfBarnstedtIndex);
						} else {
							tsfBarnstedtIndex++;
							if (tsfBarnstedtIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"tsfBarnstedtIndex", "18-40-40",
										tsfBarnstedtIndex);
								setColor(tsfBarnstedtQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								tsfBarnstedtIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "tsfBarnstedtIndex",
										"18-40-40", tsfBarnstedtIndex);
								setColor(tsfBarnstedtQueuePanel, Color.BLUE);
							}
						}
						break;
					case "mtwBarnstedtIndex":
						if (mtwBarnstedtIndex < 6) {
							mtwBarnstedtIndex++;
							if (mtwBarnstedtIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(mtwBarnstedtQueuePanel, Color.RED);
							} else if (mtwBarnstedtIndex == 2) {
								buttonBack.setText("<<");
								setColor(mtwBarnstedtQueuePanel, Color.YELLOW);
							} else if (mtwBarnstedtIndex == 3) {
								setColor(mtwBarnstedtQueuePanel, Color.GREEN);
							} else if (mtwBarnstedtIndex == 4) {
								setColor(mtwBarnstedtQueuePanel, Color.CYAN);
							} else if (mtwBarnstedtIndex == 5) {
								setColor(mtwBarnstedtQueuePanel, Color.ORANGE);
							} else if (mtwBarnstedtIndex == 6) {
								setColor(mtwBarnstedtQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181740()[mtwBarnstedtIndex],
									reader.getBemerkung181740()[mtwBarnstedtIndex],
									reader.getFragen181740()[mtwBarnstedtIndex],
									reader.getAntworten181740()[mtwBarnstedtIndex],
									"mtwBarnstedtIndex", "18-17-40",
									mtwBarnstedtIndex);
						} else {
							mtwBarnstedtIndex++;
							if (mtwBarnstedtIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"mtwBarnstedtIndex", "18-17-40",
										mtwBarnstedtIndex);
								setColor(mtwBarnstedtQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								mtwBarnstedtIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "mtwBarnstedtIndex",
										"18-17-40", mtwBarnstedtIndex);
								setColor(mtwBarnstedtQueuePanel, Color.BLUE);
							}
						}
						break;
					case "lf8KolkhagenIndex":
						if (lf8KolkhagenIndex < 6) {
							lf8KolkhagenIndex++;
							if (lf8KolkhagenIndex == 1) {
								btnRichtig.setText("Richtig");
								buttonBack.setText("Abmelden");
								setColor(lf8KolkhagenQueuePanel, Color.RED);
							} else if (lf8KolkhagenIndex == 2) {
								buttonBack.setText("<<");
								setColor(lf8KolkhagenQueuePanel, Color.YELLOW);
							} else if (lf8KolkhagenIndex == 3) {
								setColor(lf8KolkhagenQueuePanel, Color.GREEN);
							} else if (lf8KolkhagenIndex == 4) {
								setColor(lf8KolkhagenQueuePanel, Color.CYAN);
							} else if (lf8KolkhagenIndex == 5) {
								setColor(lf8KolkhagenQueuePanel, Color.ORANGE);
							} else if (lf8KolkhagenIndex == 6) {
								setColor(lf8KolkhagenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184342()[lf8KolkhagenIndex],
									reader.getBemerkung184342()[lf8KolkhagenIndex],
									reader.getFragen184342()[lf8KolkhagenIndex],
									reader.getAntworten184342()[lf8KolkhagenIndex],
									"lf8KolkhagenIndex", "18-43-42",
									lf8KolkhagenIndex);
						} else {
							lf8KolkhagenIndex++;
							if (lf8KolkhagenIndex == 7) {
								btnRichtig.setText("Abmelden");
								setLabel(reader.getSammelplatzKoordinaten()[1],
										"", reader.getSammelplatz()[1], "",
										"lf8KolkhagenIndex", "18-43-42",
										lf8KolkhagenIndex);
								setColor(lf8KolkhagenQueuePanel,
										Color.LIGHT_GRAY);
							} else {
								lf8KolkhagenIndex = 8;
								setLabel("", "Am Sammelplatz eingetroffen!",
										"", "", "lf8KolkhagenIndex",
										"18-43-42", lf8KolkhagenIndex);
								setColor(lf8KolkhagenQueuePanel, Color.BLUE);
							}
						}
						break;
					default:
						System.out.println("Unknown Button: " + button);
						break;
					}
				}
			}

			private void setColor(JPanel panel, Color farbe) {
				panel.setBackground(farbe);
			}
		});

		buttonBack = new JButton("<<");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button != null) {
					switch (button) {
					case "tlfMelbeckIndex":
						tlfMelbeckIndex--;
						if (tlfMelbeckIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							tlfMelbeckIndex = 0;
							setColor(tlfMelbeckQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-47-10 zur Funkfahrübung anmelden?",
									"", "tlfMelbeckIndex", "18-47-10",
									tlfMelbeckIndex);
						} else if (tlfMelbeckIndex > 0 && tlfMelbeckIndex <= 6) {
							if (tlfMelbeckIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(tlfMelbeckQueuePanel, Color.RED);
							} else if (tlfMelbeckIndex == 2) {
								setColor(tlfMelbeckQueuePanel, Color.YELLOW);
							} else if (tlfMelbeckIndex == 3) {
								setColor(tlfMelbeckQueuePanel, Color.GREEN);
							} else if (tlfMelbeckIndex == 4) {
								setColor(tlfMelbeckQueuePanel, Color.CYAN);
							} else if (tlfMelbeckIndex == 5) {
								setColor(tlfMelbeckQueuePanel, Color.ORANGE);
							} else if (tlfMelbeckIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(tlfMelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184710()[tlfMelbeckIndex],
									reader.getBemerkung184710()[tlfMelbeckIndex],
									reader.getFragen184710()[tlfMelbeckIndex],
									reader.getAntworten184710()[tlfMelbeckIndex],
									"tlfMelbeckIndex", "18-47-10",
									tlfMelbeckIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"tlfMelbeckIndex", "18-47-10",
									tlfMelbeckIndex);
							setColor(tlfMelbeckQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "lf16MelbeckIndex":
						lf16MelbeckIndex--;
						if (lf16MelbeckIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							lf16MelbeckIndex = 0;
							setColor(lf16MelbeckQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-44-10 zur Funkfahrübung anmelden?",
									"", "lf16MelbeckIndex", "18-44-10",
									lf16MelbeckIndex);
						} else if (lf16MelbeckIndex > 0
								&& lf16MelbeckIndex <= 6) {
							if (lf16MelbeckIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(lf16MelbeckQueuePanel, Color.RED);
							} else if (lf16MelbeckIndex == 2) {
								setColor(lf16MelbeckQueuePanel, Color.YELLOW);
							} else if (lf16MelbeckIndex == 3) {
								setColor(lf16MelbeckQueuePanel, Color.GREEN);
							} else if (lf16MelbeckIndex == 4) {
								setColor(lf16MelbeckQueuePanel, Color.CYAN);
							} else if (lf16MelbeckIndex == 5) {
								setColor(lf16MelbeckQueuePanel, Color.ORANGE);
							} else if (lf16MelbeckIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(lf16MelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184410()[lf16MelbeckIndex],
									reader.getBemerkung184410()[lf16MelbeckIndex],
									reader.getFragen184410()[lf16MelbeckIndex],
									reader.getAntworten184410()[lf16MelbeckIndex],
									"lf16MelbeckIndex", "18-44-10",
									lf16MelbeckIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"lf16MelbeckIndex", "18-44-10",
									lf16MelbeckIndex);
							setColor(lf16MelbeckQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "lf8MelbeckIndex":
						lf8MelbeckIndex--;
						if (lf8MelbeckIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							lf8MelbeckIndex = 0;
							setColor(lf8MelbeckQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-43-10 zur Funkfahrübung anmelden?",
									"", "lf8MelbeckIndex", "18-43-10",
									lf8MelbeckIndex);
						} else if (lf8MelbeckIndex > 0 && lf8MelbeckIndex <= 6) {
							if (lf8MelbeckIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(lf8MelbeckQueuePanel, Color.RED);
							} else if (lf8MelbeckIndex == 2) {
								setColor(lf8MelbeckQueuePanel, Color.YELLOW);
							} else if (lf8MelbeckIndex == 3) {
								setColor(lf8MelbeckQueuePanel, Color.GREEN);
							} else if (lf8MelbeckIndex == 4) {
								setColor(lf8MelbeckQueuePanel, Color.CYAN);
							} else if (lf8MelbeckIndex == 5) {
								setColor(lf8MelbeckQueuePanel, Color.ORANGE);
							} else if (lf8MelbeckIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(lf8MelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184310()[lf8MelbeckIndex],
									reader.getBemerkung184310()[lf8MelbeckIndex],
									reader.getFragen184310()[lf8MelbeckIndex],
									reader.getAntworten184310()[lf8MelbeckIndex],
									"lf8MelbeckIndex", "18-43-10",
									lf8MelbeckIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"lf8MelbeckIndex", "18-43-10",
									lf8MelbeckIndex);
							setColor(lf8MelbeckQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "mtwMelbeckIndex":
						mtwMelbeckIndex--;
						if (mtwMelbeckIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							mtwMelbeckIndex = 0;
							setColor(mtwMelbeckQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-17-10 zur Funkfahrübung anmelden?",
									"", "mtwMelbeckIndex", "18-17-10",
									mtwMelbeckIndex);
						} else if (mtwMelbeckIndex > 0 && mtwMelbeckIndex <= 6) {
							if (mtwMelbeckIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(mtwMelbeckQueuePanel, Color.RED);
							} else if (mtwMelbeckIndex == 2) {
								setColor(mtwMelbeckQueuePanel, Color.YELLOW);
							} else if (mtwMelbeckIndex == 3) {
								setColor(mtwMelbeckQueuePanel, Color.GREEN);
							} else if (mtwMelbeckIndex == 4) {
								setColor(mtwMelbeckQueuePanel, Color.CYAN);
							} else if (mtwMelbeckIndex == 5) {
								setColor(mtwMelbeckQueuePanel, Color.ORANGE);
							} else if (mtwMelbeckIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(mtwMelbeckQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181710()[mtwMelbeckIndex],
									reader.getBemerkung181710()[mtwMelbeckIndex],
									reader.getFragen181710()[mtwMelbeckIndex],
									reader.getAntworten181710()[mtwMelbeckIndex],
									"mtwMelbeckIndex", "18-17-10",
									mtwMelbeckIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"mtwMelbeckIndex", "18-17-10",
									mtwMelbeckIndex);
							setColor(mtwMelbeckQueuePanel, Color.LIGHT_GRAY);

						}
						break;
					case "lf8DeutschEvernIndex":
						lf8DeutschEvernIndex--;
						if (lf8DeutschEvernIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							lf8DeutschEvernIndex = 0;
							setColor(lf8DeutschEvernQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-43-20 zur Funkfahrübung anmelden?",
									"", "lf8DeutschEvernIndex", "18-43-20",
									lf8DeutschEvernIndex);
						} else if (lf8DeutschEvernIndex > 0
								&& lf8DeutschEvernIndex <= 6) {
							if (lf8DeutschEvernIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(lf8DeutschEvernQueuePanel, Color.RED);
							} else if (lf8DeutschEvernIndex == 2) {
								setColor(lf8DeutschEvernQueuePanel,
										Color.YELLOW);
							} else if (lf8DeutschEvernIndex == 3) {
								setColor(lf8DeutschEvernQueuePanel, Color.GREEN);
							} else if (lf8DeutschEvernIndex == 4) {
								setColor(lf8DeutschEvernQueuePanel, Color.CYAN);
							} else if (lf8DeutschEvernIndex == 5) {
								setColor(lf8DeutschEvernQueuePanel,
										Color.ORANGE);
							} else if (lf8DeutschEvernIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(lf8DeutschEvernQueuePanel,
										Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184320()[lf8DeutschEvernIndex],
									reader.getBemerkung184320()[lf8DeutschEvernIndex],
									reader.getFragen184320()[lf8DeutschEvernIndex],
									reader.getAntworten184320()[lf8DeutschEvernIndex],
									"lf8DeutschEvernIndex", "18-43-20",
									lf8DeutschEvernIndex);

						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"lf8DeutschEvernIndex", "18-43-20",
									lf8DeutschEvernIndex);
							setColor(lf8DeutschEvernQueuePanel,
									Color.LIGHT_GRAY);

						}
						break;
					case "tlfDeutschEvernIndex":
						tlfDeutschEvernIndex--;
						if (tlfDeutschEvernIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							tlfDeutschEvernIndex = 0;
							setColor(tlfDeutschEvernQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-24-20 zur Funkfahrübung anmelden?",
									"", "lf8DeutschEvernIndex", "18-24-20",
									lf8DeutschEvernIndex);
						} else if (tlfDeutschEvernIndex > 0
								&& tlfDeutschEvernIndex <= 6) {
							if (tlfDeutschEvernIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(tlfDeutschEvernQueuePanel, Color.RED);
							} else if (tlfDeutschEvernIndex == 2) {
								setColor(tlfDeutschEvernQueuePanel,
										Color.YELLOW);
							} else if (tlfDeutschEvernIndex == 3) {
								setColor(tlfDeutschEvernQueuePanel, Color.GREEN);
							} else if (tlfDeutschEvernIndex == 4) {
								setColor(tlfDeutschEvernQueuePanel, Color.CYAN);
							} else if (tlfDeutschEvernIndex == 5) {
								setColor(tlfDeutschEvernQueuePanel,
										Color.ORANGE);
							} else if (tlfDeutschEvernIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(tlfDeutschEvernQueuePanel,
										Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten182420()[tlfDeutschEvernIndex],
									reader.getBemerkung182420()[tlfDeutschEvernIndex],
									reader.getFragen182420()[tlfDeutschEvernIndex],
									reader.getAntworten182420()[tlfDeutschEvernIndex],
									"tlfDeutschEvernIndex", "18-24-20",
									lf8DeutschEvernIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"tlfDeutschEvernIndex", "18-24-20",
									lf8DeutschEvernIndex);
							setColor(tlfDeutschEvernQueuePanel,
									Color.LIGHT_GRAY);

						}
						break;
					case "mtwDeutschEvernIndex":
						mtwDeutschEvernIndex--;
						if (mtwDeutschEvernIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							mtwDeutschEvernIndex = 0;
							setColor(mtwDeutschEvernQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-17-20 zur Funkfahrübung anmelden?",
									"", "lf8DeutschEvernIndex", "18-17-20",
									lf8DeutschEvernIndex);
						} else if (mtwDeutschEvernIndex > 0
								&& mtwDeutschEvernIndex <= 6) {
							if (mtwDeutschEvernIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(mtwDeutschEvernQueuePanel, Color.RED);
							} else if (mtwDeutschEvernIndex == 2) {
								setColor(mtwDeutschEvernQueuePanel,
										Color.YELLOW);
							} else if (mtwDeutschEvernIndex == 3) {
								setColor(mtwDeutschEvernQueuePanel, Color.GREEN);
							} else if (mtwDeutschEvernIndex == 4) {
								setColor(mtwDeutschEvernQueuePanel, Color.CYAN);
							} else if (mtwDeutschEvernIndex == 5) {
								setColor(mtwDeutschEvernQueuePanel,
										Color.ORANGE);
							} else if (mtwDeutschEvernIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(mtwDeutschEvernQueuePanel,
										Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181720()[mtwDeutschEvernIndex],
									reader.getBemerkung181720()[mtwDeutschEvernIndex],
									reader.getFragen181720()[mtwDeutschEvernIndex],
									reader.getAntworten181720()[mtwDeutschEvernIndex],
									"mtwDeutschEvernIndex", "18-17-20",
									lf8DeutschEvernIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"mtwDeutschEvernIndex", "18-17-20",
									lf8DeutschEvernIndex);
							setColor(mtwDeutschEvernQueuePanel,
									Color.LIGHT_GRAY);

						}
						break;
					case "rwEmbsenIndex":
						rwEmbsenIndex--;
						if (rwEmbsenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							rwEmbsenIndex = 0;
							setColor(rw1EmbsenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-51-30 zur Funkfahrübung anmelden?",
									"", "rwEmbsenIndex", "18-51-30",
									rwEmbsenIndex);
						} else if (rwEmbsenIndex > 0 && rwEmbsenIndex <= 6) {
							if (rwEmbsenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(rw1EmbsenQueuePanel, Color.RED);
							} else if (rwEmbsenIndex == 2) {
								setColor(rw1EmbsenQueuePanel, Color.YELLOW);
							} else if (rwEmbsenIndex == 3) {
								setColor(rw1EmbsenQueuePanel, Color.GREEN);
							} else if (rwEmbsenIndex == 4) {
								setColor(rw1EmbsenQueuePanel, Color.CYAN);
							} else if (rwEmbsenIndex == 5) {
								setColor(rw1EmbsenQueuePanel, Color.ORANGE);
							} else if (rwEmbsenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(rw1EmbsenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten185130()[rwEmbsenIndex],
									reader.getBemerkung185130()[rwEmbsenIndex],
									reader.getFragen185130()[rwEmbsenIndex],
									reader.getAntworten185130()[rwEmbsenIndex],
									"rwEmbsenIndex", "18-51-30", rwEmbsenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"rwEmbsenIndex", "18-51-30", rwEmbsenIndex);
							setColor(rw1EmbsenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "lfEmbsenIndex":
						lfEmbsenIndex--;
						if (lfEmbsenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							lfEmbsenIndex = 0;
							setColor(lfEmbsenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-47-30 zur Funkfahrübung anmelden?",
									"", "lfEmbsenIndex", "18-47-30",
									lfEmbsenIndex);
						} else if (lfEmbsenIndex > 0 && lfEmbsenIndex <= 6) {
							if (lfEmbsenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(lfEmbsenQueuePanel, Color.RED);
							} else if (lfEmbsenIndex == 2) {
								setColor(lfEmbsenQueuePanel, Color.YELLOW);
							} else if (lfEmbsenIndex == 3) {
								setColor(lfEmbsenQueuePanel, Color.GREEN);
							} else if (lfEmbsenIndex == 4) {
								setColor(lfEmbsenQueuePanel, Color.CYAN);
							} else if (lfEmbsenIndex == 5) {
								setColor(lfEmbsenQueuePanel, Color.ORANGE);
							} else if (lfEmbsenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(lfEmbsenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184730()[lfEmbsenIndex],
									reader.getBemerkung184730()[lfEmbsenIndex],
									reader.getFragen184730()[lfEmbsenIndex],
									reader.getAntworten184730()[lfEmbsenIndex],
									"lfEmbsenIndex", "18-47-30", lfEmbsenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"lfEmbsenIndex", "18-47-30", lfEmbsenIndex);
							setColor(lfEmbsenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "mtwEmbsenIndex":
						mtwEmbsenIndex--;
						if (mtwEmbsenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							mtwEmbsenIndex = 0;
							setColor(mtwEmbsenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-17-30 zur Funkfahrübung anmelden?",
									"", "mtwEmbsenIndex", "18-17-30",
									mtwEmbsenIndex);
						} else if (mtwEmbsenIndex > 0 && mtwEmbsenIndex <= 6) {
							if (mtwEmbsenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(mtwEmbsenQueuePanel, Color.RED);
							} else if (mtwEmbsenIndex == 2) {
								setColor(mtwEmbsenQueuePanel, Color.YELLOW);
							} else if (mtwEmbsenIndex == 3) {
								setColor(mtwEmbsenQueuePanel, Color.GREEN);
							} else if (mtwEmbsenIndex == 4) {
								setColor(mtwEmbsenQueuePanel, Color.CYAN);
							} else if (mtwEmbsenIndex == 5) {
								setColor(mtwEmbsenQueuePanel, Color.ORANGE);
							} else if (mtwEmbsenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(mtwEmbsenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181730()[mtwEmbsenIndex],
									reader.getBemerkung181730()[mtwEmbsenIndex],
									reader.getFragen181730()[mtwEmbsenIndex],
									reader.getAntworten181730()[mtwEmbsenIndex],
									"mtwEmbsenIndex", "18-17-30",
									mtwEmbsenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"mtwEmbsenIndex", "18-17-30",
									mtwEmbsenIndex);
							setColor(mtwEmbsenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "tlfOerzenIndex":
						tlfOerzenIndex--;
						if (tlfOerzenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							tlfOerzenIndex = 0;
							setColor(tlfOerzenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-47-32 zur Funkfahrübung anmelden?",
									"", "tlfOerzenIndex", "18-47-32",
									tlfOerzenIndex);
						} else if (tlfOerzenIndex > 0 && tlfOerzenIndex <= 6) {
							if (tlfOerzenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(tlfOerzenQueuePanel, Color.RED);
							} else if (tlfOerzenIndex == 2) {
								setColor(tlfOerzenQueuePanel, Color.YELLOW);
							} else if (tlfOerzenIndex == 3) {
								setColor(tlfOerzenQueuePanel, Color.GREEN);
							} else if (tlfOerzenIndex == 4) {
								setColor(tlfOerzenQueuePanel, Color.CYAN);
							} else if (tlfOerzenIndex == 5) {
								setColor(tlfOerzenQueuePanel, Color.ORANGE);
							} else if (tlfOerzenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(tlfOerzenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184732()[tlfOerzenIndex],
									reader.getBemerkung184732()[tlfOerzenIndex],
									reader.getFragen184732()[tlfOerzenIndex],
									reader.getAntworten184732()[tlfOerzenIndex],
									"tlfOerzenIndex", "18-47-32",
									tlfOerzenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"tlfOerzenIndex", "18-47-32",
									tlfOerzenIndex);
							setColor(tlfOerzenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "tsfOerzenIndex":
						tsfOerzenIndex--;
						if (tsfOerzenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							tsfOerzenIndex = 0;
							setColor(tsfOerzenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-40-32 zur Funkfahrübung anmelden?",
									"", "tsfOerzenIndex", "18-40-32",
									tsfOerzenIndex);
						} else if (tsfOerzenIndex > 0 && tsfOerzenIndex <= 6) {
							if (tsfOerzenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(tsfOerzenQueuePanel, Color.RED);
							} else if (tsfOerzenIndex == 2) {
								setColor(tsfOerzenQueuePanel, Color.YELLOW);
							} else if (tsfOerzenIndex == 3) {
								setColor(tsfOerzenQueuePanel, Color.GREEN);
							} else if (tsfOerzenIndex == 4) {
								setColor(tsfOerzenQueuePanel, Color.CYAN);
							} else if (tsfOerzenIndex == 5) {
								setColor(tsfOerzenQueuePanel, Color.ORANGE);
							} else if (tsfOerzenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(tsfOerzenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184032()[tsfOerzenIndex],
									reader.getBemerkung184032()[tsfOerzenIndex],
									reader.getFragen184032()[tsfOerzenIndex],
									reader.getAntworten184032()[tsfOerzenIndex],
									"tsfOerzenIndex", "18-40-32",
									tsfOerzenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"tsfOerzenIndex", "18-40-32",
									tsfOerzenIndex);
							setColor(tsfOerzenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "mtwOerzenIndex":
						mtwOerzenIndex--;
						if (mtwOerzenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							mtwOerzenIndex = 0;
							setColor(mtwOerzenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-17-32 zur Funkfahrübung anmelden?",
									"", "mtwOerzenIndex", "18-17-32",
									mtwOerzenIndex);
						} else if (mtwOerzenIndex > 0 && mtwOerzenIndex <= 6) {
							if (mtwOerzenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(mtwOerzenQueuePanel, Color.RED);
							} else if (mtwOerzenIndex == 2) {
								setColor(mtwOerzenQueuePanel, Color.YELLOW);
							} else if (mtwOerzenIndex == 3) {
								setColor(mtwOerzenQueuePanel, Color.GREEN);
							} else if (mtwOerzenIndex == 4) {
								setColor(mtwOerzenQueuePanel, Color.CYAN);
							} else if (mtwOerzenIndex == 5) {
								setColor(mtwOerzenQueuePanel, Color.ORANGE);
							} else if (mtwOerzenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(mtwOerzenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181732()[mtwOerzenIndex],
									reader.getBemerkung181732()[mtwOerzenIndex],
									reader.getFragen181732()[mtwOerzenIndex],
									reader.getAntworten181732()[mtwOerzenIndex],
									"mtwOerzenIndex", "18-17-32",
									mtwOerzenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"mtwOerzenIndex", "18-17-32",
									mtwOerzenIndex);
							setColor(mtwOerzenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "tlfBarnstedtIndex":
						tlfBarnstedtIndex--;
						if (tlfBarnstedtIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							tlfBarnstedtIndex = 0;
							setColor(tlfBarnstedtQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-47-40 zur Funkfahrübung anmelden?",
									"", "tlfBarnstedtIndex", "18-47-40",
									tlfBarnstedtIndex);
						} else if (tlfBarnstedtIndex > 0
								&& tlfBarnstedtIndex <= 6) {
							if (tlfBarnstedtIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(tlfBarnstedtQueuePanel, Color.RED);
							} else if (tlfBarnstedtIndex == 2) {
								setColor(tlfBarnstedtQueuePanel, Color.YELLOW);
							} else if (tlfBarnstedtIndex == 3) {
								setColor(tlfBarnstedtQueuePanel, Color.GREEN);
							} else if (tlfBarnstedtIndex == 4) {
								setColor(tlfBarnstedtQueuePanel, Color.CYAN);
							} else if (tlfBarnstedtIndex == 5) {
								setColor(tlfBarnstedtQueuePanel, Color.ORANGE);
							} else if (tlfBarnstedtIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(tlfBarnstedtQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184740()[tlfBarnstedtIndex],
									reader.getBemerkung184740()[tlfBarnstedtIndex],
									reader.getFragen184740()[tlfBarnstedtIndex],
									reader.getAntworten184740()[tlfBarnstedtIndex],
									"tlfBarnstedtIndex", "18-47-40",
									tlfBarnstedtIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"tlfBarnstedtIndex", "18-47-40",
									tlfBarnstedtIndex);
							setColor(tlfBarnstedtQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "tsfBarnstedtIndex":
						tsfBarnstedtIndex--;
						if (tsfBarnstedtIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							tsfBarnstedtIndex = 0;
							setColor(tsfBarnstedtQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-40-40 zur Funkfahrübung anmelden?",
									"", "tsfBarnstedtIndex", "18-40-40",
									tsfBarnstedtIndex);
						} else if (tsfBarnstedtIndex > 0
								&& tsfBarnstedtIndex <= 6) {
							if (tsfBarnstedtIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(tsfBarnstedtQueuePanel, Color.RED);
							} else if (tsfBarnstedtIndex == 2) {
								setColor(tsfBarnstedtQueuePanel, Color.YELLOW);
							} else if (tsfBarnstedtIndex == 3) {
								setColor(tsfBarnstedtQueuePanel, Color.GREEN);
							} else if (tsfBarnstedtIndex == 4) {
								setColor(tsfBarnstedtQueuePanel, Color.CYAN);
							} else if (tsfBarnstedtIndex == 5) {
								setColor(tsfBarnstedtQueuePanel, Color.ORANGE);
							} else if (tsfBarnstedtIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(tsfBarnstedtQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184040()[tsfBarnstedtIndex],
									reader.getBemerkung184040()[tsfBarnstedtIndex],
									reader.getFragen184040()[tsfBarnstedtIndex],
									reader.getAntworten184040()[tsfBarnstedtIndex],
									"tsfBarnstedtIndex", "18-40-40",
									tsfBarnstedtIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"tsfBarnstedtIndex", "18-40-40",
									tsfBarnstedtIndex);
							setColor(tsfBarnstedtQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "mtwBarnstedtIndex":
						mtwBarnstedtIndex--;
						if (mtwBarnstedtIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							mtwBarnstedtIndex = 0;
							setColor(mtwBarnstedtQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-17-40 zur Funkfahrübung anmelden?",
									"", "mtwBarnstedtIndex", "18-17-40",
									mtwBarnstedtIndex);
						} else if (mtwBarnstedtIndex > 0
								&& mtwBarnstedtIndex <= 6) {
							if (mtwBarnstedtIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(mtwBarnstedtQueuePanel, Color.RED);
							} else if (mtwBarnstedtIndex == 2) {
								setColor(mtwBarnstedtQueuePanel, Color.YELLOW);
							} else if (mtwBarnstedtIndex == 3) {
								setColor(mtwBarnstedtQueuePanel, Color.GREEN);
							} else if (mtwBarnstedtIndex == 4) {
								setColor(mtwBarnstedtQueuePanel, Color.CYAN);
							} else if (mtwBarnstedtIndex == 5) {
								setColor(mtwBarnstedtQueuePanel, Color.ORANGE);
							} else if (mtwBarnstedtIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(mtwBarnstedtQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten181740()[mtwBarnstedtIndex],
									reader.getBemerkung181740()[mtwBarnstedtIndex],
									reader.getFragen181740()[mtwBarnstedtIndex],
									reader.getAntworten181740()[mtwBarnstedtIndex],
									"mtwBarnstedtIndex", "18-17-40",
									mtwBarnstedtIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"mtwBarnstedtIndex", "18-17-40",
									mtwBarnstedtIndex);
							setColor(mtwBarnstedtQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					case "lf8KolkhagenIndex":
						lf8KolkhagenIndex--;
						if (lf8KolkhagenIndex <= 0) {
							btnRichtig.setText("Anmelden");
							buttonBack.setText("<<");
							lf8KolkhagenIndex = 0;
							setColor(lf8KolkhagenQueuePanel, getBackground());
							setLabel(
									"",
									"",
									"Wollen Sie Florian Lüneburg 18-43-42 zur Funkfahrübung anmelden?",
									"", "lf8KolkhagenIndex", "18-43-42",
									lf8KolkhagenIndex);
						} else if (lf8KolkhagenIndex > 0
								&& lf8KolkhagenIndex <= 6) {
							if (lf8KolkhagenIndex == 1) {
								buttonBack.setText("Abmelden");
								setColor(lf8KolkhagenQueuePanel, Color.RED);
							} else if (lf8KolkhagenIndex == 2) {
								setColor(lf8KolkhagenQueuePanel, Color.YELLOW);
							} else if (lf8KolkhagenIndex == 3) {
								setColor(lf8KolkhagenQueuePanel, Color.GREEN);
							} else if (lf8KolkhagenIndex == 4) {
								setColor(lf8KolkhagenQueuePanel, Color.CYAN);
							} else if (lf8KolkhagenIndex == 5) {
								setColor(lf8KolkhagenQueuePanel, Color.ORANGE);
							} else if (lf8KolkhagenIndex == 6) {
								btnRichtig.setText("Richtig");
								setColor(lf8KolkhagenQueuePanel, Color.MAGENTA);
							}
							setLabel(
									reader.getKoordinaten184342()[lf8KolkhagenIndex],
									reader.getBemerkung184342()[lf8KolkhagenIndex],
									reader.getFragen184342()[lf8KolkhagenIndex],
									reader.getAntworten184342()[lf8KolkhagenIndex],
									"lf8KolkhagenIndex", "18-43-42",
									lf8KolkhagenIndex);
						} else {
							btnRichtig.setText("Abmelden");
							setLabel(reader.getSammelplatzKoordinaten()[1], "",
									reader.getSammelplatz()[1], "",
									"lf8KolkhagenIndex", "18-43-42",
									lf8KolkhagenIndex);
							setColor(lf8KolkhagenQueuePanel, Color.LIGHT_GRAY);
						}
						break;
					default:
						System.out.println("<< Unknown Button: " + button);
						break;
					}
				} else {

				}
			}

			private void setColor(JPanel panel, Color color) {
				panel.setBackground(color);
			}
		});

		buttonBack.setHorizontalTextPosition(SwingConstants.RIGHT);
		buttonBack.setHorizontalAlignment(SwingConstants.RIGHT);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(buttonBack);
		buttonPanel.add(btnRichtig);
		vehiclePanel.add(buttonPanel, "cell 5 11");

		JMenuBar menuBar = new JMenuBar();

		JMenu mainMenu = new JMenu("Menü");
		menuBar.add(mainMenu);

		JMenuItem editVehiclesMenuItem = new JMenuItem("Fahrzeuge bearbeiten");
		editVehiclesMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			   // TODO
			}
		});
		editVehiclesMenuItem.setEnabled(false);
		mainMenu.add(editVehiclesMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Beenden");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!offlineArbeiten) {
					try {
						socketServer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.exit(1);
			}
		});
		mainMenu.add(exitMenuItem);

		// a submenu
		JMenu submenu = new JMenu("Ansicht");

		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem alphaMenuItem = new JRadioButtonMenuItem(
				"Aphabetisch");
		alphaMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				barnstedtAlphabetischPanel.setVisible(true);
				barnstedtPanel.setVisible(false);
				deutschEvernAlphabetischPanel.setVisible(true);
				deutschEvernPanel.setVisible(false);
				embsenAlphabetischPanel.setVisible(true);
				embsenPanel.setVisible(false);
				kolkhagenAlphabetischPanel.setVisible(true);
				kolkhagenPanel.setVisible(false);
				melbeckAlphabetischPanel.setVisible(true);
				melbeckPanel.setVisible(false);
				oerzenAlphabetischPanel.setVisible(true);
				oerzenPanel.setVisible(false);
			}
		});
		group.add(alphaMenuItem);
		submenu.add(alphaMenuItem);

		JRadioButtonMenuItem chronMenuItem = new JRadioButtonMenuItem(
				"Numerisch");
		chronMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				barnstedtAlphabetischPanel.setVisible(false);
				barnstedtPanel.setVisible(true);
				deutschEvernAlphabetischPanel.setVisible(false);
				deutschEvernPanel.setVisible(true);
				embsenAlphabetischPanel.setVisible(false);
				embsenPanel.setVisible(true);
				kolkhagenAlphabetischPanel.setVisible(false);
				kolkhagenPanel.setVisible(true);
				melbeckAlphabetischPanel.setVisible(false);
				melbeckPanel.setVisible(true);
				oerzenAlphabetischPanel.setVisible(false);
				oerzenPanel.setVisible(true);
			}
		});
		chronMenuItem.setSelected(true);
		group.add(chronMenuItem);
		submenu.add(chronMenuItem);

		final JCheckBoxMenuItem chckbxmntmMtwZugriff = new JCheckBoxMenuItem(
				"MTW erlauben");
		chckbxmntmMtwZugriff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxmntmMtwZugriff.isSelected()) {
					mtwMelbeck.setEnabled(true);
					mtwDE.setEnabled(true);
					mtwEmbsen.setEnabled(true);
					mtwOerzen.setEnabled(true);
					mtwbarnstedt.setEnabled(true);
					mtwAlphabetischBarnstedt.setEnabled(true);
					mtwAlphabetischDE.setEnabled(true);
					mtwAlphabetischEmbsen.setEnabled(true);
					mtwAlphabetischMelbeck.setEnabled(true);
					mtwAlphabetischOerzen.setEnabled(true);
					chckbxmntmMtwZugriff.setText("MTW verbieten");
				} else {
					mtwMelbeck.setEnabled(false);
					mtwDE.setEnabled(false);
					mtwEmbsen.setEnabled(false);
					mtwOerzen.setEnabled(false);
					mtwbarnstedt.setEnabled(false);
					mtwAlphabetischBarnstedt.setEnabled(false);
					mtwAlphabetischDE.setEnabled(false);
					mtwAlphabetischEmbsen.setEnabled(false);
					mtwAlphabetischMelbeck.setEnabled(false);
					mtwAlphabetischOerzen.setEnabled(false);
					chckbxmntmMtwZugriff.setText("MTW erlauben");
				}
			}
		});
		submenu.add(chckbxmntmMtwZugriff);
		menuBar.add(submenu);

		JMenu helpMenu = new JMenu("Hilfe");

		JMenuItem aboutMenuItem = new JMenuItem("Über");
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
						"Funkfahrübung by ©S.Kunz\n"
								+ "Developer: Steffen Kunz\n"
								+ "Version: 1.0 \n"
								+ "\n©S.Kunz. All rights reserved",
						"Über Funkfahrübung by ©S.Kunz",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		frame.setJMenuBar(menuBar);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected void inkrementQueueWaiting(String auto) {
		switch (auto) {
		case "tlfMelbeck":
			tlfMelbeckWaiting = queueWaitingList;
			label_1.setText(String.valueOf(tlfMelbeckWaiting));
			break;
		case "lf16Melbeck":
			lf16MelbeckWaiting = queueWaitingList;
			label_2.setText(String.valueOf(lf16MelbeckWaiting));
			break;
		case "lf8Melbeck":
			lf8MelbeckWaiting = queueWaitingList;
			label_3.setText(String.valueOf(lf8MelbeckWaiting));
			break;
		case "mtwMelbeck":
			mtwMelbeckWaiting = queueWaitingList;
			label_4.setText(String.valueOf(mtwMelbeckWaiting));
			break;
		case "lf8De":
			lf8DeWaiting = queueWaitingList;
			label_5.setText(String.valueOf(lf8DeWaiting));
			break;
		case "tlfDe":
			tlfDeWaiting = queueWaitingList;
			label_6.setText(String.valueOf(tlfDeWaiting));
			break;
		case "mtwDe":
			mtwDeWaiting = queueWaitingList;
			label_7.setText(String.valueOf(mtwDeWaiting));
			break;
		case "rwEmbsen":
			rwEmbsenWaiting = queueWaitingList;
			label_8.setText(String.valueOf(rwEmbsenWaiting));
			break;
		case "tlfEmbsen":
			tlfEmbsenWaiting = queueWaitingList;
			label_9.setText(String.valueOf(tlfEmbsenWaiting));
			break;
		case "mtwEmbsen":
			mtwEmbsenWaiting = queueWaitingList;
			label_10.setText(String.valueOf(mtwEmbsenWaiting));
			break;
		case "tlfOerzen":
			tlfOerzenWaiting = queueWaitingList;
			label_11.setText(String.valueOf(tlfOerzenWaiting));
			break;
		case "tsfOerzen":
			tsfOerzenWaiting = queueWaitingList;
			label_12.setText(String.valueOf(tsfOerzenWaiting));
			break;
		case "mtwOerzen":
			mtwOerzenWaiting = queueWaitingList;
			label_13.setText(String.valueOf(mtwOerzenWaiting));
			break;
		case "tlfBarnstedt":
			tlfBarnstedtWaiting = queueWaitingList;
			label_14.setText(String.valueOf(tlfBarnstedtWaiting));
			break;
		case "tsfBarnstedt":
			tsfBarnstedtWaiting = queueWaitingList;
			label_15.setText(String.valueOf(tsfBarnstedtWaiting));
			break;
		case "mtwBarnstedt":
			mtwBarnstedtWaiting = queueWaitingList;
			label_16.setText(String.valueOf(mtwBarnstedtWaiting));
			break;
		case "lf8Kolkhagen":
			lf8KolkhagenWaiting = queueWaitingList;
			label_17.setText(String.valueOf(lf8KolkhagenWaiting));
			break;
		default:
			System.out.println("Unknown Auto: " + auto);
			break;
		}

	}

	protected void dekrementQueueWaiting() {
		queueWaitingList--;
		if (queueWaitingList <= 0) {
			queueWaitingList = 0;
		}

		tlfMelbeckWaiting--;
		label_1.setText(String.valueOf(tlfMelbeckWaiting));
		lf16MelbeckWaiting--;
		label_2.setText(String.valueOf(lf16MelbeckWaiting));
		lf8MelbeckWaiting--;
		label_3.setText(String.valueOf(lf8MelbeckWaiting));
		mtwMelbeckWaiting--;
		label_4.setText(String.valueOf(mtwMelbeckWaiting));
		lf8DeWaiting--;
		label_5.setText(String.valueOf(lf8DeWaiting));
		tlfDeWaiting--;
		label_6.setText(String.valueOf(tlfDeWaiting));
		mtwDeWaiting--;
		label_7.setText(String.valueOf(mtwDeWaiting));
		rwEmbsenWaiting--;
		label_8.setText(String.valueOf(rwEmbsenWaiting));
		tlfEmbsenWaiting--;
		label_9.setText(String.valueOf(tlfEmbsenWaiting));
		mtwEmbsenWaiting--;
		label_10.setText(String.valueOf(mtwEmbsenWaiting));
		tlfOerzenWaiting--;
		label_11.setText(String.valueOf(tlfOerzenWaiting));
		tsfOerzenWaiting--;
		label_12.setText(String.valueOf(tsfOerzenWaiting));
		mtwOerzenWaiting--;
		label_13.setText(String.valueOf(mtwOerzenWaiting));
		tlfBarnstedtWaiting--;
		label_14.setText(String.valueOf(tlfBarnstedtWaiting));
		tsfBarnstedtWaiting--;
		label_15.setText(String.valueOf(tsfBarnstedtWaiting));
		mtwBarnstedtWaiting--;
		label_16.setText(String.valueOf(mtwBarnstedtWaiting));
		lf8KolkhagenWaiting--;
		label_17.setText(String.valueOf(lf8KolkhagenWaiting));
		if (tlfMelbeckWaiting <= 0) {
			label_1.setText("");
		}
		if (lf16MelbeckWaiting <= 0) {
			label_2.setText("");
		}
		if (lf8MelbeckWaiting <= 0) {
			label_3.setText("");
		}
		if (mtwMelbeckWaiting <= 0) {
			label_4.setText("");
		}
		if (lf8DeWaiting <= 0) {
			label_5.setText("");
		}
		if (tlfDeWaiting <= 0) {
			label_6.setText("");
		}
		if (mtwDeWaiting <= 0) {
			label_7.setText("");
		}
		if (rwEmbsenWaiting <= 0) {
			label_8.setText("");
		}
		if (tlfEmbsenWaiting <= 0) {
			label_9.setText("");
		}
		if (mtwEmbsenWaiting <= 0) {
			label_10.setText("");
		}
		if (tlfOerzenWaiting <= 0) {
			label_11.setText("");
		}
		if (tsfOerzenWaiting <= 0) {
			label_12.setText("");
		}
		if (mtwOerzenWaiting <= 0) {
			label_13.setText("");
		}
		if (tlfBarnstedtWaiting <= 0) {
			label_14.setText("");
		}
		if (tsfBarnstedtWaiting <= 0) {
			label_15.setText("");
		}
		if (mtwBarnstedtWaiting <= 0) {
			label_16.setText("");
		}
		if (lf8KolkhagenWaiting <= 0) {
			label_17.setText("");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			switch (e.getItem().toString()) {
			// Barnstedt
			case "18-47-40":
				tlfBarnstedtQueuePanel.setVisible(true);
				break;
			case "18-40-40":
				tsfBarnstedtQueuePanel.setVisible(true);
				break;
			case "18-17-40":
				mtwBarnstedtQueuePanel.setVisible(true);
				break;
			// Deutsch Evern
			case "18-24-20":
				tlfDeutschEvernQueuePanel.setVisible(true);
				break;
			case "18-43-20":
				lf8DeutschEvernQueuePanel.setVisible(true);
				break;
			case "18-17-20":
				mtwDeutschEvernQueuePanel.setVisible(true);
				break;
			// Embsen
			case "18-51-30":
				rw1EmbsenQueuePanel.setVisible(true);
				break;
			case "18-47-30":
				lfEmbsenQueuePanel.setVisible(true);
				break;
			case "18-17-30":
				mtwEmbsenQueuePanel.setVisible(true);
				break;
			// Kolkhagen
			case "18-43-42":
				lf8KolkhagenQueuePanel.setVisible(true);
				break;
			// Melbeck
			case "18-17-10":
				mtwMelbeckQueuePanel.setVisible(true);
				break;
			case "18-43-10":
				lf8MelbeckQueuePanel.setVisible(true);
				break;
			case "18-44-10":
				lf16MelbeckQueuePanel.setVisible(true);
				break;
			case "18-47-10":
				tlfMelbeckQueuePanel.setVisible(true);
				break;
			// Oerzen
			case "18-40-32":
				tsfOerzenQueuePanel.setVisible(true);
				break;
			case "18-47-32":
				tlfOerzenQueuePanel.setVisible(true);
				break;
			case "18-17-32":
				mtwOerzenQueuePanel.setVisible(true);
				break;
			default:
				System.out.println("Something going wrong: "
						+ e.getItem().toString());
				break;
			}
		} else {
			switch (e.getItem().toString()) {
			// Barnstedt
			case "18-47-40":
				tlfBarnstedtQueuePanel.setVisible(false);
				break;
			case "18-40-40":
				tsfBarnstedtQueuePanel.setVisible(false);
				break;
			case "18-17-40":
				mtwBarnstedtQueuePanel.setVisible(false);
				break;
			// Deutsch Evern
			case "18-24-20":
				tlfDeutschEvernQueuePanel.setVisible(false);
				break;
			case "18-43-20":
				lf8DeutschEvernQueuePanel.setVisible(false);
				break;
			case "18-17-20":
				mtwDeutschEvernQueuePanel.setVisible(false);
				break;
			// Embsen
			case "18-51-30":
				rw1EmbsenQueuePanel.setVisible(false);
				break;
			case "18-47-30":
				lfEmbsenQueuePanel.setVisible(false);
				break;
			case "18-17-30":
				mtwEmbsenQueuePanel.setVisible(false);
				break;
			// Kolkhagen
			case "18-43-42":
				lf8KolkhagenQueuePanel.setVisible(false);
				break;
			// Melbeck
			case "18-17-10":
				mtwMelbeckQueuePanel.setVisible(false);
				break;
			case "18-43-10":
				lf8MelbeckQueuePanel.setVisible(false);
				break;
			case "18-44-10":
				lf16MelbeckQueuePanel.setVisible(false);
				break;
			case "18-47-10":
				tlfMelbeckQueuePanel.setVisible(false);
				break;
			// Oerzen
			case "18-40-32":
				tsfOerzenQueuePanel.setVisible(false);
				break;
			case "18-47-32":
				tlfOerzenQueuePanel.setVisible(false);
				break;
			case "18-17-32":
				mtwOerzenQueuePanel.setVisible(false);
				break;
			default:
				System.out.println("Something going wrong: "
						+ e.getItem().toString());
				break;
			}
		}
	};

	public void setLabel(String koordinate, String bemerkung, String frage,
			String antwort, String auto, String rufname, int aktuellerStand) {
		if (lblKoordinaten.getText().startsWith("ND")) {
			lblKoordinaten.setToolTipText("Nordpol Dora");
		} else if (lblKoordinaten.getText().startsWith("PE")) {
			lblKoordinaten.setToolTipText("Paula Emil");
		} else if (lblKoordinaten.getText().startsWith("NE")) {
			lblKoordinaten.setToolTipText("Nordpol Emil");
		} else if (lblKoordinaten.getText().startsWith("PD")) {
			lblKoordinaten.setToolTipText("Paula Dora");
		}
		lblAuto.setText(rufname);
		lblBemerkung.setText(bemerkung);
		lblKoordinaten.setText(koordinate);
		lblFrage.setText(frage);
		lblAntwort.setText(antwort);
		button = auto;
		if (!offlineArbeiten) {
			String sendingContent = auto + " " + aktuellerStand;
			try {
				// Eingabe-Reader/Ausgabe-Writer erzeugen:
				PrintWriter out = new PrintWriter(
						socketServer.getOutputStream(), true);
				// Ab zum Server:
				out.println(sendingContent);
				out.flush();
				// User hat "X" eingegeben: Socket dichtmachen.
			} catch (IOException e) {
				System.out.println("IOException: " + e.getMessage());
				System.exit(-1);
			}
		}
	}

	public void setIPAdresse(String ipAdress) {
		if (ipAdress.equals("")) {
			offlineArbeiten = true;
		} else {
			offlineArbeiten = false;
			// Server-Verbindung aufbauen:
			try {
				socketServer = new Socket(ipAdress, 13112);
			} catch (UnknownHostException ex) {
				System.out
						.println("UnknownHostException bei Verbindung zu Host 'localhost', Port 13000: "
								+ ex.getMessage());
			} catch (IOException ex) {
				System.out
						.println("IOException bei Verbindung zu Host 'localhost', Port 13000: "
								+ ex.getMessage());
			}
		}
	}
}
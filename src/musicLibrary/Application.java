package musicLibrary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Application {

	private JFrame frame;
	private JTextField tfPath;
	private JTable table;
	private DefaultTableModel model;
	public ArrayList<File> arLi;
	public ArrayList<File> displayedArLi;
	public ArrayList<File> categories;
	public JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		categories = new ArrayList<File>();
		
		
		frame = new JFrame();
		frame.setFont(new Font("Calibri", Font.PLAIN, 12));
		//frame.getContentPane().setBackground(Color.WHITE);
		ImageIcon imgicon = new ImageIcon("imageIcon.png");
		frame.setIconImage(imgicon.getImage());
		
		frame.getContentPane().setLayout(new BorderLayout());
		ImageIcon ii = new ImageIcon("image.jpg");
		frame.setContentPane(new JLabel(ii));
		
		frame.setBounds(128, 128, 676, 500);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Music Library");
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 102, 629, 316);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(472, 23, 172, 29);
		frame.getContentPane().add(comboBox);
		comboBox.setFont(new Font("Calibri", Font.ITALIC, 12));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPath.setText(comboBox.getSelectedItem().toString());
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(472, 23, 172, 29);
		frame.getContentPane().add(scrollPane_1);

		JCheckBox chckbxAllFiles = new JCheckBox("Όλα ");
		chckbxAllFiles.setBounds(26, 55, 99, 23);
		JCheckBox chckbxpdf = new JCheckBox(".pdf");
		chckbxpdf.setBounds(161, 55, 99, 23);
		JCheckBox chckbxmusx = new JCheckBox(".MUSX");
		chckbxmusx.setBounds(26, 81, 99, 23);
		JCheckBox chckbxmp4 = new JCheckBox(".mp4");
		chckbxmp4.setBounds(300, 81, 99, 23);
		JCheckBox chckbxmp3 = new JCheckBox(".mp3");
		chckbxmp3.setBounds(300, 55, 99, 23);
		JCheckBox chckbxmid = new JCheckBox(".MID");
		chckbxmid.setBounds(161, 81, 99, 23);
		JButton btnGetPath = new JButton("Επιλογή...");
		btnGetPath.setBounds(26, 23, 121, 29);
		ImageIcon imIcGetPath = new ImageIcon("getPath.png");
		Image img = imIcGetPath.getImage();
		Image newImg = img.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
		imIcGetPath = new ImageIcon(newImg);
		btnGetPath.setIcon(imIcGetPath);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.setBounds(159, 23, 121, 29);
		
		
		chckbxAllFiles.setBackground(new Color(255, 255, 255));
		chckbxmusx.setBackground(new Color(255, 255, 255));
		chckbxmp4.setBackground(new Color(255, 255, 255));
		chckbxmp3.setBackground(new Color(255, 255, 255));
		chckbxmid.setBackground(new Color(255, 255, 255));
		
		
		
		
		chckbxmp4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxmp4.isSelected()) {
					chckbxAllFiles.setSelected(false);
				}
			}
		});
		
		frame.getContentPane().add(chckbxmp4);
		
		
		
		chckbxmp3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxmp3.isSelected()) {
					chckbxAllFiles.setSelected(false);
				}
			}
		});
		
		chckbxAllFiles.setOpaque(false);
		chckbxmp3.setOpaque(false);
		chckbxmp4.setOpaque(false);
		chckbxmusx.setOpaque(false);
		chckbxpdf.setOpaque(false);
		chckbxmid.setOpaque(false);
		
		
		frame.getContentPane().add(chckbxmp3);
		
		chckbxmusx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxmusx.isSelected()) {
					chckbxAllFiles.setSelected(false);
				}
			}
		});
		
		frame.getContentPane().add(chckbxmusx);
		
		
		chckbxpdf.setBackground(new Color(255, 255, 255));
		chckbxpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxpdf.isSelected()) {
					chckbxAllFiles.setSelected(false);
				}
			}
		});
		
		frame.getContentPane().add(chckbxpdf);
		
		chckbxmid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxmid.isSelected()) {
					chckbxAllFiles.setSelected(false);
				}
			}
		});
		frame.getContentPane().add(chckbxmid);
		
		
		chckbxAllFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAllFiles.isSelected()) {
					chckbxmusx.setSelected(false);
					chckbxpdf.setSelected(false);
					chckbxmid.setSelected(false);
					chckbxmp3.setSelected(false);
					chckbxmp4.setSelected(false);
				}
			}
		});
		
		frame.getContentPane().add(chckbxAllFiles);
		
		
		btnGetPath.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 12));
		btnGetPath.setBackground(UIManager.getColor("info"));
		
		btnGetPath.addActionListener(new ActionListener() {
			Component c = new JFileChooser();
			public void actionPerformed(ActionEvent arg0) {
				//choose path
				tfPath.setText(promptForFolder(c));
				
				//add all files of directory and sub-directories to an ArrayList
				categories.clear();
				List list = listf(tfPath.getText(), categories);
				//System.out.println(list);
				arLi = new ArrayList<File>(list);
				displayedArLi = new ArrayList<File>(arLi);
				//populate table
				
				createTable(displayedArLi, scrollPane);
				
				createComboBox(categories);
			}
		});
		frame.getContentPane().add(btnGetPath);
		
		tfPath = new JTextField();
		tfPath.setBounds(290, 23, 172, 29);
		frame.getContentPane().add(tfPath);
		tfPath.setColumns(10);
		
		
		btnSearch.setBackground(UIManager.getColor("info"));
		btnSearch.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 12));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(arLi);
				if (displayedArLi == null) {
					JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε έναν φάκελο (Επιλογή...)");
				}
				else {
					chckbxAllFiles.setSelected(false);
					displayedArLi.clear();
					String str = tfPath.getText();
					for (File file : arLi) {
						String s = file.getAbsolutePath().toString().toLowerCase();
						String pdf = "";
						String midi = "";
						String musx = "";
						String mp3 = "";
						String mp4 = "";
						if(chckbxpdf.isSelected()) {
							pdf = ".pdf";
						}
						if(chckbxmid.isSelected()) {
							midi = ".mid";
						}
						if(chckbxmusx.isSelected()) {
							musx = ".musx";
						}
						if(chckbxmp3.isSelected()) {
							mp3 = ".mp3";
						}
						if(chckbxmp4.isSelected()) {
							mp4 = ".mp4;";
						}
						
						
						if (s.contains(str.toLowerCase().toLowerCase()) && s.contains(pdf.toLowerCase()) && s.contains(midi.toLowerCase()) && s.contains(musx.toLowerCase()) && s.contains(mp3.toLowerCase()) && s.contains(mp4.toLowerCase())) {
							displayedArLi.add(file);
						}
					}
					createTable(displayedArLi, scrollPane);
				}
			}
		});
		frame.getContentPane().add(btnSearch);
		
		
		
		
		
		JButton button = new JButton("Ταξεινόμηση");
		button.setBounds(533, 423, 111, 29);
		button.setFont(new Font("Calibri", Font.ITALIC, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Collections.sort(displayedArLi, new Comparator<File>() {
					@Override
					public int compare(File f1, File f2) {
						return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
					}
				});
				createTable(displayedArLi, scrollPane);
			}
			
		});
		frame.getContentPane().add(button);
	}
	//promt for folder
	public String promptForFolder( Component parent )
	{
	    JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

	    if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
	    {
	        return fc.getSelectedFile().getAbsolutePath();
	    }

	    return null;
	}
	
	
	public List<File> listf(String directoryName, ArrayList<File> categories) {
        File directory = new File(directoryName);
        
        
        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                //System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath(), categories));
                categories.add(file);
            }
        }
        //System.out.println(fList);
        return resultList;
    }
	
	public void createTable(ArrayList<File> displayedArLi, JScrollPane scrollPane) {
		String[] columnNames = {"No", "Διεύθυνση", "Όνομα", "Τελευταία Αλλαγή"};
		Object[][] data = new Object[displayedArLi.size()][columnNames.length];
		
		frame.getContentPane().add(scrollPane);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for (int i = 0; i < displayedArLi.size(); i++) {
			data[i][0] = i;
			data[i][1] = displayedArLi.get(i).getAbsolutePath();
			data[i][2] = displayedArLi.get(i).getName();
			data[i][3] = sdf.format(displayedArLi.get(i).lastModified());
		}
		
		model = new DefaultTableModel(data, columnNames) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		//JTable table = new JTable(data, columnNames);
		JTable table = new JTable(model);
		table.setRowHeight(21);
		table.setGridColor(new Color(210, 216, 224));
		table.getTableHeader().setBackground(new Color(175, 190, 214));
		table.getTableHeader().setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 12));
		
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent me) {
				
				int row = table.rowAtPoint(me.getPoint());
				int col = table.columnAtPoint(me.getPoint());
				//System.out.println(row + " ,  " + col); 
				if (me.getClickCount() == 2) {
					if (col == 1) {
						try {
							if (displayedArLi.get(row).isDirectory()) {
								Desktop.getDesktop().open(displayedArLi.get(row));
							}
							else if(displayedArLi.get(row).isFile()) {
								Desktop.getDesktop().open(displayedArLi.get(row).getParentFile());
							}
						} catch(IOException e) {
							JOptionPane.showMessageDialog(frame, "file doesn't exist");
							e.printStackTrace();
						}
					}
					if (col == 2) {
						try {
							Desktop.getDesktop().open(displayedArLi.get(row));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(frame, "file doesn't exist");
							e.printStackTrace();
						}
					}
				}
			}
		});
	}
	
	public void createComboBox(ArrayList<File> categories) {
		ArrayList<String> cat = new ArrayList<String>();
		for(File file : categories) {
			try {
				Integer.parseInt(file.getName().charAt(0) + "");
			}
			catch(Exception ex){
				cat.add(file.getName());
			}
		}
		comboBox.setModel(new DefaultComboBoxModel(cat.toArray()));
	}
}

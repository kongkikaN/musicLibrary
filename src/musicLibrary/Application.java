package musicLibrary;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Application {

	private JFrame frame;
	private JTextField tfPath;
	private JTable table;
	private DefaultTableModel model;
	public ArrayList<File> arLi;
	public ArrayList<File> displayedArLi;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 414, 247);
		
		JButton btnGetPath = new JButton("Get Path");
		
		btnGetPath.addActionListener(new ActionListener() {
			Component c = new JFileChooser();
			public void actionPerformed(ActionEvent arg0) {
				//choose path
				tfPath.setText(promptForFolder(c));
				
				//add all files of directory and sub-directories to an ArrayList
				List list = listf(tfPath.getText());
				//System.out.println(list);
				arLi = new ArrayList<File>(list);
				displayedArLi = new ArrayList<File>(arLi);
				//populate table
				
				createTable(displayedArLi, scrollPane);
				
			}
		});
		
		
		btnGetPath.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnGetPath);
		
		tfPath = new JTextField();
		tfPath.setBounds(208, 12, 172, 20);
		frame.getContentPane().add(tfPath);
		tfPath.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(arLi);
				if (displayedArLi == null) {
					JOptionPane.showMessageDialog(frame, "please select a folder (Browse)");
				}
				else {
					displayedArLi.clear();
					String str = tfPath.getText();
					for (File file : arLi) {
						if (file.getAbsolutePath().toString().toLowerCase().contains(str.toLowerCase())) {
							displayedArLi.add(file);
						}
					}
					
					createTable(displayedArLi, scrollPane);
				}
			}
		});
		
		btnSearch.setBounds(109, 11, 89, 23);
		frame.getContentPane().add(btnSearch);
		
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
	
	
	public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                //System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    }
	
	public void createTable(ArrayList<File> displayedArLi, JScrollPane scrollPane) {
		String[] columnNames = {"Path", "Name"};
		Object[][] data = new Object[displayedArLi.size()][columnNames.length];
		
		
		
		
		frame.getContentPane().add(scrollPane);
		
		for (int i = 0; i < displayedArLi.size(); i++) {
			data[i][0] = displayedArLi.get(i).getAbsolutePath();
			data[i][1] = displayedArLi.get(i).getName();
		}
		
		model = new DefaultTableModel(data, columnNames);
		//JTable table = new JTable(data, columnNames);
		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent me) {
				
				int row = table.rowAtPoint(me.getPoint());
				int col = table.columnAtPoint(me.getPoint());
				//System.out.println(row + " ,  " + col); 
				if (col == 0) {
					String str = table.getValueAt(row, col).toString();
					
				}
				if (col == 1) {
					try {
						Desktop.getDesktop().open(displayedArLi.get(row));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame, "file doesn't exist");
						e.printStackTrace();
					}
				}
			}
		});
		
	}
	
}

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 397);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\ayn_2\\eclipse-workspace\\Java\\Stock_Management_System\\src\\assets\\Walmart-Banner.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(360, 180,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg); 
		contentPane.setLayout(null);
		JLabel label = new JLabel(imageIcon);
		label.setBounds(0, 0, 623, 67);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(0, 74, 306, 286);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setForeground(SystemColor.textHighlight);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrice.setBackground(SystemColor.textHighlight);
		lblPrice.setBounds(10, 211, 122, 19);
		panel.add(lblPrice);
		
		textField_3 = new JTextField();
		textField_3.setForeground(SystemColor.textHighlight);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.ORANGE);
		textField_3.setBounds(158, 211, 96, 19);
		panel.add(textField_3);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setForeground(SystemColor.textHighlight);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuantity.setBackground(SystemColor.textHighlight);
		lblQuantity.setBounds(10, 141, 122, 19);
		panel.add(lblQuantity);
		
		textField_2 = new JTextField();
		textField_2.setForeground(SystemColor.textHighlight);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.ORANGE);
		textField_2.setBounds(158, 141, 96, 19);
		panel.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setBounds(10, 74, 122, 19);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setForeground(SystemColor.textHighlight);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.ORANGE);
		textField_1.setBounds(158, 74, 96, 19);
		panel.add(textField_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product pt = new Product();
				pt.setProduct_name(textField_1.getText());
				pt.setQTY(Integer.parseInt(textField_2.getText()));
				pt.setPrice(Double.parseDouble(textField_3.getText()));
				ProductDAO_Imp dao = new ProductDAO_Imp();
				dao.save(pt);
				refreshTextField();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setBounds(29, 255, 85, 21);
		panel.add(btnNewButton);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(SystemColor.controlHighlight);
		toolBar.setBounds(0, 0, 306, 25);
		panel.add(toolBar);
		
		Button button_2 = new Button("Refresh");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
			}
		});
		button_2.setFont(new Font("Dialog", Font.BOLD, 12));
		button_2.setForeground(SystemColor.textHighlight);
		toolBar.add(button_2);
		
		Button button_1 = new Button("Delete");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO_Imp dao = new ProductDAO_Imp();
				dao.delete(Integer.parseInt(JOptionPane.showInputDialog("Enter product ID to delete")));
			}
		});
		button_1.setFont(new Font("Dialog", Font.BOLD, 12));
		button_1.setForeground(SystemColor.textHighlight);
		toolBar.add(button_1);
		
		Button button = new Button("Search");
		button.setForeground(SystemColor.textHighlight);
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product pt = new Product();
				ProductDAO_Imp dao = new ProductDAO_Imp();
				pt = dao.get(Integer.parseInt(JOptionPane.showInputDialog("Enter product ID to search")));
				textField_1.setText(pt.getProduct_name());
				textField_2.setText(""+pt.getQTY());
				textField_3.setText(""+pt.getPrice());
			}
		});
		toolBar.add(button);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product pt = new Product();
				pt.setProduct_name(textField_1.getText());
				pt.setQTY(Integer.parseInt(textField_2.getText()));
				pt.setPrice(Double.parseDouble(textField_3.getText()));
				pt.setId(Integer.parseInt(JOptionPane.showInputDialog("Enter product ID to update")));
				ProductDAO_Imp dao = new ProductDAO_Imp();
				dao.update(pt);
				refreshTextField();
			}
		});
		btnUpdate.setForeground(SystemColor.textHighlight);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnUpdate.setBounds(136, 255, 109, 21);
		panel.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 74, 316, 286);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 255));
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Product Name", "QTY", "Price"
				}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		UpdateTable();
	}
	void refreshTextField() {
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_1.requestFocus();
	}
	void UpdateTable() {
		 ProductDAO_Imp dao = new ProductDAO_Imp();
	        List<Product> list = dao.list();
	        DefaultTableModel DFT = (DefaultTableModel) table.getModel();
	        DFT.setRowCount(0);
	        for(Product pt: list)
	        {
	            DFT.addRow(new Object[]{pt.getId(),pt.getProduct_name(),pt.getQTY(),pt.getPrice()});
	        }     
	}
}


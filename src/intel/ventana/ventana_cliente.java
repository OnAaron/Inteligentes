package intel.ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import jade.util.Logger;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.logging.Level;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;

public class ventana_cliente {

	public JFrame frame;
	public JTextArea textArea;
	public JScrollPane scroll;
	public static String text;
	public static ArrayList<Integer> res;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana_cliente window = new ventana_cliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ventana_cliente() {
		//initialize();
	}
	public void setText(String text) {
		ventana_cliente.text = text;
	}
	public void setLista(ArrayList<Integer> res) {
		ventana_cliente.res = res;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void initialize() {
		/*if(frame == null) {
			frame = new JFrame();
			frame.setSize(1300, 1000);
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			textArea = new JTextArea(1300,1100);
			scroll = new JScrollPane(textArea);
			scroll.setBounds(new Rectangle(0, 0, 1293,960));
			frame.show(true);
		}
		else {
			textArea.setText(null);
		}*/
		if(frame==null) {
			frame = new JFrame("test");
			//frame.setSize(1000, 700);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setLayout(null);
			textArea = new JTextArea(1000,700);
			//textArea.setText("hola");
			textArea.setFont(new Font("Arial", Font.PLAIN, 18));
			scroll = new JScrollPane(textArea);
			scroll.setBounds(new Rectangle(0, 0, 1000, 670));
			frame.add(scroll);
			frame.show(true);
			//ArrayList<Integer> res = new ArrayList<>();
		}
		else {
			textArea.setText(null);
		}
		textArea.setText(text);
		pintar(textArea,res);

	}
	public static void pintar(JTextArea area, ArrayList<Integer> lista) {
		if (area.getText().length() >= 1) {
			DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
			Highlighter h = area.getHighlighter();
			h.removeAllHighlights();
			//String text = area.getText();
			int tam = lista.get(0);
			for(int i=1;i<lista.size();i++) {
				try {
					h.addHighlight(lista.get(i), lista.get(i)+tam, highlightPainter);
				} catch (BadLocationException ex) {
					Logger.getLogger(Color.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else {
			JOptionPane.showMessageDialog(area, "la palabra a buscar no puede ser vacia");
		}
	}

}

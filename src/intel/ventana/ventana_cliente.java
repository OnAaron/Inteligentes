package intel.ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.TextArea;

public class ventana_cliente {

	public static JFrame frame;
	public static JTextArea textArea;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void setText(String text) {
		ventana_cliente.text = text;
	}
	public void setLista(ArrayList<Integer> res) {
		ventana_cliente.res = res;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public  static void initialize() {
		if(frame == null) {
			frame = new JFrame();
			textArea = new JTextArea();
		}
		else {
			textArea.setText(null);
		}
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea.setFont(new Font("Dialog", Font.PLAIN, 18));
		textArea.setText(text);
		textArea.setEditable(false);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		System.out.println(res);
		pintar(textArea, res);
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

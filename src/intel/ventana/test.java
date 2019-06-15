package intel.ventana;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import jade.util.Logger;

public class test extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextArea jt;
	private static JScrollPane scroll;
	private String text;
	private ArrayList<Integer>res;
	
	public test() {
		
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public void setLista(ArrayList<Integer>res) {
		this.res = res;
	}
	
	public void inicializar() {
		if(jt == null) {
			setSize(1100,750);
			setResizable(false);
			setTitle("Fichero");
			setLocation(200, 100);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setBackground(Color.white);
			setLayout(null);
			scroll = new JScrollPane();
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jt = new JTextArea();
			//jt.setText(text);
			jt.setEditable(false);
			jt.setLineWrap(true);
			jt.setWrapStyleWord(true);
			jt.setBounds(0,0,1000,600);
			jt.setBorder(new LineBorder(Color.BLACK));
			scroll.setBounds(0, 0, 1095, 725);
			scroll.getViewport().setBackground(Color.WHITE);
			scroll.getViewport().add(jt);
			add(scroll);
			setVisible(true);
		}
		else {
			jt.setText(null);
		}
		jt.setText(this.text);
		pintar(jt,this.res);
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
	public static void main(String[]args) {
		new test();
	}

}

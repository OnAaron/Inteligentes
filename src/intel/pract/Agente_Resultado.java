package intel.pract;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import intel.ventana.ventana_cliente;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.util.Logger;

public class Agente_Resultado extends Agent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ventana_cliente ventana;

	public void setup() {
		System.out.println("Soy el agente Resultado");
		DFAgentDescription directorio = new DFAgentDescription();
		directorio.setName(getAID());
		ServiceDescription servicio = new ServiceDescription();
		servicio.setName("Buscador");
		servicio.setType("resultados");
		servicio.addOntologies("Ontologia");
		servicio.addLanguages(new SLCodec().getName());
		directorio.addServices(servicio);
		try {
			DFService.register(this, directorio);
		}
		catch(FIPAException e) {
			System.err.println("Error " +e.getMessage());
		}
		addBehaviour(new CyclicBehaviour(this){
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			public void action() {
				if(ventana == null) {
					ventana  = new ventana_cliente();
				}
				Comunicaciones.enviarMensaje(this.myAgent, "busqueda", "Soy resultado");
				ACLMessage msg=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));//text original
				ACLMessage msg2=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));//lista indices
				try {
					@SuppressWarnings("unchecked")
					ArrayList<Integer> r = (ArrayList<Integer>)msg2.getContentObject();
					for(int i=0;i<r.size();i++) System.out.println(r.get(i));
					try {
						//ventana = new ventana_cliente((String)msg.getContentObject(),(ArrayList<Integer>)msg2.getContentObject());
						ventana.setText((String)msg.getContentObject());
						ventana.setLista((ArrayList<Integer>)msg2.getContentObject());
						System.out.println(ventana.text);
						System.out.println(ventana.res.toString());
						ventana.initialize();
						ventana.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}

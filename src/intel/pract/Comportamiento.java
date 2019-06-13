package intel.pract;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class Comportamiento extends CyclicBehaviour {

	private static final long serialVersionUID = 1L;

	public void action() {
		ACLMessage msg=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		ACLMessage msg2=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		//System.out.println("hola");
		ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
		try {
			if(((String)msg.getContentObject()).equals("Soy resultado")) {
				System.out.println("if "+(String)msg.getContentObject());
				aclMessage.addReceiver(msg.getSender());
				System.out.println((String)msg2.getContentObject());
				//lamada a metodo
				aclMessage.setContentObject((Serializable)msg2.getContentObject());
			}
			else {
				System.out.println((String)msg2.getContentObject());
				aclMessage.addReceiver(msg2.getSender());
				System.out.println("else "+(String)msg.getContentObject());
				aclMessage.setContentObject((Serializable)msg.getContentObject());
			}
			//Comunicaciones.buscarAgente(this, "resultados");
			//aclMessage.addReceiver(msg.getSender());
			aclMessage.setOntology("ontologia");
			aclMessage.setLanguage(new SLCodec().getName());
			aclMessage.setEnvelope(new Envelope());
			aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.myAgent.send(aclMessage);  
	}

	public static boolean coincide(String txt, String clave, int conTxt) {
		boolean result = true;
		int conCl = 0;
		char t;
		char c;
		while((conCl< clave.length()) && result) {
			t = clave.charAt(conCl);
			c = txt.charAt(conTxt);
			if(c != t) {
				result = false;
			}
			conTxt++;
			conCl++;
		}
		return result;
	}
	public static ArrayList algoritmo(String txt, String clave) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conTxt = 0;
		int conCl = 0;
		char t;
		char c;
		while(conTxt < txt.length()) {
			t = txt.charAt(conTxt);
			c = clave.charAt(conCl);
			if(t == c) {
				if(coincide(txt,clave,conTxt)) {
					lista.add(conTxt);
				}
			}
			conTxt++;
		}
		return lista;
	}
}
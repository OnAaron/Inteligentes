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
		//Msg de resultados
		ACLMessage msg=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		//Msg texto
		ACLMessage msg2=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		//Msg patron
		ACLMessage msg3=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		//Msg modo
		ACLMessage msg4=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
		ACLMessage aclMessage2 = new ACLMessage(ACLMessage.INFORM);
		
		try {
			/*if(((String)msg.getContentObject()).equals("Soy resultado")) {
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
			}*/
			aclMessage.addReceiver(msg.getSender());
			aclMessage2.addReceiver(msg.getSender());
			aclMessage.setContentObject((Serializable)msg2.getContentObject());
			aclMessage2.setContentObject((Serializable)buscar((String)msg2.getContentObject(),(String)msg3.getContentObject(),(String)msg4.getContentObject()));
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
		this.myAgent.send(aclMessage2);
	}
	
	public static ArrayList<Integer> buscar(String text, String pattern,String modo){
		ArrayList<Integer> res = new ArrayList<>();
		switch(Integer.parseInt(modo)) {
		case 1:{
			//PaSimple+No coinicde
			res = algoritmo1(text, pattern);
			break;
		}
		case 2:{
			//PaSimple + Coincide
			res = algoritmo2(text, pattern);
			break;
		}
		case 3:{
			//Completa+No coinc
			res = algoritmo3(text, pattern);
			break;
		}
		case 4:{
			//Compñeta+Coind
			res = algoritmo4(text, pattern);
			break;
		}
		}
		return res;
	}
	
	public static boolean coincide1(String txt, String clave, int conTxt) {
		boolean result = true;
		int conCl = 0;
		char t;
		char c;
		String tS;
		String cS;
		while((conCl< clave.length()) && result) {
			t = clave.charAt(conCl);
			c = txt.charAt(conTxt);
			tS = Character.toString(t);
			cS = Character.toString(c);
			if(!tS.equalsIgnoreCase(cS)) {
				result = false;
			}
			conTxt++;
			conCl++;
		}
		return result;
	}


	public static ArrayList<Integer> algoritmo1(String txt, String clave) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conTxt = 0;
		int conCl = 0;
		char t;
		char c;
		String tS;
		String cS;
		lista.add(clave.length());
		while(conTxt < txt.length()) {
			t = txt.charAt(conTxt);
			c = clave.charAt(conCl);
			tS = Character.toString(t);
			cS = Character.toString(c);
			if(tS.equalsIgnoreCase(cS)) {
				if(coincide1(txt,clave,conTxt)) {
					lista.add(conTxt);
				}
			}
			conTxt++;
		}
		return lista;
	}
	
	
	public static boolean coincide2(String txt, String clave, int conTxt) {
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
	

	public static ArrayList<Integer> algoritmo2(String txt, String clave) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conTxt = 0;
		int conCl = 0;
		char t;
		char c;
		lista.add(clave.length());
		while(conTxt < txt.length()) {
			t = txt.charAt(conTxt);
			c = clave.charAt(conCl);
			if(t == c) {
				if(coincide2(txt,clave,conTxt)) {
					lista.add(conTxt);
				}
			}
			conTxt++;
		}
		return lista;
	}

	
	
	public static ArrayList<Integer> algoritmo3(String txt, String clave) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conTxt = 0;
		int puntero = 0;
		String palTxt = "";
		char t;
		lista.add(clave.length());
		while(conTxt < txt.length()) {
			t = txt.charAt(conTxt);
			if((t >= 0  && t <= 47) || (t >= 58 && t <= 64) || (t >= 91 && t <= 96) || (t >= 123 && t <= 126)){
				if(palTxt.equalsIgnoreCase(clave)) {
					lista.add(puntero);
				}
				palTxt = "";
				puntero = conTxt+1;
			}else {
				palTxt = palTxt + t;
			}

			conTxt++;
		}
		return lista;
	}


	public static ArrayList<Integer> algoritmo4(String txt, String clave) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int conTxt = 0;
		int puntero = 0;
		String palTxt = "";
		char t;
		lista.add(clave.length());
		while(conTxt < txt.length()) {
			t = txt.charAt(conTxt);
			if((t >= 0  && t <= 47) || (t >= 58 && t <= 64) || (t >= 91 && t <= 96) || (t >= 123 && t <= 126)){
				if(palTxt.equals(clave)) {
					lista.add(puntero);
				}
				palTxt = "";
				puntero = conTxt+1;
			}else {
				palTxt = palTxt + t;
			}

			conTxt++;
		}
		return lista;
	}
}
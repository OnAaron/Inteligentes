package intel.pract;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Comportamiento extends CyclicBehaviour {
	
	private static final long serialVersionUID = 1L;

	public void action() {
		try {
			System.out.println(msg.getSender().getName()+":"+ (String)msg.getContentObject());
			//Llamada a Buscar
			ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
			aclMessage.addReceiver(msg.getSender());
			
		}
		catch() {
			
		}
		return;
	}
	
	public void Buscar() {
		return;
	}
public static void enviarMensaje(Agent agent, String tipo, Object objeto){
		
		DFAgentDescription dfd= Buscar();
		try
		{
		if(dfd!=null){
		ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
		aclMessage.addReceiver(dfd.getName());
		aclMessage.setOntology("ontologia");
		aclMessage.setLanguage(new SLCodec().getName());
		aclMessage.setEnvelope(new Envelope());
		aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
		aclMessage.setContentObject((Serializable)objeto);
		agent.send(aclMessage);
		}
		}
		catch(IOException e)
		{
		JOptionPane.showMessageDialog(null, "Agente "+agent.getName()+": "+e.getMessage(), "Error",
		JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		}
	}

}

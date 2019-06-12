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

}

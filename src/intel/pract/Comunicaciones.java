package intel.pract;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Comunicaciones {
	public static DFAgentDescription buscarAgente(Agent agente, String tipo) {
		DFAgentDescription template=new DFAgentDescription();
		ServiceDescription templateSd=new ServiceDescription();
		templateSd.setType(tipo);
		template.addServices(templateSd);
		SearchConstraints agents = new SearchConstraints();
		agents.setMaxResults(new Long(1));
		try {
			DFAgentDescription [] res = DFService.search(agente,template);
			if(res.length > 0) {
				for(int i=0;i<res.length;i++) {
					DFAgentDescription agente_res = res[i];
					@SuppressWarnings("rawtypes")
					Iterator cursor = agente_res.getAllServices();
					while(cursor.hasNext()) {
						ServiceDescription sc = (ServiceDescription) cursor.next();
						if(sc.getType().equals(tipo)) {
							return agente_res;
						}
					}
				}
			}
			else {
				System.err.println("No hay agentes que den ese servicio");
			}
		}
		catch(FIPAException e) {
			e.printStackTrace();

		}
		return null;
	}
	public static void enviarMensaje(Agent agent, String tipo, Object objeto){

		DFAgentDescription dfd= buscarAgente(agent, tipo);
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
			e.printStackTrace();
		}
	}
}

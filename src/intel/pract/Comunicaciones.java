package intel.pract;

import java.util.Iterator;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

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
	public static void enviarMensaje() {
		
	}
}

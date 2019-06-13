package intel.pract;

import java.util.Scanner;

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
		//idfjhsdhjkadfhjd
		addBehaviour(new CyclicBehaviour(this){
			private static final long serialVersionUID = 1L;

			public void action() {
				
				Comunicaciones.enviarMensaje(this.myAgent, "busqueda", "Soy resultado");
				ACLMessage msg=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
				try {
					System.out.println((String)msg.getContentObject());
					
					try {
						ventana = new ventana_cliente((String)msg.getContentObject());
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

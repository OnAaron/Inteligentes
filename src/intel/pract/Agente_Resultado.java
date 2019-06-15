package intel.pract;



import java.util.ArrayList;
import intel.ventana.test;
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
	static test ventana;

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
					ventana  = new test();
				}
				Comunicaciones.enviarMensaje(this.myAgent, "busqueda", "Soy resultado");
				ACLMessage msg=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));//text original
				ACLMessage msg2=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));//lista indices
				try {
					//ventana = new test((String)msg.getContentObject(),(ArrayList<Integer>)msg2.getContentObject());
					ventana.setText((String)msg.getContentObject());
					ventana.setLista((ArrayList<Integer>)msg2.getContentObject());
					ventana.inicializar();
					//ventana.frame.setVisible(true);

				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}

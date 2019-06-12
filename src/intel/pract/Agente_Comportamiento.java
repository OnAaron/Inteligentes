package intel.pract;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Agente_Comportamiento extends Agent {
	private static final long serialVersionUID = 1L;

	public void setup() {
		DFAgentDescription directorio = new DFAgentDescription();
		directorio.setName(getAID());
		System.out.println(directorio.getName());
		ServiceDescription servicio = new ServiceDescription();
		servicio.setName("Buscador");
		servicio.setType("busqueda");
		servicio.addOntologies("Ontologia");
		servicio.addLanguages(new SLCodec().getName());
		directorio.addServices(servicio);
		try {
			DFService.register(this, directorio);
		}
		catch(FIPAException e) {
			System.err.println("Error " +e.getMessage());
		}
		addBehaviour(new Comportamiento());
		//Comunicaciones.enviarMensaje(this.myAgent, "buscar", temp);
	}

}

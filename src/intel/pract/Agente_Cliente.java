package intel.pract;

import jade.core.behaviours.CyclicBehaviour;

import java.util.Scanner;

import jade.core.Agent;

public class Agente_Cliente extends Agent{

	private static final long serialVersionUID = 1L;

	public void setup() {
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			private static final long serialVersionUID = 1L;

			public void action() {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el fichero a buscar: ");
				String fich = scanner.nextLine();
				System.out.println(temp);
				Comunicaciones.enviarMensaje(this.myAgent, "busqueda", temp);
			}
		});
	}
}
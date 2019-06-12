package intel.pract;

import jade.core.behaviours.CyclicBehaviour;

import java.util.Scanner;

import jade.core.Agent;

public class Agente_Cliente extends Agent{
	public void setup() {
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			public void action() {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el texto a buscar: ");
				String temp=scanner.nextLine();
				Comunicaciones.enviarMensaje(this.myAgent, "buscar", temp);
			}
		});
	}
}
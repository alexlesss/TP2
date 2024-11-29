package dirogue.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe représentant un client pour l'application DIROgue. Ce client se
 * connecte à un serveur spécifique et peut envoyer des commandes pour charger,
 * sauvegarder des fichiers ou quitter l'application.
 */
public class DIROgueClient {
	public static void main(String[] args) {
		String serverAddress = null;
		int serverPort = 0;

		Socket socket = null;
		PrintWriter out = null; // utilisé pour écrire dans le socket avec des commandes comme println()

		// TODO: Se connecter au serveur.

		Scanner scanner = new Scanner(System.in);
		String input;

		while (true) {
			System.out.println("Entrer une commande (load, save, exit):");
			input = scanner.nextLine().trim();

			if (input.equals("load")) {
				System.out.println("Entrez le chemin du fichier que vous souhaitez charger :");

				// TODO: Lire le fichier et envoyer les commandes au serveur ligne par ligne.

			} else if (input.equals("save")) {
				System.out.println(" Entrez le chemin où vous voulez sauvegarder le rapport :");
				var reportPath = scanner.nextLine().trim();
				out.println(input + " " + reportPath);

			} else if (input.equals("exit")) {
				out.println(input);
				break;
			} else {
				System.out.println("Commande non valide. Veuillez entrer 'load', 'save' ou 'exit'.");
			}
		}

		System.out.println("Sortie du programme.");
        scanner.close();
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}

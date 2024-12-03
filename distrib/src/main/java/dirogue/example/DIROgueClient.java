package dirogue.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe représentant un client pour l'application DIROgue. Ce client se
 * connecte à un serveur spécifique et peut envoyer des commandes pour charger,
 * sauvegarder des fichiers ou quitter l'application.
 */
public class DIROgueClient {
	public static void main(String[] args) {
		/**
		 * On initialise les parametres de la connexion au serveur, ils sont donnés dans les instructions du TP.
		 */
		String serverAddress = "127.0.0.1";
		int serverPort = 1370;

		/**
		 * Ouverture de connexion avec le serveur. On fait un try-catch car intelliJ le voulait!! (Sur une note plus
		 * serieuse, ce try-catch nous permet de voir s'il y a une quelconque erreur reseau. Le autoflush nous permet de ne pas avoir a toujours
		 * flush pour envoyer les donnes.
		 */
		try {
			Socket clientSocket = new Socket(serverAddress, serverPort);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // utilisé pour écrire dans le socket avec des commandes comme println

			Scanner scanner = new Scanner(System.in);
			String input;

			while (true) {
				System.out.println("Entrer une commande (load, save, exit):");
				input = scanner.nextLine().trim();

				if (input.equals("load")) {
					System.out.println("Entrez le chemin du fichier que vous souhaitez charger :");
					var loadPath = scanner.nextLine().trim();

					/**
					 * Un autre try-catch pour s'assurer que le fichier a proprement ete manipule. On repete ici la syntaxe vue a maintes reprises dans le cours.
					 * Le fichier contient les commandes pour l'aventure, donc simplement besoin d'envoyer les lignes de fichier en soi. Ce sera le serveur qui s'occupera
					 * de les traiter.
					 */
					try {
						FileReader lecteurFichier = new FileReader(loadPath);
						BufferedReader lecteurFichierLigne = new BufferedReader(lecteurFichier);
						String ligne;

						while ((ligne = lecteurFichierLigne.readLine()) != null) {
							out.println(ligne);
						}
						lecteurFichierLigne.close();
					} catch (IOException e) {
						System.out.println("Erreur lors de la manipulation du fichier");
					}

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
			if (clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de la connexion avec le serveur.");
		}
	}
}

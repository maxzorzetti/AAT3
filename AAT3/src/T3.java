import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.io.IOException;
//import java.nio.*;
import java.nio.file.Paths;

public class T3 {
	
	public static void main(String args[]) throws IOException{
		Scanner in = new Scanner(Paths.get(args[0]));
		
		int maxDominos = in.nextInt();
		
		ArrayList<Domino> dominos = new ArrayList<Domino>();
		
		while(in.hasNextInt()){
			Domino domino = new Domino(in.nextInt(), in.nextInt());
			dominos.add(domino);
		}
		
		System.out.println(maxDominos);
		for(Domino d: dominos){
			System.out.println(d);
		}
		
		String sequencia = achaSequencia(dominos);		
		
		if(sequencia != null) System.out.println(sequencia);
		else System.out.println("Nenhuma sequência foi encontrada!");
	}

	public static String achaSequencia(ArrayList<Domino> dominos) {
		
		for(int i = 0; i < dominos.size(); i++){
			Domino d = dominos.remove(i);
			
			// Tenta iniciar uma sequencia com o domino d
			String result = achaSequencia(dominos, d.front);
			if(result != null) return d.back + " " + d.front + " " + result;			
			
			// Tenta iniciar uma sequencia com o domino d virado
			result = achaSequencia(dominos, d.back);
			if(result != null) return d.front + " " + d.back + " " + result;		
			
			// Poe o domino d de volta na lista se não achou uma sequencia
			dominos.add(i, d);
		}
		
		return null;
	}
	public static String achaSequencia(ArrayList<Domino> dominos, int encaixe){
		if(dominos.size() == 0) return "";		 // Encaixou todos os dominos
		int frente, tras;
		
		for(int i = 0; i < dominos.size(); i++){
			Domino d = dominos.remove(i); 		// Tira um domino da lista
			
			if(d.back == encaixe){				// Encaixa o domino, de frente ou de tras
				tras = d.back;					//
				frente = d.front;				// 
			} else if(d.front == encaixe){		// 
				tras = d.front;					//
				frente = d.back;				//
			} else {
				dominos.add(i, d);				// Se nao encaixou, poe de volta na
				continue;						// lista e tenta outro
			}
			
			String result = achaSequencia(dominos, frente); // Com o domino encaixado, tenta encaixar outros
			
			if(result != null) return tras + " " + frente + " " + result;	// Retorna a sequencia ate entao, se ela foi encontrada
																			// (so entra aqui se encaixou todos)
			
			dominos.add(i, d);	// Se chegou aqui, nao conseguiu encontrar uma sequencia com o domino d,
								// entao poe ele de volta no saco
		}
		
		return null; // Volta null se passou por todos os dominos e nao conseguiu encaixar nenhum
	}
	
}

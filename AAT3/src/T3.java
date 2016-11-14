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
		
		String sequencia = achaSequencia(dominos);		
		
		if(sequencia != null) System.out.println(sequencia);
		else System.out.println("Nenhuma sequência foi encontrada!");
	}

	public static String achaSequencia(ArrayList<Domino> dominos) {
		
		for(int i = 0; i < dominos.size(); i++){
			Domino d = dominos.remove(i);
			
			String result = achaSequencia(dominos, d.front);
			if(result != null) return d.back + " " + d.front+ " " + result;			
			
			result = achaSequencia(dominos, d.back);
			if(result != null) return d.front+ " " + d.back+ " " + result;		
		}
		
		return null;
	}
	public static String achaSequencia(ArrayList<Domino> dominos, int anterior){
		if(dominos.size() == 0) return ""; // Encaixou todos os dominos
		int proximo;
		
		for(int i = 0; i < dominos.size(); i++){
			Domino d = dominos.remove(i); // Tira um domino do "saco"
			
			if(d.back == anterior){				// "Encaixa" o domino, de frente ou de tras
				anterior = d.back;				//
				proximo = d.front;				// 
			} else if(d.front == anterior){		// 
				anterior = d.front;				//
				proximo = d.back;				//
			} else {
				dominos.add(i, d);				// Se nao encaixou, poe de volta no
				continue;						// saco e tenta outro
			}
			
			String result = achaSequencia(dominos, proximo); // Com o domino encaixado, tenta encaixar outros
			
			if(result != null) return anterior + " " + proximo + " " + result;	// Retorna a sequencia ate entao, se ela foi encontrada
																				// (so entra aqui se encaixou todos)
			
			dominos.add(i, d);	// Se chegou aqui, nao conseguiu encontrar uma sequencia com o domino d,
								// entao poe ele de volta no saco
		}
		
		return null; // Volta null se passou por todos os dominos e nao conseguiu encaixar nenhum
	}
	
}

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
		if(dominos.size() == 0) return "";
		int proximo;
		
		for(int i = 0; i < dominos.size(); i++){
			Domino d = dominos.remove(i);
			
			if(d.back == anterior){
				anterior = d.back;
				proximo = d.front;
			} else if(d.front == anterior){
				anterior = d.front;
				proximo = d.back;
			} else {
				dominos.add(i, d);
				continue;	
			}
			
			String result = achaSequencia(dominos, proximo);
			if(result != null) return anterior + " " + proximo + " " + result;			
		}
		
		return null;
	}
	
}

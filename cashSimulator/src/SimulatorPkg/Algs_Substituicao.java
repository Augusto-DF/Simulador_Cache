package SimulatorPkg;

import java.util.ArrayList;
import java.util.Random;

public class Algs_Substituicao {
	
	private static ArrayList<Integer> LRUcontadores = new ArrayList();
	private static ArrayList<Integer> FIFOcontadores = new ArrayList();
	private static ArrayList<Integer> LFUcontadores = new ArrayList();
	
//(LRU)Inicializa um vetor do tamanho igual ao da cache enviada onde todas as posições tem valor zero e cada posição representa o valor do contador da linha do vetor
	public static void iniciaLRU(Cache cache) {
		for(int i = 0; i < cache.getN_linhas(); i++) {
			LRUcontadores.add(i, 0);
		}
	}

//(LRU)Incrementa todos os contadores referentes as linhas que n tomaram hit
	public static void hitLinhaLRU(int linha, Cache cache) {
		LRUcontadores.set(linha, 0);
		
		for(int i = 0; i < cache.getN_linhas(); i++) {			
			if(i != linha) {					
				LRUcontadores.set(i, LRUcontadores.get(i) + 1);				
			}			
		}
	}
	
//(LRU)Substitui o bloco menos usado recentemente na cache:
	public static void subsCacheLRU(Cache cache, Bloco bloco) {
			int maior = 0;
			int index = 0;
			for(int i = 0; i < cache.getN_linhas(); i++) {
				if(maior < LRUcontadores.get(i)) {
					maior = LRUcontadores.get(i);
					index = i;
				}
			}
			cache.addBloco(index, bloco);
			LRUcontadores.set(index, 0);
	}
	
//(Aleatório)Substitui um bloco aleatório da cache pelo bloco vindo da memoria em questão:
	static void subsCacheRandom(Cache cache, Bloco bloco) {
		Random gerador = new Random();
		int r = gerador.nextInt(cache.getN_linhas());
		
		cache.addBloco(r, bloco);
	}
	
//(FIFO)Preenche o vetor FIFO:
	static void iniciaFIFO(Cache cache) {
		//Preenche o vetor com os idices das linhas da cache:
				for(int i = 0; i < cache.getN_linhas(); i++) {
					FIFOcontadores.add(i);
				}
	}
	
//(FIFO)Após o preenchimento da cache o primeiro a entrar é o primeiro a sair:
	static void subsCacheFIFO(Cache cache, Bloco bloco) {		
		int first = 0;
		
		//Salva o primeiro elemento em uma variavel e faz o vetor caminhar como uma fila
		cache.addBloco(FIFOcontadores.get(0), bloco);
		first = FIFOcontadores.get(0);
		
		for(int j = 0; j < cache.getN_linhas() - 1; j++) {
			FIFOcontadores.set(j, FIFOcontadores.get(j+1));
		}
		FIFOcontadores.set(cache.getN_linhas() - 1, first);
	}
	
//(LFU) Preenche o vetor LFU: (Cada posição do vetor equivale a uma linha da cache. Cada posição inicialmente vale zero)
	public static void iniciaLFU(Cache cache) {
		for(int i = 0; i < cache.getN_linhas(); i++) {
			LFUcontadores.add(i, 0);
		}
	}
	
//(LFU) Quando acontecer um hit, o contador do bloco aumenta:
	public static void hitLinhaLFU(int linha) {		
		LFUcontadores.set(linha, LFUcontadores.get(linha) + 1);		
		
			
	}

//(LFU) Substitui o de menor contador:
	public static void subsCacheLFU(Cache cache, Bloco bloco) {		
		int menor = LFUcontadores.get(0);
		int index = 0;
		
		for(int i = 0; i < cache.getN_linhas(); i++) {
			if(menor > LFUcontadores.get(i)) {
				menor = LFUcontadores.get(i);
				index = i;
			}			
		}		
		cache.addBloco(index, bloco);
		LFUcontadores.set(index, 0);
	}
}

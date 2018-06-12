package SimulatorPkg;

import java.util.Scanner;

public class TotalAssociativo extends Algs_Substituicao {
	private Cache cache;
	private Memoria memoria;
	
	public TotalAssociativo(Memoria memoria, Cache cache) {
		this.memoria = memoria;
		this.cache = cache;
	}
	
	public void subs(Bloco bloco, int politica) {
		/*
		 1 - Aleatorio
		 2 - FIFO
		 3 - LRU 
		 4 - LFU
		*/
		//Cache não cheia
		if(cache.isFull() != -1) {
			cache.addBloco(cache.buscaEnd(-1), bloco);
		//Cache cheia:
		}else {
			switch(politica) {
				case 1://RANDOM
					subsCacheRandom(cache, bloco);
					break;
					
				case 2://FIFO
					iniciaFIFO(cache);
					subsCacheFIFO(cache, bloco);
					break;
					
				case 3://LRU
					subsCacheLRU(cache, bloco);
					break;
					
				case 4://LFU
					subsCacheLFU(cache, bloco);
					break;
			}
		}
	}
	
	public void comando(int command, int politica) {
		
		if(command != 1 && command != 2 && command != 3 && command != 4) {
			System.out.println("====!!COMANDO INVÁLIDO!!====");		
		}else {
			Scanner sc = new Scanner(System.in);
			int endereco;
			int valor;
			
			switch (command) {
				case 1: //READ
					System.out.print("Digite o endereço: "); 
					endereco = sc.nextInt();
					
					//MISS
					if(cache.buscaEnd(endereco) == -1) {
						subs(memoria.getBloco(memoria.buscaBlocoEnd(endereco)), politica);
						System.out.println("Read " + endereco + "-> MISS alocando na linha " + cache.buscaEnd(endereco) + "-> Bloco " + memoria.buscaBlocoEnd(endereco) + " substituido." );
					//HIT
					}else {
						System.out.println("Read " + endereco + "-> HIT na linha: " + cache.buscaEnd(endereco));
						if(politica == 3) {
							hitLinhaLRU(cache.buscaEnd(endereco), cache);
						}
						else if(politica == 4) {
							hitLinhaLFU(cache.buscaEnd(endereco));
						}
					}
					break;
					
				case 2: //WRITE
					System.out.print("Digite o endereço: ");
					endereco = sc.nextInt();
					
					System.out.print("Digite o valor: ");
					valor = sc.nextInt();
					
					//MISS
					if(cache.buscaEnd(endereco) == -1) {
						memoria.insereValor(endereco, valor);
						cache.insereValor(endereco, valor);
						
						subs(memoria.getBloco(memoria.buscaBlocoEnd(endereco)), politica);
						System.out.println("Write " + endereco + "-> MISS alocando na linha " + cache.buscaEnd(endereco) + " bloco " + memoria.buscaBlocoEnd(endereco) + " alocado. Novo valor do endereço " + endereco + ": " + valor);
					//HIT
					}else {
						memoria.insereValor(endereco, valor);
						cache.insereValor(endereco, valor);
						
						System.out.println("Write " + endereco + "-> HIT na linha: " + cache.buscaEnd(endereco) + "-> Novo valor do endereço " + endereco + " = " + valor);	
						if(politica == 3) {
							hitLinhaLRU(cache.buscaEnd(endereco), cache);
						}
						else if(politica == 4) {
							hitLinhaLFU(cache.buscaEnd(endereco));
						}
					}
					break;
					
				case 3: // SHOW
					cache.printCache();
					System.out.println();
					memoria.printMemoria();
					System.out.println();
					System.out.println();
					break;
					
				case 4: //EXIT
					System.out.println("|======::PROGRAMA FINALIZADO::======|");
					break;
			}
		}
	}
	
	public void inicializa(int politica) {
			
			int command = 0;
			Scanner sc = new Scanner(System.in);
			
			if(politica == 3) {
				iniciaLRU(cache);
			}
			else if(politica == 4) {
				iniciaLFU(cache);
			}
			
			while(command != 4) {
				System.out.print("Digite um comando: | 1 - READ | 2 - WRITE | 3 - SHOW | 4 - EXIT | => ");
				command = sc.nextInt();			
				comando(command, politica);
				if(command < 1 || command > 4) {
					System.out.println("=====::COMANDO INVÁLIDO::=====");
				}
			}
		}	
	
	
}

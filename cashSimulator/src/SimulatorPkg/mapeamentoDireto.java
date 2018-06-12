package SimulatorPkg;

import java.util.Scanner;

public class mapeamentoDireto {
	private Cache cache;
	private Memoria memoria;
	
	public mapeamentoDireto(Cache cache, Memoria memoria) {
		this.cache = cache;
		this.memoria = memoria;
	}
	
	private void subs(Bloco bloco) {
		cache.addBloco((bloco.getId() % bloco.getSize()), bloco);
	}
	
	private void comando(int command) {
		
		if(command != 1 && command != 2 && command != 3 && command != 4) {
			System.out.println("====!!COMANDO INVÁLIDO!!====");
		
		}else {
			
			Scanner sc = new Scanner(System.in);
			int endereco;
			int valor;
			
			switch (command) {
				
				//Verifica se o bloco está na cache:
				case 1: //READ
					System.out.println("Digite o endereço: "); 
					endereco = sc.nextInt();
					
					//MISS
					if(cache.buscaEnd(endereco) == -1) {
						subs(memoria.getBloco(memoria.buscaBlocoEnd(endereco)));
						System.out.println("Read " + endereco + "-> MISS alocando na linha " + cache.buscaEnd(endereco) + "-> Bloco " + memoria.buscaBlocoEnd(endereco) + " substituido." );
					//HIT
					}else {
						System.out.println("Read " + endereco + "-> HIT na linha: " + cache.buscaEnd(endereco));
					}
					break;
				
				case 2: //WRITE
					System.out.println("Digite o endereço: ");
					endereco = sc.nextInt();
					
					System.out.println("Digite o valor: ");
					valor = sc.nextInt();
					
					//MISS
					if(cache.buscaEnd(endereco) == -1) {
						memoria.insereValor(endereco, valor);
						cache.insereValor(endereco, valor);
						
						subs(memoria.getBloco(memoria.buscaBlocoEnd(endereco)));
						System.out.println("Write " + endereco + "-> MISS alocando na linha " + cache.buscaEnd(endereco) + " bloco " + memoria.buscaBlocoEnd(endereco) + " alocado. Novo valor do endereço " + endereco + ": " + valor);
					//HIT
					}else {
						memoria.insereValor(endereco, valor);
						cache.insereValor(endereco, valor);
						
						System.out.println("Write " + endereco + "-> HIT na linha: " + cache.buscaEnd(endereco) + "-> Novo valor do endereço " + endereco + " = " + valor);	
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
	
	public void inicializa() {
		
		int command = 0;
		Scanner sc = new Scanner(System.in);
		
		while(command != 4) {
			System.out.print("Digite um comando: | 1 - READ | 2 - WRITE | 3 - SHOW | 4 - EXIT | => ");
			command = sc.nextInt();			
			comando(command);
		}
	}
}
























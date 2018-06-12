package SimulatorPkg;

import java.util.ArrayList;

public class Memoria {
	
	private static int end = 0;
	
	private ArrayList<Bloco> _memoria = new ArrayList();
	private int n_blocos;
	private static int NULL = -1;
	
	public Memoria(int n_blocos, int words) {
		this.n_blocos = n_blocos;
		
	//Preenche os blocos da memoria com endereços fixos e o conteudo com lixo (no caso -77)
		for(int i = 0; i < n_blocos; i++) {
			_memoria.add(new Bloco(i, words));
			for(int j = 0; j < words; j++) {
				_memoria.get(i).setEndereco(end);
				end++;
				_memoria.get(i).setValor(NULL);
			}
		}
	}

	/**
	 * @return the n_blocos
	 */
	public int getN_blocos() {
		return n_blocos;
	}

	/**
	 * @param n_blocos the n_blocos to set
	 */
	public void setN_blocos(int n_blocos) {
		this.n_blocos = n_blocos;
	}
	
	/**
	 * Busca um bloco a partir de um endereço
	 * @param endereco
	 * @return id do bloco
	 */
	public int buscaBlocoEnd(int endereco) {
		int target = 0;
		if(endereco > end || endereco < 0) {
			System.out.println("Endereço inválido: " + endereco);
			throw new IllegalArgumentException(Integer.toString(endereco));
		}else {
			/*
			Os dois casos da na mesma: 
			int id = endereco/_memoria.get(0).getSize() - (endereco%_memoria.get(0).getSize());
			return id;
			*/
			for(int i = 0; i < this.n_blocos; i++) {
				for(int j = 0; j < _memoria.get(i).getSize(); j++) {
					if(endereco == _memoria.get(i).getEndereco(j)) {
						target = _memoria.get(i).getId();
					}
				}
			}
		}
		return target;
	}
	
	/**
	 * Busca um bloco
	 * @param id do bloco
	 * @return bloco referente ao id
	 */
	public Bloco getBloco(int id) {
		if(id > n_blocos || id < 0) {
			System.out.println("Bloco inexistente!");
			throw new IllegalArgumentException(Integer.toString(id));
		}else {
			return _memoria.get(id);
		}		
	}
	
	public void insereValor(int endereco, int valor) {
		if(endereco < 0 || endereco > end) {
			throw new IllegalArgumentException(Integer.toString(endereco));
		}else {
			_memoria.get(buscaBlocoEnd(endereco)).setValor(endereco, valor);
		}
	}
	
	/**
	 * Imprime todo o conteudo da memoria com a seguinte tipografia:
	 * Bloco	|	 Endereço	 |	Conteudo
	 */
	public void printMemoria() {
		System.out.println("MEMORIA:");
		System.out.println("|Bloco\t\t" + "|Endereço\t|" + "Conteudo\t|");
		for(int i = 0; i < n_blocos; i++) {
			for(int j = 0; j < _memoria.get(i).getSize(); j++) {
				System.out.print("|"+_memoria.get(i).getId() + "\t\t|" + _memoria.get(i).getEndereco(j) + "\t\t|");
				if(_memoria.get(i).getValor(j) != -1) {
					System.out.println(_memoria.get(i).getValor(j) + "\t\t|");
				}else {
					System.out.println(" - \t\t|");
				}
			}
		}
	}
	
	
}
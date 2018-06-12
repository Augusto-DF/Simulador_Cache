package SimulatorPkg;

import java.util.ArrayList;

public class Cache {
	ArrayList<Bloco> _cache = new ArrayList();
	private int n_linhas;
	private static int NULL = -1;
	
//Construtor que inicializa a cache vazia:
	public Cache(int n_linhas, int words) {
		this.n_linhas = n_linhas;
		
		for(int i = 0; i < n_linhas; i++) {
			_cache.add(new Bloco(NULL,words));
			for(int j = 0; j < words; j++) {
				_cache.get(i).setEndereco(NULL);
				_cache.get(i).setValor(NULL);
			}
		}
	}
	
	
	
/**
	 * @return the n_linhas
	 */
	public int getN_linhas() {
		return n_linhas;
	}



	/**
	 * @param n_linhas the n_linhas to set
	 */
	public void setN_linhas(int n_linhas) {
		this.n_linhas = n_linhas;
	}



	//Busca um endereço na cache e retorna a posição na cache (linha) caso exista ou -1 caso n exista
	public int buscaEnd(int endereco) {
		for(int i = 0; i < this.n_linhas; i++) {
			for(int j = 0; j < _cache.get(i).getSize(); j++) {
				if(endereco == _cache.get(i).getEndereco(j)) {
					return i;
				}
			}
		}
		return -1;
	}
	
//Muda o valor do conteudo de um determinado endereço:
	public void insereValor(int end, int valor) {
		for(int i = 0; i < this.n_linhas; i++) {
			_cache.get(i).setValor(end, valor);
		}
	}
	
//Adiciona um bloco para a cache:
	public void addBloco(int index, Bloco b) {
		if(index <= _cache.size() - 1) {
			_cache.get(index).copiaBloco(b);
		}else {
			System.out.println("Valor de index: "+ index +" \n\n====!!VALOR DE INDEX INVÁLIDO!!====\n\n");
		}	
	}
	
//Remove um bloco da cache:
	public void removeBloco(int index) {
		_cache.get(index).setId(NULL);
		for(int i = 0; i < _cache.get(index).getSize(); i++) {			
			_cache.get(index).removeEnd(i);
			_cache.get(index).removeVal(i);
		}
	}
	
/**
 * Verifica se a cache está cheia 
 * @return -1 se estiver cheia, index da primeira posição vazia se n estiver cheia
 */
	public int isFull() {
		for(int i = 0; i < n_linhas; i++) {
			if(_cache.get(i).getId() == -1) {
				return i;
			}
		}
		return -1;
	}
	
	
//Imprime a cache:
	public void printCache() {
		System.out.println("CACHE:");
		System.out.println("|Linha\t|Bloco\t|Endereço \t|Conteúdo \t|");
		for(int i = 0; i < n_linhas; i++) {			
			
			for(int j = 0; j < _cache.get(i).getSize(); j++) {				
				System.out.print("|"+i + "\t|");
				
				//Imprime o endereço
				if(_cache.get(i).getId() != -1) {
					System.out.print(_cache.get(i).getId() + "\t|");
				}else {
					System.out.print(" - \t|");
				}
				
				//Imprime o endereço
				if(_cache.get(i).getEndereco(j) != -1) {
					System.out.print(_cache.get(i).getEndereco(j) + "\t\t|");
				}else {
					System.out.print(" - \t\t|");
				}
				
				//Imprime o valor
				if(_cache.get(i).getValor(j) != -1) {
					System.out.println(_cache.get(i).getValor(j) + "\t\t|");
				}else {
					System.out.println(" - \t\t|");
				}
			}
		}
	}
}

package SimulatorPkg;

import java.util.ArrayList;

public class Bloco {
	private int id;
	private int size;
	private ArrayList<Integer> _endereco = new ArrayList();
	private ArrayList<Integer> _valor = new ArrayList();
	private static int NULL = -1;
	
	public Bloco(int id, int size) {
		this.id = id;
		this.size = size;
	}	
	/**
	 * @return O id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return O tamanho
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public int getEndereco(int index) {
		return _endereco.get(index);
	}
	
	public void setEndereco(int endereco) {
		_endereco.add(endereco);
	}
	
	public void removeEnd(int index) {
		_endereco.set(index, NULL);
	}
	
	public void removeVal(int index) {
		_valor.set(index, NULL);
	}
	
	public int getValor(int index) {
		return _valor.get(index);
	}
	
	public void setValor(int valor) {
		_valor.add(valor);
	}
	//Setta o valor apartir do endereço:
	public void setValor(int endereco, int valor) {
		for(int i = 0; i < size; i++) {
			if(_endereco.get(i) == endereco) {
				_valor.set(i, valor);
			}
		}
	}
	//Copia os valores de um bloco para outro:
	public void copiaBloco(Bloco b) {
		this.id = b.getId();
		this.size = getSize();
		
		for(int i = 0; i < b.getSize(); i++) {
			_endereco.set(i, b.getEndereco(i));
			_valor.set(i, b.getValor(i));
		}
		
	}
}

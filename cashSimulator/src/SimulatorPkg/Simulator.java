package SimulatorPkg;

import java.io.*;
import java.util.ArrayList;

public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n_palavras = 0;
		int n_linhas = 0;
		int n_blocos = 0;
		int tipo_map = 0;
		int n_conjuntos = 0;
		int pol_subs = 0;
		
		//Ler o arquivo de configuração: ------------------------
		
		File arquivo = new File("src/config.txt");
		try{
			if(!arquivo.exists()) {
				System.out.println("Arquivo n encontrado");
			}
			
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			//Preenche as variáveis:
			n_palavras = Integer.parseInt(br.readLine());
			n_linhas = Integer.parseInt(br.readLine());
			n_blocos = Integer.parseInt(br.readLine());
			tipo_map = Integer.parseInt(br.readLine());
			n_conjuntos = Integer.parseInt(br.readLine());
			pol_subs = Integer.parseInt(br.readLine());
			
			br.close();
			fr.close();
		} catch(IOException ex) {
				ex.printStackTrace();
		}
		//Fim da leitura-----------------------------------------
		
		Memoria me = new Memoria(n_blocos, n_palavras);
		Cache ca = new Cache(n_linhas, n_palavras);
		
		switch (tipo_map) {
			case 1://Mapeamento direto
				mapeamentoDireto MD = new mapeamentoDireto(ca, me);
				MD.inicializa();
				break;
				
			case 2://Totalmente Associativo
				TotalAssociativo TA = new TotalAssociativo(me, ca);
				TA.inicializa(pol_subs);
				break;
		}
	}
		
}




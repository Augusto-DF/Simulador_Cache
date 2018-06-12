Para rodar o arquivo é necessário importar a pasta "/cashSimulator" no eclipse e configurar o arquivo: "/cashSimulator/bin/config.txt".

-config.txt
	linha 01 do arquivo: <Numero de palavras por bloco na memoria principal>
	linha 02 do arquivo: <Numero de linhas da cache>
	linha 03 do arquivo: <Numero de blocos na memoria principal>
	linha 04 do arquivo: <Tipo de mapeamento> 
		1 - Map. Direto 
		2 - Totalmente Associativo 
		3 - Parcialmente Associativo
	linha 05 do arquivo: <Numero de divisoes da cache (P/ o caso de ser Parciamente associativo apenas)>
	linha 06 do arquivo: <Politica de substituição> 
		1- Aleatorio 
		2- FIFO 
		3- LRU
		4- LFU

Mattheus Augusto
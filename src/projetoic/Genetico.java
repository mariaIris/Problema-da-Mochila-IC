/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Aluno
 */
public class Genetico {
    
    //configuracao dos parametros do algoritmo genetico
	static final double TAXADEMUTACAO = 0.5;
	static final double TAXADECRUZAMENTO = 0.9;
	static final boolean ELITISMO = true;
	static final int TAMANHODAPOPULACAO = 400;
	static final int MAXIMODEGERACOES = 1000;
    //-------------------------------------------------
        
        private Populacao populacao;
        private final Random r;
        private int contEstagnar;
	private double melhorAptidaoAnterior;

    public Genetico() {
        
        populacao = new Populacao();
        r = new Random();
        contEstagnar = 0;
        melhorAptidaoAnterior = -1;
       
    }
    //Fluxo principal do algoritmo
	public void Iniciar(){
		int geracao = 0;
		populacao.IniciarPopulacao(TAMANHODAPOPULACAO);
		do {
			populacao = gerarPopulacao();

			System.out.println("Geracao " + geracao +  "| Melhor " + populacao.getIndividuo(0));

			contaEstagnacao();
			if(contEstagnar >= 200)
				break;

		} while (++geracao < MAXIMODEGERACOES);
	}
	
        //Gera nova populacao de solucoes
	private Populacao gerarPopulacao() {
		Populacao novaPopulacao = new Populacao();

		if (ELITISMO) {
			novaPopulacao.setIndividuo(populacao.getIndividuo(0));
		}

		// insere novos individuos na nova populacao, ate atingir o tamanho maximo
		while (novaPopulacao.getNumIndividuos() <= TAMANHODAPOPULACAO) {
			ArrayList<Individuo> filhos = cruzamento(selecaoRoleta());
			novaPopulacao.setIndividuos(filhos);
		}
		
		novaPopulacao.OrdenarPopulacao();
		return novaPopulacao;
	}

	private ArrayList<Individuo> selecaoTorneioBinario(){
		ArrayList<Individuo> pais = new ArrayList<>();
		int a, b;
		//repete esse laco 2 vezes para pegar 2 pais
		for (int i = 0; i < 2; i++) {
			a = r.nextInt(TAMANHODAPOPULACAO);
			b = r.nextInt(TAMANHODAPOPULACAO);
			//considerando que a populacao esta ordenada, o individuo na posicao menor eh melhor
			if (a < b)
				pais.add(populacao.getIndividuo(a));
			else
				pais.add(populacao.getIndividuo(b));
		}

		return pais;
	}
        
        //selecao por roleta para problema de MINIMIZACAO
        private ArrayList<Individuo> selecaoRoleta(){
		ArrayList<Individuo> pais = new ArrayList<>();
		
                double totalAptidoes = 0;
                double[] percentual = new double[populacao.getNumIndividuos()];
                double[] fatias = new double[populacao.getNumIndividuos()];
                
                //soma todas as aptidoes
                for(int i = 0; i < populacao.getNumIndividuos(); i++){
                    totalAptidoes += 1/populacao.getIndividuo(i).getAptidao();
                }
                
                //calcula o percentual de cada individuo no total das aptidoes
                for(int i = 0; i < populacao.getNumIndividuos(); i++){
                    percentual[i] = (1/populacao.getIndividuo(i).getAptidao())/totalAptidoes;
                }
                
                //calcula a fatia da roleta para cada individuo de acordo com seu percentual
                for(int i = 0; i < populacao.getNumIndividuos(); i++){
                    if(i == 0)
                        fatias[i] = percentual[i];
                    else
                        fatias[i] = fatias[i-1] + percentual[i];
                }
                
                //roda a roleta 2 vezes, para selecionar 2 pais
                for(int i = 0; i < 2; i++){
                    pais.add(populacao.getIndividuo(rodaRoleta(fatias)));
                }
		return pais;
	}
        
        private int rodaRoleta(double[] fatias){
            double random = new Random().nextDouble();
            for(int i = 0; i < fatias.length; i++){
                 if(random < fatias[i])
                    return i;
            }
            return 0;
        }

	private ArrayList<Individuo> cruzamento(ArrayList<Individuo> pais) {
		int[] pai0 = pais.get(0).getGenes();
		int[] pai1 = pais.get(1).getGenes();

		int[] filho0 = new int[pai0.length];
		int[] filho1 = new int[pai1.length];

		if (r.nextDouble() <= TAXADECRUZAMENTO) {
                    int p = r.nextInt(7)+1;
                    // se tiver mais genes, adapta os pontos de corte
                    System.arraycopy(pai0, 0, filho0, 0, p);
                    System.arraycopy(pai0, p, filho1, p, (8-p));

                    System.arraycopy(pai1, 0, filho1, 0, p);
                    System.arraycopy(pai1, p, filho0, p, (8-p));
		} else {
			filho0 = pai0;
			filho1 = pai1;
		}

		ArrayList<Individuo> filhos = new ArrayList<>();
		filhos.add(new Individuo(filho0));
		filhos.add(new Individuo(filho1));

		return filhos;
	}

	private void contaEstagnacao(){
		if (melhorAptidaoAnterior == -1 || populacao.getIndividuo(0).getAptidao() != melhorAptidaoAnterior) {
			melhorAptidaoAnterior = populacao.getIndividuo(0).getAptidao();
			contEstagnar = 1;
		} else {
			contEstagnar++;
		}
	}
        
}

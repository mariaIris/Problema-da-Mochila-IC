/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoic;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Aluno
 */
public class Individuo implements Comparable<Individuo>{
    
    private final Random random = new Random();
    private int aptidao;

    //atributos do problema especifico
    private int[] NumPeso;
    private int[] NumPreco;
    private final int qtd = 8;
       //cria um individuo aleatorio da primeira geracao

    public Individuo() {
        
        do{
            this.setNumPreco();
            this.setNumPeso();
            
        }while(!validar());
        avaliar();
    }
        
    public Individuo(int[] genes) {
      for (int i = 0; i < qtd; i++) {
           NumPeso[i] = genes[0];
	   NumPreco[i] = genes[1];
      }
	    //testa se deve fazer mutacao
	    if (random.nextInt() <= Genetico.TAXADEMUTACAO) {
		int posAleatoria = random.nextInt(genes.length); //define gene que sera mutado
		mutacao(posAleatoria);
		}
		avaliar();
	}

     
    public void setNumPreco() {
        for (int i = 0; i < qtd; i++) {
             this.NumPreco[i] = random.nextInt();
        }
    }
    public void setNumPeso() {       
        for (int i = 0; i < qtd; i++) {
             this.NumPeso[i] = random.nextInt();
        }        
    }
    
    public int getAptidao() {
	return aptidao;
    }
    
    private boolean validar() {
        //validar/
        int TotalPeso = 0;
        for (int i = 0; i < qtd; i++) {
             TotalPeso += NumPeso[i];
          }
        
        return TotalPeso <=25;
    }

    private void avaliar() {
        //aptidao = 0.8 * qtdMilho + 3.8 * qtdSoja;
        //A avaliação vai a maximação do Preco dos Objetos da Bolsa;
        for (int i = 0; i < qtd; i++) {
             aptidao += NumPreco[i]; 
        }
    }
    
    private void mutacao(int posicao) {
	do {
	    if (posicao == 0)
		this.setNumPeso();
	    else if (posicao == 1)
		this.setNumPreco();
	} while (!validar());

    }

   

    //Comparar para Ordenar --------------------/
        @Override
	public int compareTo(Individuo i) {
		return this.aptidao.compareTo(i.aptidao);
	}
}

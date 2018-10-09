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
    private Double aptidao;

    //atributos do problema especifico
    private final static double[] NumPeso = {5, 4, 7, 8, 4, 4, 6, 8};
    private final static double[] NumPreco = {3, 3, 2, 4, 2, 3, 5, 8};
    private static int[] cromossomo;
    private int qtd = 8;
    

    //cria um individuo aleatorio da primeira geracao
    public Individuo() {
        
        do{
          cromossomo = new int[8];
          this.setCromossomo();
            
        }while(!validar());
        avaliar();
    }
        
    public Individuo(int[] genes) { 
	   cromossomo = genes;
      
	    //testa se deve fazer mutacao
	    if (random.nextInt() <= Genetico.TAXADEMUTACAO) {
		int posAleatoria = random.nextInt(genes.length); //define gene que sera mutado
		mutacao(posAleatoria);
		}
		avaliar();
	}

    public void setCromossomo() {
       boolean x = random.nextBoolean();
     
       for (int i = 0; i < qtd; i++) {
            if(x == true){
               cromossomo[i] = 1;
            }else{
               cromossomo[i] = 0;
            }
        }
    }   
  
    private boolean validar() {
        int TotalPeso = 0;
        
        for (int i = 0; i < qtd; i++) {
            if(cromossomo[i] == 1){
              TotalPeso += NumPeso[i];
            }
        }
        return TotalPeso <=25;
    }

    private void avaliar() {
        aptidao = 0.0;
        //A avaliação vai a maximação do Preco dos Objetos da Bolsa;
        for (int i = 0; i < qtd; i++) {
            if(cromossomo[i] == 1){
               aptidao += NumPreco[i]; 
            }
        }
    }
    
    private void mutacao(int posicao) {
	do {
	  if(cromossomo[posicao] == 0){
             cromossomo[posicao] = 1;
          }else{
             cromossomo[posicao] = 0;
          }
	} while (!validar());

    }

    public Double getAptidao() {
        return aptidao;
    }

    public int[] getGenes() {
        return cromossomo;          
    }

    //Comparar para Ordenar --------------------/
        @Override
	public int compareTo(Individuo i) {
		return this.aptidao.compareTo(i.aptidao);
	}
        
        @Override
	public String toString() {
		return "Cromossomo " + Arrays.toString(getGenes()) + " Aptidao: " + aptidao + "\n";
	}
}

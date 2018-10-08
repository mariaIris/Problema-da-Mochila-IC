/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoic;

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
   
        
}

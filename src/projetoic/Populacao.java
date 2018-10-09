/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoic;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Aluno
 */
public class Populacao {
    
    private final ArrayList<Individuo> ListaIndividuos;

    public Populacao() {
        ListaIndividuos = new ArrayList<Individuo>();
    
    }

    public void IniciarPopulacao(int tamPop){
        
        for (int i = 0; i < tamPop; i++) {
             ListaIndividuos.add(new Individuo());
            
        }
    }
    
    public void OrdenarPopulacao(){
        
        //decrescente para casos de maximização
	Collections.sort(ListaIndividuos, Collections.reverseOrder()); 
		
        //crescente para casos de minimização
        //Collections.sort(ListaIndividuos);

    }
    
    public Individuo getIndividuo(int pos) {
	return ListaIndividuos.get(pos);
    }
    
    
    // coloca um individuo na proxima posicao disponivel da populacao
    public void setIndividuo(Individuo individuo) {
	   ListaIndividuos.add(individuo);
    }
	
    public void setIndividuos(ArrayList<Individuo> filhos){
	   ListaIndividuos.addAll(filhos);
    }

    // numero de individuos existentes na populacao
    public int getNumIndividuos() {
	   return ListaIndividuos.size();
    }
    
}

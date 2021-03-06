/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.sistema;

import estacionamento.Bilhete;
import estacionamento.local.Local;
import estacionamento.local.ListaVagas;
import estacionamento.pessoa.Cliente;
import estacionamento.veiculo.VeiculoValor;
import estacionamento.veiculo.Veiculo;
/**
 *
 * @author chris
 */
public class Sistema {
    
    /**
     * Classe responsável por realizar atividades pelo qual o sistema é responsável 
     */
    public Sistema(){}
    
    
    /**
     * realiza checkin de usuario ja cadastrado
     * @param cliente
     * @param vaga
     * @param local
     */
    public void checkInComCadastro(Cliente cliente, ListaVagas vaga, Local local){
        
        if (cliente.getVeiculo().getTipoVeiculo() == vaga.getTipoVaga() && local.conferirDisponibilidadeVaga(local, vaga)) {
            
            local.addBilhete(
                new Bilhete(cliente, vaga)
            );
            
            System.out.println("Estacionado com sucesso!");
            
        }else System.out.println("Vaga incompatível com veículo informado!");
    }
    
    /**
     * checkin expresso para usuario sem cadastro
     * @param veiculo
     * @param vaga 
     * @param local
     */
    public void checkInSemCadastro(Veiculo veiculo, ListaVagas vaga, Local local){
        
        if (veiculo.getTipoVeiculo() == vaga.getTipoVaga() && local.conferirDisponibilidadeVaga(local, vaga)) {

            local.addBilhete(
                    new Bilhete(veiculo, vaga)
            );
            
            System.out.println("Estacionado com sucesso!");
            
        }else System.out.println("Vaga incompatível com veículo informado!");
    }
    
    /**
     * Raliza checkout do cliente com base no bilhete dado
     * 
     * @param codBilhete
     * @param local
     */
    public void checkOut(String codBilhete, Local local){

        Bilhete bilhete = local.getBilhete(codBilhete);
        
        if (bilhete != null) {

            double valorCobrar;
            int horasCobrar = new AuxiliarSistema().calculaTempoCobrar(bilhete.getHoraInicio());

            VeiculoValor tv = bilhete.getVeiculo().getTipoVeiculo();

            valorCobrar =  tv.getValor() * horasCobrar;

            System.out.println("A pagar: R$" + valorCobrar);
            local.removeBilhete(codBilhete);
            
        }else System.out.println("Número de bilhete não encontrado!");
    }
    
}

package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private ArrayList<Conta> contas = new ArrayList<>();

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);

        contas.add(new Conta("1", 200));
        contas.add(new Conta("2", 400));
        contas.add(new Conta("3", 600));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    @Override
    public String adicionarConta(String numero, double valor) throws RemoteException {
        Conta conta = new Conta(numero, valor);
        contas.add(conta);
        return "Cadastrado";
    }

    @Override
    public String pesquisarConta(String numero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().compareTo(numero) == 0) {
                System.out.println(conta.getNumero());
                return "Conta: " + conta.getNumero() + " Saldo: " + conta.getSaldo();
            };
        }
        return "Conta nao encontrada";
    }

    @Override
    public String removerConta(String numero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().compareTo(numero) == 0) {
                contas.remove(conta);
                return "Conta removida com sucesso!";
            };
        }
        return "Conta não existe";
    }
    
    
}

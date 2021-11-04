package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println("Digite o saldo:");
                    String saldo = entrada.next();
                    double saldoDouble = Double.parseDouble(saldo);
                    System.out.println(banco.adicionarConta(conta, saldoDouble));
                    break;
                }
                case 4: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Digite o numero da conta:");
                    String conta = entrada.next();
                    System.out.println(banco.pesquisarConta(conta));
                    break;
                }
                case 5: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println(banco.removerConta(conta));
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("--------------------------");
        System.out.println("Rafael Oliveira Batista");
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adiciona nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }

}

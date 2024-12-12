package br.com.exame.view;

import br.com.exame.controller.ClienteController;
import br.com.exame.model.Client;

import java.sql.SQLException;02
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner read = new Scanner(System.in);
    private ClienteController clienteController = new ClienteController();

    public Menu() {
        while (true) {

            int valueOption = opcoes();

            if (valueOption == 1) {
                List<Client> clients = clienteController.listClient();

                if (clients.size() == 0) {
                    System.out.println("Nenhum cliente cadastrado.");
                }

                else {
                    System.out.println("<-- Clientes -->");
                    for (Client client: clients) {
                        System.out.println(client.getId() + " - " + client.getName());
                        System.out.println(client.getAddress());
                        System.out.println(client.getNumber());
                        System.out.println(client.getEmail());
                        System.out.println(client.getPhone());
                        System.out.println();
                    }
                }
            }

            else if (valueOption == 2) {
                System.out.println("Preencha os dados abaixo: ");

                System.out.println("Nome: ");
                String name = read.next();

                System.out.println("Endereço: ");
                read.nextLine();
                String address = read.nextLine();

                System.out.println("Número do endereço: ");
                int number = read.nextInt();
                read.nextLine();

                System.out.println("E-mail: ");
                String email = read.nextLine();

                System.out.println("Telefone: ");
                String phone = read.nextLine();


                Client client = new Client(name, address, number, email, phone);


                try {
                    if (clienteController.insertClient(client)) {
                        System.out.println("Cliente %s cadastrado com sucesso!".formatted(client.getName()));
                    }
                    else {
                        System.out.println("Houve um erro.");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (valueOption == 3) {
                System.out.println("Escolhe o ID do cliente para remover:");
                int idSelected = read.nextInt();
                read.nextLine();
                System.out.println("Deseja realmente remover? [S/N]? ");
                String confirme = read.nextLine();
                if (confirme.equals("S")) {
                    boolean removed = clienteController.removeClient(idSelected);
                    if (removed) {
                        System.out.println("Removido com sucesso!");
                    }
                    else {
                        System.out.println("Não foi possível remover");
                    }
                }
                else if (confirme.equals("N")) {
                    System.out.println("Não removido.");
                }

                else {
                    System.out.println("Nenhum valor correto.");
                }

            }

            else if (valueOption == 4) {
                System.out.println("Encerrando o sistema...");
                break;
            }

        }

    }

    public int opcoes() {
        System.out.println("1 - Listar clientes");
        System.out.println("2 - Adicionar cliente");
        System.out.println("3 - Remover cliente");
        System.out.println("4 - Para sair do sistema");
        return read.nextInt();
    }
}

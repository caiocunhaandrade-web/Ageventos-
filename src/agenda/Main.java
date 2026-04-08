package agenda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Agenda agenda = new Agenda();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_HORA = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   Bem-vindo à Agenda de Eventos");
        System.out.println("===========================================");

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("Encerrando a Agenda de Eventos. Até logo!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Adicionar evento");
        System.out.println("2. Listar todos os eventos");
        System.out.println("3. Buscar evento por ID");
        System.out.println("4. Buscar evento por título");
        System.out.println("5. Buscar eventos por data");
        System.out.println("6. Buscar eventos por tipo");
        System.out.println("7. Ver eventos de hoje");
        System.out.println("8. Ver eventos futuros");
        System.out.println("9. Ver eventos passados");
        System.out.println("10. Remover evento");
        System.out.println("0. Sair");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> adicionarEvento();
            case 2 -> agenda.listarTodos();
            case 3 -> buscarPorId();
            case 4 -> buscarPorTitulo();
            case 5 -> buscarPorData();
            case 6 -> buscarPorTipo();
            case 7 -> listarEventos("Eventos de Hoje", agenda.eventosHoje());
            case 8 -> listarEventos("Eventos Futuros", agenda.eventosFuturos());
            case 9 -> listarEventos("Eventos Passados", agenda.eventosPassados());
            case 10 -> removerEvento();
            case 0 -> {}
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void adicionarEvento() {
        System.out.println("\n--- Adicionar Novo Evento ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();

        LocalDate data = lerData("Data (dd/MM/yyyy): ");
        LocalTime hora = lerHora("Hora (HH:mm): ");

        System.out.print("Local: ");
        String local = scanner.nextLine().trim();

        TipoEvento tipo = lerTipoEvento();

        Evento evento = new Evento(titulo, descricao, data, hora, local, tipo);
        agenda.adicionarEvento(evento);
    }

    private static void buscarPorId() {
        int id = lerInteiro("Digite o ID do evento: ");
        Evento evento = agenda.buscarPorId(id);
        if (evento != null) {
            System.out.println("\nEvento encontrado:");
            System.out.println(evento);
        } else {
            System.out.println("Evento com ID " + id + " não encontrado.");
        }
    }

    private static void buscarPorTitulo() {
        System.out.print("Digite o título (ou parte dele): ");
        String titulo = scanner.nextLine().trim();
        listarEventos("Resultado da busca por \"" + titulo + "\"", agenda.buscarPorTitulo(titulo));
    }

    private static void buscarPorData() {
        LocalDate data = lerData("Digite a data (dd/MM/yyyy): ");
        listarEventos("Eventos em " + data.format(FMT_DATA), agenda.buscarPorData(data));
    }

    private static void buscarPorTipo() {
        TipoEvento tipo = lerTipoEvento();
        listarEventos("Eventos do tipo: " + tipo.getDescricao(), agenda.buscarPorTipo(tipo));
    }

    private static void removerEvento() {
        int id = lerInteiro("Digite o ID do evento a remover: ");
        agenda.removerEvento(id);
    }

    private static void listarEventos(String titulo, List<Evento> lista) {
        System.out.println("\n=== " + titulo + " ===");
        if (lista.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
        } else {
            lista.forEach(System.out::println);
            System.out.println("Total: " + lista.size() + " evento(s).");
        }
    }

    private static TipoEvento lerTipoEvento() {
        System.out.println("Tipos disponíveis:");
        TipoEvento[] tipos = TipoEvento.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("%d. %s%n", i + 1, tipos[i].getDescricao());
        }
        int escolha;
        do {
            escolha = lerInteiro("Escolha o tipo (1-" + tipos.length + "): ");
        } while (escolha < 1 || escolha > tipos.length);
        return tipos[escolha - 1];
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return LocalDate.parse(entrada, FMT_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }

    private static LocalTime lerHora(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return LocalTime.parse(entrada, FMT_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("Hora inválida. Use o formato HH:mm.");
            }
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }
}

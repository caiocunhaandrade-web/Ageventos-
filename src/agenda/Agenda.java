package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {

    private final List<Evento> eventos;

    public Agenda() {
        this.eventos = new ArrayList<>();
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        Collections.sort(eventos);
        System.out.println("Evento adicionado com sucesso: " + evento.getTitulo());
    }

    public boolean removerEvento(int id) {
        boolean removido = eventos.removeIf(e -> e.getId() == id);
        if (removido) {
            System.out.println("Evento removido com sucesso.");
        } else {
            System.out.println("Evento com ID " + id + " não encontrado.");
        }
        return removido;
    }

    public Evento buscarPorId(int id) {
        return eventos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Evento> buscarPorTitulo(String titulo) {
        String busca = titulo.toLowerCase();
        return eventos.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(busca))
                .collect(Collectors.toList());
    }

    public List<Evento> buscarPorData(LocalDate data) {
        return eventos.stream()
                .filter(e -> e.getData().equals(data))
                .collect(Collectors.toList());
    }

    public List<Evento> buscarPorTipo(TipoEvento tipo) {
        return eventos.stream()
                .filter(e -> e.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public List<Evento> eventosHoje() {
        return buscarPorData(LocalDate.now());
    }

    public List<Evento> eventosFuturos() {
        LocalDate hoje = LocalDate.now();
        return eventos.stream()
                .filter(e -> e.getData().isAfter(hoje))
                .collect(Collectors.toList());
    }

    public List<Evento> eventosPassados() {
        LocalDate hoje = LocalDate.now();
        return eventos.stream()
                .filter(e -> e.getData().isBefore(hoje))
                .collect(Collectors.toList());
    }

    public List<Evento> getTodosEventos() {
        return Collections.unmodifiableList(eventos);
    }

    public int getTotalEventos() {
        return eventos.size();
    }

    public void listarTodos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        System.out.println("=== Todos os Eventos (" + eventos.size() + ") ===");
        eventos.forEach(System.out::println);
    }
}

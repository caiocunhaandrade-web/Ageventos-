package agenda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Evento implements Comparable<Evento> {

    private static int contadorId = 1;

    private final int id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private String local;
    private TipoEvento tipo;

    public Evento(String titulo, String descricao, LocalDate data, LocalTime hora, String local, TipoEvento tipo) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    @Override
    public int compareTo(Evento outro) {
        int cmpData = this.data.compareTo(outro.data);
        if (cmpData != 0) return cmpData;
        return this.hora.compareTo(outro.hora);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");
        return String.format(
            "[%d] %s | %s | %s %s | Local: %s | Tipo: %s",
            id, titulo, descricao,
            data.format(fmtData), hora.format(fmtHora),
            local, tipo.getDescricao()
        );
    }
}

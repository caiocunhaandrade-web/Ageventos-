package agenda;

public enum TipoEvento {
    REUNIAO("Reunião"),
    ANIVERSARIO("Aniversário"),
    CONSULTA("Consulta"),
    SOCIAL("Social"),
    TRABALHO("Trabalho"),
    PESSOAL("Pessoal"),
    OUTRO("Outro");

    private final String descricao;

    TipoEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

package bancoweb.model;

/**
 *
 * @author lopes
 */
public class Cliente {
    
    private int id;
    private String nome;
    private char sexo;
    private String email;
    private String civil;
    private String regiao;

    public Cliente(String nome, char sexo, String email, String civil, String regiao) {
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.civil = civil;
        this.regiao = regiao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
 public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCivil() {
        return civil;
    }

    public void setCivil(String civil) {
        this.civil = civil;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }


   

}

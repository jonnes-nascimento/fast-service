package br.com.fiap.core.domain.model;

import br.com.fiap.core.domain.exception.CpfInvalidException;

import java.util.Objects;

public class Cliente {

    private String nome;
    private String cpf;
    private String email;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email) {

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        if (!this.cpfValido(cpf))
            throw new CpfInvalidException("O CPF informado é inválido");

        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        if (!this.cpfValido(cpf))
            throw new CpfInvalidException("O CPF informado é inválido");
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean cpfValido(String cpf) {

        //FIXME: depois dos testes, trocar a verificacao de CPF abaixo pelo comentado
        if (cpf == null || cpf.length() != 11)
            return (false);
//        if (cpf == null || cpf.length() != 11 ||
//                cpf.equals("00000000000") || cpf.equals("11111111111") ||
//                cpf.equals("22222222222") || cpf.equals("33333333333") ||
//                cpf.equals("44444444444") || cpf.equals("55555555555") ||
//                cpf.equals("66666666666") || cpf.equals("77777777777") ||
//                cpf.equals("88888888888") || cpf.equals("99999999999"))
//            return (false);

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
            // e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            // para o segundo digito repita o procedimento incluindo o primeiro
            // digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }
        ;

        // Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
        // menos o resultado anterior.
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        // Segundo resto da divisão por 11.
        resto = (d2 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
        // menos o resultado anterior.
        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        // Digito verificador do CPF que está sendo validado.
        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        // Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        // comparar o digito verificador do cpf com o primeiro resto + o segundo
        // resto.
        return nDigVerific.equals(nDigResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf) && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

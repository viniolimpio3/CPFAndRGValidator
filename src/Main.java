import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Digite um CPF ou RG - ou digite 'sair' para sair: ");
            String value = sc.nextLine();
            if(value.equalsIgnoreCase("sair"))
                break;

            value = value.replace(".", "").replace("-", "");

            switch (value.length()){
                case 11:
                    System.out.println(validaCPF(value) ? "CPF Válido" : "CPF Inválido");
                    break;
                case 9:
                    System.out.println(verificaRg(value) ? "RG Válido" : "RG Inválido");
                    break;
                default:
                    System.out.println("Informação inválida!");
                    break;
            }
        }
    }

    /**
     * @author viniolimpio
     */
    static boolean validaCPF(String cpf){
        if ((cpf.length() != 11) ||
                cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")){
            return false;
        }

        try{
            int somaPrimeiro = 0, somaSegundo = 0,
                    sequenciaPrimeiro = 10, sequenciaSegundo = 11;
            int primeiroDig, segundoDig;
            //Validando o primeiro dígito
            for(int i=0; i <= 9; i++){
                somaSegundo += (sequenciaSegundo * (Integer.parseInt(String.valueOf(cpf.charAt(i)))));
                if(i < 9){
                    somaPrimeiro += (sequenciaPrimeiro * (Integer.parseInt(String.valueOf(cpf.charAt(i)))));
                    sequenciaPrimeiro--;
                }
                sequenciaSegundo--;
            }

            int restoPrimeiro = somaPrimeiro % 11;
            primeiroDig = (restoPrimeiro < 2) ? 0 : (11 - restoPrimeiro);

            int restoSegundo = somaSegundo % 11;
            segundoDig = (restoSegundo < 2) ? 0 : (11 - restoSegundo);

            return (primeiroDig == (Integer.parseInt(String.valueOf(cpf.charAt(9)))) && segundoDig == (Integer.parseInt(String.valueOf(cpf.charAt(10)))));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @author mayar
     */
    // Considerando que os RG's de São Paulo tem 9 dígitos
    public static boolean verificaRg(String numeroRg) {
        if (numeroRg == null || numeroRg.length() != 9) {
            return false;
        }

        // Calculando o dígito verificador
        int soma = 0;
        int multiplicador[] = {2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < 8; i++) {
            int digitoCalculado = Character.getNumericValue(numeroRg.charAt(i));
            soma += digitoCalculado * multiplicador[i];
        }

        int resto = soma % 11;
        int digitoVerificador = (11 - resto);

        if(digitoVerificador == 10){
            digitoVerificador = 0;
        }

        // Verificação do último dígito
        // Pega o último dígito fornecido pelo usuário e faz a comparação com o dígito calculado
        int digitoUsuario = Character.getNumericValue(numeroRg.charAt(8));
        return digitoVerificador == digitoUsuario;
    }

}
import java.util.Scanner;
import java.io.IOException;

public class Menu {

    // Método para o menu de compactação
    public static void compactarMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do arquivo: ");
        Arquivo Arquivo = new Arquivo(scanner.nextLine());

        System.out.println("\nCompactar por: 1 - Caractere ou 2 - Palavra");
        System.out.println("Informe o que deseja fazer:"); 
        int aux = scanner.nextInt();

        scanner.close();

        switch (aux) {  
            case 0:
                System.exit(0);
                break;
            case 1:
                try {
                    Algoritmos.compactarCaractere(Arquivo); 
                } catch (IOException erro) { 
                    System.out.println("Erro: " + erro.getMessage());
                }
                break;
            case 2:
                Algoritmos.compactarPalavra(Arquivo);
                break;
            default:
                System.out.println("Tente novamente!");
        }
    }

    // Método para o menu de descompactação
    public static void descompactarMenu() {
        Scanner scanner = new Scanner(System.in);
        int aux;

        System.out.println("\nDescompactar por: 1 - Caractere ou 2 - Palavra");
        System.out.println("Informe o que deseja fazer:");
        aux = scanner.nextInt();
       
        System.out.println("Nome do arquivo: ");
        Arquivo Arquivo = new Arquivo(scanner.nextLine());

        System.out.println("Nome do arquivo: " + Arquivo.getNomeArquivo());
        
        scanner.close();

        
        switch (aux) {
            case 0:
                System.exit(0);
                break;
            case 1:
                try{
                    Algoritmos.descompactarCaractere(Arquivo);
                } catch (IOException erro) { 
                    System.out.println("Erro: " + erro.getMessage());
                }
                break;
            case 2:
                Algoritmos.descompactarPalavra();
                break;
            default:
                System.out.println("Tente novamente!");
        }
    }

    // Método para imprimir o menu principal
    public static void printar() {
        System.out.println("\n0 - Sair do programa\n1 - Compactar Arquivo\n2 - Descompactar Arquivo");
    }

    // Método do menu principal
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int aux;

        printar();
        System.out.println("Informe o que deseja fazer:");
        aux = scanner.nextInt();

        while (aux >= 0 && aux <= 2) {
            switch (aux) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    compactarMenu();
                    break;
                case 2:
                    descompactarMenu();
                    break;
                default:
                    System.out.println("Tente novamente!");
            }
            printar();
            System.out.println("\nInforme o que deseja fazer:");
            aux = scanner.nextInt();
        }

        scanner.close();
    }
}

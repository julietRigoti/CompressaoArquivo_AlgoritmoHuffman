import java.util.Scanner;
import java.io.IOException;

public class Menu {
    
    // Exibe um menu para o usuário escolher entre compactar um arquivo por caractere ou por palavra.
    // pré-condição: O usuário deve fornecer um nome de arquivo válido e escolher uma opção de compactação.
    // pós-condição: O método executa a compactação conforme a opção selecionada e exibe o tempo gasto.
    public static void compactarMenu() {
        long tempoInicial = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do arquivo: ");
        Arquivo Arquivo = new Arquivo(scanner.nextLine());

        System.out.println("\n\nCompactar por: 1 - Caractere ou 2 - Palavra");
        System.out.println("Informe o que deseja fazer:");
        int aux = scanner.nextInt();

        switch (aux) {
            case 0:
                System.exit(0);
                break;
            case 1:
                try {
                    tempoInicial = System.currentTimeMillis();
                    Algoritmos.compactarCaractere(Arquivo);
                    System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
                } catch (IOException erro) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                break;
            case 2:
                try {
                    tempoInicial = System.currentTimeMillis();
                    Algoritmos.compactarPalavra(Arquivo);
                    System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
                } catch (IOException erro) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                break;
            default:
                System.out.println("Tente novamente!");
        }
    }

    // Exibe um menu para o usuário escolher entre descompactar um arquivo por caractere ou por palavra.
    // pré-condição: O usuário deve fornecer um nome de arquivo válido e escolher uma opção de descompactação.
    // pós-condição: O método executa a descompactação conforme a opção selecionada e exibe o tempo gasto.
    public static void descompactarMenu() {
        Scanner scanner = new Scanner(System.in);
        long tempoInicial = 0;

        System.out.println("Nome do arquivo: ");
        Arquivo Arquivo = new Arquivo(scanner.nextLine());

        System.out.println("\n\nDescompactar por: 1 - Caractere ou 2 - Palavra");
        System.out.println("Informe o que deseja fazer:");
        int aux = scanner.nextInt();
        switch (aux) {
            case 0:
                System.exit(0);
                break;
            case 1:
                try {
                    try {
                        tempoInicial = System.currentTimeMillis();
                        Algoritmos.descompactarBinarioCaractere(Arquivo);
                        System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
                    } catch (ClassNotFoundException erro) {
                        System.out.println("Erro: " + erro.getMessage());
                    }
                } catch (IOException erro) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                break;
            case 2:
                try {
                    try {
                        tempoInicial = System.currentTimeMillis();
                        Algoritmos.descompactarBinarioPalavra(Arquivo);
                        System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
                    } catch (ClassNotFoundException erro) {
                        System.out.println("Erro: " + erro.getMessage());
                    }
                } catch (IOException erro) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                break;
            default:
                System.out.println("Tente novamente!");
        }
    }

    // Método para imprimir o menu principal
    public static void printar() {
        System.out.println("\n0 - Sair do programa\n1 - Compactar Arquivo\n2 - Descompactar Arquivo");
    }

    // Gerencia a interação do usuário com o menu principal, permitindo escolhas entre compactação e descompactação.
    // pré-condição: O usuário deve inserir uma opção válida.
    // pós-condição: O método executa a ação correspondente à opção escolhida e continua até que o usuário opte por sair.
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int aux;

        printar();
        System.out.println("Informe o que deseja fazer:");
        aux = scanner.nextInt();
        scanner.nextLine();

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
            scanner.nextLine();
        }

        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Trabalho de Projetos e Analises de Algoritmos - Juliet Rigoti e Matheus dos Santos");
        Menu.menu();
    }
}

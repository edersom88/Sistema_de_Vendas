package testelogica;
import java.util.Scanner;

public class Testelogica {


    public static void main(String[] args) {
        // TODO code application logic here
        /*int cod1, cod2, qtde1, qtde2;
        Float valor1, valor2, valorFinal;
        Scanner dados = new Scanner(System.in);
        Scanner valor = new Scanner(System.in);
        
        System.out.println("Insira o código da peça 1: ");
        cod1 = dados.nextInt();

        System.out.println("Insira a quantidade da peça 1: ");
        qtde1 = dados.nextInt();

        System.out.println("Insira o valor da peça 1: ");
        valor1 = valor.nextFloat();

        System.out.println("Insira o código da peça 2: ");
        cod2 = dados.nextInt();

        System.out.println("Insira a quantidade da peça 2: ");
        qtde2 = dados.nextInt();

        System.out.println("Insira o valor da peça 2: ");
        valor2 = valor.nextFloat();

        valorFinal = valor1 * qtde1 + valor2 * qtde2;

        System.out.println("O valor total é  de R$ " + valorFinal);

        dados.close();*/
        
        /* Scanner dados = new Scanner(System.in);
         double raio, area;
         
         System.out.println("Insira o raio do circulo: ");
         raio = dados.nextDouble();
         
         area = 3.14159 * (raio*raio);
         
         System.out.printf("Area do circulo = %.4f %n",  (area));
         
         dados.close();*/
        
         /*Scanner dados = new Scanner(System.in);
         int senha, senhacorreta;
         
         System.out.println("Insira a senha: ");
         senha = dados.nextInt();
         
         senhacorreta = 2002;
         
        if (senha == senhacorreta){
            System.out.println("Acesso permitido");
                }
        else{
            System.out.println("Senha incorreta");
            main(args);
        }*/
         
        Scanner dados = new Scanner(System.in);
        System.out.println("Insira o valor de N: ");
        int N = dados.nextInt();
         
        int X[] = new int [N];
                  
        for (int i = 0; i < N; i++){
             System.out.println("Insira o valor de X: ");
             X[i] = dados.nextInt();
         }
         
        for(int i=0 ; i < N ; i++){
                System.out.printf("%d \n", X[i]);
                if (X[i] <= 10){
                    System.out.println();
                   
            }
                
        }
        
          
         
         dados.close();
         
    }
}

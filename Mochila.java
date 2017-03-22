import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Mochila{

public static void main(String[] args) {

     BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));

        int w = 0, 
            n = 0;

        try {

            //primeira linha do arquivo
            String arr [] = new String [2];
            arr = inReader.readLine().split(" "); 
            n = Integer.parseInt(arr[0]);
            w = Integer.parseInt(arr[1]);

            int val[] = new int[n], 
                wt[] = new int[n];

            //segunda linha do arquivo (Pesos dos itens)
            arr = new String[n];
            arr = inReader.readLine().split(" ");
            for (int i = 0; i < arr.length; i++) 
                wt[i] = Integer.parseInt(arr[i]);

            //terceira linha do arquivo (Valores dos itens)
            arr = new String[w];
            arr = inReader.readLine().split(" ");
            for (int i = 0; i < arr.length; i++) 
                val[i] = Integer.parseInt(arr[i]);

            inReader.close();

            System.out.println(Mochila(val, wt, w));
        
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private static int Mochila(int val[], int itemP[], int mochilaP){
        int nItens = itemP.length;
        int [][] a = new int[nItens+1][mochilaP + 1];
          
        for(int item = 1; item <= nItens; item++){
            for(int pesoM = 1; pesoM <= mochilaP; pesoM++){

                if(itemP[item - 1] <= pesoM){
                    int x = val[item - 1] + a[item - 1][pesoM- itemP[item-1]];
                    int y = a[item - 1][pesoM];
                    a[item][pesoM] = Math.max(x,y);
                }
                else{
                    a[item][pesoM] = a[item - 1][pesoM]; 
                }
            }
           
    }

    ArrayList<Integer> itemselec = new ArrayList<Integer>();
    int r = mochilaP;
    for (int item = nItens; item >=1 ; item--) {
        if(a[item][mochilaP] != a[item -1][mochilaP]){
            itemselec.add(item);
            mochilaP -= itemP[item - 1];
        }
    }

    System.out.println(itemselec);

    return a[nItens][r];
    }
}

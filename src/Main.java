import java.util.Scanner;
import java.util.List;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        int n = 0;
        int W = 0;
        int L = 0;
        Triple<Integer,Integer,Integer>[] P;
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        W = s.nextInt();
        L = s.nextInt();

        P = (Triple<Integer,Integer,Integer>[]) new Triple[n];

        for(int i=0; i<n; i++){
            int newW = s.nextInt();
            int newL = s.nextInt();
            P[i] = new Triple<Integer,Integer,Integer>(newW, newL, i+1);
        }

        Entry<Integer, List<Integer>> solucion = expandir(P, W, L);

        System.out.println(solucion.getKey());
        for(Integer i : solucion.getValue()){
            System.out.print(i + " ");
        }
    }

    protected static Entry<Integer, List<Integer>> expandir(Triple<Integer,Integer,Integer>[] P, int W, int L){
        Entry<Triple<Integer,Integer,Integer>[], Integer> respuesta = ordenar(P);
        Triple<Integer,Integer,Integer>[] pOrdenado = respuesta.getKey();
        int maxIndice = respuesta.getValue() - 1;
        int[] R = new int[respuesta.getValue()];
        List<Integer> indicesPlanes = new ArrayList<Integer>();

        if(respuesta.getValue() > 0){
            if( W < pOrdenado[maxIndice].getValue0() && L < pOrdenado[maxIndice].getValue1() ){
                R[maxIndice] = 1;
                W = pOrdenado[maxIndice].getValue0();
                L = pOrdenado[maxIndice].getValue1();
                indicesPlanes.add(pOrdenado[maxIndice].getValue2());
            }else{
                R[maxIndice] = 0;
            }

            for(int i=maxIndice-1; i>=0; i--){
                if(W < pOrdenado[i].getValue0() && L < pOrdenado[i].getValue1()){
                    R[i] = 1 + R[i+1];
                    W = pOrdenado[i].getValue0();
                    L = pOrdenado[i].getValue1();
                    indicesPlanes.add(pOrdenado[i].getValue2());
                }else{
                    R[i] = R[i+1];
                }
            }
        }

        return new SimpleEntry(R[0], indicesPlanes);
    }

    protected static Entry<Triple<Integer,Integer,Integer>[],Integer> ordenar(Triple<Integer,Integer,Integer>[] P){
        Arrays.sort(P, new Comparator<Triple<Integer,Integer,Integer>>(){

            @Override
            public int compare(Triple<Integer,Integer,Integer> arg0, Triple<Integer,Integer,Integer> arg1){
                return arg1.getValue0() - arg0.getValue0();
            }
        });

        Triple<Integer,Integer,Integer>[] pOrdenado = (Triple<Integer,Integer,Integer>[]) new Triple[P.length];
        int j = 0;

        for (int i=0; i<P.length-1; i++){
            if(P[i].getValue1() > P[i+1].getValue1()){
                pOrdenado[j++] = P[i];
            }
        }

        pOrdenado[j++] = P[P.length-1];

        return new SimpleEntry<Triple<Integer,Integer,Integer>[], Integer>(pOrdenado, j);
    }
}

class Triple<L,M,R>{
    protected L left;
    protected M middle;
    protected R right;

    public Triple(L left, M middle, R right){
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public L getValue0(){
        return left;
    }

    public M getValue1(){
        return middle;
    }

    public R getValue2(){
        return right;
    }
}
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
        int j = 0;

        Triple<Integer,Integer,Integer>[] P;
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        W = s.nextInt();
        L = s.nextInt();

        P = (Triple<Integer,Integer,Integer>[]) new Triple[n];

        for(int i=0; i<n; i++){
            int newW = s.nextInt();
            int newL = s.nextInt();
            if(newW > W && newL > L) {
                P[j++] = new Triple<Integer, Integer, Integer>(newW, newL, i + 1);
            }
        }

        Entry<Integer, List<Integer>> solucion = expandir(P, j);

        System.out.println(solucion.getKey());
        for(Integer i : solucion.getValue()){
            System.out.print(i + " ");
        }
    }

    protected static Entry<Integer, List<Integer>> expandir(Triple<Integer,Integer,Integer>[] P, int cantElementos){

        if (cantElementos > 0){
            Triple<Integer,Integer,Integer>[] pOrdenado = ordenar(P);
            return lis(pOrdenado, pOrdenado.length);
        }else{
            return new SimpleEntry<Integer, List<Integer>>(0, new ArrayList<Integer>());
        }


    }

    protected static Triple<Integer,Integer,Integer>[] ordenar(Triple<Integer,Integer,Integer>[] P){
        Arrays.sort(P, new Comparator<Triple<Integer,Integer,Integer>>(){

            @Override
            public int compare(Triple<Integer,Integer,Integer> arg0, Triple<Integer,Integer,Integer> arg1){
                return arg0.getValue0() - arg1.getValue0();
            }
        });

        // P ahora está ordenado de manera ascendente con respecto a w

        int inicio = 0;
        int i = 1;

        Comparator<Triple<Integer,Integer,Integer>> comparador_l = new Comparator<Triple<Integer,Integer,Integer>>(){

            @Override
            public int compare(Triple<Integer,Integer,Integer> arg0, Triple<Integer,Integer,Integer> arg1){
                return arg1.getValue1() - arg0.getValue1();
            }
        };

        while (i < P.length){
            if(P[inicio].getValue0() != P[i].getValue0()){
                Arrays.sort(P, inicio, i, comparador_l);
                inicio = i;
            }
            i++;
        }

        if(P[inicio].getValue0() == P[P.length-1].getValue0()){
            Arrays.sort(P, inicio, P.length, comparador_l);
        }

        // P ahora está ordenado de manera ascendente con respecto a w y
        // los que tienen igual w están ordenados de manera descendente con respecto a l

        return P;
    }

    /**
     * This code is contributed by Rajat Mishra. Adaptado para la solución
     * particular por la comisión López-Migliaro
     * @param arr
     * @param n
     * @return
     */

    //protected static Entry<Integer, List<Integer>> lis(Triple<Integer,Integer,Integer>[] P, int W, int L)
    static Entry<Integer, List<Integer>> lis(Triple<Integer,Integer,Integer>[] arr, int n) {
        int lis[] = new int[n];
        List<Integer>[] soluciones = (ArrayList<Integer>[]) new ArrayList[n];

        // Initialize LIS values for all indexes
        for (int i = 0; i < n; i++) {
            soluciones[i] = new ArrayList<Integer>();
            soluciones[i].add(arr[i].getValue2());
            lis[i] = 1;
        }

        // Compute optimized LIS values in
        // bottom up manner
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++) {
                if (arr[i].getValue1() > arr[j].getValue1() && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    soluciones[i].add(arr[j].getValue2());
                }
            }
        }

        // Pick maximum of all LIS values
        int max = 0;
        int indiceSolucion = 0;
        for (int i = 0; i < n; i++) {
            if (max < lis[i]) {
                max = lis[i];
                indiceSolucion = i;
            }
        }

        return new SimpleEntry<Integer, List<Integer>>(max, soluciones[indiceSolucion]);
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

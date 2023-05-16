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

        List<Triple<Integer,Integer,Integer>> P = new ArrayList<Triple<Integer,Integer,Integer>>();
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        W = s.nextInt();
        L = s.nextInt();

        for(int i=0; i<n; i++){
            int newW = s.nextInt();
            int newL = s.nextInt();
            if(W < newW && L < newL) {
                P.add(new Triple<Integer,Integer,Integer>(newW, newL, i + 1));
            }
        }

        Triple<Integer,Integer,Integer>[] arregloP = new Triple[P.size()];
        arregloP = P.toArray(arregloP);
        Entry<Integer, List<Integer>> solucion = expandir(arregloP);

        System.out.println(solucion.getKey());
        for(Integer i : solucion.getValue()){
            System.out.print(i + " ");
        }
    }

    protected static Entry<Integer, List<Integer>> expandir(Triple<Integer,Integer,Integer>[] P){

        if (P.length > 0){
            Triple<Integer,Integer,Integer>[] pOrdenado = ordenar(P);
            return lis(pOrdenado);
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

    /*
     * Made by Venki
     */
    static Entry<Integer, List<Integer>> lis(Triple<Integer,Integer,Integer>[] v){
        List<Integer> solucion = new ArrayList<Integer>();

        if (v.length == 0) // boundary case
            return new SimpleEntry<Integer,List<Integer>>(0, solucion);

        Triple<Integer,Integer,Integer>[] tail = (Triple<Integer,Integer,Integer>[]) new Triple[v.length];
        int length = 1; // always points empty slot in tail
        tail[0] = v[0];
        solucion.add(v[0].getValue2());

        for (int i = 1; i < v.length; i++) {

            if (v[i].getValue1() > tail[length - 1].getValue1()) {
                // v[i] extends the largest subsequence
                tail[length++] = v[i];
                solucion.add(v[i].getValue2());
            }
            else {
                // v[i] will extend a subsequence and
                // discard older subsequence

                // find the largest value just smaller than
                // v[i] in tail

                // to find that value do binary search for
                // the v[i] in the range from begin to 0 +
                // length
                int idx = Arrays.binarySearch(
                        tail, 0, length - 1, v[i]);

                // binarySearch in java returns negative
                // value if searched element is not found in
                // array

                // this negative value stores the
                // appropriate place where the element is
                // supposed to be stored
                if (idx < 0)
                    idx = -1 * idx - 1;

                // replacing the existing subsequence with
                // new end value
                tail[idx] = v[i];
                solucion.set(idx, v[i].getValue2());
            }
        }
        return new SimpleEntry<Integer, List<Integer>>(length, solucion);
    }

    /**
     * This code is contributed by Rajat Mishra. Adaptado para la solución
     * particular por la comisión López-Migliaro
     * @param arr Arreglo que contiene la lista de elementos
     * @param n La cantidad de elementos del arreglo
     * @return Una tupla cuya primer componente son la cantidad máxima de expansiones y la segunda
     * componente es la lista de expansiones
     */
    static Entry<Integer, List<Integer>> lis2(Triple<Integer,Integer,Integer>[] arr, int n) {
        int lis[] = new int[n];
        List<Integer>[] soluciones = (ArrayList<Integer>[]) new ArrayList[n];

        // Initialize LIS values for all indexes
        for (int i = 0; i < n; i++) {
            soluciones[i] = new ArrayList<Integer>();
            lis[i] = 1;
        }

        soluciones[0].add(arr[0].getValue2());
        // Compute optimized LIS values in
        // bottom up manner
        int insertar = -1;
        for (int i = 1; i < n; i++){
            insertar = -1;
            for (int j = 0; j < i; j++) {
                if (arr[i].getValue1() > arr[j].getValue1() && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    insertar = j;
                    //soluciones[i].add(arr[j].getValue2());
                }
            }
            if(insertar >= 0) {
                soluciones[i].addAll(soluciones[insertar]);
            }
            soluciones[i].add(arr[i].getValue2());
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
class Triple<L,M,R> implements Comparable{
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
    @Override
    public int compareTo(Object o) {
        Triple<L,M,R> t = (Triple<L,M,R>) o;
        return ((int) this.middle - (int) t.getValue1());
    }
}
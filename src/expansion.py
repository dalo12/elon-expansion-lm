from bisect import bisect_left

def patience_sort(arreglo):
    pilas = []
    for elemento in arreglo:
        posicion = bisect_left(pilas, elemento)
        if posicion == len(pilas):
            pilas.append(elemento)
        else:
            pilas[posicion] = elemento
    return pilas

def expandir_fabrica(W,L,planes):
    resultado = []
    planes = patience_sort(planes)
    for extension in planes:
        if extension[0]> W and extension[1]>L:
            resultado.append(extension[2])
            W = extension[0]
            L= extension[1]
    return resultado

n, W, L = map(int, input().split())
expansiones = []
for i in range(n):
    w, l = map(int, input().split())
    if w>W and l>L:
        expansiones.append((w, l, i+1))
expansiones.sort()

resultado = expandir_fabrica(W,L,expansiones)
if(len(resultado) > 0):
    print(len(resultado))
    print(*resultado)
else:
    print(0) 

def expandir_fabrica(W, L, planes):
    cant_expansiones = 0
    indices_expansion=[]

    planes.sort()
    
    for wl, i in planes:
        if wl[0] > W and wl[1] >L:
            indices_expansion.append(i)
            cant_expansiones+=1
            W = wl[0]
            L= wl[1]

    print(cant_expansiones)
    print(*indices_expansion)

n, W, L = map(int, input().split())
planes = []

for i in range(n):
    w, l = map(int, input().split())
    planes.append(((w , l), i + 1))


expandir_fabrica(W,L,planes)


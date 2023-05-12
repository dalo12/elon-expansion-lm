# elon-expansion-lm
Elon quiere realizar la mayor cantidad de expansiones posible, y necesita tu ayuda

# Elon quiere expandir

Las Gigafactories son fábricas de Tesla, la compañía con mayor producción de autos eléctricos en el mundo.

Elon quiere expandir una fábrica que actualmente ocupa W
unidades de ancho y L unidades de largo. Reclutó a los mejores arquitectos, que le ofrecieron un conjunto de n planes de expansión P={(w1,l1),(w2,l2),…,(wn,ln)} donde el i-ésimo plan permite expandir la fábrica a un ancho de wi unidades y un largo de li unidades.

Obviamente, solo tiene sentido aplicar un plan de expansión si las dimensiones actuales de la fábrica son estrictamente menores en ancho y largo a las dimensiones propuestas en el plan.

Como es importante mantener la atención del público en la empresa, Elon quiere realizar la mayor cantidad de expansiones posible, y necesita tu ayuda.


### Input

La primera línea contiene los enteros n, W, L (1 ≤n≤5000, 1≤W, L ≤10^6) — la cantidad de planes de expansión, y el ancho y largo de la fábrica en su estado inicial. Luego siguen n líneas, cada una con dos enteros wi y li — el ancho y largo del i-ésimo plan de expansión (1≤wi, li≤10^6).

### Output

En la primera línea, se debe imprimir la máxima cantidad de planes de expansión que se pueden realizar. En la segunda línea, se deben imprimir los índices de los planes de expansión a ejecutar, separados por un espacio, en el orden en que se deben aplicar. Recuerden que el plan de expansión aplicado debe tener mayores dimensiones que la fábrica en su estado actual. Si hay múltiples soluciones, imprimir cualquiera.

Si no es posible aplicar ningún plan de expansión, imprimir 0.

| Input:   |
|:---------|
|    2 1 1 |
|    2 2   |
|    2 2   |
|Output:   |
|:---------|
|    1     |
|    1     |


| Input:   |
|:---------|
|    3 3 3 |
|    5 4   |
|    12 11 |
|    9 8   |
|Output:   |
|:---------|
|    3     |
|    1 3 2 |

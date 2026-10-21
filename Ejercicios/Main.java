import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Cliente[] clientes = {
            new Cliente("1001", 34, "Ana Torres",     850000f),
            new Cliente("1002", 28, "Luis Prado",     1200000f),
            new Cliente("1003", 45, "Marta Ríos",     430000f),
            new Cliente("1004", 28, "Camilo Vélez",   990000f),   // misma edad que Luis
            new Cliente("1005", 52, "Sofía Mejía",    2100000f),
            new Cliente("1006", 31, "Julián Ortiz",   670000f),
            new Cliente("1007", 23, "Valentina Cruz", 310000f),
            new Cliente("1008", 39, "Andrés Lozano",  1750000f),
            new Cliente("1009", 34, "Paola Gil",      540000f)    // misma edad que Ana
        };

        BinarySearchTree arbolCompras = new BinarySearchTree();
        BinarySearchTree arbolEdad = new BinarySearchTree();

        for (Cliente c : clientes) {
            arbolCompras.insertNode(new Node<Cliente>((int) c.getTotal_compras(), c));  // TODO 1
            arbolEdad.insertNode(new Node<Cliente>(c.getEdad(), c));                    // TODO 2
        }

        System.out.println("Clientes registrados: " + clientes.length);
        System.out.println("Nodos en arbolCompras: " + arbolCompras.getKeyList().size());
        System.out.println("Nodos en arbolEdad: " + arbolEdad.getKeyList().size());

        System.out.println("\n== R1: clientes por compras (ascendente) ==");
        for (Cliente c : arbolCompras.clientesAscendente())
            System.out.println(c);

        System.out.println("\n== R2: Top 3 compradores ==");
        for (Cliente c : arbolCompras.mayoresN(3))
            System.out.println(c);

        System.out.println("\n== R3: clientes entre 25 y 35 años ==");
        for (Cliente c : arbolEdad.clientesEnRango(25, 35))
            System.out.println(c);

        System.out.println("\n== R4: búsqueda por total de compras ==");
        int buscado = 990000;
        Node encontrado = arbolCompras.buscarNodo(buscado, arbolCompras.getRoot());
        if (encontrado != null)
            System.out.println("Existe: " + encontrado.getValue());
        else
            System.out.println("No existe un cliente con compras = " + buscado);

        System.out.println("\n== R5: más joven y mayor ==");
        System.out.println("Más joven: " + arbolEdad.minimo());
        System.out.println("Mayor:     " + arbolEdad.maximo());

        // PARTE D: forma del árbol según el orden de inserción
        System.out.println("\n== PARTE D: altura según orden de inserción ==");
        int[] ordenA = {10, 20, 30, 40, 50, 60};
        int[] ordenB = {40, 20, 60, 10, 30, 50};
        BinarySearchTree tA = new BinarySearchTree();
        BinarySearchTree tB = new BinarySearchTree();
        for (int k : ordenA) tA.insertNode(new Node<String>(k));
        for (int k : ordenB) tB.insertNode(new Node<String>(k));
        System.out.println("Orden 10,20,30,40,50,60 -> altura " + tA.altura(tA.getRoot()));
        System.out.println("Orden 40,20,60,10,30,50 -> altura " + tB.altura(tB.getRoot()));
    }
}
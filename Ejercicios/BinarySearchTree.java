import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() { root = null; }
    public BinarySearchTree(Node root) { this.root = root; }
    public Node getRoot() { return root; }

    /* ---------- INSERCIÓN ---------- */

    public void insertNode(Node newNode) {
        root = insertRecursive(newNode, root);
    }

    // PARTE C: las llaves iguales NO se descartan; van a la rama derecha.
    // Invariante: subárbol izquierdo < nodo <= subárbol derecho.
    public Node insertRecursive(Node nodeToInsert, Node rootNode) {
        if (rootNode == null)
            return nodeToInsert;

        if (nodeToInsert.getKey() < rootNode.getKey())
            rootNode.setLeft(insertRecursive(nodeToInsert, rootNode.getLeft()));
        else // mayor O IGUAL: va a la derecha
            rootNode.setRight(insertRecursive(nodeToInsert, rootNode.getRight()));
        return rootNode;
    }

    /* ---------- BÚSQUEDA ---------- */

    public boolean searchNodeRecursive(int key, Node rootNode) {
        if (rootNode == null) return false;
        if (rootNode.getKey() == key) return true;
        if (rootNode.getKey() > key)
            return searchNodeRecursive(key, rootNode.getLeft());
        return searchNodeRecursive(key, rootNode.getRight());
    }

    /** PARTE A.1 */
    public Node buscarNodo(int key, Node rootNode) {
        if (rootNode == null) return null;
        if (rootNode.getKey() == key) return rootNode;
        if (key < rootNode.getKey())
            return buscarNodo(key, rootNode.getLeft());
        return buscarNodo(key, rootNode.getRight());
    }

    /* ---------- RECORRIDOS (vistos en clase) ---------- */

    public void InOrder(Node root) {
        if (root == null) return;
        InOrder(root.getLeft());
        System.out.println("->" + root.getKey());
        InOrder(root.getRight());
    }

    public void preOrder(Node root) {
        if (root == null) return;
        System.out.println("->" + root.getKey());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public void posOrder(Node root) {
        if (root == null) return;
        posOrder(root.getLeft());
        posOrder(root.getRight());
        System.out.println("->" + root.getKey());
    }

    /* ---------- OPERACIONES COMPLETADAS ---------- */

    /** PARTE A.2: llaves ascendentes (in-orden). */
    public ArrayList<Integer> getKeyList() {
        ArrayList<Integer> keyList = new ArrayList<>();
        keyListRec(root, keyList);
        return keyList;
    }

    private void keyListRec(Node n, ArrayList<Integer> keyList) {
        if (n == null) return;
        keyListRec(n.getLeft(), keyList);
        keyList.add(n.getKey());
        keyListRec(n.getRight(), keyList);
    }

    /** R1: in-orden. */
    public ArrayList<Cliente> clientesAscendente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        ascendenteRec(root, lista);
        return lista;
    }

    private void ascendenteRec(Node n, ArrayList<Cliente> lista) {
        if (n == null) return;
        ascendenteRec(n.getLeft(), lista);
        lista.add((Cliente) n.getValue());
        ascendenteRec(n.getRight(), lista);
    }

    /** R2: in-orden INVERSO (derecha, nodo, izquierda) con parada temprana. */
    public ArrayList<Cliente> mayoresN(int n) {
        ArrayList<Cliente> lista = new ArrayList<>();
        mayoresRec(root, n, lista);
        return lista;
    }

    private void mayoresRec(Node nodo, int n, ArrayList<Cliente> lista) {
        if (nodo == null || lista.size() >= n)
            return;                                   // parada temprana
        mayoresRec(nodo.getRight(), n, lista);        // primero las claves mayores
        if (lista.size() < n)
            lista.add((Cliente) nodo.getValue());
        mayoresRec(nodo.getLeft(), n, lista);
    }

    /** R3: in-orden con PODA. Con duplicados a la derecha: izquierda < nodo <= derecha. */
    public ArrayList<Cliente> clientesEnRango(int min, int max) {
        ArrayList<Cliente> lista = new ArrayList<>();
        rangoRec(root, min, max, lista);
        return lista;
    }

    private void rangoRec(Node n, int min, int max, ArrayList<Cliente> lista) {
        if (n == null) return;
        int k = n.getKey();
        if (min < k)                       // izquierda tiene claves < k: solo sirve si min < k
            rangoRec(n.getLeft(), min, max, lista);
        if (k >= min && k <= max)
            lista.add((Cliente) n.getValue());
        if (k <= max)                      // con k == max puede haber duplicados a la derecha
            rangoRec(n.getRight(), min, max, lista);
    }

    /** R5: siempre por la izquierda. O(h) */
    public Cliente minimo() {
        if (root == null) return null;
        Node actual = root;
        while (actual.getLeft() != null)
            actual = actual.getLeft();
        return (Cliente) actual.getValue();
    }

    /** R5: siempre por la derecha. O(h) */
    public Cliente maximo() {
        if (root == null) return null;
        Node actual = root;
        while (actual.getRight() != null)
            actual = actual.getRight();
        return (Cliente) actual.getValue();
    }

    /** PARTE D: vacío = -1, un nodo = 0. */
    public int altura(Node rootNode) {
        if (rootNode == null) return -1;
        return 1 + Math.max(altura(rootNode.getLeft()), altura(rootNode.getRight()));
    }
}
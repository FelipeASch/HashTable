public class LinkedList<E> {
    private Node<E> topo;
    private Node<E> base;
    private Node<E> primeiro;

    public LinkedList(){
        topo = null;
        base = null;
    }
    public Node<E> getTopo(){
        return topo;
    }
    public void addNode(E value){
        Node<E> node = new Node<>(value);
        if (topo == null){
            topo = base = node;
        } else {
            topo.next = node;
            node.previous = topo;
            topo = node;
        }
    }
    public Node<E> getPrimeiro(){
        if(topo == null) {
            return null;
        }

        Node<E> atual = topo;
        while(atual.previous != null){
            atual = atual.previous;
        }
        return atual;
    }
    public void removePrimeiro(){
        Node<E> primeiro = getPrimeiro();
        if(primeiro == null){
            return;
        }
        if (base == topo) {
            topo = base = null;
        } else {
            base = base.next;
            base.previous = null;
        }
    }
    public void removeTopo(){
        if (base == topo) {
            topo = base = null;
        } else {
            topo = topo.previous;
            topo.next = null;
        }

    }
    public boolean removeNode(E value) {
        if (primeiro == null) return false;

        if (primeiro.value.equals(value)) {
            primeiro = primeiro.next;
            return true;
        }
        Node<E> atual = primeiro;
        while (atual.next != null) {
            if (atual.next.value.equals(value)) {
                atual.next = atual.next.next;
                return true;
            }
            atual = atual.next;
        }
        return false;
    }
    public void clear() {
        primeiro = null;
    }
    public int size() {
        int count = 0;
        Node<E> atual = primeiro;
        while (atual != null) {
            count++;
            atual = atual.next;
        }
        return count;
    }


}

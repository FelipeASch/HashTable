public abstract class HashTable {
    protected int capacity = 32;
    protected LinkedList<String>[] table;
    protected int collisionCount;
    protected long insertionTime;
    protected long searchTime;
    protected int size;  // número de elementos

    protected final double LOAD_FACTOR_THRESHOLD = 0.75;

    public HashTable() {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        collisionCount = 0;
        size = 0;
    }

    protected abstract int hash(String key);

    public void insert(String key) {
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);
        LinkedList<String> list = table[index];

        if (list.getTopo() != null) {
            collisionCount++;
        }

        list.addNode(key);
        size++;
    }

    private void resize() {
        capacity *= 2;
        LinkedList<String>[] oldTable = table;
        table = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }

        size = 0;  // resetar, será recalculado ao reinserir

        for (LinkedList<String> list : oldTable) {
            Node<String> node = list.getPrimeiro();
            while (node != null) {
                reinsert(node.value);
                node = node.next;
            }
        }
    }

    // Reinserção sem contar colisões nem verificar fator de carga
    private void reinsert(String key) {
        int index = hash(key);
        table[index].addNode(key);
        size++;
    }

    public boolean search(String key) {
        int index = hash(key);
        LinkedList<String> list = table[index];

        Node<String> node = list.getPrimeiro();
        while (node != null) {
            if (node.value.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public int[] getDistribution() {
        int[] distribution = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            int count = 0;
            Node<String> node = table[i].getPrimeiro();
            while (node != null) {
                count++;
                node = node.next;
            }
            distribution[i] = count;
        }
        return distribution;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public long getInsertionTime() {
        return insertionTime;
    }

    public long getSearchTime() {
        return searchTime;
    }

    public double getLoadFactor() {
        return (double) size / capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

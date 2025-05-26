public abstract class HashTable {
    protected int capacity = 32;
    protected LinkedList<String>[] table;
    protected int collisionCount;
    protected long insertionTime;
    protected long searchTime;

    public HashTable() {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        collisionCount = 0;
    }

    protected abstract int hash(String key);

    public void insert(String key) {
        int index = hash(key);
        LinkedList<String> list = table[index];

        if (list.getTopo() != null) {
            collisionCount++;
        }

        list.addNode(key);
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
}

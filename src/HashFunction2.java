public class HashFunction2 extends HashTable {
    @Override
    protected int hash(String key) {
        int hash = 9;
        for (char c : key.toCharArray()) {
            hash = hash * 31 + c;
        }
        return Math.abs(hash) % capacity;
    }
}

public class HashFunction1 extends HashTable {
    @Override
    protected int hash(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += c;
        }
        return sum % capacity;
    }
}

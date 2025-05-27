import java.util.Arrays;

public class ArraiA<E> {
    private Object[] data;
    private int tamanho = 10;
    private int posLivre = 0;
    private Node<E> primeiro;

    public ArraiA(){
        data = new Object[tamanho];
    }

    public ArraiA(int tamanho){
        if(tamanho <= 0){
            throw new IllegalArgumentException("não pode!");
        } else {
            this.tamanho = tamanho;
            data = new Object[tamanho];
        }
    }

    public void add (E e){
        if(cheio()){
            aumentarArraiA();
        }
        data[posLivre] = e;
        posLivre++;
    }

    public void add(int index, E e){
        checkIndex(index);
        if(cheio()){
            aumentarArraiA();
        }
        if (index < posLivre){
            System.arraycopy(data, index, data, index + 1, posLivre - index);
        }
        data[index] = e;
        posLivre++;
    }

    public void remove(int index){
        checkIndex(index);
        System.arraycopy(data, index + 1, data, index, posLivre - index - 1);
        data[posLivre - 1] = null;
        posLivre--;
    }

    public boolean remove(E e) {
        for (int i = 0; i < posLivre; i++) {
            if (data[i].equals(e)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void set(int index, E e){
        checkIndex(index);
        data[index] = e;
    }

    public E get(int index) {
        checkIndex(index);
        if (data[index] == null) {
            throw new IllegalStateException("Valor na posição " + index + " é nulo");
        }
        return (E) data[index];
    }

    private void checkIndex(int index){
        if(index >= posLivre || index < 0){
            throw new IndexOutOfBoundsException("Índice fora do permitido: "+index);
        }
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, posLivre);
    }

    private boolean cheio(){
        return posLivre >= tamanho;
    }

    private void aumentarArraiA(){
        int novoTamanho = tamanho + (tamanho/2);
        Object[] novaArraiA = new Object[novoTamanho];
        System.arraycopy(data, 0, novaArraiA, 0, data.length);
        data = novaArraiA;
        tamanho = novoTamanho;
    }
}


# [ 리스트 추상화1 ( 인터페이스 도입 ) ]

- 순서가 있고 중복을 허용하는 자료구조를 List라고 한다.
- 지금까지 만든 ArrayList와 LinkedList는 내부 구현만 다를 뿐
같은 기능을 제공하는 리스트이다.
- 따라서 이 둘의 공통 기능을 인터페이스로 뽑아서 추상화 하면 다형성을 활용한 다양한 이득을 얻을 수 있다.
- 먼저 ArrayList와 LinkedList에서 같은 기능을 제공하는 메서드를 인터페이스로 뽑아보자.

```java
public interface MyList<E> {

    int size();

    void add(E e);

    void add(int index, E e);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    int indexOf(E o);

} 
```

- 기존에 만들었던 MyArrayList와 MyLinkedList를 MyList를 구현하는 방식으로 수정 해 보자.

```java
package collection.list;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size = 0;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = e;
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (size == elementData.length) {
            grow();
        }
        shiftRightFrom(index);
        elementData[index] = e;
        size++;
    }

    //요소의 마지막부터 index까지 오른쪽으로 밀기
    private void shiftRightFrom(int index) {
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        E oldValue = get(index);
        shiftLeftFrom(index);

        size--;
        elementData[size] = null;
        return oldValue;
    }

    //요소의 index부터 마지막까지 왼쪽으로 밀기
    private void shiftLeftFrom(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
    }

    @Override
    public int indexOf(E o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size)) + " size=" + size + ", capacity=" + elementData.length;
    }

}
```

```java
package collection.list;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> first;
    private int size = 0;

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (first == null) {
            first = newNode;
        } else {
            Node<E> lastNode = getLastNode();
            lastNode.next = newNode;
        }
        size++;
    }

    private Node<E> getLastNode() {
        Node<E> x = first;
        while (x.next != null) {
            x = x.next;
        }
        return x;
    }

    @Override
    public void add(int index, E e) {
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<E> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public E set(int index, E element) {
        Node<E> x = getNode(index);
        E oldValue = x.item;
        x.item = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        Node<E> removeNode = getNode(index);
        E removedItem = removeNode.item;
        if (index == 0) {
            first = removeNode.next;
        } else {
            Node<E> prev = getNode(index - 1);
            prev.next = removeNode.next;
        }
        removeNode.item = null;
        removeNode.next = null;
        size--;
        return removedItem;
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index);
        return node.item;
    }

    private Node<E> getNode(int index) {
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    @Override
    public int indexOf(E o) {
        int index = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyLinkedListV1{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<E> temp = this;
            sb.append("[");
            while (temp != null) {
                sb.append(temp.item);
                if (temp.next != null) {
                    sb.append("->");
                }
                temp = temp.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
```

# [ 리스트 추상화2 ( 의존 관계 주입 )]
- 먼저 이전에 MyArrayList를 의존하는 배치 프로세스를 하나 만들어 보자.

```java
public class BatchProcessor {
    
    private final MyArrayList<Integer> list = new MyArrayList<>(); //코드 변경
    
    public void logic(int size) {
        for (int i = 0; i < size; i++) {
            list.add(0, i); //앞에 추가
        }
    }
}
```

- 만약 위 상황에서 MyLinkedList로 변경을 해야 한다면 코드를 직접적을 수정해야 한다.

```java
public class BatchProcessor {
    
    private final MyLinkedList<Integer> list = new MyLinkedList<>(); //코드 변경
    
    public void logic(int size) {
        for (int i = 0; i < size; i++) {
            list.add(0, i); //앞에 추가
        }
    }
}
```

- 이렇게 구현체를 직접 사용하는 것을 구체적인 클래스에 의존한다고 표현한다.
- 이렇게 구체적인 클래스에 의존하면 변경할때 마다 BatchProcessor의 코드도 함께 수정해야 한다.
- 이번에는 인터페이스에 의존하게 수정 해 보자.

```java
public class BatchProcessor {

    private final MyList<Integer> list;

    public BatchProcessor(MyList<Integer> list) {
        this.list = list;
    }

    public void logic(int size) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(0, i); //앞에 추가
        }
        long endTime = System.currentTimeMillis();
        System.out.println("크기: " + size + ", 계산 시간: " + (endTime - startTime) + "ms");
    }

}
```
- 위의 경우 처럼 인터페이스에 의존하게 되면 해당 클래스 생성 시점에 구현체를 전달해서 
구현체 변경이 필요할시 BatchProcessor의 코드 수정 없이 생성자에 전달하는 구현체만 변경하면 된다.

```java
main() {
    new BatchProcessor(new MyArrayList()); //MyArrayList를 사용하고 싶을 때
    new BatchProcessor(new MyLinkedList()); //MyLinkedList를 사용하고 싶을 때
}
```

- 이 방식을 BatchProcessor의 관점엥서 보면 실행 시점에 외부에서 구현체가 주입된다.
- 따라서 이것을 의존관계 주입, Dependency Injection 이라 한다.

# [ 리스트 추상화3 ( 컴파일 타임 의존관계 / 런타임 의존관계)]
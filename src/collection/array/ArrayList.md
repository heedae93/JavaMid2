# [ 배열과 인덱스 ]
- 여러 데이터(자료)를 구조화해서 다루는 것을 자료 구조라고 한다.
- 자바는 배열 뿐만 아니라, 컬렉션 프레임 워크라는 이름으로 다양한 자료 구조를 제공한다.
- 먼저 배열에 대해서 알아보자

### 인덱스를 통한 입력, 변경, 조회
- 배열에서 자료를 찾을 때 인덱스를 사용하면 매우 빠르게 자료를 찾을 수 있다.
- 인덱스를 통한 입력, 변경, 조회의 경우 한번의 계산으로 자료의 위치를 찾을 수 있다.
- 배열을 생성하면 메모리 힙 영역에 순서대로 붙어서 만들어진다.
- 여러개의 상자가 붙어 있는 이미지를 연상하면 된다.
- 따라서 첫번째 박스 즉, 배열의 시작 위치만 알면 해당 위치에  ( 메모리 박스 하나 크기 * 인덱스 ) 를
  더하면 해당 인덱스에 해당하는 값을 바로 찾을 수 있는 것이다.

```java
public class ArrayMain1 {

    public static void main(String[] args) {
        int[] arr = new int[5];
        //index 입력: O(1)
        System.out.println("==index 입력: O(1)==");
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        System.out.println(Arrays.toString(arr));

        //index 변경: O(1)
        System.out.println("==index 변경: O(1)==");
        arr[2] = 10;
        System.out.println(Arrays.toString(arr));

        //index 조회: O(1)
        System.out.println("==index 조회: O(1)==");
        System.out.println("arr[2] = " + arr[2]);

    }
}
```
![img.png](img/img.png)

### 배열의 검색
- 배열에 들어 있는 데이터를 찾는 것을 검색이라고 한다.
- 이때는 데이터를 하나하나 비교해야 한다.
- 이전과 같이 인덱스를 통해서 한번에 찾을 수 없다.
- 따라서 배열의 크기가 크면 클 수록 오랜 시간이 걸리기 때문에
배열의 크기가 n이면 n번의 연산이 필요하다.
```java
    public static void main(String[] args) {
    int[] arr = new int[5];
    
    //검색: O(n)
    System.out.println("==배열 검색: O(n)==");
    System.out.println(Arrays.toString(arr));
    int value = 10;
    for (int i = 0; i < arr.length; i++) {
        System.out.println("arr[" + i + "]:" + arr[i]);
        if (arr[i] == value) {
            System.out.println(value + " 찾음");
            break;
        }
    }

}
```
# [ 빅오 표기법 ]

- 빅오 표기법은 매우 큰 데이터를 입력한다고 가정하고, 데이터의 양 증가에 따른
성능의 변화 추세를 비교하는데 사용한다.
- 대략적인 추세를 보는 것이기 때문에 상수를 제거하여 계수가 제거된 최고차항으로 표기한다.
- 또한 최악의 경우를 가정하여 표기한다.
- 빅오 표기법의 예시
  - **O(1)** - 상수 시간: 입력 데이터의 크기에 관계없이 알고리즘의 실행 시간이 일정한다.
  예) 배열에서 인덱스를 사용하는 경우
  - **O(n)** - 선형 시간: 알고리즘의 실행 시간이 입력 데이터의 크기에 비례하여 증가한다.
  예) 배열의 검색, 배열의 모든 요소를 순회하는 경우
  - **O(n²)** - 제곱 시간: 알고리즘의 실행 시간이 입력 데이터의 크기의 제곱에 비례하여 증가한다.
  n²은 `n * n` 을 뜻한다.
  예) 보통 이중 루프를 사용하는 알고리즘에서 나타남
  - **O(log n)** - 로그 시간: 알고리즘의 실행 시간이 데이터 크기의 로그에 비례하여 증가한다.
  예) 이진 탐색
  - **O(n log n)** - 선형 로그 시간:
  예) 많은 효율적인 정렬 알고리즘들

![img.png](img/bigO.png)

# [ 배열에 데이터 추가 ]

- 배열에 새로운 데이터를 추가할려면 먼저 공간을 확보해야 한다.
- 따라서 경우에 따라서 기존 데이터를 오른쪽으로 한칸씩 밀어내야 한다.
- 배열에 데이터를 추가하는 경우는 크게 3가지로 구분할 수 있다.
    - 배열의 첫번째 위치에 추가하는 경우
      - 기존 데이터를 모두 오른쪽으로 한칸씩 이동해야 한다
      - 배열의 마지막 부분부터 한칸씩 오른쪽으로 이동하게 된다.
      - 이동 후 확보된 공간에 데이터가 추가된다.
    - 배열의 중간에 추가하는 경우
      - 추가하고자 하는 인덱스부터 시작해서 한칸씩 오른쪽으로 이동하게 된다.
      - 이 경우 인덱스 왼쪽의 데이터는 움직이지 않는다.
      - 이렇게 확보된 공간에 데이터를 추가한다.
    - 배열의 마지막에 추가하는 경우
      - 이 경우는 기존 데이터를 이동하지 않고 바로 값이 추가 된다.

### 배열에 데이터를 추가할 때 위치에 따른 성능 변화

- 배열의 첫번째 위치에 추가
  - 배열의 첫번째 위치를 찾는데는 인덱스를 사용하므로 O(1)이 걸린다.
  모든 데이터를 배열의 크기만큼 한 칸씩 이동해야 한다. 따라서 O(n) 만큼의 연산이 걸린다.
  O(1 + n) O(n)이 된다.
- 배열의 중간 위치에 추가
  - 배열의 위치를 찾는데는 O(1)이 걸린다.
  index의 오른쪽에 있는 데이터를 모두 한 칸씩 이동해야 한다. 따라서 평균 연산은 O(n/2)이 된다.
  O(1 + n/2) O(n)이 된다.
- 배열의 마지막 위치에 추가
  - 이 경우 배열이 이동하지 않고 배열의 길이를 사용하면 마지막 인덱스에 바로 접근할 수 있으므로 한번의 계
  산으로 위치를 찾을 수 있고, 기존 배열을 이동하지 않으므로 O(1)이 된다.

```java
package collection.array;

import java.util.Arrays;

/**
 * 배열의 특징
 */
public class ArrayMain2 {

    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        System.out.println(Arrays.toString(arr));

        //배열의 첫번째 위치에 추가
        //기본 배열의 데이터를 한 칸씩 뒤로 밀고 배열의 첫번째 위치에 추가
        System.out.println("배열의 첫번째 위치에 3 추가 O(n)");
        int newValue = 3;
        addFirst(arr, newValue);
        System.out.println(Arrays.toString(arr));

        //index 위치에 추가
        //기본 배열의 데이터를 한 칸씩 뒤로 밀고 배열의 index 위치에 추가
        System.out.println("배열의 index(2) 위치에 4 추가 O(n)");
        int index = 2;
        int value = 4;
        addAtIndex(arr, index, value);
        System.out.println(Arrays.toString(arr));

        System.out.println("배열의 마지막 위치에 5 추가 O(1)");
        addLast(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    private static void addLast(int[] arr, int newValue) {
        arr[arr.length - 1] = newValue;
    }

    private static void addFirst(int[] arr, int newValue) {
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = newValue;
    }

    private static void addAtIndex(int[] arr, int index, int value) {
        for (int i = arr.length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
    }
}
```

### 배열의 한계
- 배열은 가장 기본적인 자료 구조이고, 특히 인덱스를 활용할 때 최고 효율이 나온다.
- 하지만 생성 시점에 크기를 미리 정해야 하는 단점이 있다.
- 미리 엄청 큰 배열을 선언할 수도 있지만 이렇게 되면 사용하지 않는 메모리 공간을
낭비하게 된다.
- 따라서 언제든 동적으로 크기를 늘리고 줄일 수 있는 자료 구조가 필요한데
그것이 컬렉션 프레임 워크가 존재하는 이유다.

# [ ArrayList ( 배열 리스트 )]
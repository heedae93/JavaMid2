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

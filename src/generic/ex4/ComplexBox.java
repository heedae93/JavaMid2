package generic.ex4;

import generic.animal.Animal;

public class ComplexBox<T extends Animal> {

    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    /**
     * 클래스 레벨의 제네릭 타입보다 제네릭 메서드가 우선순위를 가진다.
     * 아래의 T타입은 클래스 생성시 전달한 타입과는 상관없이 메서드를 호출 할 때 받는 파라미터 타입으로
     * 결졍된다.
     */
    public <T> T printAndReturn(T t) {
        System.out.println("animal.className: " + animal.getClass().getName());
        System.out.println("t.className: " + t.getClass().getName());
        // t.getName(); // 호출 불가다. 왜냐하면 메서드는 <T> 타입이다. <T extends Animal> 타입이 아니다.
        return t;
    }

}
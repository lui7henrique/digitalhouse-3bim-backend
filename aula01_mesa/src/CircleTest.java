import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {
    Circle c1, c2, c3, c4, c5;

    @BeforeEach
    void doBefore(){
        c1 = new Circle(5);
        c2 = new Circle(10);
        c3 = new Circle(15);
        c4 = new Circle(20);
        c5 = new Circle(25);
    }

    @Test
    void printObject(){
        Assertions.assertEquals(2*Math.PI*5, c1.calcPerimeter());
        Assertions.assertEquals(2*Math.PI*10, c2.calcPerimeter());
        Assertions.assertEquals(2*Math.PI*15, c3.calcPerimeter());
        Assertions.assertEquals(2*Math.PI*20, c4.calcPerimeter());
        Assertions.assertEquals(2*Math.PI*25, c5.calcPerimeter());
    }
}
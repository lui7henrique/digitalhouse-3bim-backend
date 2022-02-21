import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {
    Circle c1;

    @BeforeEach
    void doBefore(){
        c1 = new Circle(5);
    }

    @Test
    void printObject(){
        Assertions.assertEquals(2*Math.PI*5, c1.calcPerimeter());
    }
}
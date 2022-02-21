import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {
    Square s1;

    @BeforeEach
    void doBefore(){
        s1 = new Square(2);
    }

    @Test
    void printObject(){
        Assertions.assertEquals(2*2, s1.calcPerimeter());
    }
}
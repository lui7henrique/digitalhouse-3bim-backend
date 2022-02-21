import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {
    Square s1, s2, s3, s4, s5;

    @BeforeEach
    void doBefore(){
        s1 = new Square(2);
        s2 = new Square(4);
        s3 = new Square(8);
        s4 = new Square(9);
        s5 = new Square(10);
    }

    @Test
    void printObject(){
        Assertions.assertEquals(2*2, s1.calcPerimeter());
        Assertions.assertEquals(4*4, s2.calcPerimeter());
        Assertions.assertEquals(8*8, s3.calcPerimeter());
        Assertions.assertEquals(9*9, s4.calcPerimeter());
        Assertions.assertEquals(10*10, s5.calcPerimeter());
    }
}
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuessingNumberUnitTest {
    @Test
    public void TestThrowingException() {
        assertThrows(Exception.class, () -> Program.HadejCislo(-1));
    }

    @Test
    public void TestTimeouting() throws Exception {
        Random r = new Random();
        StopWatch sw = new StopWatch();
        for (int i = 0; i < 80; i++) {
            sw.start();
            boolean nasel = Program.HadejCislo(r.nextInt(10000000) + 1);
            sw.stop();
            long time = sw.getElapsedTime();
            if (nasel) {
                assertTrue(time <= 1000);
            } else {
                System.out.println(nasel+":"+time);
                assertTrue(time < 1100 && time > 1000);
            }
        }
    }
    @Test
    public void TestNumberOfThreads() throws Exception {
        int prevThreadsCount = Thread.activeCount();

        Thread test = new Thread(() -> {
            try {
                Program.HadejCislo(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        test.start();
        Thread.sleep(10);
        int postThreadsCout = Thread.activeCount();
        assertTrue(postThreadsCout - prevThreadsCount > 1);
    }
}

import java.util.Random;

public class Program {
    private static boolean timeout, found;

    public static void main(String[] args) throws Exception {
        if (HadejCislo(6378)) {
            System.out.println("nasel jsem");
        } else {
            System.out.println("nenasel jsem");
        }
    }

    public static boolean HadejCislo(int hledaneCislo) throws Exception {
        if (hledaneCislo < 0 || hledaneCislo > 10000000) {
            throw new Exception("Number not in range 0-10000000");
        }
        Random random = new Random();
        Thread thread = new Thread(Program::stopky);
        timeout = false;
        found = false;
        thread.start();
        while (!timeout && !found) {
            int number = random.nextInt(10000001);
            if (number == hledaneCislo) {
                found = true;
                thread.stop();
            }
        }
        return found && !timeout;
    }

    private static void stopky() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (!timeout) {
            if (stopWatch.getElapsedTime() >= 1000) {
                timeout = true;
                stopWatch.stop();
            }
        }
    }

    public static boolean HadejCisloSignleThread(int hledaneCislo) throws Exception {
        if (hledaneCislo < 0 || hledaneCislo > 10000000) {
            throw new Exception("Number not in range 0-10000000");
        }
        Random random = new Random();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        timeout = false;
        found = false;
        while (stopWatch.getElapsedTime() < 1000 && !found) {
            int number = random.nextInt(10000001);
            if (number == hledaneCislo) {
                found = true;
            }

        }
        return found && stopWatch.getElapsedTime() < 1000;
    }
}

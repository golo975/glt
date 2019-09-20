import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Stopwatch {

    private StopwatchInstant startTime;
    private List<StopwatchInstant> instantList = new ArrayList<>();

    private Stopwatch() {
    }

    public static Stopwatch getInstance() {
        Stopwatch stopwatch = new Stopwatch();
        StopwatchInstant stopwatchInstant = StopwatchInstant.getInstant(Instant.now(), stopwatch);
        stopwatch.startTime = stopwatchInstant;
        stopwatch.instantList.add(stopwatchInstant);
        return stopwatch;
    }

    public StopwatchInstant getStartTime() {
        return startTime;
    }

    public StopwatchInstant getLatest() {
        return instantList.get(instantList.size() - 1);
    }

    public Duration getTotalDuration() {
        return StopwatchInstant.getDuration(startTime, getLatest());
    }

    public StopwatchInstant newInstant() {
        StopwatchInstant stopwatchInstant = StopwatchInstant.getInstant(Instant.now(), this, getLatest());
        instantList.add(stopwatchInstant);
        return stopwatchInstant;
    }

    public StopwatchInstant newInstant(String name) {
        StopwatchInstant stopwatchInstant = newInstant();
        stopwatchInstant.name = name;
        return stopwatchInstant;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Stopwatch.class.getSimpleName() + "[", "]")
                .add("instantList=" + instantList)
                .toString();
    }

    public static class StopwatchInstant {
        private Stopwatch stopwatch;

        private StopwatchInstant previous;

        private String name;
        private Instant instant;

        private StopwatchInstant() {
        }

        private static StopwatchInstant getInstant(Instant instant, Stopwatch stopwatch) {
            StopwatchInstant stopwatchInstant = new StopwatchInstant();
            stopwatchInstant.instant = instant;
            stopwatchInstant.stopwatch = stopwatch;
            return stopwatchInstant;
        }

        private static StopwatchInstant getInstant(Instant instant, Stopwatch stopwatch, StopwatchInstant previous) {
            if (previous.stopwatch != stopwatch) {
                throw new IllegalArgumentException();
            }
            StopwatchInstant stopwatchInstant = getInstant(instant, stopwatch);
            stopwatchInstant.previous = previous;
            return stopwatchInstant;
        }

        public Duration getLatestDuration() {
            return getDuration(previous, this);
        }

        public Instant getInstant() {
            return instant;
        }

        public static Duration getDuration(StopwatchInstant instant1, StopwatchInstant instant2) {
            return Duration.between(instant1.getInstant(), instant2.getInstant());
        }

        @Override
        public String toString() {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateTimeFormatter1 = dateTimeFormatter.withZone(ZoneId.systemDefault());
            return new StringJoiner(", ", StopwatchInstant.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("instant=" + dateTimeFormatter1.format(instant))
                    .toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.getInstance();
        TimeUnit.SECONDS.sleep(2L);

        System.out.println(stopwatch.newInstant().getLatestDuration());
        System.out.println(stopwatch.getLatest());
        System.out.println(stopwatch.getLatest());
        System.out.println(stopwatch.getTotalDuration());

        System.out.println(stopwatch);
    }


}

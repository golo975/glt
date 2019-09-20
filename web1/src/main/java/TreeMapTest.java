import java.util.Arrays;
import java.util.TreeSet;

public class TreeMapTest {

    private static class User implements Comparable<User> {
        private Integer id;

        public User(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


        @Override
        public int compareTo(User o) {
            return Integer.compare(id, o.getId());
        }

        @Override
        public String toString() {
            return id.toString();
        }
    }


    public static void main(String[] args) {
        byte[] bytes = "魑魅魍魉".getBytes();
        System.out.println(Arrays.toString(bytes));
        char[] chars = "魑魅魍魉".toCharArray();
        System.out.println(Arrays.toString(chars));

    }
}

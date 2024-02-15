import java.io.*;

public class ExternReader {
    public static void main(String[] args) throws IOException {
        try (FileReader fr = new FileReader("../test-data/test-1.txt");
        BufferedReader br = new BufferedReader(fr)) {
            br.lines().forEach(System.out::println);
        }
    }
}

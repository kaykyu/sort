import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Books {

    private String title;
    private String author;
    private String publisher;

    public Books(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public static void main(String[] args) {

        List<String> record = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("books.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                record.add(line);
            }
        } catch (IOException e) {
            System.out.println("Import error");
        }

        Map<String, Long> sortPublisher = record.stream()
            .map(line -> {
                
                String[] fields = line.toLowerCase().split(",");
                return new Books(fields[1], fields[2], fields[11]);
            })
            .collect(Collectors.groupingBy(Books::getPublisher, Collectors.counting()));

        Iterator<Entry<String, Long>> itr = sortPublisher.entrySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
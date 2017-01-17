package bootwildfly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWildFlyController {

  private int c = 0;

  @RequestMapping("hello")
  public String sayHello() throws IOException {
    String read = "";
    try {
      PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
      writer.println("The " + c+ ". line");
      writer.close();
    } catch (IOException e) {
      // do something
    }
    
    BufferedReader br = new BufferedReader(new FileReader("test.txt"));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        read = sb.toString();
    } finally {
        br.close();
    }
    

    c++;
    return ("Counter: " + c + " - Last written: " + read);
  }
}

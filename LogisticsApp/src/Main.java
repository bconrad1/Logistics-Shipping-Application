import order.OrderService;
import java.io.PrintWriter;

/**
 * Logistics Application
 *
 * Authors: Eric Janowski & Ben Conrad
 * Version: 3/3/2016
 *
 * Output is printed to mainoutput.txt in the root project directory for ease of reading.
 */

public class Main {


    public static void main(String[] args) {
        final String EOL = System.lineSeparator();

        String toFile = "";
        OrderService os = OrderService.getInstance();

        toFile += StatusReport.generateReport() + EOL;
        toFile += OrderProcessor.processOrderBatch(os.prepareOrderBatch()) + EOL;
        toFile += StatusReport.generateReport() + EOL;


        System.out.println( toFile );

        try {
            PrintWriter out = new PrintWriter("mainoutput.txt");
            out.write( toFile );
            out.close();
        } catch (Throwable e) {e.printStackTrace();}

    }
}

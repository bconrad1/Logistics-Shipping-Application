import order.OrderService;

public class Main {


    public static void main(String[] args) {

        OrderService os = OrderService.getInstance();

        System.out.println( StatusReport.generateReport() );

        System.out.println( OrderProcessor.processOrderBatch(os.prepareOrderBatch()) );

        System.out.println( StatusReport.generateReport() );



    }
}

import common.DataValidationException;

public class ItemFactory {

    public static Item build(String id, int quantity) throws DataValidationException {

        if      (id.equals("ABC123")) return new ItemImpl(id, 550, quantity);
        else if (id.equals("PL132-C")) return new ItemImpl(id, 440, quantity);
        else if (id.equals("SR71-D")) return new ItemImpl(id, 1600, quantity);
        else if (id.equals("CR2032")) return new ItemImpl(id, 240, quantity);
        else if (id.equals("PU238")) return new ItemImpl(id, 2200, quantity);
        else if (id.equals("XLK200B")) return new ItemImpl(id, 820, quantity);
        else if (id.equals("CT1928")) return new ItemImpl(id, 910, quantity);
        else if (id.equals("RL123A")) return new ItemImpl(id, 360, quantity);
        else if (id.equals("XTP202")) return new ItemImpl(id, 345, quantity);
        else if (id.equals("E241i")) return new ItemImpl(id, 10400, quantity);
        else if (id.equals("RTF110")) return new ItemImpl(id, 715, quantity);
        else if (id.equals("ZTF109")) return new ItemImpl(id, 1100, quantity);
        else if (id.equals("JBL3100")) return new ItemImpl(id, 180, quantity);
        else if (id.equals("RX100-3")) return new ItemImpl(id, 642, quantity);
        else if (id.equals("MM35P")) return new ItemImpl(id, 1950, quantity);
        else if (id.equals("SN-241-L")) return new ItemImpl(id, 620, quantity);

        else throw new DataValidationException("Item of that id not found");
    }
}

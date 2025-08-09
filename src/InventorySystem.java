import java.util.HashMap;
import java.util.Map;

class InventorySystem {
    private ProductBST bst = new ProductBST();
    private String formula;

    public InventorySystem(String formula) {
        this.formula = formula;
    }

    public void addProduct(String id, String name, double price) {
        bst.insert(new Product(id, name, price));
    }

    public void listProducts() {
        bst.inorder();
    }

    public void calculateFinalPrice(String productId) {
        Product p = bst.search(productId);
        if (p == null) {
            System.out.println("Produk tidak ditemukan!");
            return;
        }
        ExpressionTree et = new ExpressionTree(formula);
        Map<String, Double> vars = new HashMap<>();
        vars.put("harga", p.price);
        vars.put("biaya_admin", 5000.0);

        double finalPrice = et.evaluate(vars);
        System.out.printf("Harga akhir %s: Rp %.2f%n", p.name, finalPrice);
    }
}
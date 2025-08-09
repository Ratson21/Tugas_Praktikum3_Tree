import java.util.Scanner;

public class Main {

    // Sistem Manajemen Inventori & Perhitungan Diskon Otomatis

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Formula diskon bisa diubah sesuai kebutuhan
        System.out.print("Masukkan formula harga akhir (contoh: harga * 0.9 + biaya_admin): ");
        String formula = sc.nextLine();

        InventorySystem system = new InventorySystem(formula);

        while (true) {
            System.out.println("\n=== MENU INVENTORY ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Semua Produk");
            System.out.println("3. Hitung Harga Akhir Produk");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1 -> {
                    System.out.print("ID Produk: ");
                    String id = sc.nextLine();
                    System.out.print("Nama Produk: ");
                    String name = sc.nextLine();
                    System.out.print("Harga Produk: ");
                    double price = sc.nextDouble();
                    system.addProduct(id, name, price);
                }
                case 2 -> system.listProducts();
                case 3 -> {
                    System.out.print("Masukkan ID Produk: ");
                    String searchId = sc.nextLine();
                    system.calculateFinalPrice(searchId);
                }
                case 4 -> {
                    System.out.println("Terima kasih!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
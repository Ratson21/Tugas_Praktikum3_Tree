class ProductBST {
    private BSTNode root;

    public void insert(Product product) {
        root = insertRec(root, product);
    }

    private BSTNode insertRec(BSTNode node, Product product) {
        if (node == null) return new BSTNode(product);
        if (product.id.compareTo(node.product.id) < 0)
            node.left = insertRec(node.left, product);
        else if (product.id.compareTo(node.product.id) > 0)
            node.right = insertRec(node.right, product);
        return node;
    }

    public Product search(String id) {
        BSTNode node = searchRec(root, id);
        return (node != null) ? node.product : null;
    }

    private BSTNode searchRec(BSTNode node, String id) {
        if (node == null || node.product.id.equals(id)) return node;
        return id.compareTo(node.product.id) < 0 ?
                searchRec(node.left, id) : searchRec(node.right, id);
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(BSTNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.product);
            inorderRec(node.right);
        }
    }
}
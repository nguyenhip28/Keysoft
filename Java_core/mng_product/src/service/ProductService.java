package service;

import model.Product;
import util.FileUtil;
import util.Validate;

import java.io.File;
import java.util.*;

public class ProductService {
    private static final String FILE_PATH = "D:/Keysoft Java/Java_core/mng_product/src/lib/products.txt";
    private List<Product> products;
    private int nextId = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public ProductService() {
        loadFromFile();
        updateNextId();
    }

    private void loadFromFile() {
        products = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            return;
        }

        List<String> lines = FileUtil.readFile(FILE_PATH);
        System.out.println("Reading data from file: " + file.getAbsolutePath());
        System.out.println("Number of lines: " + lines.size());

        for (String line : lines) {
            try {
                Product p = Product.parse(line);
                products.add(p);
            } catch (Exception e) {
                System.out.println("Could not parse line: " + line);
                e.printStackTrace();
            }
        }
    }

    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Product p : products) {
            lines.add(p.toFileString());
        }
        FileUtil.writeFile(FILE_PATH, lines);
        System.out.println("Saved " + lines.size() + " products to file.");
    }

    private void updateNextId() {
        for (Product p : products) {
            if (p.getId() >= nextId) {
                nextId = p.getId() + 1;
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        saveToFile();
    }

    public Product findbyId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            for (Product p : products) {
                if (p.getId() == id) {
                    return p;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + idStr);
        }
        return null;
    }

    public void deleteById(String idSr) {
        try{
            int id = Integer.parseInt(idSr);
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == id) {
                    products.remove(i);
                    saveToFile();
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + idSr);
        }
        }
    }
    

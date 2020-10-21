package co.unicauca.products.access;

import co.unicauca.products.domain.entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación por defecto. El framewok contenedor de CDI (Contexts and
 * Dependency Injection) carga la implementación por defecto.
 *
 * @author Santiago Acuña
 */
public class ProductRepositoryImplArrays implements IProductRepository {

    /**
     * Por simplicidad los datos se cargan en un array.
     */
    public static List<Product> products;

    public ProductRepositoryImplArrays() {
        if (products == null) {
            products = new ArrayList<>();
            initialize();
        }
    }

    private void initialize() {

        products.add(new Product(1, "Tv samsung", 200000d));
        products.add(new Product(2, "Tv lg", 300000d));
        products.add(new Product(1, "Tablet asus RESD-TD-34", 400000d));

    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Integer id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean create(Product newProduct) {
        Product product = this.findById(newProduct.getId());
        if (product != null) {
            //Ya existe
            return false;
        }
        products.add(newProduct);
        return true;
    }

    @Override
    public boolean update(Product newProduct) {
        Product product = this.findById(newProduct.getId());
        if (product != null) {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        int i = 0;
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

}

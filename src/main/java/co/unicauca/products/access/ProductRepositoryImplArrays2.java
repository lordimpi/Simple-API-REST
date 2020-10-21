package co.unicauca.products.access;

import co.unicauca.products.domain.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

/**
 * Implementación del repositorio alternativa. Se puede cambiar las annotaciones
 *
 * @Default y @Alternative al gusto.
 *
 * @author Santiago Acuña
 */
@RequestScoped
@Alternative
public class ProductRepositoryImplArrays2 implements IProductRepository {

    /**
     * Por simplicidad los datos se cargan en un array.
     */
    public static List<Product> products;

    public ProductRepositoryImplArrays2() {
        if (products == null) {
            products = new ArrayList<>();
            initialize();
        }
    }

    private void initialize() {
        products.add(new Product(1, "Cama duplex", 300000d));
        products.add(new Product(2, "Sofa cama", 300000d));
        products.add(new Product(1, "Nochero", 400000d));
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

import java.util.List;

public interface ProductDAO { //Data Acces Object interface for products
	 	public void save(Product product);
	    public void update(Product product);
	    public void delete(int id);
	    public Product get(int id);
	    public List<Product> list();
}

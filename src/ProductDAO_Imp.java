import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class ProductDAO_Imp implements ProductDAO { //Data Acces Object class for products
	@Override
	  public void save(Product product) { //To add new product
		try {
			Connection con =  ProductDB.getConnection();
			String sql = "INSERT INTO products(product_name,QTY,price) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product.getProduct_name());
			ps.setInt(2, product.getQTY());
			ps.setDouble(3, product.getPrice());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Saved.");
		} 
		catch (Exception e) {
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
		}
	}
	@Override
	public void update(Product product) { //To update an existing product
		try {
            Connection con = ProductDB.getConnection();
            String sql = "UPDATE products SET product_name=?,QTY=?,price=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getProduct_name());
            ps.setInt(2, product.getQTY());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getId());
            ps.executeUpdate();        
            JOptionPane.showMessageDialog(null, "Updated.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
	}
	@Override
	public void delete(int id) {
		try {
            Connection con = ProductDB.getConnection();
            String sql = "DELETE FROM products WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();        
            JOptionPane.showMessageDialog(null, "Deleted.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
	}
	@Override
	public Product get(int id) {
		Product pt = new Product();
		try {
			Connection con = ProductDB.getConnection();
			String sql = "SELECT * FROM products WHERE id =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				pt.setId(id);
				pt.setProduct_name(rs.getString("product_name"));
				pt.setPrice(rs.getDouble("price"));
				pt.setQTY(rs.getInt("QTY"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		}
		return pt;
	}
	@Override
	    public List<Product> list(){
		List<Product> list = new ArrayList<Product>();
		try {
			Connection con = ProductDB.getConnection();
			String sql = "SELECT * FROM products";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product pt = new Product();
				pt.setId(rs.getInt("id"));
				pt.setProduct_name(rs.getString("product_name"));
				pt.setPrice(rs.getDouble("price"));
				pt.setQTY(rs.getInt("QTY"));
				list.add(pt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		}
		return list;
	}
}

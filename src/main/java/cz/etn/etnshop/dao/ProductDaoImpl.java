package cz.etn.etnshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		persist(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

	@Override
	public Product getProduct(int productId) {
		Object product = getSession().get(Product.class, productId);
		return (Product) product;
	}

	@Override
	public void deleteProduct(int productId) {
		Query query = getSession().createSQLQuery("delete from Product where id = :id");
		query.setInteger("id", productId);
		query.executeUpdate();
	}

	@Override
	public void updateProduct(Product product) {
		getSession().update(product);
	}

	@Override
	public boolean isProductExist(int productId) {
		return getSession().get(Product.class, productId) != null;
	}

	@Override
	public List<Product> searchProducts(String expression) {
		Query query = getSession().createQuery("from Product where name like :expression or serial like :expression");
		query.setParameter("expression", "%" + expression + "%");
		List<Product> resultList = query.list();
		return resultList;
	}
}

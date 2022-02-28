package webshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

public class ProductRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        try (Connection conn = dataSource.getConnection();
             //language=sql
             PreparedStatement stmt = conn.prepareStatement(
                     "insert into products (product_name, price, stock) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, productName);
            stmt.setInt(2, price);
            stmt.setInt(3, stock);
            stmt.executeUpdate();
            return returnGeneratedKey(stmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException(" Cannot connect to database.", sqle);
        }
    }

    public Product findProductById(long id) {
        //language=sql
        return jdbcTemplate.queryForObject("select * from products where id = ?",
                (rs, i) -> new Product(
                        rs.getLong("id"),
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                ), id);
    }

    public void updateProductStock(long id, int amount) {
        int newStock = getCurrentStockOfProduct(id) - amount;
        //language=sql
        jdbcTemplate.update("update products set stock = ? where id = ?", newStock, id);
    }

    public int getCurrentStockOfProduct(long id) {
        return jdbcTemplate.queryForObject("select stock from products where id = ?", (rs, i) -> rs.getInt(1), id);
    }

    private long returnGeneratedKey(PreparedStatement statement) throws SQLException {
        try (ResultSet rs = statement.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new IllegalStateException("No generated key created.");
        }
    }
}

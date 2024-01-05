package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public ShoppingCart getByUserId(int userId) {
        String sql= """
                SELECT s.quantity, p.* From shopping_cart as s
                Join products as p On s.product_id = p.product_id
                WHERE s.user_id= ?;
                """;
        ShoppingCart shoppingCart = new ShoppingCart();
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ShoppingCartItem shoppingCartItem = mapRow(resultSet);
                shoppingCart.add(shoppingCartItem);
            }
            return shoppingCart;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCart addProduct(int userId, int productId) {
            ShoppingCart cart = getByUserId(userId);
            int quantity = 1;
            if (cart.contains(productId)){
                quantity = cart.get(productId).getQuantity() +1;
                cart.get(productId).setQuantity(quantity);
            }
            String insertSql = """
                      INSERT INTO shopping_cart (quantity, user_id, product_id)
                                    VALUES (?,?,?);
                    """;
            String updateSql = """
            UPDATE shopping_cart
            SET quantity = ?
            WHERE user_id = ? AND product_id = ?;
            """;
            try(Connection connection = getConnection()) {
                PreparedStatement ps = cart.contains(productId) ? connection.prepareStatement(updateSql) : connection.prepareStatement(insertSql);
                ps.setInt(1, quantity);
                ps.setInt(2, userId);
                ps.setInt(3,productId);
                ps.executeUpdate();
                cart = getByUserId(userId);
                return cart;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
    }

    @Override
    public void emptyCart(int userId) {
        String sql = """
                DELETE FROM shopping_cart WHERE user_id = ?
                """;
        try(Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateQuantity(int productId, int quantity, int userId) {
        String sql = """
                UPDATE shopping_cart
            SET quantity = ?
            WHERE user_id = ? AND product_id = ?;
                """;
        try(Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3,productId);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    protected static ShoppingCartItem mapRow(ResultSet row) throws SQLException
    {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

        int productId = row.getInt("product_id");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");
        int quantity = row.getInt("quantity");

        Product product= new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(quantity);

        return shoppingCartItem;
    }
}

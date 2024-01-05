package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    ShoppingCart addProduct(int userId, int productId);
    void emptyCart(int userId);
    void updateQuantity(int productId, int quantity, int userId);
}

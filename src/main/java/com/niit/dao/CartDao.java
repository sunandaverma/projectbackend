package com.niit.dao;

import java.util.List;

import com.niit.model.Cart;

public interface CartDao{
	
	public void insertCart(Cart cart);
	public Cart getCartById(int pro_id, String userEmail);
	public void updateCart(Cart cm);
	public List<Cart> findByCartId(String userEmail);
	public void deleteCart(int cartId);
	
}




























/*package com.niit.dao;


import java.util.List;

import com.niit.model.Cart;


public interface CartDao {
	public void insertOrUpdateCart(Cart cart);
	public Cart getCartItem(int cart_item_id);
	public List<Cart> getCartItems(String username);
	public void deleteCartItem(Cart cart);
	public List<Cart> getPurchasedCartItems(String username);
	public void cartFinal(String username);
}
*/
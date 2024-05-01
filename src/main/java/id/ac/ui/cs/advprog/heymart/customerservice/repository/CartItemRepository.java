package id.ac.ui.cs.advprog.heymart.customerservice.repository;

import id.ac.ui.cs.advprog.heymart.customerservice.dto.CartItemDto;
import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> getCartItemByUserId(Long userId);

    Optional<CartItem> getCartItemByUserIdAndProductId(Long userId, String productId);

    @Query("SELECT new id.ac.ui.cs.advprog.heymart.customerservice.dto.CartItemDto(ci.productId, p.productName, ci.amount,  ci.supermarketId, p.productPrice) " +
            "FROM CartItem ci INNER JOIN Product p ON ci.productId = p.productId " +
            "WHERE ci.userId = :userId")
    List<CartItemDto> getCartItemDtoByUserId(Long userId);


    @Query(value = "SELECT new id.ac.ui.cs.advprog.heymart.customerservice.dto.CartItemDto(ci.PRODUCT_ID, p.PRODUCT_NAME, ci.AMOUNT,  ci.SUPERMARKET_ID, p.PRODUCT_PRICE) " +
            "FROM CART_ITEM ci INNER JOIN PRODUCT p " +
            "ON ci.PRODUCT_ID = p.PRODUCT_ID " +
            "WHERE ci.USER_ID = :userId AND ci.PRODUCT_ID = :productId", nativeQuery = true)
    Optional<CartItemDto> getCartItemDtoByUserIdAndProductId(Long userId, String productId);
}

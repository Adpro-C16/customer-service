package id.ac.ui.cs.advprog.heymart.customerservice.controller;


import id.ac.ui.cs.advprog.heymart.customerservice.dto.ShoppingCartDto;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.request.CartItemRequest;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.request.UserRequest;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.response.GeneralResponse;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;
import id.ac.ui.cs.advprog.heymart.customerservice.service.ShoppingCartService;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-cart")
@Log4j2
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("")
    public ResponseEntity<GeneralResponse> getShoppingCart(@RequestParam Long userId){
        ShoppingCartDto cart = shoppingCartService.getCartDtoByUserId(userId);
        GeneralResponse response = new GeneralResponse();
        response.setStatus("OK");
        response.setMessage("Success");
        response.setData(cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-item")
    public ResponseEntity<GeneralResponse> addItem(@RequestBody CartItemRequest request){
        GeneralResponse response = new GeneralResponse();
        try {
            ShoppingCartDto cart = shoppingCartService.addItem(request);
            response.setStatus("OK");
            response.setMessage("Success");
            response.setData(cart);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadRequestException | IllegalStateException e){
            response.setStatus("Failed");
            response.setMessage(e.getMessage());
            log.info(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            response.setStatus("Failed");
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            log.info(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove-item")
    public ResponseEntity<GeneralResponse> removeItem(@RequestBody CartItemRequest request){
        GeneralResponse response = new GeneralResponse();
        try {
            ShoppingCartDto cart = shoppingCartService.removeItem(request);
            response.setStatus("OK");
            response.setMessage("Success");
            response.setData(cart);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadRequestException | IllegalStateException e){
            response.setStatus("Failed");
            response.setMessage(e.getMessage());
            log.info(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            response.setStatus("Failed");
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            log.info(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.config.PaypalPaymentIntent;
import com.aptech.foodmarket.food_market.config.PaypalPaymentMethod;
import com.aptech.foodmarket.food_market.repository.PaymentRepository;
import com.aptech.foodmarket.food_market.service.ImplService.PaypalService;
import com.aptech.foodmarket.food_market.utils.URLUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaypalService paypalService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@ResponseBody
	public com.aptech.foodmarket.food_market.model.Payment create(@RequestBody com.aptech.foodmarket.food_market.model.Payment payment){
		com.aptech.foodmarket.food_market.model.Payment newPayment = new com.aptech.foodmarket.food_market.model.Payment();
		newPayment.setOrder(payment.getOrder());
		newPayment.setPayerEmail(payment.getPayerEmail());
		newPayment.setTransactionAmount(payment.getTransactionAmount());
		newPayment.setTransactionAt(payment.getTransactionAt());
		newPayment.setTransactionId(payment.getTransactionId());
		return paymentRepository.save(newPayment);
	}

	@RequestMapping(method = RequestMethod.POST, value = "pay")
	public String pay(HttpServletRequest request){
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
		try {
			Payment payment = paypalService.createPayment(
					4.00, 
					"USD", 
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(){
		return "cancel";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	
}

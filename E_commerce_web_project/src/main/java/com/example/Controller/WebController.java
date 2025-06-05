package com.example.Controller;

import com.example.Entity.ContactForm;
import com.example.Entity.User;
import com.example.Entity.Product;
import com.example.Service.ProductService;
import com.example.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Controller
public class WebController {

    private static final String USER2 = "user";

	@Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    // Signup form GET
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute(USER2, new User());
        return "signup"; // signup.html
    }

    // signup form post
    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute(USER2) User user,
                               Model model,
                               HttpSession session) {
        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        // Check if email already exists
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "User with this email already exists");
            return "signup";
        }

        // Save user
        userService.saveUser(user);

        // Redirect to login page after successful signup
        return "redirect:/login";
    }

    // Show login form
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User()); // Add this line
        return "login";
    }


    // Handle login form submission
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user,
                               Model model,
                               HttpSession session) {

        var users = userService.findByEmail(user.getEmail());
        if (users.isPresent() && users.get().getPassword().equals(user.getPassword())) {
            session.setAttribute("user", users.get());
            return "redirect:/home";	
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }


    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // ✅ clear the session
        return "redirect:/login";  // Redirect to login
    }
    
    // 3. Home page
    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home"; // home.html
    }

    // 4. Shop page - pass product list
    @GetMapping("/shop")
    public String shop(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop"; // shop.html
    }

    // 5. Sell page - GET for form, POST to save product
    @GetMapping("/sell")
    public String showSellForm(Model model) {
        model.addAttribute("product", new Product()); // Product is your form backing object
        return "sell"; // returns sell.html view
    }

    @PostMapping("/sell")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/shop";  // Redirect to shop after adding product
    }

    // 6. My Orders page
    @GetMapping("/myorders")
    public String ordersPage() {
        return "myorders"; // myorders.html (to implement backend later)
    }
    
//    @GetMapping("/buy/{id}")
//    public String buyProduct(@PathVariable("id") String productId, HttpSession session, Model model) {
//        // Check if user is logged in
//        User user = (User) session.getAttribute("student");
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        Optional<Product> productOpt = productService.getProductById(productId);
//        if (productOpt.isPresent()) {
//            Product product = productOpt.get();
//
//            // Simulate placing an order (just show confirmation)
//            model.addAttribute("product", product);
//            model.addAttribute("success", "Order placed successfully!");
//            return "order-confirmation"; // ✅ You must create order-confirmation.html
//        } else {
//            model.addAttribute("error", "Product not found!");
//            return "redirect:/shop";
//        }
//    }
//    
//    @Autowired
//    private ProductRepository productRepository;  // instance, not class name
//
//    @GetMapping("/buyNow")
//    public String buyNow(@RequestParam("productId") String productId, Model model) {
//        Product products = productRepository.findById(productId).orElse(null);
//        if (products == null) {
//            // handle product not found
//            return "errorPage";
//        }
//        model.addAttribute("products", products);
//        return "order-confirmation";
//    }


    // 7. Profile page
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile"; // Your profile.html template
    }

    // 8. Help page
    @GetMapping("/help")
    public String helpPage(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "help";
    }
    
    @PostMapping("/help")
    public String submitHelpForm(@ModelAttribute ContactForm contactForm, Model model) {
    	
        // Process contactForm data (send email, save, etc.)
        model.addAttribute("successMessage", "Your message has been sent!");
        return "help";  // or redirect if preferred
    }
}

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Controller
public class WebController {

    private static final String USER_SESSION_ATTRIBUTE = "user";

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    public WebController() {
        // Ideally inject BCryptPasswordEncoder as a @Bean in a config class and inject here
        // For simplicity, creating new instance here
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 1. Signup form GET
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute(USER_SESSION_ATTRIBUTE, new User());
        return "signup"; // signup.html template
    }

    // 1. Signup form POST
    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute(USER_SESSION_ATTRIBUTE) User user,
                               Model model,
                               HttpSession session) {

        // Null-safe check for matching passwords
        if (user.getPassword() == null || !user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        // Check if email already exists
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "User with this email already exists");
            return "signup";
        }

        // Save user; password should be hashed inside userService.saveUser
        userService.saveUser(user);

        // Redirect to login page after successful signup
        return "redirect:/login";
    }

    // 2. Show login form
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute(USER_SESSION_ATTRIBUTE, new User()); // consistent model attribute name
        return "login";  // login.html template
    }

    // 3. Handle login form submission
    @PostMapping("/login")
    public String processLogin(@ModelAttribute(USER_SESSION_ATTRIBUTE) User user,
                               Model model,
                               HttpSession session) {

        // Find user by email
        var userOpt = userService.findByEmail(user.getEmail());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();

            String rawPassword = user.getPassword();
            String encodedPassword = foundUser.getPassword();

            // Use BCryptPasswordEncoder to verify password hash
            if (rawPassword != null && encodedPassword != null &&
                passwordEncoder.matches(rawPassword, encodedPassword)) {

                // Set authenticated user in session
                session.setAttribute(USER_SESSION_ATTRIBUTE, foundUser);
                return "redirect:/home";
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // 4. Logout and invalidate session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // clears all session attributes
        return "redirect:/login";  // Redirect to login page
    }

    // 5. Home page – accessible only if logged in
    @GetMapping({"/", "/home"})
    public String homePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE);
        if (user == null) {
            return "redirect:/login";
        }
        return "home"; // home.html template
    }

    // 6. Shop page - display product list
    @GetMapping("/shop")
    public String shop(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop"; // shop.html template
    }

    // 7. Sell page - GET for product form
    @GetMapping("/sell")
    public String showSellForm(Model model) {
        model.addAttribute("product", new Product());
        return "sell"; // sell.html template
    }

    // 7. Sell page - POST to add new product
    @PostMapping("/sell")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/shop"; // redirect to shop after adding
    }

    // 8. My Orders page placeholder
    @GetMapping("/myorders")
    public String ordersPage() {
        // Backend implementation pending
        return "myorders"; // myorders.html template
    }

    // 9. Profile page - shows user details, only if logged in
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile"; // profile.html template
    }

    // 10. Help page GET
    @GetMapping("/help")
    public String helpPage(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "help"; // help.html template
    }

    // 10. Help page POST - process contact form submission
    @PostMapping("/help")
    public String submitHelpForm(@ModelAttribute ContactForm contactForm, Model model) {
        // Process contactForm data (e.g., save to DB, send email, etc.)
        // TODO: add saving or mailing logic here

        model.addAttribute("successMessage", "Your message has been sent!");
        return "help";  // reload help page with success message
    }
}

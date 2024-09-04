package com.kholanhcaonguyen.PMQL.controller;

import com.kholanhcaonguyen.PMQL.entity.AppUser;
import com.kholanhcaonguyen.PMQL.entity.RegisterDto;
import com.kholanhcaonguyen.PMQL.payload.ApiResponse;
import com.kholanhcaonguyen.PMQL.payload.UserLoginRequest;
import com.kholanhcaonguyen.PMQL.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AppUserRepository repo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        // Xác thực người dùng
        if ("user".equals(userLoginRequest.getUsername()) && "password".equals(userLoginRequest.getPassword())) {
            // Tạo cookie
            Cookie cookie = new Cookie("authToken", "example-token");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(24 * 60 * 60); // 1 ngày
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("Đăng nhập thành công!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterDto registerDto) {
        // Kiểm tra mật khẩu xác nhận
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Password and confirm password do not match", null));
        }

        // Kiểm tra email đã tồn tại
        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Email address is already used", null));
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            // Tạo người dùng mới
            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("client");
            newUser.setCreateAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(HttpStatus.OK.value(), "User registered successfully!", null));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred: " + ex.getMessage(), null));
        }
    }

}

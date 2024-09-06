package com.kholanhcaonguyen.PMQL.controller;

import com.kholanhcaonguyen.PMQL.entity.AppUser;
import com.kholanhcaonguyen.PMQL.entity.RegisterDto;
import com.kholanhcaonguyen.PMQL.payload.ApiResponse;
import com.kholanhcaonguyen.PMQL.payload.UserLoginRequest;
import com.kholanhcaonguyen.PMQL.repository.AppUserRepository;
import com.kholanhcaonguyen.PMQL.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AppUserRepository repo;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginRequest userLoginRequest) {
        // Tìm kiếm người dùng trong cơ sở dữ liệu theo email
        AppUser appUser = repo.findByEmail(userLoginRequest.getUsername());

        // Kiểm tra nếu người dùng không tồn tại
        if (appUser == null) {
            // Trả về thông báo lỗi với mã 404 - Not Found nếu không tìm thấy người dùng
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Người dùng không tồn tại!", null));
        }

        // Kiểm tra nếu mật khẩu không đúng
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        if (!bCryptEncoder.matches(userLoginRequest.getPassword(), appUser.getPassword())) {
            // Trả về thông báo lỗi với mã 401 - Unauthorized nếu mật khẩu không đúng
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "Sai mật khẩu!", null));
        }

        // Tạo token JWT khi thông tin đăng nhập đúng
        String token = jwtService.generateToken(appUser);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Đăng nhập thành công!", token));
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

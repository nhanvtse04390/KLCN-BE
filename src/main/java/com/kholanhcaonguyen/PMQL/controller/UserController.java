package com.kholanhcaonguyen.PMQL.controller;

import com.kholanhcaonguyen.PMQL.entity.AppUser;
import com.kholanhcaonguyen.PMQL.payload.ApiResponse;
import com.kholanhcaonguyen.PMQL.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AppUserRepository userRepository;

    // Lấy danh sách tất cả người dùng
    @GetMapping
    public ResponseEntity<ApiResponse<List<AppUser>>> getAllUsers() {
        List<AppUser> users = userRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách người dùng thành công", users));
    }

    // Lấy thông tin chi tiết người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppUser>> getUserById(@PathVariable Long id) {
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Lấy thông tin người dùng thành công", user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy người dùng với ID: " + id, null));
        }
    }

    // Tạo mới người dùng
    @PostMapping
    public ResponseEntity<ApiResponse<AppUser>> createUser(@RequestBody AppUser newUser) {
        AppUser savedUser = userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo người dùng mới thành công", savedUser));
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AppUser>> updateUser(@PathVariable Long id, @RequestBody AppUser updatedUser) {
        Optional<AppUser> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            AppUser user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setRole(updatedUser.getRole());
            userRepository.save(user);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật thông tin người dùng thành công", user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy người dùng với ID: " + id, null));
        }
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Xóa người dùng thành công", "User with ID: " + id + " has been deleted."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy người dùng với ID: " + id, null));
        }
    }
}

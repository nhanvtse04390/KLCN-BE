package com.kholanhcaonguyen.PMQL.repository;

import com.kholanhcaonguyen.PMQL.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByEmail(String email);

    // Tìm kiếm người dùng với các tham số tùy chọn
    @Query("SELECT u FROM AppUser u WHERE " +
            "(:name IS NULL OR coalesce(u.name,'') LIKE concat('%', :name, '%')) AND " +
            "(:email IS NULL OR coalesce(u.email,'') LIKE  concat('%', :email, '%')) AND " +
            "(:phone IS NULL OR coalesce(u.phone,'') LIKE concat('%', :phone, '%')) AND " +
            "(:createdBy IS NULL OR coalesce(u.createdBy,'') LIKE concat('%', :createdBy, '%')) AND " +
            "(:updatedBy IS NULL OR coalesce(u.updatedBy,'') LIKE concat('%', :updatedBy, '%'))")
    List<AppUser> searchUsers(
                              @Param("name") String name,
                              @Param("email") String email,
                              @Param("phone") String phone,
                              @Param("createdBy") String createdBy,
                              @Param("updatedBy") String updatedBy);
}

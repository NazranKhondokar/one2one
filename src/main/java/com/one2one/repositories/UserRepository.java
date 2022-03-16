package com.one2one.repositories;

import com.one2one.entities.User;
import com.one2one.responses.AllUsersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByMobileOrEmail(String mobile, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByMobile(String mobile);

    Boolean existsByEmail(String email);

    Boolean existsByMobile(String mobile);

    @Query("select new com.one2one.responses.AllUsersResponse(u.id, u.userName, u.email, u.mobile) from User u where u.id IN (select r.userId from UserRoles r where r.roleId=:roleId)")
    List<AllUsersResponse> getUserByAgent(@RequestParam("roleId") Long roleId);

    @Query("select u from User u where u.id IN (select r.userId from UserRoles r where r.roleId=:roleId)")
    List<User> getAllUserByAgent(@RequestParam("roleId") Long roleId);

    @Query("select new com.one2one.responses.AllUsersResponse(u.id, u.userName, u.email, u.mobile) from User u where u.isActive = true")
    Page<AllUsersResponse> getAllUsers(Pageable pageable);

}

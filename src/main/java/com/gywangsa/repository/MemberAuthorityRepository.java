package com.gywangsa.repository;

import com.gywangsa.domain.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority,String> {
    @Query("select ma from MemberAuthority ma where ma.userId = :userId")
    MemberAuthority selectUserAuthority(@Param("userId") String userId);
}

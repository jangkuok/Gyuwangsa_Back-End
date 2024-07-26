package com.gywangsa.repository;

import com.gywangsa.domain.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority,String> {
    @Query("select ma from MemberAuthority ma where ma.userId = :userId")
    MemberAuthority selectUserAuthority(@Param("userId") String userId);

    //회원 탈퇴
    @Transactional
    @Modifying
    @Query("delete from MemberAuthority m where m.userId = :userId")
    void removeMemberAuthority(@Param("userId") String userId);
}

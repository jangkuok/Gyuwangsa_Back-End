package com.gywangsa.repository;

import com.gywangsa.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,String> {

    //@EntityGraph(attributePaths = {"roleEmpList"})
    @Query("select m from Member m where m.userId = :userId")
    Member selectMemberId(@Param("userId") String userId);

    @Modifying
    @Query("update Member m set m.pwd = :pwd, m.name = :name, m.email = :email, m.phone = :phone, m.addr = :addr, m.addrNo = :addrNo, m.addrDtl = :addrDtl, m.sexCd = :sexCd where m.userId = :userId")
    void modifyUserInfo(@Param("userId") String userId,
                        @Param("pwd") String pwd,
                        @Param("name") String name,
                        @Param("email") String email,
                        @Param("phone") String phone,
                        @Param("addrNo") String addrNo,
                        @Param("addr") String addr,
                        @Param("addrDtl") String addrDtl,
                        @Param("sexCd") String sexCd
                        );

}

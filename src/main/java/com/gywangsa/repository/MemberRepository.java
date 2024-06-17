package com.gywangsa.repository;

import com.gywangsa.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,String> {

    //@EntityGraph(attributePaths = {"roleEmpList"})
    @Query("select m from Member m where m.userId = :userId")
    Member selectMemberId(@Param("userId") String userId);
}

package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "GYU_SYS_USER")
public class Member {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "addr_no")
    private String addrNo;
    @Column(name = "addr")
    private String addr;
    @Column(name = "addr_dtl")
    private String addrDtl;
    @Column(name = "sex_cd")
    private String sexCd;
    @Column(name = "pwd_yn")
    private char pwdYn;
    @Column(name = "pwd_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pwdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "login_date")
    private LocalDateTime loginDate;
    @Column(name = "join_type")
    private String joinType;
    @Column(name = "api_key")
    private String apiKey;

//    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    @OrderColumn(name = "id")
//    @CollectionTable(name = "GYU_SYS_ROLE")
//    private List<MemberRoleEmp> roleEmpList = new ArrayList<>();
//
//    public void addRole(MemberRoleEmp roleEmp){
//        roleEmpList.add(roleEmp);
//    }
//
//    public void clearRole(){
//        roleEmpList.clear();
//    }

    public void changePwd(String pwd) {
        this.pwd = pwd;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeAddrNo(String addrNo) {
        this.addrNo = addrNo;
    }

    public void changeAddr(String addr) {
        this.addr = addr;
    }

    public void changeAddrDtl(String addrDtl) {
        this.addrDtl = addrDtl;
    }

    public void changePwdYn(char pwdYn) {
        this.pwdYn = pwdYn;
    }

    public void changePwdDate(LocalDateTime pwdDate) {
        this.pwdDate = pwdDate;
    }

    public void changeLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public void changeJoinType(String joinType) {
        this.joinType = joinType;
    }

    public void changeApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void changeSexCd(String sexCd) {
        this.sexCd = sexCd;
    }
}

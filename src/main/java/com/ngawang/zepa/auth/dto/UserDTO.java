package com.ngawang.zepa.auth.dto;

import java.io.Serializable;

/**
 * Created by N-Zepa on 25-Sep-19.
 */
public class UserDTO implements Serializable {
    private String username;
    private String fullName;
    private String password;
    private String branchCode;
    private String branchName;
    private Integer userGroupId;
    private Short branchType;
    private String bankName;
    private Boolean enabled;
    private Boolean changePasswordYN;
    private Character userStatus;
    private String roleName;
    public UserDTO() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Character getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Character userStatus) {
        this.userStatus = userStatus;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Short getBranchType() {
        return branchType;
    }

    public void setBranchType(Short branchType) {
        this.branchType = branchType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getChangePasswordYN() {
        return changePasswordYN;
    }

    public void setChangePasswordYN(Boolean changePasswordYN) {
        this.changePasswordYN = changePasswordYN;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDTO) {
            UserDTO userLogin = (UserDTO) obj;
            return userLogin.getUsername().equals(this.username);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.username.hashCode();
        return hash;
    }
}
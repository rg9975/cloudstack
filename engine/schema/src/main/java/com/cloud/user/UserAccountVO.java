// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.cloudstack.api.InternalIdentity;

import com.cloud.utils.db.Encrypt;
import com.cloud.utils.db.GenericDao;
import org.apache.cloudstack.utils.reflectiontostringbuilderutils.ReflectionToStringBuilderUtils;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "user")
@SecondaryTable(name = "account", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "id")})
public class UserAccountVO implements UserAccount, InternalIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = null;

    @Column(name = "username")
    private String username = null;

    @Column(name = "password")
    private String password = null;

    @Column(name = "firstname")
    private String firstname = null;

    @Column(name = "lastname")
    private String lastname = null;

    @Column(name = "account_id")
    private long accountId;

    @Column(name = "email")
    private String email = null;

    @Column(name = "state")
    private String state;

    @Column(name = "api_key")
    private String apiKey = null;

    @Encrypt
    @Column(name = "secret_key")
    private String secretKey = null;

    @Column(name = GenericDao.CREATED_COLUMN)
    private Date created;

    @Column(name = GenericDao.REMOVED_COLUMN)
    private Date removed;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "registration_token")
    private String registrationToken = null;

    @Column(name = "is_registered")
    boolean registered;

    @Column(name = "incorrect_login_attempts")
    int loginAttempts;

    @Column(name = "account_name", table = "account", insertable = false, updatable = false)
    private String accountName = null;

    @Column(name = "type", table = "account", insertable = false, updatable = false)
    private short type;

    @Column(name = "domain_id", table = "account", insertable = false, updatable = false)
    private Long domainId = null;

    @Column(name = "state", table = "account", insertable = false, updatable = false)
    private String accountState;

    @Column(name = "source")
    @Enumerated(value = EnumType.STRING)
    private User.Source source;

    @Column(name = "external_entity", length = 65535)
    private String externalEntity = null;

    @Column(name = "is_user_2fa_enabled")
    private boolean user2faEnabled = false;

    @Column(name = "user_2fa_provider")
    private String user2faProvider;

    @Column(name = "key_for_2fa")
    private String keyFor2fa;

    @Transient
    Map<String, String> details;

    public enum Setup2FAstatus {
        ENABLED, VERIFIED
    }
    public UserAccountVO() {
    }

    @Override
    public String toString() {
        return String.format("UserAccount %s.", ReflectionToStringBuilderUtils.reflectOnlySelectedFields
                (this, "id", "uuid", "username", "accountName"));
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public Date getCreated() {
        return created;
    }

//    public void setCreated(Date created) {
//        this.created = created;
//    }

    @Override
    public Date getRemoved() {
        return removed;
    }

    public void setRemoved(Date removed) {
        this.removed = removed;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    @Override
    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    @Override
    public String getTimezone() {
        if (StringUtils.isEmpty(timezone)) {
            return "UTC";
        }
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    @Override
    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    @Override
    public int getLoginAttempts() {
        return loginAttempts;
    }

    @Override
    public User.Source getSource() {
        return source;
    }

    public void setSource(User.Source source) {
        this.source = source;
    }

    public String getExternalEntity() {
        return externalEntity;
    }

    public void setExternalEntity(String externalEntity) {
        this.externalEntity = externalEntity;
    }

    @Override
    public boolean isUser2faEnabled() {
        return user2faEnabled;
    }

    @Override
    public void setUser2faEnabled(boolean user2faEnabled) {
        this.user2faEnabled = user2faEnabled;
    }

    @Override
    public String getKeyFor2fa() {
        return keyFor2fa;
    }

    @Override
    public void setKeyFor2fa(String keyFor2fa) {
        this.keyFor2fa = keyFor2fa;
    }

    @Override
    public String getUser2faProvider() {
        return user2faProvider;
    }

    @Override
    public void setUser2faProvider(String user2faProvider) {
        this.user2faProvider = user2faProvider;
    }

    @Override
    public Map<String, String> getDetails() {
        if (details == null) {
            details = new HashMap<>();
        }
        return details;
    }

    @Override
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}

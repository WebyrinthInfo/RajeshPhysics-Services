package com.RajeshPhysics_Services.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8116180697459646523L;

	@Column(name = "ID", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", nullable = true)
	private String middleName;

	@Column(name = "LAST_NAME", nullable = true)
	private String lastName;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "MOBILE", nullable = false, length = 10, unique = true)
	private String mobile;
	
	@Column(name="PASSWORD", nullable = false)
	private String password;

	@Column(name = "LANGUGE_MODE", nullable = false)
	private String languageMode;

	@Column(name = "PROFILE_IMAGE", nullable = false)
	private String profileImage = "default.png";
	
//  1 : active, 0 : inactive,  -1 : 
	@Column(name="STATUS", nullable = false)
	private String status;

//	1 : active, 0 : inactive 
	@Column(name = "IS_ACTIVE", nullable = false)
	private int isActive;
	
	@Column(name="JWT_TOKEN", nullable=false)
	private String jwtToken;

	@Column(name = "ACCOUNT_EXPIRE_AT", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private String accountExpireAt;

	@Column(name = "CREATED_AT")
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;

	@Column(name = "UPDATE_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updateAt;

//	-------map user to role many to many-----------
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE_MAPPER", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	private List<Role> roles = new ArrayList<>();

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLanguageMode() {
		return languageMode;
	}

	public void setLanguageMode(String languageMode) {
		this.languageMode = languageMode;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	public String getAccountExpireAt() {
		return accountExpireAt;
	}

	public void setAccountExpireAt(String accountExpireAt) {
		this.accountExpireAt = accountExpireAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
		
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	

	public User(Long id, String firstName, String middleName, String lastName, String address, String mobile,
			String password, String languageMode, String profileImage, String status, int isActive, String jwtToken,
			String accountExpireAt, LocalDateTime createdAt, LocalDateTime updateAt, List<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address = address;
		this.mobile = mobile;
		this.password = password;
		this.languageMode = languageMode;
		this.profileImage = profileImage;
		this.status = status;
		this.isActive = isActive;
		this.jwtToken = jwtToken;
		this.accountExpireAt = accountExpireAt;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.roles = roles;
	}

	public User() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> collect = roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return collect;
	}

	@Override
	public String getUsername() {
		return mobile;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	

}

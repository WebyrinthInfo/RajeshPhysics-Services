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

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "LANGUGE_MODE", nullable = false)
	private String languageMode;

	@Column(name = "PROFILE_IMAGE", nullable = false)
	private String profileImage = "default.png";

//  1 : active, 0 : inactive,  -1 : 
	@Column(name = "STATUS", nullable = false)
	private String status;

//	1 : active, 0 : inactive 
	@Column(name = "IS_ACTIVE", nullable = false)
	private int isActive;

	@Column(name = "JWT_TOKEN", nullable = false)
	private String jwtToken;

	@Column(name = "IS_PAID", nullable = false)
	private String isPaid = "FREE";

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

//	----------------map user to course --------------------------
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "USER_COURSE_MAPPER", joinColumns = @JoinColumn(name = "User_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"))
	private List<Course> courses = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> collect = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
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

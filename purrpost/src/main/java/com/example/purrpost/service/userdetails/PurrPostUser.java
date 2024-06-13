//package com.example.purrpost.service.userdetails;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.example.purrpost.model.SocialUser;
//
//public class PurrPostUser extends SocialUser implements UserDetails {
//	private static final long serialVersionUID = 1L;
//	private SocialUser underlyingUser;
//	
//	public PurrPostUser(SocialUser user) {
//		super(user);
//		this.underlyingUser = user;
//	}
//	
//	public long getExposedUserId() {
//		return this.underlyingUser.getUserId();
//	}
//
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO !!! THIS
//		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
//	}
//
//	@Override
//	public String getPassword() {
//		return this.underlyingUser.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return this.underlyingUser.getNameTag();
//	}
//
//	
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//}

//package com.example.purrpost.service.userdetails;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.purrpost.model.SocialUser;
//import com.example.purrpost.repository.UserRepository;
//
//@Service
//public class PurrPostUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<SocialUser> foundUser = userRepository.findByNameTag(username);
//        if (foundUser.size() == 0 || foundUser.size() > 1) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new PurrPostUser(foundUser.get(0));
//	}
//
//}

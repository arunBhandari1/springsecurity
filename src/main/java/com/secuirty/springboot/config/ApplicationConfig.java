package com.secuirty.springboot.config;

import com.secuirty.springboot.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationConfig
{
   private final UserRepository userRepository;

   public ApplicationConfig(UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }

   @Bean
   public UserDetailsService userDetailsService(){
      return username->userRepository.findByEmail(username)
                                     .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
   }
}

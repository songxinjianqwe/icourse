package com.sinjinsong.icourse.core.security;


import com.sinjinsong.icourse.common.security.domain.JwtUser;
import com.sinjinsong.icourse.core.domain.dto.user.AbstractUserDTO;
import com.sinjinsong.icourse.core.service.user.impl.UserQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by SinjinSong on 2017/5/8.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserQueryServiceImpl userQueryService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbstractUserDTO user = userQueryService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
            //用户没有任何身份
        } else if (user.getRoles().isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isValid(),
                user.isValid(),
                true,
                true,
                user.getRoles().stream().map((r) -> new SimpleGrantedAuthority(r.getRoleName().toUpperCase())).collect(Collectors.toList())
        );
    }
}

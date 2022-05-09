package cat.itb.m09_marccanals_apirest_persistent.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class mUserDetailsService implements UserDetailsService {

    private  final ServiceUser serviceUser;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return serviceUser.checkByUsername(s);
    }
}

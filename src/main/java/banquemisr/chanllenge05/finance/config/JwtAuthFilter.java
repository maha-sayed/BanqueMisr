package banquemisr.chanllenge05.finance.config;

import banquemisr.chanllenge05.finance.entities.FusersEntity;
import banquemisr.chanllenge05.finance.repositories.FusersRepo;
import banquemisr.chanllenge05.finance.repositories.UserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private FusersRepo fuserRepo ;
    private final UserDao userDao ;


    private final JwtUtil jwtUtil ;
    public JwtAuthFilter(FusersRepo fuserRepo, UserDao userDao, JwtUtil jwtUtil) {
        super();
        this.fuserRepo = fuserRepo;
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String username;
        final String jwtToken;
        if(authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
        }
        jwtToken = authHeader.substring(7);
        username= jwtUtil.extractUsername(jwtToken);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            FusersEntity userDetails = fuserRepo.findByUsername(username);
            UserDetails userDetails = userDao.findUserByEmail(username);
            if(jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}

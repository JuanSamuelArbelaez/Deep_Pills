package deep_pills.utils;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class TokenFilter implements Filter {
    private final JWTUtils jwtUtils;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = getToken(req);
        try{
            if (token != null) {
                Jws<Claims> jws = jwtUtils.parseJwt(token);
                System.out.println(jws.getBody().getSubject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }
    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }
}

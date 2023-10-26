package deep_pills.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import deep_pills.dto.controllers.ResponseDTO;
import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String token = getToken(req);
        boolean error = true;
        try{
            if (requestURI.startsWith("deep_pills/api/patient") || requestURI.startsWith("deep_pills/api/physician")

                    || requestURI.startsWith("deep_pills/api/admin") ) {
                if(token != null) {
                    Jws<Claims> jws = jwtUtils.parseJwt(token);
                    if (
                            ( requestURI.startsWith("deep_pills/api/patient") &&

                                    !jws.getBody().get("role").equals("patient") ) ||

                                    ( requestURI.startsWith("deep_pills/api/physician") &&

                                            !jws.getBody().get("role").equals("physician") ) ||

                                    ( requestURI.startsWith("deep_pills/api/admin") &&

                                            !jws.getBody().get("role").equals("admin") )) {

                        createErrorResponse("Not permits required to load this resource",

                                HttpServletResponse.SC_FORBIDDEN, res);

                    }else{
                        error = false;
                    }
                }else{
                    createErrorResponse("No Token", HttpServletResponse.SC_FORBIDDEN,

                            res);

                }
            }else{
                error = false;
            }
        }catch (MalformedJwtException | SignatureException e){
            createErrorResponse("Incorrect Token",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }catch (ExpiredJwtException e ){
            createErrorResponse("Timed-Out Token",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }catch (Exception e){
            createErrorResponse(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,

                    res);
        }
        if(!error){
            chain.doFilter(request, response);
        }
    }
    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }
    private void createErrorResponse(String message, int codigoError, HttpServletResponse
            response) throws IOException {
        ResponseDTO<String> dto = new ResponseDTO<>(true, message);
        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}

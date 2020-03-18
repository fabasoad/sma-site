package io.github.fabasoad.rest.provider;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static io.github.fabasoad.db.pojo.PojoProperties.UserRoles.Values.ADMIN;
import static io.github.fabasoad.rest.provider.AuthenticationUtils.SMA_SESSION_COOKIE_NAME;

public class AdministrationFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpRequest.getCookies();
        if (cookies == null) {
            httpResponse.sendRedirect("/login");
        } else {
            Optional<String> encodedValue = Arrays.stream(cookies)
                    .filter(c -> Objects.equals(c.getName(), SMA_SESSION_COOKIE_NAME))
                    .findAny()
                    .map(Cookie::getValue);

            if (encodedValue.isPresent()) {
                try {
                    AuthenticationUtils.validateUser(encodedValue.get(), new String[]{ADMIN});
                } catch (AuthenticationException e) {
                    httpResponse.sendRedirect("/login");
                    return;
                }
                if (httpRequest.getServletPath().contains("login")) {
                    httpResponse.sendRedirect("/admin");
                }
                filterChain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {
    }
}

package com.ead.course.clients;

import com.ead.course.dtos.ResponsePageDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.services.UtilsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Component
public class AuthUserClient {

    @Value("${ead.api.url.authuser}")
    private String AUTHUSER_BASE_URL;

    private final WebClient authUserClient;
    private final UtilsService utilsService;

    public AuthUserClient(WebClient authUserService, UtilsService utilsService) {
        this.authUserClient = authUserService;
        this.utilsService = utilsService;
    }

    public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable) {
        List<UserDto> searchResult = null;
        String url = utilsService.createUrlGetAllUsersByCourse(courseId, pageable);
        log.debug("Request URL: {} ", AUTHUSER_BASE_URL + url);
        log.info("Request URL: {} ", AUTHUSER_BASE_URL + url);
        try {
            searchResult = authUserClient.get()
                            .uri(url)
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<ResponsePageDto<UserDto>>(){})
                            .map(x -> x.getContent())
                            .block();

            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /users {} ", e);
        }
        log.info("Ending request /users courseId {} ", courseId);
        return new PageImpl<>(searchResult);
    }

    public Optional<UserDto> getOneUserById(UUID userId) {
        try {
            String url = "/users/" + userId;
            UserDto userDto = authUserClient.get()
                    .uri(url)
                    .retrieve()
                    .onStatus(HttpStatus::isError, response -> null)
                    .bodyToMono(UserDto.class)
                    .block();
            return Optional.ofNullable(userDto);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

}
